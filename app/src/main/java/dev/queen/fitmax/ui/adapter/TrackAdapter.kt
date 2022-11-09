package dev.queen.fitmax.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.queen.fitmax.databinding.TrackItemRowBinding
import dev.queen.fitmax.models.Exercise

class TrackAdapter(val exerciseList: List<Exercise>, val logworkout: Logworkout) :
    RecyclerView.Adapter<ExerciseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val binding = TrackItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExerciseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val currentExercise = exerciseList.get(position)
        holder.binding.tvExercise.text = currentExercise.exerciseName
        holder.binding.cbDoneOne.setOnClickListener {
            val weight = holder.binding.etWeight1.text.toString()
            val reps = holder.binding.etRepsOne.text.toString()
            logworkout.onClickDone(
                set = 1,
                weight = weight.toInt(),
                reps = reps.toInt(),
                currentExercise.exerciseId
            )
        }
        holder.binding.cbDone2.setOnClickListener {
            val weight = holder.binding.etWeightTwo.text.toString()
            val reps = holder.binding.etRepsTwo.text.toString()
            logworkout.onClickDone(
                set = 2,
                weight = weight.toInt(),
                reps = reps.toInt(),
                currentExercise.exerciseId
            )
        }
        holder.binding.cbDone3.setOnClickListener {
            val weight = holder.binding.etWeight3.text.toString()
            val reps = holder.binding.etReps3.text.toString()
            logworkout.onClickDone(
                set = 3,
                weight = weight.toInt(),
                reps = reps.toInt(),
                currentExercise.exerciseId
            )
        }
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }
}


class ExerciseViewHolder(val binding: TrackItemRowBinding) : RecyclerView.ViewHolder(binding.root)

interface Logworkout {
    fun onClickDone(set: Int, weight: Int, reps: Int, exerciseId: String)
}