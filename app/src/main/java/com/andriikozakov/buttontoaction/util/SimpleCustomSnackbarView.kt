package com.andriikozakov.buttontoaction.util

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.andriikozakov.buttontoaction.R
import com.google.android.material.snackbar.ContentViewCallback
import kotlinx.android.synthetic.main.simple_popup.view.*

class SimpleCustomSnackbarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), ContentViewCallback {

    lateinit var tvMsg: TextView
    lateinit var tvAction: TextView
    lateinit var imLeft: ImageView
    lateinit var layRoot: ConstraintLayout

    init {
        View.inflate(context, R.layout.simple_popup, this)
        clipToPadding = false
        this.tvMsg = findViewById(R.id.tv_message)
        this.tvAction = findViewById(R.id.tv_action)
        this.imLeft = findViewById(R.id.im_action_left)
        this.layRoot = findViewById(R.id.snack_constraint)
    }

    override fun animateContentIn(delay: Int, duration: Int) {
        val scaleX = ObjectAnimator.ofFloat(im_action_left, View.SCALE_X, 0f, 1f)
        val scaleY = ObjectAnimator.ofFloat(im_action_left, View.SCALE_Y, 0f, 1f)
        val animatorSet = AnimatorSet().apply {
            interpolator = OvershootInterpolator()
            setDuration(500)
            playTogether(scaleX, scaleY)
        }
        animatorSet.start()
    }

    override fun animateContentOut(delay: Int, duration: Int) {
    }
}