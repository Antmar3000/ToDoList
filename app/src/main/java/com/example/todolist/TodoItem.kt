package com.example.todolist

import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID
import kotlin.math.exp

data class TodoItem (

    var title : String,
    var description : String,
    var targetTime : LocalTime?,
    var expirationDate : LocalDate?,
    var id : UUID = UUID.randomUUID()
    ){
}