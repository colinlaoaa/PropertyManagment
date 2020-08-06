package com.liao.propertymanagement.viewModel.todoList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.liao.propertymanagement.helper.RoomDatabase
import com.liao.propertymanagement.model.TodoList

class TodoListViewModel : ViewModel() {
    private val todoRepository = TodoRepository()
    private val tableName: String = "AddNotes"
    fun  todoRepositoryObserver():MutableLiveData<List<TodoList>?>{
      todoRepository.getDataFromFirebase()
        return todoRepository.todoList
    }

    fun onAddNoteButtonClicked() {

    }

    fun removeButtonClicked(item: TodoList) {
        println("11111111111111111")
        RoomDatabase.deleteTodoList(tableName,item)
    }

    fun updataButtonClicked() {

    }

}