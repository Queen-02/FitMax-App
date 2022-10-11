package dev.queen.fitmax.ui.home_and_splash

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dev.queen.fitmax.R
import dev.queen.fitmax.utils.Constants
import dev.queen.fitmax.databinding.ActivityHomeBinding
import dev.queen.fitmax.ui.fragments.PlanFragment
import dev.queen.fitmax.ui.fragments.ProfileFragment
import dev.queen.fitmax.ui.fragments.TrackFragment
import dev.queen.fitmax.viewmodel.ExerciseViewModel

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    val exerciseViewModel: ExerciseViewModel by viewModels()
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpBottomNav()
        triggerFetchExerciseCategories()

    }

    fun triggerFetchExerciseCategories() {
        sharedPreferences = getSharedPreferences(Constants.prefsFile, MODE_PRIVATE)
        val getAccessToken = sharedPreferences.getString(Constants.access_token, "")
        exerciseViewModel.fetchExerciseCatrgories(getAccessToken!!)
        exerciseViewModel.fetchExercises(getAccessToken)

    }

    override fun onResume() {
        super.onResume()
        exerciseViewModel.exerciseLiveData.observe(this, Observer { categoryResponse ->
            Toast.makeText(baseContext, "fetched ${categoryResponse.size}", Toast.LENGTH_LONG).show()
        })
        exerciseViewModel.errorLiveData.observe(this, Observer { errorMsg ->
            Toast.makeText(baseContext, errorMsg, Toast.LENGTH_LONG).show()
        })
    }

    fun setUpBottomNav() {
        binding.bttmNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.plan -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fcvHome, PlanFragment())
                        .commit()
                    true
                }
                R.id.track -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fcvHome, TrackFragment())
                        .commit()
                    true
                }
                R.id.profile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fcvHome, ProfileFragment()).commit()
                    true
                }
                else -> false
            }
        }
    }
}