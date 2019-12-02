package com.reactions.fixt.presentation.ui.features.main

import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.Disposable
import com.reactions.fixt.domain.entity.Entity
import com.reactions.fixt.domain.usecase.google.GetFixturesUseCase
import com.reactions.fixt.domain.usecase.google.GetResultsUseCase
import com.reactions.fixt.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(private val getFixturesUseCase: GetFixturesUseCase,
                                        private val getResultsUseCase: GetResultsUseCase) : BaseViewModel() {

    private var tempDispossable: Disposable? = null

    val fixturesLiveData: MutableLiveData<List<Entity.Fixture>> = MutableLiveData()
    val resultsMutable: MutableLiveData<List<Entity.Results>> = MutableLiveData()
    val year = MutableLiveData<Int>()
    val month = MutableLiveData<Int>()
    val league = MutableLiveData<String>()


    fun requestFixtures() {
        if (tempDispossable?.isDisposed != true)
            tempDispossable?.dispose()
        tempDispossable = getFixturesUseCase.getFixtures().subscribe { resultState ->
            fixturesLiveData.postValue(resultState)
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