package ru.telembot.scbhelperkotlin.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.telembot.scbhelperkotlin.`interface`.ItemClickListener
import ru.telembot.scbhelperkotlin.data.entity.Section
import ru.telembot.scbhelperkotlin.databinding.ActivityMainBinding
import ru.telembot.scbhelperkotlin.ui.docs.DocsActivity
import ru.telembot.scbhelperkotlin.ui.sections.SectionsAdapter
import ru.telembot.scbhelperkotlin.ui.sections.SectionsViewModel
import ru.telembot.scbhelperkotlin.utils.Resource

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: SectionsViewModel by viewModels()
    private lateinit var adapter: SectionsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = SectionsAdapter()
        binding.sectionsRv.layoutManager = LinearLayoutManager(this)
        binding.sectionsRv.addItemDecoration(
            DividerItemDecoration(
                binding.sectionsRv.context,
                (binding.sectionsRv.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.sectionsRv.adapter = adapter
        setupCallback()
    }

    private fun setupObservers() {
        viewModel.sections.observe(this, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    private fun setupCallback() {
        adapter.attachCallback(object : ItemClickListener<Section> {
            override fun onItemClick(view: View, position: Int) {
                when (position) {
                    0 -> { val docsIntent = Intent(this@MainActivity, DocsActivity::class.java)
                        startActivity(docsIntent)
                    }
                }
            }
        })
    }
}