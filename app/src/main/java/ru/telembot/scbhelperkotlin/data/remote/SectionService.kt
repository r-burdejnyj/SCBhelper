package ru.telembot.scbhelperkotlin.data.remote

import retrofit2.Response
import retrofit2.http.GET
import ru.telembot.scbhelperkotlin.data.entity.Section
import ru.telembot.scbhelperkotlin.data.entity.SectionList

interface SectionService {

    @GET("section.json")
    suspend fun getAllSections() : Response<SectionList>
}