package com.fortie40.cardshuffle

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class CalculateWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    private val _context = context
    override fun doWork(): Result {
        val sum: Int
        val inputData = inputData.getString(KEY_TASK_INPUT)
        if (inputData?.toInt() != null) {
            sum = inputData.toInt() + 1
            Log.i("TAG", sum.toString())
        }

        Thread.sleep(3000)
        val output = Data.Builder()
            .putString(KEY_TASK_OUTPUT, _context.getString(R.string.results))
            .build()
        return Result.success(output)
    }
}