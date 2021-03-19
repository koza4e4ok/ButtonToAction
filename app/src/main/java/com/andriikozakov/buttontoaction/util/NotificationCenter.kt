package com.andriikozakov.buttontoaction.util

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.andriikozakov.buttontoaction.R
import com.andriikozakov.buttontoaction.ui.MainActivity
import com.andriikozakov.buttontoaction.util.Constants.Companion.ACTION_CHOOSE_CONTACT
import javax.inject.Inject

class NotificationCenter @Inject
constructor(var application: Application) {

    private var notificationManager: NotificationManager =
        application.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    private lateinit var notificationBuilder: NotificationCompat.Builder

    fun sendNotification() {
        val intent = Intent(application, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        intent.action = ACTION_CHOOSE_CONTACT
        val pendingIntent = PendingIntent.getActivity(
            application, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        notificationBuilder =
            NotificationCompat.Builder(application, "buttontoaction_notification_channel")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(application.getString(R.string.txt_action))
                .setContentText(application.getString(R.string.txt_notification_title))
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "buttontoaction_notification_channel",
                "buttontoaction",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())
    }

    fun cancelNotificationById(id: Int) {
        notificationManager.cancel(id)
    }

}