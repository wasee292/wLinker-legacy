package com.wasee292.wlinker.legacy.main

import com.wasee292.wlinker.legacy.db.entity.Link

sealed class MainEvent {
    data class LinksUpdated(val links: List<Link>) : MainEvent()
}