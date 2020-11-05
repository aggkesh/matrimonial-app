package com.example.matrimonial_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.matrimonial_app.ui.main.MainFragment

/**
 * Created by Keshav Aggarwal 11/4/2020
 *
 * Activity showing [MainFragment]
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}
