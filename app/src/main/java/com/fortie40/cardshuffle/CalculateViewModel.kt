package com.fortie40.cardshuffle

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.work.*

class CalculateViewModel(application: Application): AndroidViewModel(application) {
    private val workManager = WorkManager.getInstance(application)
    val calculateWorkInfo: LiveData<List<WorkInfo>>

    init {
        calculateWorkInfo = workManager.getWorkInfosByTagLiveData(TAG_CALCULATE)
    }

    private fun createInputData(): Data {
        return Data.Builder()
            .putString(KEY_TASK_INPUT, "1")
            .build()
    }

    fun performWork(isChecked: Boolean) {
        val constraints = Constraints.Builder()
            .setRequiresCharging(isChecked)
            .build()

        val calculate = OneTimeWorkRequestBuilder<CalculateWorker>()
            .addTag(TAG_CALCULATE)
            .setConstraints(constraints)
            .setInputData(createInputData())
            .build()
        workManager.enqueueUniqueWork(WORK_TAG_NAME, ExistingWorkPolicy.REPLACE, calculate)
    }

    fun cancelWork() {
        workManager.cancelUniqueWork(WORK_TAG_NAME)
    }
}