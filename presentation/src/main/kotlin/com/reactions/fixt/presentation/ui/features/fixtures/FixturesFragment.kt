package com.reactions.fixt.presentation.ui.features.fixtures

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.reactions.fixt.domain.entity.Entity
import com.reactions.fixt.presentation.R
import com.reactions.fixt.presentation.ui.base.BaseFragment
import com.reactions.fixt.presentation.ui.features.main.MainViewModel
import javax.inject.Inject

class FixturesFragment : BaseFragment() {
    lateinit var fixturesAdapter : FixturesAdapter
    var rvRules : RecyclerView? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fixtures, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fixturesAdapter = FixturesAdapter(context)
        rvRules = view?.findViewById(R.id.rv_fixtures)
        rvRules?.layoutManager = LinearLayoutManager(activity)
        rvRules?.adapter = fixturesAdapter
        viewModel.fixturesLiveData.observe(this, Observer { fixtures -> setAdapter(fixtures) })
        viewModel.requestFixtures()
    }

    fun setAdapter(fixtures : List<Entity.Fixture>) {
        Log.d("fixturesfragment", "setAdapter " + fixtures.toString())
        fixturesAdapter.setFixtures(fixtures)
    }
}
