package com.reactions.fixt.presentation.ui.features.main

import android.os.Bundle
import com.reactions.fixt.presentation.R
import com.reactions.fixt.presentation.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun getNavControllerId(): Int = R.id.activityMainHomeHostFragment
}