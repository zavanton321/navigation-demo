package com.example.demonav

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkBuilder
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private lateinit var notificationManager: NotificationManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initNotificationManager()
        onNotificationButtonClick()
    }

    private fun initNotificationManager() {
        notificationManager =
            requireContext().getSystemService(Context.NOTIFICATION_SERVICE)
                    as NotificationManager

        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(
                NotificationChannel(
                    "normal",
                    "normal",
                    NotificationManager.IMPORTANCE_HIGH
                )
            )
        }
    }

    private fun onNotificationButtonClick() {
        btnNotification.setOnClickListener {

            val bundle = bundleOf("username" to "zavanton")
            val pendingIntent = createPendingIntent(bundle)
            val notification = createNotification(pendingIntent)

            notificationManager.notify(0, notification)
        }
    }

    private fun createPendingIntent(bundle: Bundle): PendingIntent =
        NavDeepLinkBuilder(requireContext())
            .setGraph(R.navigation.main_graph)
            .setDestination(R.id.explicitDemoFragment)
            .setArguments(bundle)
            .createPendingIntent()

    private fun createNotification(pendingIntent: PendingIntent): Notification =
        NotificationCompat.Builder(requireContext(), "normal")
            .setContentTitle("Explicit Deep Link Demo")
            .setContentText("I am testing explicit deep links...")
            .setSmallIcon(android.R.drawable.arrow_down_float)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

}
