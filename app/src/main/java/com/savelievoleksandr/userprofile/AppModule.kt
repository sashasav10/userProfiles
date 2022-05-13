package com.savelievoleksandr.userprofile

import android.content.Context
import androidx.room.Room
import com.savelievoleksandr.userprofile.database.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton // Tell Dagger-Hilt to create a singleton accessible everywhere in ApplicationCompenent (i.e. everywhere in the application)
    @Provides
    fun provideUserDBDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        UserDatabase::class.java,
        "user_history_database"
    ).build() // The reason we can construct a database for the repo

    @Singleton
    @Provides
    fun provideUserDBDao(db: UserDatabase) = db.userDatabaseDao()

}