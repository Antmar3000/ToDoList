package com.example.todolist

import java.time.LocalDate
import java.time.LocalTime

object Constances {
    val DEFAULT_TIME: LocalTime = LocalTime.of(0, 0, 30)
    val DEFAULT_DATE: LocalDate = LocalDate.of(1970, 1, 1)
    const val NEW_TODO_ITEM_TAG = "newTodoItemTag"
    const val EDIT_TODO_ITEM_TAG = "editTodoItemTag"
}