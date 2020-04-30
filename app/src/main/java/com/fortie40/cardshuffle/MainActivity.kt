package com.fortie40.cardshuffle

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.WorkInfo
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<CalculateViewModel>()
    private var isClicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        calculate.setOnClickListener {
            val isChecked = switch1.isChecked
            showWorkInProgress()
            viewModel.performWork(isChecked)
            isClicked = true
        }

        cancel.setOnClickListener {
            showWorkIsFinished()
            viewModel.cancelWork()
            status.text = getString(R.string.cancelled)
        }

        viewModel.calculateWorkInfo.observe(this, calculateWorkInfoObserver())
    }

    private fun showWorkInProgress() {
        calculate.isEnabled = false
        cancel.isEnabled = true
        progress_bar.visibility = View.VISIBLE
        status.text = getString(R.string.calculating)
        results_is.visibility = View.GONE
        results.text = ""
    }

    private fun showWorkIsFinished() {
        calculate.isEnabled = true
        cancel.isEnabled = false
        progress_bar.visibility = View.GONE
    }

    private fun calculateWorkInfoObserver(): Observer<List<WorkInfo>> {
        return Observer {
            if (it.isNullOrEmpty()) {
                return@Observer
            }

            val workInfo = it[0]
            if (workInfo.state.isFinished) {
                showWorkIsFinished()
                val output = workInfo.outputData.getString(KEY_TASK_OUTPUT)

                if (output != null && isClicked) {
                    results_is.visibility = View.VISIBLE
                    results.text = output
                    status.text = ""
                }

            } else {
                showWorkInProgress()
            }
        }
    }
}
