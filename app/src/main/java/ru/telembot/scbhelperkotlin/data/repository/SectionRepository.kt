package ru.telembot.scbhelperkotlin.data.repository

import ru.telembot.scbhelperkotlin.data.local.SectionDao
import ru.telembot.scbhelperkotlin.data.remote.SectionRemoteDataSource
import ru.telembot.scbhelperkotlin.utils.performGetOperation
import javax.inject.Inject

class SectionRepository @Inject constructor(
    private val remoteDataSource: SectionRemoteDataSource,
    private val  localDataSource: SectionDao
) {

    fun getSections() = performGetOperation(
        databaseQuery = { localDataSource.getAllSections() },
        networkCall = { remoteDataSource.getSections() },
        saveCallResult = { localDataSource.insertAll(it.results) }
    )
}