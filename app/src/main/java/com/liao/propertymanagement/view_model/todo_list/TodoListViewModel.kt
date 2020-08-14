package com.liao.propertymanagement.view_model.todo_list

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.liao.propertymanagement.helper.FirebaseLiveDatabase
import com.liao.propertymanagement.helper.SessionManager
import com.liao.propertymanagement.model.TodoList

class TodoListViewModel : ViewModel() {
    private val todoRepository = TodoRepository()
    var tableName = SessionManager.getLandlordEmail()!!.replace(".", "")
    val updateStatus = ObservableField<String>()
    val num = ObservableField<String>()
    val status = ObservableField<String>()
    val message = ObservableField<String>()
    val description = ObservableField<String>()




    fun  todoRepositoryObserver():MutableLiveData<List<TodoList>?>{
      todoRepository.getDataFromFirebase()
        return todoRepository.todoList
    }

    fun onAddNoteButtonClicked() {
        var item = TodoList(num.get(),status.get(),message.get(),description.get())
        FirebaseLiveDatabase.insertTodoList(tableName,item)
    }

    fun removeButtonClicked(item: TodoList) {
        FirebaseLiveDatabase.deleteTodoList(tableName,item)
    }

    fun updateButtonClicked(item: TodoList) {
        item.status = updateStatus.get()
        FirebaseLiveDatabase.updateTodoListStatus(tableName,item)
    }




}