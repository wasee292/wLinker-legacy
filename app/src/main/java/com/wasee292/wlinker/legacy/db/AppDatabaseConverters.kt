package com.wasee292.wlinker.legacy.db

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.wasee292.wlinker.legacy.db.entity.Tag
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@ProvidedTypeConverter
class AppDatabaseConverters(private val json: Json) {
    @TypeConverter
    fun fromTagList(tagList: List<Tag>) = json.encodeToString(tagList)

    @TypeConverter
    fun toTagList(data: String) = json.decodeFromString<List<Tag>>(data)
}
