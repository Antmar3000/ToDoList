package com.example.todolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlin.IllegalArgumentException

class TodoViewModel(private val repository: TodoItemRepository) : ViewModel() {

    var todoItems: LiveData<List<TodoItem>> = repository.allTodoItems.asLiveData()

    fun addTodoItem(todoItem: TodoItem) = viewModelScope.launch {
        repository.insertTodoItem(todoItem)
    }

    fun updateTodoItem(todoItem: TodoItem) = viewModelScope.launch {
        repository.updateTodoItem(todoItem)
    }

    fun deleteTodoItem(todoItem: TodoItem) = viewModelScope.launch {
        repository.deleteTodoItem(todoItem)
    }


}

class TodoItemModelFactory(private val repository: TodoItemRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoViewModel::class.java))
            return TodoViewModel(repository) as T
        throw IllegalArgumentException("Unknown class")
    }
}

