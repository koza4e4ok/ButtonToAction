package com.andriikozakov.buttontoaction.ui.base

import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    protected fun showMessage(
        message: String,
        messageTitle: String,
        positiveListener: (dialogInterface: DialogInterface, i: Int) -> Unit
    ) = AlertDialog.Builder(this)
        .setTitle(messageTitle)
        .setMessage(message)
        .setPositiveButton(android.R.string.ok, positiveListener)
        .setNegativeButton(android.R.string.no) { dialogInterface: DialogInterface, _: Int ->
            dialogInterface.dismiss()
        }
        .create()
        .show()
}