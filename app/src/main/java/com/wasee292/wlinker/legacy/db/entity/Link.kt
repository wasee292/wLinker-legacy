package com.wasee292.wlinker.legacy.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "link_table")
data class Link(
    val value: String,
    val tags: List<Tag>,
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
)
