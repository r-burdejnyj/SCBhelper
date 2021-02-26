package ru.telembot.scbhelperkotlin.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.telembot.scbhelperkotlin.data.entity.Section

@Dao
interface SectionDao {

    @Query("SELECT * FROM section")
    fun getAllSections() : LiveData<List<Section>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(sections: List<Section>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(section: Section)
}