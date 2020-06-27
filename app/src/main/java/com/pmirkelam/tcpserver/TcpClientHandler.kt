package com.pmirkelam.tcpserver

import android.util.Log
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.IOException

class TcpClientHandler(private val dataInputStream: DataInputStream, private val dataOutputStream: DataOutputStream) : Thread() {
    override fun run() {
        while (true) {
            try {
                Log.i(TAG, "Received: " + dataInputStream.readUTF())
                dataOutputStream.writeUTF("Hello Client")
                sleep(2000L)
            } catch (e: IOException) {
                e.printStackTrace()
                try {
                    dataInputStream.close()
                    dataOutputStream.close()
                } catch (ex: IOException) {
                    ex.printStackTrace()
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
                try {
                    dataInputStream.close()
                    dataOutputStream.close()
                } catch (ex: IOException) {
                    ex.printStackTrace()
                }
            }
        }
    }

    companion object {
        private val TAG = TcpClientHandler::class.java.simpleName
    }

}