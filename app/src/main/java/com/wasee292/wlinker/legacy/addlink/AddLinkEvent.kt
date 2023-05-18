package com.wasee292.wlinker.legacy.addlink

import com.wasee292.wlinker.legacy.Selectable
import com.wasee292.wlinker.legacy.db.entity.Tag

sealed class AddLinkEvent {
    object LinkAdded : AddLinkEvent()
    data class TagsUpdated(val tags: List<Selectable<Tag>>) : AddLinkEvent()
}