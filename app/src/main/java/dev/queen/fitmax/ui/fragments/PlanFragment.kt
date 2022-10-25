package dev.queen.fitmax.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels

import androidx.lifecycle.Observer
import dev.queen.fitmax.databinding.FragmentPlanBinding
import dev.queen.fitmax.ui.adapter.CategoryAdapter
import dev.queen.fitmax.ui.adapter.ExerciseAdapter
import dev.queen.fitmax.viewmodel.ExerciseViewModel

class PlanFragment : Fragment() {
    lateinit var binding: FragmentPlanBinding
    val exerciseViewModel: ExerciseViewModel by viewModels()
//
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPlanBinding.inflate(inflater, container,false)
        var view = binding.root
        return view
    }

    override fun onResume() {
        super.onResume()
        setupSpinners()
        exerciseViewModel.getDbExerciseCategories()
        exerciseViewModel.getDbExercises()
        exerciseViewModel.exerciseCategoryLiveData.observe(this, Observer { exerciseCategory ->
            binding.spCategory.adapter = CategoryAdapter(requireContext(), exerciseCategory)
            binding.spCategory.onItemSelectedListener = object : OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedCategory = exerciseCategory.get(position)
                    val categoryId = selectedCategory.categoryId
                    exerciseViewModel.getExerciseByCategoryId(categoryId)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
        })

        exerciseViewModel.exerciseLiveData.observe(this, Observer { exercise ->
            binding.spExsercise.adapter = ExerciseAdapter(requireContext(), exercise)
            binding.spExsercise.onItemSelectedListener = object : OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedExercise = exercise.get(position)
                    val categoryId = selectedExercise.categoryId
                    exerciseViewModel.getExerciseByCategoryId(categoryId)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
        })


    }

    fun setupSpinners(){
        setupDaySpinner()
    }
    fun setupDaySpinner(){
        val dayList = listOf<String>("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
        val dayAdapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, dayList)
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        binding.spDay.adapter = dayAdapter
    }

}
