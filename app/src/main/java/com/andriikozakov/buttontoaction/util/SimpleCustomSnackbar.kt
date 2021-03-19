package com.andriikozakov.buttontoaction.util

import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.andriikozakov.buttontoaction.R
import com.andriikozakov.buttontoaction.extensions.findSuitableParent
import com.google.android.material.snackbar.BaseTransientBottomBar

class SimpleCustomSnackbar(
    parent: ViewGroup,
    content: SimpleCustomSnackbarView
) : BaseTransientBottomBar<SimpleCustomSnackbar>(parent, content, content) {


    init {
        getView().setBackgroundColor(
            ContextCompat.getColor(
                view.context,
                android.R.color.transparent
            )
        )
        getView().setPadding(0, 16, 0, 0)
    }

    companion object {

        fun make(
            view: View,
            message: String, duretion: Int,
            listener: View.OnClickListener?, icon: Int, action_lable: String?, bg_color: Int
        ): SimpleCustomSnackbar? {

            // First we find a suitable parent for our custom view
            val parent = view.findSuitableParent() ?: throw IllegalArgumentException(
                "No suitable parent found from the given view. Please provide a valid view."
            )

            // We inflate our custom view
            try {
                val customView = LayoutInflater.from(view.context).inflate(
                    R.layout.layout_simple_custom_snackbar,
                    parent,
                    false
                ) as SimpleCustomSnackbarView
                // We create and return our Snackbar
                customView.tvMsg.text = message
                action_lable?.let {
                    customView.tvAction.text = action_lable
                    customView.tvAction.setOnClickListener {
                        listener?.onClick(customView.tvAction)
                    }
                }
                customView.imLeft.setImageResource(icon)
                customView.imLeft.backgroundTintList =
                    ContextCompat.getColorStateList(view.context, bg_color)
                val params =
                    view.layoutParams as FrameLayout.LayoutParams
                params.gravity = Gravity.TOP
                view.layoutParams = params
                return SimpleCustomSnackbar(
                    parent,
                    customView
                ).setDuration(duretion)
            } catch (e: Exception) {
                e.message?.let { Log.v("exception ", it) }
            }

            return null
        }

    }

}