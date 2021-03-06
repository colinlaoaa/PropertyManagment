package com.liao.propertymanagement.view_model.todo_list

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.liao.propertymanagement.helper.FirebaseLiveDatabase
import com.liao.propertymanagement.helper.SessionManager
import com.liao.propertymanagement.model.TodoList

class TodoRepository() {
    val todoList: MutableLiveData<List<TodoList>?> by lazy {
        MutableLiveData<List<TodoList>?>()
    }
    var tableName = SessionManager.getLandlordEmail()!!.replace(".", "")

    fun getDataFromFirebase() {

        var list: ArrayList<TodoList> = ArrayList()
        var databaseReference = FirebaseLiveDatabase.getTableByName(tableName)
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                for (data in snapshot.children) {
                    var todoList = data.getValue(TodoList::class.java)
                    list.add(todoList!!)
                }
                todoList.postValue(list)
            }
        })

    }


}
