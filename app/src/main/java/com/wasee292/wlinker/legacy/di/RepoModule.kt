package com.wasee292.wlinker.legacy.di

import com.wasee292.wlinker.legacy.db.dao.LinkDao
import com.wasee292.wlinker.legacy.db.dao.TagDao
import com.wasee292.wlinker.legacy.repository.LinkRepo
import com.wasee292.wlinker.legacy.repository.TagRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepoModule {
    @Provides
    fun provideLinkRepo(
        linkDao: LinkDao
    ) = LinkRepo(linkDao)

    @Provides
    fun provideTagRepo(
        tagDao: TagDao
    ) = TagRepo(tagDao)
}
