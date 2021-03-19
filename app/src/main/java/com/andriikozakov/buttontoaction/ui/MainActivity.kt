package com.andriikozakov.buttontoaction.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import androidx.navigation.NavController
import com.andriikozakov.buttontoaction.R
import com.andriikozakov.buttontoaction.ui.base.BaseActivity
import com.andriikozakov.buttontoaction.util.Constants
import com.andriikozakov.buttontoaction.util.Constants.Companion.ACTION_CHOOSE_CONTACT
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : BaseActivity(),
    HasAndroidInjector {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Any>
    lateinit var navController: NavController

    override fun androidInjector(): AndroidInjector<Any> {
        return fragmentInjector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        handleActionIntent(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        handleActionIntent(intent)
    }

    private fun handleActionIntent(intent: Intent?) {
        if (intent?.action == ACTION_CHOOSE_CONTACT) {
            val pickIntent = Intent(Intent.ACTION_PICK).apply {
                type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
            }
            if (pickIntent.resolveActivity(packageManager) != null) {
                startActivityForResult(pickIntent, Constants.RequestCode.CHOOSE_CONTACT)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.RequestCode.CHOOSE_CONTACT && resultCode == RESULT_OK) {
            data?.data?.let {
                val projection: Array<String> =
                    arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER)
                contentResolver.query(it, projection, null, null, null)
                    .use { cursor ->
                        if (cursor != null)
                            if (cursor.moveToFirst()) {
                                val numberIndex =
                                    cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                                val number = cursor.getString(numberIndex)
                                val intentDial =
                                    Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
                                if (intentDial.resolveActivity(packageManager) != null) {
                                    startActivity(intentDial)
                                }
                            }
                    }
            }
        }
    }
}