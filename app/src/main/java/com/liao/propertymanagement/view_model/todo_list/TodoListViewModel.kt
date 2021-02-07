package com.liao.propertymanagement.view_model.todo_list

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.liao.propertymanagement.helper.FirebaseLiveDatabase
import com.liao.propertymanagement.helper.SessionManager
import com.liao.propertymanagement.model.TodoList

class TodoListViewModel : ViewModel() {
    val closeFragment: MutableLiveData<Boolean?> by lazy {
        MutableLiveData<Boolean?>()
    }

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

    fun closeFragmentObserver():MutableLiveData<Boolean?>{
        return closeFragment
    }

    fun onAddNoteButtonClicked() {
        if(num.get() == null){num.set("default")}
        var item = TodoList(num.get(),status.get(),message.get(),description.get())
        FirebaseLiveDatabase.insertTodoList(tableName,item)
        clearAddinfo()
        closeFragment.postValue(true)
    }

    fun clearAddinfo(){
        num.set("")
        status.set("")
        message.set("")
        description.set("")
    }

    fun removeButtonClicked(item: TodoList) {
        FirebaseLiveDatabase.deleteTodoList(tableName,item)
    }

    fun updateButtonClicked(item: TodoList) {
        item.status = updateStatus.get()
        FirebaseLiveDatabase.updateTodoListStatus(tableName,item)
        closeFragment.postValue(true)
        updateStatus.set("")
    }

    fun clearUpdateStatus() {
        updateStatus.set("")
    }


}