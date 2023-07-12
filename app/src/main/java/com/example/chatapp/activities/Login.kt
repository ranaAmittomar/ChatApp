package com.example.chatapp.activities

import android.animation.Animator
import android.animation.LayoutTransition
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import com.example.chatapp.R
import com.example.chatapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        mAuth = FirebaseAuth.getInstance() // this is how we initialise firebase objects

//        binding.expandedContent!!.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
//        binding.cardView.setOnClickListener {
//            val v = if(binding.expandedContent.visibility ==View.GONE) View.VISIBLE else View.GONE
//            binding.expandedContent.visibility = v
//
//        }

        binding.btnSignup.setOnClickListener {
            startActivity(Intent(this, SignUp::class.java))
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            login(email, password)
        }
        binding.btnSignup.setOnClickListener {
            val myAnim: Animation = AnimationUtils.loadAnimation(this, R.anim.bounce)
            binding.btnSignup.startAnimation(myAnim)
            startActivity(Intent(this, SignUp::class.java))
        }
//        binding.cardView.setOnClickListener {
//            if (binding.expandedContent.visibility == View.GONE) {
//                expandCard()
//            } else {
//                collapseCard()
//            }
//        }
    }


//    private fun animateButton() {
//        val animation = SpringAnimation(binding.btnSignup, SpringAnimation.TRANSLATION_Y, 0f)
//        val springForce = SpringForce()
//            .setFinalPosition(0f)
//            .setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY)
//            .setStiffness(SpringForce.STIFFNESS_LOW)
//
//        animation.spring = springForce
//        animation.start()
//    }

    private fun login(email: String, password: String) {
        //logic for login
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val intent = Intent(this@Login, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this@Login, "User doesn't exist", Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }

//    private fun expandCard() {
//        val expandAnimator = ValueAnimator.ofInt(
//            binding.collapsedContent.measuredHeight,
//            binding.expandedContent.measuredHeight
//        )
//        expandAnimator.addUpdateListener { animation ->
//            val layoutParams = binding.cardView.layoutParams
////            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
//            layoutParams.height = animation.animatedValue as Int
//            binding.cardView.layoutParams = layoutParams
//        }
//        expandAnimator.addListener(object : Animator.AnimatorListener {
//            // Implement the necessary listener methods or use AnimatorListenerAdapter
//            // to handle animation start, end, cancel, and repeat events.
//            override fun onAnimationStart(animation: Animator) {
//                // Animation start logic
//            }
//
//            override fun onAnimationEnd(animation: Animator) {
//                // Animation end logic
//
//            }
//
//            override fun onAnimationCancel(animation: Animator) {
//                // Animation cancel logic
//            }
//
//            override fun onAnimationRepeat(animation: Animator) {
//                // Animation repeat logic
//            }
//        })
//        expandAnimator.duration = 300 // Set the desired animation duration
//        expandAnimator.start()
//        binding.expandedContent.visibility = View.VISIBLE
//
//    }
//
//    private fun collapseCard() {
//        val collapseAnimator = ValueAnimator.ofInt(
//            binding.expandedContent.measuredHeight,
//            binding.collapsedContent.measuredHeight
//        )
//        collapseAnimator.addUpdateListener { animation ->
//            val layoutParams = binding.cardView.layoutParams
//            layoutParams.height = animation.animatedValue as Int
//            binding.cardView.layoutParams = layoutParams
//        }
//        collapseAnimator.addListener(object : Animator.AnimatorListener {
//            // Implement the necessary listener methods or use AnimatorListenerAdapter
//            // to handle animation start, end, cancel, and repeat events.
//            override fun onAnimationStart(animation: Animator) {
//                // Animation start logic
//            }
//
//            override fun onAnimationEnd(animation: Animator) {
//                // Animation end logic
////                binding.expandedContent.visibility = View.GONE
//            }
//
//            override fun onAnimationCancel(animation: Animator) {
//                // Animation cancel logic
//            }
//
//            override fun onAnimationRepeat(animation: Animator) {
//                // Animation repeat logic
//            }
//        })
////        collapseAnimator.duration = 300 // Set the desired animation duration
////        collapseAnimator.start()
//    }
}