package com.wasee292.wlinker.legacy.addtag

import com.wasee292.wlinker.legacy.db.entity.Tag

sealed class AddTagEvent {
    object TagAdded : AddTagEvent()
	class TagAlreadyExists(val tag: Tag) : AddTagEvent()
}
