package com.wasee292.wlinker.legacy.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.wasee292.wlinker.legacy.db.entity.Link
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface LinkDao {
    @Insert
    fun addLink(link: Link): Completable

    @Query("SELECT * FROM link_table")
    fun getAllLinks(): Flowable<List<Link>>
}
