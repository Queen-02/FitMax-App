package dev.queen.fitmax.ui.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.queen.fitmax.R
import dev.queen.fitmax.utils.Constants
import dev.queen.fitmax.databinding.FragmentProfileBinding
import dev.queen.fitmax.ui.login_sign_up.LoginActivity

class ProfileFragment : Fragment() {
    lateinit var sharedPref: SharedPreferences
    lateinit var bindingProfile : FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bindingProfile = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref= requireContext().getSharedPreferences(Constants.prefsFile, Context.MODE_PRIVATE)
        bindingProfile.ivLogout.setOnClickListener {
            logout()
        }
    }

    fun logout(){
        val editor= sharedPref.edit()
        editor.putString(Constants.access_token, Constants.emptyString)
        editor.putString(Constants.userId, Constants.emptyString)
        editor.putString(Constants.profileId, Constants.emptyString)
        editor.apply()
        startActivity(Intent(requireContext(), LoginActivity::class.java))
        requireActivity().finish()
    }

}