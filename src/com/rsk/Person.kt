package com.rsk

class Person (var Age: Int) {
    var Name: String = ""

    fun display(){
        println("Display: $Name")
    }

    fun displayWithLambda(func: (s:String) -> Unit){
        func(Name)
    }
}