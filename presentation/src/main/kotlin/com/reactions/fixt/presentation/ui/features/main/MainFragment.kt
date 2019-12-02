package com.reactions.fixt.presentation.ui.features.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.reactions.fixt.domain.common.ResultState
import com.reactions.fixt.domain.entity.Entity
import com.reactions.fixt.presentation.R
import com.reactions.fixt.presentation.common.extension.observe
import com.reactions.fixt.presentation.ui.base.BaseFragment
import javax.inject.Inject

class MainFragment : BaseFragment() {

//    val TAG = MainFragment::class.qualifiedName.toString()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var tlFragments: TabLayout
    lateinit var vpFragments: ViewPager

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? = inflater.inflate(
            R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tlFragments = getView()!!.findViewById(R.id.tl_fragments)
        vpFragments = getView()!!.findViewById(R.id.vp_fragments)
        initTab()
        viewModel.fixturesLiveData.observe(this, Observer { fixtures -> setFixtures(fixtures) })
        viewModel.resultsMutable.observe(this, Observer { results -> setResults(results) })
        viewModel.requestFixtures()
        viewModel.requestResults()
        observe(viewModel.fixturesLiveData, ::setFixtures)
                Log.d("MainFragment", viewModel.getFixtures().toString())
    }

    private fun setResults(results: List<Entity.Results>?) {
        Log.d("MainFragment", results.toString())
    }

    private fun initTab() {
        val adapter = MainFragmentAdapter(fragmentManager)
        vpFragments.adapter = adapter
        tlFragments.setupWithViewPager(vpFragments)
    }

    private fun setFixtures(fixtures : List<Entity.Fixture>) {
        Log.d("MainFragment", fixtures.toString())
    }
}