package com.wasee292.wlinker.legacy.repository

import com.wasee292.wlinker.legacy.db.dao.TagDao
import com.wasee292.wlinker.legacy.db.entity.Tag
import io.reactivex.rxjava3.schedulers.Schedulers

class TagRepo(
    private val tagDao: TagDao,
) {
    fun getTags() = tagDao.getAllTags().subscribeOn(Schedulers.io())

    fun addTag(tag: Tag) = tagDao.addTag(tag).subscribeOn(Schedulers.io())

	fun tagExists(value: String) = tagDao.searchTag(value)
		.map { it.isNotEmpty() }
		.subscribeOn(Schedulers.io())
}
