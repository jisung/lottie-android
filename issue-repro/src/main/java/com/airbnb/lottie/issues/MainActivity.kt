package com.airbnb.lottie.issues

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Reproduce any issues here.
        val context = this

        val animationView = findViewById<LottieAnimationView>(R.id.animationView)
        animationView.addAnimatorListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                Toast.makeText(context, "onAnimationEnd", Toast.LENGTH_SHORT).show()
            }
            override fun onAnimationStart(animation: Animator?) {
                Toast.makeText(context, "onAnimationStart", Toast.LENGTH_SHORT).show()
            }
        })
        playAnimationQuickly()
    }

    private fun playAnimationQuickly() {
        /**
         * preconditions:
         * 1. system animating setting is disabled
         * 2. invoke playAnimation() if the LottieAnimationView.isShown() isn't true yet.
         *
         * Unlike animating via 'autoPlay' property, LottieAnimationView.playAnimation() doesn't
         * trigger the Animator.AnimatorListener if the system animation setting is disabled
         */
        animationView.playAnimation() // doesn't work

        val button = findViewById<Button>(R.id.playButton)
        button.setOnClickListener {
            animationView.playAnimation() // work
        }
    }
}
