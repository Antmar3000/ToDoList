package com.example.todolist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database (entities = [TodoItem::class], version = 1, exportSchema = false)
abstract class TodoItemDatabase : RoomDatabase() {

    abstract fun todoItemDao () : TodoItemDao


    companion object {

        @Volatile
        private var INSTANCE : TodoItemDatabase? = null

        fun getDatabase (context: Context) : TodoItemDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                TodoItemDatabase::class.java, "todo_item_database").build()
                INSTANCE = instance
                instance
            }
        }
    }

}