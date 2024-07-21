package com.example.counterappmvvm

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel

class Counter_ViewModel : ViewModel() {
    private val _repository: CounterRepository = CounterRepository()
    private val _counter = mutableIntStateOf(_repository.getCounter().counter)

    // Expose Counter
    val counter: MutableState<Int> = _counter

    fun getCounter() = _counter

    fun incrementCounter() {
        _repository.incrementCounter()
        _counter.intValue = _repository.getCounter().counter

    }

    fun decrementCounter() {
//        _counter.intValue--
        _repository.decrementCounter()
        _counter.intValue = _repository.getCounter().counter
    }
}