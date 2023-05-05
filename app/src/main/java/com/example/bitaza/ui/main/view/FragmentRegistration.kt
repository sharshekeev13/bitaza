package com.example.bitaza.ui.main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import com.example.bitaza.R
import com.example.bitaza.databinding.FragmentRegistrationBinding
import com.shashank.sony.fancytoastlib.FancyToast

class FragmentRegistration : Fragment() {

    private var _binding : FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editTxtMail.doAfterTextChanged {
            binding.editTxtMail.setBackgroundResource(R.drawable.edittext_login)
        }
        binding.editTxtMail.doAfterTextChanged {
            binding.editTxtPass.setBackgroundResource(R.drawable.edittext_login)
        }
        binding.btnReg.setOnClickListener {
            checkCharacters(binding.editTxtMail.text.toString(),binding.editTxtPass.text.toString(),binding.editTxtPassConfirm.text.toString())
        }
        binding.btnLogin.setOnClickListener {
            goToLogin()
        }
    }



    private fun checkCharacters(mail: String, pass: String, confirmPass : String) {
        if(mail.indexOf('@') == -1){
            binding.editTxtMail.setBackgroundResource(R.drawable.edittext_login_error)
            makeErrorToast("Введите корректный адрес электронной почты")
        }
        if(pass != confirmPass){
            binding.editTxtPass.setBackgroundResource(R.drawable.edittext_login_error)
            makeErrorToast("Пароли не совпадают")
        }
    }
    private fun makeErrorToast(text : String){
        FancyToast.makeText(requireActivity(),text, FancyToast.LENGTH_SHORT, FancyToast.ERROR,false).show()
    }

    private fun goToLogin(){
        findNavController().navigate(R.id.action_fragmentRegistration_to_fragmentLogin)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}