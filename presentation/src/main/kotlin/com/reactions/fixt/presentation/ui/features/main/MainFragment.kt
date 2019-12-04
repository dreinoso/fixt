package com.reactions.fixt.presentation.ui.features.main

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.reactions.fixt.presentation.R
import com.reactions.fixt.presentation.ui.base.BaseFragment
import com.reactions.fixt.presentation.ui.features.common.MonthYearPickerDialog
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject


class MainFragment : BaseFragment(), DatePickerDialog.OnDateSetListener {

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
        val pd = MonthYearPickerDialog()
        pd.setListener(this)
        fab_date_picker.setOnClickListener { pd.show(fragmentManager, "MonthYearPickerDialog") }
        fab_league_picker.setOnClickListener { showLeagueSelector() }
    }

    private fun initTab() {
        val adapter = MainFragmentAdapter(fragmentManager)
        vpFragments.adapter = adapter
        tlFragments.setupWithViewPager(vpFragments)
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, p3: Int) {
        Log.d("mainfragment", "onDataset")
        val month = if (month > 9) month.toString() else "0$month"
        val filterDate = "$year-$month"
        viewModel.filterDate.postValue(filterDate)
    }

    private fun showLeagueSelector() {
        val array = viewModel.availableLeagues.value?.toTypedArray()
        val builder = activity?.let { AlertDialog.Builder(it) }
        builder?.setTitle(getString(R.string.select_league))
        builder?.setItems(array) { _, which ->
            val selected = array?.get(which)
            viewModel.filterLeague.postValue(selected)
        }
        val dialog = builder?.create()
        dialog?.show()
    }
}