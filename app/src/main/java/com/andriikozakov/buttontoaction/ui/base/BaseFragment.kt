package com.andriikozakov.buttontoaction.ui.base

import androidx.fragment.app.Fragment
import com.andriikozakov.buttontoaction.R
import com.andriikozakov.buttontoaction.extensions.snackbar
import com.andriikozakov.buttontoaction.util.Constants
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment : Fragment(), BaseView {
    fun showFailedMessage(errorString: String) {
        this.requireView().snackbar(
            Constants.Network.Params.ERROR,
            errorString,
            Snackbar.LENGTH_LONG,
            R.drawable.ic_profile
        )
    }

    fun onNetworkError() {
        this.requireView().snackbar(
            Constants.Network.Params.ERROR,
            getString(R.string.txt_connection_issues),
            Snackbar.LENGTH_LONG,
            R.drawable.ic_network
        )
    }

    fun onSuccessMessage(messageString: String) {
        this.requireView().snackbar(
            Constants.Network.Params.SUCCESS,
            messageString,
            Snackbar.LENGTH_LONG,
            R.drawable.ic_checkmark
        )
    }

}