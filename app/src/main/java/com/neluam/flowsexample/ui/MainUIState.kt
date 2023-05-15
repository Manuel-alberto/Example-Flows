package com.neluam.flowsexample.ui

sealed class MainUIState{
    object Loading : MainUIState()
    data class Success(val numSubscribers: Int) : MainUIState()
    data class Error(val msg: String) : MainUIState()
}
