package com.wasee292.wlinker.legacy.repository

import com.wasee292.wlinker.legacy.db.dao.LinkDao
import com.wasee292.wlinker.legacy.db.entity.Link
import io.reactivex.rxjava3.schedulers.Schedulers

class LinkRepo(
    private val linkDao: LinkDao,
) {
    fun getLinks() = linkDao.getAllLinks().subscribeOn(Schedulers.io())

    fun addLink(link: Link) = linkDao.addLink(link).subscribeOn(Schedulers.io())
}
