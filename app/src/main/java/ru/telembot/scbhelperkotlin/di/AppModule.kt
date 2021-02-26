package ru.telembot.scbhelperkotlin.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.telembot.scbhelperkotlin.data.local.AppDatabase
import ru.telembot.scbhelperkotlin.data.local.SectionDao
import ru.telembot.scbhelperkotlin.data.remote.SectionRemoteDataSource
import ru.telembot.scbhelperkotlin.data.remote.SectionService
import ru.telembot.scbhelperkotlin.data.repository.SectionRepository
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://telembot.ru/app/api/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideSectionService(retrofit: Retrofit): SectionService =
        retrofit.create(SectionService::class.java)

    @Singleton
    @Provides
    fun provideSectionRemoteDataSource(sectionService: SectionService) =
        SectionRemoteDataSource(sectionService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideSectionDao(db: AppDatabase) = db.sectionDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: SectionRemoteDataSource,
                          localDataSource: SectionDao) =
        SectionRepository(remoteDataSource, localDataSource)
}