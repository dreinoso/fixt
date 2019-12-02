package com.reactions.fixt.presentation.ui.features.common

import android.app.AlertDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment
import com.reactions.fixt.presentation.R
import java.util.*

class MonthYearPickerDialog : DialogFragment() {
    private var listener: OnDateSetListener? = null
    fun setListener(listener: OnDateSetListener?) {
        this.listener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        val cal: Calendar = Calendar.getInstance()
        val dialog: View? = activity?.layoutInflater?.inflate(R.layout.dialog_month_year_picker, null)
        val monthPicker = dialog?.findViewById(R.id.picker_month) as NumberPicker
        val yearPicker = dialog.findViewById(R.id.picker_year) as NumberPicker
        monthPicker.minValue = 1
        monthPicker.maxValue = 12
        monthPicker.value = cal.get(Calendar.MONTH)
        val year: Int = cal.get(Calendar.YEAR) - 10
        yearPicker.minValue = year
        yearPicker.maxValue = MAX_YEAR
        yearPicker.value = year
        builder.setView(dialog)
                .setPositiveButton(R.string.ok) { _, _ -> listener!!.onDateSet(null, yearPicker.value, monthPicker.value, 0) }
                .setNegativeButton(R.string.cancel) { _, _ -> this@MonthYearPickerDialog.dialog.cancel() }
        return builder.create()
    }

    companion object {
        private const val MAX_YEAR = 2099
    }
}