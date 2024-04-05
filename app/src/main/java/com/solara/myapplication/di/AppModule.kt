package com.solara.myapplication.di

import com.solara.myapplication.data.repository.UserRepository
import com.solara.myapplication.data.repository.UserRepositoryImpl
import com.solara.myapplication.ui.viewmodel.LoginViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLoginViewModel(repository: UserRepository): LoginViewModel {
        return LoginViewModel(repository)
    }
}