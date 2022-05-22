package com.dolearnn.cg.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.dolearnn.cg.workers.DbSyncWorker

class OnAppUpdateBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null) return

        DbSyncWorker.schedule(context)
    }
}