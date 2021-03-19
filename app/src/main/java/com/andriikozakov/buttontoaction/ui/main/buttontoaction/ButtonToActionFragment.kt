package com.andriikozakov.buttontoaction.ui.main.buttontoaction

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.RotateAnimation
import androidx.lifecycle.ViewModelProvider
import com.andriikozakov.buttontoaction.R
import com.andriikozakov.buttontoaction.data.model.Action
import com.andriikozakov.buttontoaction.data.model.base.BaseResult
import com.andriikozakov.buttontoaction.ui.base.BaseFragment
import com.andriikozakov.buttontoaction.util.Constants.RequestCode.Companion.CHOOSE_CONTACT
import com.andriikozakov.buttontoaction.util.NotificationCenter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_button_to_action.*
import javax.inject.Inject

class ButtonToActionFragment : BaseFragment() {

    @Inject
    lateinit var notificationCenter: NotificationCenter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val buttonToActionViewModel: ButtonToActionViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(ButtonToActionViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_button_to_action, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getActions()
        btn_action.setOnClickListener {
            val validAction = buttonToActionViewModel.getValidAction()
            if (validAction != null)
                pickAction(validAction)
            else showFailedMessage(getString(R.string.txt_action_not_found))
        }
    }

    private fun getActions() {
        buttonToActionViewModel.getConfiguration().observe(viewLifecycleOwner, {
            it?.let {
                when (it.status) {
                    BaseResult.Status.SUCCESS -> {
                        it.data?.let { actionsConfiguration ->
                            setActionConfiguration(
                                actionsConfiguration
                            )
                        }
                        loading.hide()
                        btn_action.isEnabled = true
                    }
                    BaseResult.Status.LOADING -> {
                        loading.show()
                        btn_action.isEnabled = false
                    }
                    BaseResult.Status.ERROR -> {
                        loading.hide()
                        showFailedMessage(it.message!!)
                        btn_action.isEnabled = true
                    }
                    BaseResult.Status.NETWORK_ERROR -> {
                        loading.hide()
                        onNetworkError()
                        btn_action.isEnabled = true
                    }
                }
            }
        })
    }

    private fun setActionConfiguration(actionsConfiguration: List<Action>) {
        buttonToActionViewModel.setAllActions(actionsConfiguration)
    }

    private fun pickAction(action: Action) {
        when (action.type) {
            "animation" -> rotate()
            "toast" -> toast()
            "call" -> call()
            "notification" -> notification()
        }
    }

    private fun rotate() {
        val rotateAnimation = RotateAnimation(
            0.0f, 360.0f,
            RotateAnimation.RELATIVE_TO_SELF, 0.5f,
            RotateAnimation.RELATIVE_TO_SELF, 0.5f
        )
        rotateAnimation.duration = 500
        btn_action.startAnimation(rotateAnimation)
    }

    private fun toast() {
        onSuccessMessage(getString(R.string.txt_toast_message))
    }

    private fun call() {
        val intent = Intent(Intent.ACTION_PICK).apply {
            type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
        }
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            requireActivity().startActivityForResult(intent, CHOOSE_CONTACT)
        }
    }

    private fun notification() {
        notificationCenter.sendNotification()
    }

}

