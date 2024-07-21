package com.example.counterapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CounterViewModel() : ViewModel() {
    private val repository: CounterRepository = CounterRepository()
    private val _count = mutableStateOf(repository.getCounter().count)

    val count: MutableState<Int> = _count

    fun increment(){
        repository.incrementCounter()
        _count.value = repository.getCounter().count
    }

    fun decrement(){
        repository.decrementCounter()
        _count.value = repository.getCounter().count
    }

}