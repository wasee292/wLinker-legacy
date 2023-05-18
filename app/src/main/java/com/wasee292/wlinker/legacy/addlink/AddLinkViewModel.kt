package com.wasee292.wlinker.legacy.addlink

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wasee292.wlinker.legacy.Selectable
import com.wasee292.wlinker.legacy.db.entity.Link
import com.wasee292.wlinker.legacy.plusAssign
import com.wasee292.wlinker.legacy.repository.LinkRepo
import com.wasee292.wlinker.legacy.repository.TagRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class AddLinkViewModel @Inject constructor(
    private val tagRepo: TagRepo,
    private val linkRepo: LinkRepo,
) : ViewModel() {
    private val _events = MutableLiveData<AddLinkEvent?>(null)
    val events: LiveData<AddLinkEvent?> = _events
    private val compositeDisposable by lazy { CompositeDisposable() }

    private fun getTags() {
        compositeDisposable += tagRepo.getTags()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { tags ->
                _events.postValue(AddLinkEvent.TagsUpdated(tags.map { Selectable(data = it) }))
            }
    }

    fun addLink(link: Link) {
        compositeDisposable += linkRepo.addLink(link)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _events.postValue(AddLinkEvent.LinkAdded)
            }
    }

    init {
        getTags()
    }
}
