package dev.queen.fitmax.ui.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dev.queen.fitmax.databinding.FragmentTrackBinding
import dev.queen.fitmax.models.WorkoutLogRecord
import dev.queen.fitmax.ui.adapter.Logworkout
import dev.queen.fitmax.ui.adapter.TrackAdapter
import dev.queen.fitmax.utils.Constants
import dev.queen.fitmax.viewmodel.ExerciseViewModel
import dev.queen.fitmax.viewmodel.WorkoutLogRecordViewModel
import dev.queen.fitmax.viewmodel.WorkoutPlanViewModel
import java.time.LocalDate
import java.util.UUID


class TrackFragment : Fragment(), Logworkout {
    lateinit var binding: FragmentTrackBinding
    val workoutPlanViewModel: WorkoutPlanViewModel by viewModels()
    lateinit var sharedPres: SharedPreferences
    lateinit var userId: String
    val exerciseViewModel: ExerciseViewModel by viewModels()
    lateinit var workoutPlanItemId: String
    val workoutLogRecordViewModel: WorkoutLogRecordViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = FragmentTrackBinding.inflate(layoutInflater, container, false)
        return view.root
    }

    @SuppressLint("NewApi")
    override fun onResume() {
        super.onResume()
        sharedPres =
            requireContext().getSharedPreferences(Constants.prefsFile, Context.MODE_PRIVATE)
        userId = sharedPres.getString(Constants.userId, Constants.emptyString).toString()
        workoutPlanViewModel.getExistingWorkoutPlan(userId)
        workoutPlanViewModel.workoutPlanLiveData.observe(this, Observer { wrkoutPlan ->
            val wrkoutPlanId = wrkoutPlan.workoutPlanId
            val dayNumber = LocalDate.now().dayOfWeek.value
            workoutPlanViewModel.getTodayWorkoutPlanItem(wrkoutPlanId, dayNumber)
                .observe(this, Observer { wrkPlanItem ->
                    if (wrkPlanItem != null) {
                        workoutPlanItemId = wrkPlanItem.workoutPlanItemId
                        val todayExersiceId = wrkPlanItem.exerciseId
                        exerciseViewModel.getExercisesByExerciseId(todayExersiceId)
                            .observe(this, Observer { exercises ->
                                val adapter = TrackAdapter(exercises, this)
                                binding.rcvTrack.layoutManager =
                                    LinearLayoutManager(requireContext())
                                binding.rcvTrack.adapter = adapter
                            })
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "No workout Item found fro today, Create one to proceed",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                })
        })

    }

    override fun onClickDone(set: Int, weight: Int, reps: Int, exerciseId: String) {
        val workoutLogRecord = WorkoutLogRecord(
            wrkoutLogId= UUID.randomUUID().toString(),
            date = "",
            exerciseId = exerciseId,
            set= set,
            weight= weight,
            reps = reps,
            wrkPlanItemId = workoutPlanItemId,
            userId = userId
        )
        workoutLogRecordViewModel.saveWorkoutLogRecord(workoutLogRecord)
    }

}