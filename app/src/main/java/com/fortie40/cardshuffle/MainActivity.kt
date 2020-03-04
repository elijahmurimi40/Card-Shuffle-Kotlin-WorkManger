package com.fortie40.cardshuffle

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        calculate.setOnClickListener {
            showWorkInProgress()
        }

        cancel.setOnClickListener {
            showWorkIsFinished()
        }
    }

    private fun showWorkInProgress() {
        calculate.isEnabled = false
        cancel.isEnabled = true
        progress_bar.visibility = View.VISIBLE
    }

    private fun showWorkIsFinished() {
        calculate.isEnabled = true
        cancel.isEnabled = false
        progress_bar.visibility = View.GONE
    }
}
