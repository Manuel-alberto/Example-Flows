package com.neluam.flowsexample.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SuscribeteRepository {

    val counter: Flow<Int> = flow{
        delay(4000)
        var bombitas = 1
        while (true) {
            bombitas += 1
            emit(bombitas)
            delay(1000)
        }
    }

}