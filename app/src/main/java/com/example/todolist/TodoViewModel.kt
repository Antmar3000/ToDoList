package com.example.todolist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

class TodoViewModel : ViewModel() {

    var todoItems = MutableLiveData<MutableList<TodoItem>>()

    init {
        todoItems.value = mutableListOf()
    }

    fun addTodoItem (newTodo: TodoItem) {
        val list = todoItems.value
        list!!.add(newTodo)
        todoItems.postValue(list)
    }

    fun removeTodoItem (position : Int) {
        val list = todoItems.value
        list!!.removeAt(position)
        todoItems.postValue(list)
    }

    fun editTodoItem (id : UUID, title : String, description : String, targetTime : LocalTime?, expirationDate : LocalDate?){
        val list = todoItems.value
        val todo = list!!.find { it.id == id }!!
        todo.title = title
        todo.description = description
        todo.targetTime = targetTime
        todo.expirationDate = expirationDate
        todoItems.postValue(list)
    }

//    fun completeTodo (todoItem: TodoItem){
//        val list = todoItems.value
//        val todo = list!!.find { it.id == todoItem.id }!!
//        todo.expirationDate = LocalDate.now()
//        todoItems.postValue(list)
//
//    }

}