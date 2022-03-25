package com.example.userlist.di

import com.example.data.network.NetworkSettings
import com.example.data.network.apis.UserListApi
import com.example.data.network.repositories.UserListNetworkRepositoryImpl
import com.example.domain.userList.UserListNetworkRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [NetworkModule.ApiModule::class, NetworkModule.RepositoryBindsModule::class])
class NetworkModule {

    @Singleton
    @Provides
    fun provideOKHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @Singleton
    @Provides
    fun provideGSON(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofitClient(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder().baseUrl(NetworkSettings.ROOT_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory).build()
    }

    @Module
    class ApiModule {
        @Provides
        fun provideUsersApi(retrofit: Retrofit): UserListApi {
            return retrofit.create(UserListApi::class.java)
        }
    }

    @Module
    interface RepositoryBindsModule {
        @Binds
        fun bindsUserListNetworkRepository(usersRepositoryImpl: UserListNetworkRepositoryImpl): UserListNetworkRepository
    }

}