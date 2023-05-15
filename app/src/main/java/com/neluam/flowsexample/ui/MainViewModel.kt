package com.neluam.flowsexample.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neluam.flowsexample.data.SuscribeteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val suscribeteRepository = SuscribeteRepository()

    private val _uiState = MutableStateFlow<MainUIState>(MainUIState.Loading)
    val uiState: StateFlow<MainUIState> = _uiState

    fun example(){
        viewModelScope.launch {
            suscribeteRepository.counter
                .map { it.toString() }
                .collect{ bombitas: String ->
                Log.i("---------------", bombitas)
            }
        }
    }

    fun example2(){
        viewModelScope.launch {
            suscribeteRepository.counter
                .map { it.toString() }
                .onEach { save(it) }
                .catch { error ->
                    Log.e("asistiCurso", "Error ${error.message}")
                }
                .collect{ bombitas: String ->
                    Log.i("---------------", bombitas)
                }
        }
    }

    fun example3(){
        viewModelScope.launch {
            suscribeteRepository.counter
                .catch {
                    _uiState.value = MainUIState.Error(it.message.orEmpty())
                }
                .flowOn(Dispatchers.IO)
                .collect{
                _uiState.value = MainUIState.Success(it)
            }
        }
    }

    private fun save(it: String) {

    }

}