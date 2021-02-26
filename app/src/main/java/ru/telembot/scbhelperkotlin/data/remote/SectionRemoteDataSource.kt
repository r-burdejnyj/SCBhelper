package ru.telembot.scbhelperkotlin.data.remote

import javax.inject.Inject

class SectionRemoteDataSource @Inject constructor(
    private val sectionService: SectionService
) : BaseDataSource() {

    suspend fun getSections() = getResult { sectionService.getAllSections() }
}