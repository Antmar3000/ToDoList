package com.example.todolist

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.databinding.FragmentNewTodoBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.time.LocalDate
import java.time.LocalTime


class NewTodoFragment (var todoItem: TodoItem?) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentNewTodoBinding
    private lateinit var todoViewModel: TodoViewModel
    private var targetTime : LocalTime? = null
    private var expirationDate : LocalDate? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity()
        todoViewModel = ViewModelProvider(activity).get(TodoViewModel::class.java)

        todoItem?.let {
            binding.topTextView.text = getString(R.string.edit_item_name)
            val editableItem = Editable.Factory.getInstance()
            binding.editTitle.text = editableItem.newEditable(todoItem!!.title)
            binding.editDescription.text = editableItem.newEditable(todoItem!!.description)
        } ?: {
            binding.topTextView.text = getString(R.string.new_item_name)
        }

        binding.buttonTime.setOnClickListener {
            openTimePicker()
        }
        binding.buttonDate.setOnClickListener {
            openDatePicker()
        }
        binding.buttonSave.setOnClickListener {
            saveData()
        }

    }
    private fun saveData() {
        val title = binding.editTitle.text.toString()
        val description = binding.editDescription.text.toString()
        if (todoItem == null) {
           val newTodo = TodoItem(title, description, targetTime, expirationDate)
            todoViewModel.addTodoItem(newTodo) }
        else {
            todoViewModel.editTodoItem(todoItem!!.id, title, description, targetTime, expirationDate)
              }
        binding.editTitle.setText("")
        binding.editDescription.setText("")
        dismiss()
    }

    private fun openTimePicker (){
        if (targetTime == null) targetTime = LocalTime.now()
        val listener = TimePickerDialog.OnTimeSetListener{_, selectedHour, selectedMinute ->
            targetTime = LocalTime.of(selectedHour, selectedMinute)
            updateTimeButtonText()
        }
        val dialog = TimePickerDialog(activity, listener, targetTime!!.hour, targetTime!!.minute, true)
        dialog.setTitle(R.string.picker_time_title)
        dialog.show()
    }

    private fun updateTimeButtonText () {
        binding.buttonTime.text = String.format("%02d:%02d", targetTime!!.hour, targetTime!!.minute)
    }

    private fun openDatePicker (){
        if (expirationDate == null) expirationDate = LocalDate.now()
        val listener = DatePickerDialog.OnDateSetListener{_, selectedYear, selectedMonth, selectedDay ->
            expirationDate = LocalDate.of(selectedYear, selectedMonth, selectedDay)
            updateDateButtonText()
        }
        val dialog = DatePickerDialog(requireContext(), listener, expirationDate!!.year, expirationDate!!.monthValue, expirationDate!!.dayOfMonth)
        dialog.setTitle(R.string.picker_date_title)
        dialog.show()
    }

    private fun updateDateButtonText (){
        binding.buttonDate.text = String.format("%02d/%02d/%02d", expirationDate!!.dayOfMonth, expirationDate!!.monthValue, expirationDate!!.year)
    }
}