package com.wasee292.wlinker.legacy

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

data class Selectable<T>(
    var isSelected: Boolean = false,
    val data: T
) {
    fun toggleSelection() {
        isSelected = !isSelected
    }
}

operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    this.add(disposable)
}