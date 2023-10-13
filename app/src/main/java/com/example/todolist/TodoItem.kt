package com.example.todolist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Entity(tableName = "todo_item_table")
data class TodoItem(
    @ColumnInfo("title") val title: String,
    @ColumnInfo("description")val description: String,
    @ColumnInfo("targetTimeString")val targetTimeString: String?,
    @ColumnInfo("expirationDate")val expirationDateString: String?,
    @ColumnInfo("isChecked") val isChecked : Boolean,
    @ColumnInfo("type", defaultValue = "0") val type : Int = 0,
    @PrimaryKey(true)val id: Int = 0
) {

    fun targetTime(): LocalTime? = if (targetTimeString == null) null else LocalTime.parse(targetTimeString, timeFormatter)
    fun expirationDate() : LocalDate? = if (expirationDateString == null) null else LocalDate.parse(expirationDateString, dateFormatter)

    companion object {
        val timeFormatter : DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
        val dateFormatter : DateTimeFormatter = DateTimeFormatter.ISO_DATE
    }
}
