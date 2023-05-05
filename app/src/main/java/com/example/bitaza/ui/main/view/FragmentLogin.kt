package com.example.bitaza.ui.main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import com.example.bitaza.R
import com.example.bitaza.databinding.FragmentLoginBinding
import com.shashank.sony.fancytoastlib.FancyToast

class FragmentLogin : Fragment() {

    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this,object :OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                activity?.finish()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentLoginBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editTxtMail.doAfterTextChanged {
            binding.editTxtMail.setBackgroundResource(R.drawable.edittext_login)
        }
        binding.btnLogin.setOnClickListener {
            checkCharacters(binding.editTxtMail.text.toString(),binding.editTxtPass.text.toString())
            if(binding.editTxtMail.text.toString() == "test" && binding.editTxtPass.text.toString() == "test"){
                findNavController().navigate(R.id.action_fragmentLogin_to_fragmentMainScreen)
            }
        }
        binding.btnReg.setOnClickListener {
            goToRegistration()
        }
    }

    private fun checkCharacters(mail: String, pass: String) {
        if(mail.indexOf('@') == -1){
            binding.editTxtMail.setBackgroundResource(R.drawable.edittext_login_error)
            makeErrorToast("Введите корректный адрес электронной почты")
        }

    }

    private fun goToRegistration(){
        findNavController().navigate(R.id.action_fragmentLogin_to_fragmentRegistration)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun makeErrorToast(text : String){
        FancyToast.makeText(requireActivity(),text,FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show()
    }

}