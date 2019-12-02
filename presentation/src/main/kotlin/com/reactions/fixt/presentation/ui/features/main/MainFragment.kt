package com.reactions.fixt.presentation.ui.features.main

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.reactions.fixt.domain.entity.Entity
import com.reactions.fixt.presentation.R
import com.reactions.fixt.presentation.ui.base.BaseFragment
import com.reactions.fixt.presentation.ui.features.common.MonthYearPickerDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject


class MainFragment : BaseFragment(), DatePickerDialog.OnDateSetListener {

//    val TAG = MainFragment::class.qualifiedName.toString()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var tlFragments: TabLayout
    lateinit var vpFragments: ViewPager
    lateinit var fabDatePicker: FloatingActionButton

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
        val pd = MonthYearPickerDialog()
        pd.setListener(this)
        fab_date_picker.setOnClickListener { pd.show(fragmentManager, "MonthYearPickerDialog") }
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

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, p3: Int) {
        Log.d("mainfragment", "onDataset")
        viewModel.year.postValue(year)
        viewModel.month.postValue(month)
    }
}