package com.neluam.flowsexample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.neluam.flowsexample.R
import com.neluam.flowsexample.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val vieModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //vieModel.example()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                vieModel.uiState.collect{ uiState ->
                    when (uiState){
                        is MainUIState.Error -> {
                            binding.progress.isVisible = false
                            Toast.makeText(this@MainActivity, "Ha ocurrido un error: ${uiState.msg}", Toast.LENGTH_SHORT).show()
                        }
                        MainUIState.Loading -> {
                            binding.progress.isVisible = true
                        }
                        is MainUIState.Success -> {
                            binding.progress.isVisible = false
                            Toast.makeText(this@MainActivity, "Numero de suscriptortes way: ${uiState.numSubscribers}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

        vieModel.example3()

    }
}