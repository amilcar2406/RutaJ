package com.amilcar.rutaj.presentation.sendMail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.util.*
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class SendMail  {

    var stateMail by mutableStateOf(false)


    fun sendEmailFunction(args : Array<String>) {
        val userName = Credentials.EMAIL
        val password = Credentials.PASSWORD

        //Getting content for email
        val emailTo = args[0]
        val subject = args[1]
        val message = args[2]
        val emailCC = ""

        val props = Properties()

        /*putIfMissing(props, "mail.smtp.host", "smtp.office365.com")
        putIfMissing(props, "mail.smtp.port", "587")
        putIfMissing(props, "mail.smtp.auth", "true")
        putIfMissing(props, "mail.smtp.starttls.enable", "true")*/

        putIfMissing(props, "mail.smtp.host", "rutaj.com.ar")
        putIfMissing(props, "mail.smtp.port", "587")
        putIfMissing(props, "mail.smtp.auth", "true")
        putIfMissing(props, "mail.smtp.starttls.enable", "false")


        //putIfMissing(props,"mail.smtp.host", "smtp.gmail.com")
        // putIfMissing(props,"mail.smtp.socketFactory.port", "465")
        // putIfMissing(props,"mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
        // putIfMissing(props,"mail.smtp.auth", "true")
        // putIfMissing(props,"mail.smtp.port", "465")


        val session = Session.getDefaultInstance(props, object : javax.mail.Authenticator() {
            override fun getPasswordAuthentication() : PasswordAuthentication {
                return PasswordAuthentication(userName, password)
            }
        })

        session.debug = true

        try {
            val mimeMessage = MimeMessage(session)
            mimeMessage.setFrom(InternetAddress(userName))
            mimeMessage.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(emailTo, false)
            )
            mimeMessage.setRecipients(
                Message.RecipientType.CC,
                InternetAddress.parse(emailCC, false)
            )
            mimeMessage.setText(message)
            mimeMessage.subject = subject
            mimeMessage.sentDate = Date()

            val smtpTransport = session.getTransport("smtp")
            smtpTransport.connect()
            smtpTransport.sendMessage(mimeMessage, mimeMessage.allRecipients)
            smtpTransport.close()

            stateMail = true

        } catch (messagingException : MessagingException) {
            messagingException.printStackTrace()
            stateMail =  false
        }

    }

    private fun putIfMissing(props : Properties, key : String, value : String) {
        if (!props.containsKey(key)) {
            props[key] = value
        }
    }
}