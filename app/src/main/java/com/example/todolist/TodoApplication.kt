package com.example.todolist

import android.app.Application
import com.example.todolist.room.TodoItemDatabase
import com.example.todolist.room.TodoItemRepository

class TodoApplication : Application () {
    private val database by lazy { TodoItemDatabase.getDatabase(this)}
    val repository by lazy { TodoItemRepository(database.todoItemDao()) }
}