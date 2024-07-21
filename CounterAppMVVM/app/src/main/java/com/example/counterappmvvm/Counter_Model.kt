package com.example.counterappmvvm


data class Counter_Model(var counter: Int)

class CounterRepository {
    private val _counter = Counter_Model(0)

    fun getCounter() = _counter

    fun incrementCounter() {
        _counter.counter++
    }

    fun decrementCounter() {
        _counter.counter--
    }

}