package com.wasee292.wlinker.legacy.addtag

sealed class AddTagEvent {
    object TagAdded : AddTagEvent()
}