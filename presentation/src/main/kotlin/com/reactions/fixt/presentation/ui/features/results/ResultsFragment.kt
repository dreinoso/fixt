package com.reactions.fixt.presentation.ui.features.results

import android.os.Bundle
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

class ResultsFragment : BaseFragment() {

    lateinit var resultsAdapter : ResultsAdapter
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
        return inflater.inflate(R.layout.fragment_results, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        resultsAdapter = ResultsAdapter(context)
        rvRules = view.findViewById(R.id.rv_results)
        rvRules?.layoutManager = LinearLayoutManager(activity)
        rvRules?.adapter = resultsAdapter
        val dividerItemDecoration = DividerItemDecoration(context, resources.configuration.orientation)
        rvRules?.addItemDecoration(dividerItemDecoration)
        observeViewModel()
        viewModel.requestResults()
    }

    private fun observeViewModel() {
        viewModel.resultsMutable.observe(this, Observer { results -> setAdapter(results) })
        viewModel.filterDate.observe(this, Observer { filterResults() })
        viewModel.filterLeague.observe(this, Observer { filterResults() })
    }

    fun setAdapter(results : List<Entity.Results>) {
        resultsAdapter.setResults(results)
    }

    fun filterResults() {
        if (viewModel.resultsMutable.value != null) {
            resultsAdapter.filter(viewModel.resultsMutable.value!!, viewModel.filterDate.value!!,
                    viewModel.filterLeague.value!!)
        }
    }
}
