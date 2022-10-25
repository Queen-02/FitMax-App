package dev.queen.fitmax.ui.adapter

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import dev.queen.fitmax.R
import dev.queen.fitmax.models.ExerciseCategory

class CategoryAdapter(context: Context, var categories: List<ExerciseCategory>) :
    ArrayAdapter<ExerciseCategory>(context, 0, categories) {

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View =
        getCustomView(position, convertView, parent)

    private fun getCustomView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = LayoutInflater.from(context).inflate(R.layout.custom_layout, parent, false)
        val tvSpinnerText = view.findViewById<TextView>(R.id.spinnerText)
        tvSpinnerText.text = categories.get(position).categoryName
        return view
    }
}