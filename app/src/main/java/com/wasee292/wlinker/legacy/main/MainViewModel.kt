package com.wasee292.wlinker.legacy.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wasee292.wlinker.legacy.plusAssign
import com.wasee292.wlinker.legacy.repository.LinkRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
	private val linkRepo: LinkRepo,
) : ViewModel() {
	private val _events = MutableLiveData<MainEvent?>(null)
	val events: LiveData<MainEvent?> = _events
	private val compositeDisposable by lazy { CompositeDisposable() }

	private fun getLinks() {
		compositeDisposable += linkRepo.getLinks()
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe { links ->
				_events.postValue(MainEvent.LinksUpdated(links))
			}
	}

	init {
		getLinks()
	}
}
