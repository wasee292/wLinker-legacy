package com.wasee292.wlinker.legacy.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "tag_table")
@Serializable
data class Tag(
    val value: String,
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
)
