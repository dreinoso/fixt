package com.reactions.fixt.presentation.ui.features.main

import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.Disposable
import com.reactions.fixt.domain.entity.Entity
import com.reactions.fixt.domain.usecase.google.GetAvailableLeaguesUseCase
import com.reactions.fixt.domain.usecase.google.GetFixturesUseCase
import com.reactions.fixt.domain.usecase.google.GetResultsUseCase
import com.reactions.fixt.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(private val getFixturesUseCase: GetFixturesUseCase,
                                        private val getResultsUseCase: GetResultsUseCase,
                                        private val getAvailableLeaguesUseCase: GetAvailableLeaguesUseCase) : BaseViewModel() {

    private var tempDispossable: Disposable? = null

    val fixturesLiveData: MutableLiveData<List<Entity.Fixture>> = MutableLiveData()
    val resultsMutable: MutableLiveData<List<Entity.Results>> = MutableLiveData()
    val filterDate = MutableLiveData<String>()
    val filterLeague = MutableLiveData<String>()
    var availableLeagues = MutableLiveData<MutableSet<String>>()

    fun requestFixtures() {
        if (tempDispossable?.isDisposed != true)
            tempDispossable?.dispose()
        tempDispossable = getFixturesUseCase.getFixtures().subscribe { resultState ->
            fixturesLiveData.postValue(resultState)
            availableLeagues.value = getAvailableLeaguesUseCase.getAvailableLeagues(resultState)
        }
        tempDispossable?.track()
    }

    fun requestResults() {
        if (tempDispossable?.isDisposed != true)
            tempDispossable?.dispose()
        tempDispossable = getResultsUseCase.getResults().subscribe { resultState ->
            resultsMutable.postValue(resultState)
        }
        tempDispossable?.track()
    }

    fun getFixtures(): List<Entity.Fixture>? {
        return fixturesLiveData.value
    }
}