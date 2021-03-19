package com.andriikozakov.buttontoaction.extensions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.FrameLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.andriikozakov.buttontoaction.R
import com.andriikozakov.buttontoaction.callbacks.OnDismissListener
import com.andriikozakov.buttontoaction.util.Constants.Network.Params.Companion.DEFAULT
import com.andriikozakov.buttontoaction.util.Constants.Network.Params.Companion.ERROR
import com.andriikozakov.buttontoaction.util.Constants.Network.Params.Companion.SUCCESS
import com.andriikozakov.buttontoaction.util.SimpleCustomSnackbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.BaseTransientBottomBar

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

enum class Anim(val propertyName: String) {
    Alpha("alpha"),
    Rotation("rotation"),
    RotationX("rotationX"),
    RotationY("rotationY"),
    ScaleX("scaleX"),
    ScaleY("scaleY"),
    TranslationX("translationX"),
    TranslationY("translationY"),
    TranslationZ("translationZ"),
    X("x"),
    Y("y"),
    Z("z"),
}

fun MaterialButton.anim(anim: Anim, vararg values: Int, init: (ObjectAnimator.() -> Unit)?) =
    ObjectAnimator.ofInt(this, anim.propertyName, *values).apply {
        if (init != null) init()
    }!!

fun MaterialButton.anim(anim: Anim, vararg values: Float, init: (ObjectAnimator.() -> Unit)?) =
    ObjectAnimator.ofFloat(this, anim.propertyName, *values).apply {
        if (init != null) init()
    }!!

fun AnimatorSet.onAnimationEnd(onAnimationEnd: () -> Unit) {
    addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator?) {
            super.onAnimationEnd(animation)
            onAnimationEnd()
        }
    })
}

fun Animation.onAnimationEnd(onAnimationEnd: () -> Unit) {
    setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationRepeat(p0: Animation?) {}
        override fun onAnimationEnd(p0: Animation?) {
            onAnimationEnd()
        }

        override fun onAnimationStart(p0: Animation?) {}
    })
}

internal fun View?.findSuitableParent(): ViewGroup? {
    var view = this
    var fallback: ViewGroup? = null
    do {
        if (view is CoordinatorLayout) {
            return view
        } else if (view is FrameLayout) {
            if (view.id == android.R.id.content) {
                return view
            } else {
                fallback = view
            }
        }
        if (view != null) {
            val parent = view.parent
            view = if (parent is View) parent else null
        }
    } while (view != null)
    return fallback
}

fun View.snackbar(
    type: String,
    msg: String,
    dur: Int,
    custom: Int? = null,
    action: String? = null,
    clickListener: View.OnClickListener? = null,
    dismissListener: OnDismissListener? = null
) {
    val icon = custom
        ?: when (type) {
            DEFAULT -> R.mipmap.ic_launcher
            SUCCESS -> R.mipmap.ic_launcher
            ERROR -> R.mipmap.ic_launcher
            else -> custom
        }

    val color = when (type) {
        DEFAULT -> R.color.colorGreen
        SUCCESS -> R.color.colorGreen
        ERROR -> R.color.colorRed
        else -> R.color.colorGreen
    }
    val snack: SimpleCustomSnackbar? =
        SimpleCustomSnackbar.make(this, msg, dur, clickListener, icon!!, action, color)
    val view = snack?.view
    val params =
        view?.layoutParams as FrameLayout.LayoutParams
    params.gravity = Gravity.TOP
    view.layoutParams = params
    snack.addCallback(object : BaseTransientBottomBar.BaseCallback<SimpleCustomSnackbar>() {
        override fun onDismissed(transientBottomBar: SimpleCustomSnackbar?, event: Int) {
            super.onDismissed(transientBottomBar, event)
            dismissListener?.onDismiss()
        }
    })

    snack.show()

}