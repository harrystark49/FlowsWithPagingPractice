package com.example.flowswithpagingpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flowswithpagingpractice.databinding.ActivityMainBinding
import com.example.flowswithpagingpractice.databinding.RecyclerviewRowBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var rva:adap
    lateinit var vm:Viewmodel
    @Inject
    @Named("retro")
    lateinit var retro:Retro_Service
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rva= adap()
         vm=Viewmodel(retro)

        vm.getdata()
        lifecycleScope.launch {
           var s= vm.result.value
            Log.d("valueis","data $s")
        }
        initRecyclerView()
        initviewmodel()
    }

    fun initRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            var decoration =
                DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            rva = adap()
            adapter = rva
        }
    }
    private fun initviewmodel(){
        lifecycleScope.launchWhenCreated {
            
            var s=vm.result.value
            rva.submitData(s)
        }
    }
}