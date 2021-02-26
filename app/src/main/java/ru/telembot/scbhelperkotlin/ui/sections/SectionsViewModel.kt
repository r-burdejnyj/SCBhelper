package ru.telembot.scbhelperkotlin.ui.sections

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import ru.telembot.scbhelperkotlin.data.repository.SectionRepository

class SectionsViewModel @ViewModelInject constructor(
    private val repository: SectionRepository
) : ViewModel() {

    val sections = repository.getSections()
}