package com.wasee292.wlinker.legacy.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.wasee292.wlinker.legacy.db.dao.LinkDao
import com.wasee292.wlinker.legacy.db.dao.TagDao
import com.wasee292.wlinker.legacy.db.entity.Link
import com.wasee292.wlinker.legacy.db.entity.Tag

@Database(entities = [Tag::class, Link::class], version = 1)
@TypeConverters(AppDatabaseConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tagDao(): TagDao
    abstract fun linkDao(): LinkDao
}
