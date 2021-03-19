package com.andriikozakov.buttontoaction.ui.main.buttontoaction

import android.net.ConnectivityManager
import androidx.lifecycle.ViewModel
import com.andriikozakov.buttontoaction.data.ConfigurationRepository
import com.andriikozakov.buttontoaction.data.model.Action
import com.andriikozakov.buttontoaction.preferenceHelper
import com.andriikozakov.buttontoaction.util.NetworkUtils
import com.andriikozakov.buttontoaction.util.getDayOfWeek
import com.andriikozakov.buttontoaction.util.getTimeInMillis
import javax.inject.Inject

class ButtonToActionViewModel @Inject
constructor(
    private val configurationRepository: ConfigurationRepository,
    private var connectivityManager: ConnectivityManager
) : ViewModel() {

    private var allActions: List<Action>? = null

    fun getConfiguration() = configurationRepository.getConfiguration

    fun setAllActions(allActions: List<Action>) {
        this.allActions = allActions
    }

    fun getValidAction(): Action? {
        val action =
            allActions?.sortedByDescending { action -> action.priority }?.firstOrNull { action ->
                action.enabled &&
                        !action.validDays.isNullOrEmpty() &&
                        action.validDays?.contains(getDayOfWeek() - 1)!! &&
                        ((preferenceHelper.action?.lastActionCoolDown != null &&
                                getTimeInMillis() - preferenceHelper.action?.lastActionCoolDown!! > action.coolDown) ||
                                preferenceHelper.action?.lastActionCoolDown == null)
            }
        if (action?.type.equals("toast") && !NetworkUtils.isInternetAvailable(connectivityManager)) {
            return null
        }
        if (action != null) {
            action.lastActionCoolDown = getTimeInMillis()
            preferenceHelper.action = action
        }
        return action
    }

}