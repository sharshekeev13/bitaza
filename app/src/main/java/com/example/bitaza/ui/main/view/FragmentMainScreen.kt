package com.example.bitaza.ui.main.view

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bitaza.data.model.User
import com.example.bitaza.databinding.FragmentMainScreenBinding
import com.example.bitaza.ui.main.adapter.UserAdapter
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.shashank.sony.fancytoastlib.FancyToast
import java.io.IOException
import java.util.Locale

class FragmentMainScreen : Fragment() {

    private var _binding : FragmentMainScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var userArray : ArrayList<User>
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userRecyclerViewAdapter: UserAdapter
    private lateinit var fusedLocationProviderClient : FusedLocationProviderClient



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentMainScreenBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        setAdapter()
        binding.addNewBtn.setOnClickListener {
            checkLocaionPermission()
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if(takePictureIntent.resolveActivity(requireActivity().packageManager) != null){
                startActivityForResult(takePictureIntent,42)
            }else{
                FancyToast.makeText(requireContext(),"Невозможно открыть камеру", FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show()
            }
        }
        binding.profileBtn.setOnClickListener {
            var dialog = FragmentProfile()
            dialog.show(requireActivity().supportFragmentManager,"Dialog")
        }
    }

    private fun initData() {
        userRecyclerViewAdapter = UserAdapter()
        userRecyclerViewAdapter.updateData(arrayListOf(
            User("dastan.sharshekeev@gmail.com","123",2450,"Шаршекеев Дастан",10),
            User("dastan.sharshekeev@gmail.com","123",1234,"Югай Илья",10),
            User("dastan.sharshekeev@gmail.com","123",6123,"Пратов Тимур",10),
            User("dastan.sharshekeev@gmail.com","123",1230,"Молчанов Кирилл",10),
            User("dastan.sharshekeev@gmail.com","123",1111,"Асанов Баястнан",10),
        )
        )
    }

    private fun setAdapter() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        userRecyclerView = binding.userRecycleView
        userRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        userRecyclerView.adapter = userRecyclerViewAdapter
    }

    private fun checkLocaionPermission() {
        if(ActivityCompat.checkSelfPermission(requireActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            getLocation()
        }else{
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),44)
        }
    }

    private fun getLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),44)
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION),44)
            return
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener {
            var location : Location = it.getResult()
            if(location != null){
                var geocoder : Geocoder = Geocoder(requireContext(), Locale.getDefault())
                try {
                    var addresses: List<Address> =
                        geocoder.getFromLocation(
                            location.latitude,
                            location.longitude,
                            1
                        ) as List<Address>
                    Log.d("TIMA", addresses[0].toString())
                }catch (e : IOException){
                    e.printStackTrace()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == 42 && resultCode == Activity.RESULT_OK){
            val takenImage = data?.extras?.get("data") as Bitmap
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }



}