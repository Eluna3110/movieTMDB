package io.software.functionalutilities

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat

fun NotificationManager.sendNotification(
    title: String,
    message: String,
    channel: String,
    applicationContext: Context
) {
    val builder = NotificationCompat.Builder(
        applicationContext,
        channel)
        .setSmallIcon(R.mipmap.ic_launcher)
        .setContentTitle(title)
        .setContentText(message)
    notify(Constants.COMMENT_NOTIFICATION_ID, builder.build())
}

fun NotificationManager.cancelNotification() = cancelAll()