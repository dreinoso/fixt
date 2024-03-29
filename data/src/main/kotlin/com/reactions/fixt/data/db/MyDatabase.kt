package com.reactions.fixt.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.reactions.fixt.data.db.album.AlbumDao
import com.reactions.fixt.data.db.album.AlbumData

/**
 * Database class with all of its dao classes
 */
@Database(entities = [AlbumData.Album::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {

    abstract fun albumDao(): AlbumDao
}