package com.reactions.fixt.presentation.ui.features.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.Disposable
import com.reactions.fixt.domain.entity.Entity
import com.reactions.fixt.domain.usecase.google.GetAvailableLeaguesUseCase
import com.reactions.fixt.domain.usecase.google.GetFixturesUseCase
import com.reactions.fixt.domain.usecase.google.GetResultsUseCase
import com.reactions.fixt.presentation.common.extension.defaultValue
import com.reactions.fixt.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(private val getFixturesUseCase: GetFixturesUseCase,
                                        private val getResultsUseCase: GetResultsUseCase,
                                        private val getAvailableLeaguesUseCase: GetAvailableLeaguesUseCase) : BaseViewModel() {

    private var requestFixturesDisposable: Disposable? = null
    private var requestResultsDisposable: Disposable? = null

    val fixturesMutable: MutableLiveData<List<Entity.Fixture>> = MutableLiveData()
    val resultsMutable: MutableLiveData<List<Entity.Results>> = MutableLiveData()
    val filterDate = MutableLiveData<String>().defaultValue("")
    val filterLeague = MutableLiveData<String>().defaultValue("")
    var availableLeagues = MutableLiveData<MutableSet<String>>()

    fun requestFixtures() {
        Log.d("mainviewmodel", "requestFixtures")
        if (requestFixturesDisposable?.isDisposed != true)
            requestFixturesDisposable?.dispose()
        requestFixturesDisposable = getFixturesUseCase.getFixtures().subscribe { resultState ->
            fixturesMutable.postValue(resultState)
            availableLeagues.value = getAvailableLeaguesUseCase.getAvailableLeagues(resultState)
        }
        requestFixturesDisposable?.track()
    }

    fun requestResults() {
        Log.d("mainviewmodel", "requestResults")
        if (requestResultsDisposable?.isDisposed != true)
            requestResultsDisposable?.dispose()
        requestResultsDisposable = getResultsUseCase.getResults().subscribe { resultState ->
            resultsMutable.postValue(resultState)
        }
        requestResultsDisposable?.track()
    }
}