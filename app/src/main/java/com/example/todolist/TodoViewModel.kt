package com.example.todolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlin.IllegalArgumentException

class TodoViewModel (private val repository: TodoItemRepository) : ViewModel() {

        var todoItems: LiveData<List<TodoItem>> = repository.allTodoItems.asLiveData()

        fun addTodoItem (todoItem: TodoItem) = viewModelScope.launch {
                repository.insertTodoItem(todoItem)
        }

        fun updateTodoItem (todoItem: TodoItem) = viewModelScope.launch {
                repository.updateTodoItem(todoItem)
        }

        fun deleteTodoItem (todoItem: TodoItem) = viewModelScope.launch {
                repository.deleteTodoItem(todoItem)
        }


}

class TodoItemModelFactory (private val repository: TodoItemRepository) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(TodoViewModel::class.java))
                        return TodoViewModel(repository) as T
                throw IllegalArgumentException("Unknown class")
        }
}


//    fun addTodoItem(newTodo: TodoItem) {
//        val list = todoItems.value ?: mutableListOf()
//        list.add(newTodo)
//        todoItems.postValue(list)
//    }
//
//    fun removeTodoItem(position: Int) {
//        val list = todoItems.value
//        list!!.removeAt(position)
//        todoItems.postValue(list)
//    }
//
//    fun editTodoItem(
//        id: UUID,
//        title: String,
//        description: String,
//        targetTime: LocalTime?,
//        expirationDate: LocalDate?
//    ) {
//        val list = todoItems.value
//        val todo = list?.find { it.id == id } ?: return
//        todo.title = title
//        todo.description = description
//        todo.targetTime = targetTime
//        todo.expirationDate = expirationDate
//        todoItems.postValue(list)
//    }

//    fun completeTodo (todoItem: TodoItem){
//        val list = todoItems.value
//        val todo = list!!.find { it.id == todoItem.id }!!
//        todo.expirationDate = LocalDate.now()
//        todoItems.postValue(list)
//
//    }

