package com.example.todolist.room

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todolist.TodoItem

@Database (entities = [TodoItem::class], version = 2, autoMigrations = [AutoMigration(from = 1, to = 2)], exportSchema = true)
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