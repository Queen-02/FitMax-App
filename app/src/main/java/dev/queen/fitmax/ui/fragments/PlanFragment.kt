package dev.queen.fitmax.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels

import androidx.lifecycle.Observer
import dev.queen.fitmax.databinding.FragmentPlanBinding
import dev.queen.fitmax.models.Exercise
import dev.queen.fitmax.models.ExerciseCategory
import dev.queen.fitmax.models.WorkoutPlan
import dev.queen.fitmax.ui.adapter.CategoryAdapter
import dev.queen.fitmax.ui.adapter.ExerciseAdapter
import dev.queen.fitmax.utils.Constants
import dev.queen.fitmax.viewmodel.ExerciseViewModel
import dev.queen.fitmax.viewmodel.WorkoutPlanViewModel
import java.util.UUID

class PlanFragment : Fragment() {
    var binding: FragmentPlanBinding? = null
    val exerciseViewModel: ExerciseViewModel by viewModels()
    val workoutPlanViewModel : WorkoutPlanViewModel by viewModels()

    val bind get() = binding!!

    //
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPlanBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onResume() {
        super.onResume()
        setupSpinners()
        exerciseViewModel.getDbExerciseCategories()
        exerciseViewModel.getDbExercises()
        setExerciseCategory()
        bind.btnAddItem.setOnClickListener {
            clickAddItem()
        }
        checkExistingWorkout()
    }

    fun setupSpinners() {
        setupDaySpinner()
    }

    fun setupDaySpinner() {
        val dayList = listOf<String>(
            "Select Day",
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday",
            "Saturday",
            "Sunday"
        )
        val dayAdapter =
            ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, dayList)
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        bind.spDay.adapter = dayAdapter
    }

    fun setExerciseCategory() {
        exerciseViewModel.exerciseCategoryLiveData.observe(this, Observer { exerciseCategory ->
            val firstCategory = ExerciseCategory("Select category", "0")
            val displayCategories = mutableListOf(firstCategory)
            displayCategories.addAll(exerciseCategory)
            bind.spCategory.adapter = CategoryAdapter(requireContext(), displayCategories)
            bind.spCategory.onItemSelectedListener = object : OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedCategory = displayCategories.get(position)
                    val categoryId = selectedCategory.categoryId
                    exerciseViewModel.getExerciseByCategoryId(categoryId)
                    setExerciseSpinner()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }
        })
    }

    fun setExerciseSpinner() {
        exerciseViewModel.exerciseLiveData.observe(this, Observer { exercise ->
            val firstExercise = Exercise("0", "", "", "0", "Select Exercise")
            val displayExercise = mutableListOf(firstExercise)
            displayExercise.addAll(exercise)
            bind.spExsercise.adapter = ExerciseAdapter(requireContext(), displayExercise)
            exerciseViewModel.getDbExercises()
        })
    }

    //    Adding onclick on buttons
    fun clickAddItem() {
        var error = false
        if (bind.spDay.selectedItemPosition == 0) {
            error = true
            Toast.makeText(requireContext(), "Select Day", Toast.LENGTH_LONG).show()

        }
        if (bind.spCategory.selectedItemPosition == 0) {
            error = true
            Toast.makeText(requireContext(), "Select Category", Toast.LENGTH_LONG).show()

        }
        if (bind.spExsercise.selectedItemPosition == 0) {
            error = true
            Toast.makeText(requireContext(), "Select Exercise", Toast.LENGTH_LONG).show()

        }

        if (!error) {
            val selectedExercise = bind.spExsercise.selectedItem as Exercise
            exerciseViewModel.selectedExerciseIds.add(selectedExercise.exerciseId)
            bind.spExsercise.setSelection(0)
            bind.spCategory.setSelection(0)
        }
    }

    //    Checking existing workout plan
    fun checkExistingWorkout() {
        val pref =
            requireActivity().getSharedPreferences(Constants.prefsFile, Context.MODE_PRIVATE)
        val userId = pref.getString(Constants.userId, Constants.emptyString).toString()
        workoutPlanViewModel.getExistingWorkoutPlan(userId)
        workoutPlanViewModel.workoutPlanLiveData.observe(this, Observer { wrkPlan ->
            if (wrkPlan == null){
                val newWrkPlan = WorkoutPlan(UUID.randomUUID().toString(), userId)
                workoutPlanViewModel.saveWorkoutPlan(newWrkPlan)
            }
        })

    }

}
