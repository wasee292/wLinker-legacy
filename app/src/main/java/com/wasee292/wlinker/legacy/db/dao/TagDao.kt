package com.wasee292.wlinker.legacy.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.wasee292.wlinker.legacy.db.entity.Tag
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

@Dao
interface TagDao {
	@Query("SELECT * FROM tag_table")
	fun getAllTags(): Flowable<List<Tag>>

	@Insert
	fun addTag(tag: Tag): Completable

	@Delete
	fun deleteTag(tag: Tag): Completable

	@Query("SELECT * FROM tag_table WHERE value = :value")
	fun searchTag(value: String): Single<List<Tag>>
}
