package com.amilcar.rutaj.presentation.util.downloadManager

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri

class AndroidDownloader(
    private  val context : Context
) :Downloader {

    private val downloadManager = context.getSystemService(DownloadManager::class.java)

    override fun DonwloadFile(uri : String,fileName: String) : Long {
       val request = DownloadManager.Request(uri.toUri())
           .setMimeType("pdf/pdf")
           .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
           .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
           .setTitle(fileName)
           .addRequestHeader("Autorizacion","password")
           .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,fileName)

        return downloadManager.enqueue(request)
    }

}