package com.amilcar.rutaj.presentation.util.downloadManager

interface Downloader {
    fun DonwloadFile(uri :String,fileName : String) :Long
}