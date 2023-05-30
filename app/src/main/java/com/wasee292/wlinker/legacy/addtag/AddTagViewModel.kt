package com.wasee292.wlinker.legacy.addtag

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wasee292.wlinker.legacy.db.entity.Tag
import com.wasee292.wlinker.legacy.plusAssign
import com.wasee292.wlinker.legacy.repository.TagRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class AddTagViewModel @Inject constructor(
    private val tagRepo: TagRepo,
) : ViewModel() {
	private val compositeDisposable by lazy { CompositeDisposable() }
	private val _events = MutableLiveData<AddTagEvent?>(null)
	val events: LiveData<AddTagEvent?> = _events

	fun addTag(tag: Tag) {
		compositeDisposable += tagRepo.tagExists(tag.value)
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe { tagAlreadyExists ->
				if (tagAlreadyExists) {
					_events.postValue(AddTagEvent.TagAlreadyExists(tag))
				} else {
					addNewTag(tag)
				}
			}
	}

	private fun addNewTag(tag: Tag) {
		compositeDisposable += tagRepo.addTag(tag)
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe {
				_events.postValue(AddTagEvent.TagAdded)
			}
	}
}
