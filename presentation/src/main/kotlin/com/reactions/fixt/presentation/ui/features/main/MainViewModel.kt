package com.reactions.fixt.presentation.ui.features.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import io.reactivex.disposables.Disposable
import com.reactions.fixt.domain.entity.Entity
import com.reactions.fixt.domain.usecase.google.GetFixturesUseCase
import com.reactions.fixt.presentation.common.OperationLiveData
import com.reactions.fixt.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(private val getFixturesUseCase: GetFixturesUseCase) : BaseViewModel() {

    private var tempDispossable: Disposable? = null
    private val fetch = MutableLiveData<String>()

    val fixturesLiveData: MutableLiveData<List<Entity.Fixture>> = MutableLiveData()

    fun requestFixtures() {
        if (tempDispossable?.isDisposed != true)
            tempDispossable?.dispose()
        tempDispossable = getFixturesUseCase.getFixtures().subscribe { resultState ->
            fixturesLiveData.postValue(resultState)
        }
        tempDispossable?.track()
    }

    fun getFixtures(): List<Entity.Fixture>? {
        return fixturesLiveData.value
    }
}