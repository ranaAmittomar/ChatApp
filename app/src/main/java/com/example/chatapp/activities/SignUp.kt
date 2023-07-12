package com.example.chatapp.activities

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.chatapp.R
import com.example.chatapp.databinding.ActivitySignUpBinding
import com.example.chatapp.dataclass.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

//        binding.cardView.setOnClickListener {
//            if (binding.expandedLL.visibility == View.GONE) {
//                expandCard()
//            } else {
//                collapseCard()
//            }
//        }

        binding.btnSignup.setOnClickListener {
            val name = binding.edtName.text.toString()
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()

            signUp(name, email, password)
        }
    }

    private fun signUp(name: String, email: String, password: String) {
        //logic for creating user

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //adding user to database
                    addingUserToDatabase(name, email, mAuth.currentUser?.uid!!)
                    //jump to home activity
                    // Sign in success, update UI with the signed-in user's information
                    val intent = Intent(this@SignUp, MainActivity::class.java)
                    val myAnim: Animation = AnimationUtils.loadAnimation(this, R.anim.bounce)
                    binding.btnSignup.startAnimation(myAnim)
                    finish()
                    startActivity(intent)

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this@SignUp, "Error Occurred in Signup", Toast.LENGTH_SHORT)
                        .show()
                }
            }

    }

    private fun addingUserToDatabase(name: String, email: String, uid: String) {

        mDbRef = FirebaseDatabase.getInstance().reference
        mDbRef.child("user") //making parent(main node in database for user)
            .child(uid) //creating the child of main node using the UID of user
            .setValue(
                User(
                    name,
                    email,
                    uid
                )
            ) //setting the value of that user according to name,email and uid

    }


//    private fun expandCard() {
//        val expandAnimator =
//            ValueAnimator.ofInt(
//                binding.collapsedLL.measuredHeight,
//                binding.expandedLL.measuredHeight
//            )
//        expandAnimator.addUpdateListener { animation ->
//            val layoutParams = binding.cardView.layoutParams
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
//
//        binding.expandedLL.visibility = View.VISIBLE
//    }
//
//    private fun collapseCard() {
//        val collapseAnimator =
//            ValueAnimator.ofInt(
//                binding.expandedLL.measuredHeight,
//                binding.collapsedLL.measuredHeight
//            )
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
//                binding.expandedLL.visibility = View.GONE
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
//        collapseAnimator.duration = 300 // Set the desired animation duration
//        collapseAnimator.start()
//    }

}