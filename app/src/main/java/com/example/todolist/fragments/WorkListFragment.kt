package com.example.todolist.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.MainActivity
import com.example.todolist.R
import com.example.todolist.TodoViewModel
import com.example.todolist.adapters.WorkTodoAdapter
import com.example.todolist.databinding.FragmentWorkListBinding

class WorkListFragment : Fragment() {

    private lateinit var binding: FragmentWorkListBinding
    private val todoViewModel: TodoViewModel by lazy {
        ViewModelProvider(requireActivity()).get(TodoViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorkListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
    }

    private fun setRecyclerView() {
        todoViewModel.workTodoItems.observe(viewLifecycleOwner) {
            binding.workRecyclerView.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = WorkTodoAdapter(it, (requireActivity() as MainActivity).listener)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = WorkListFragment()
    }


}
