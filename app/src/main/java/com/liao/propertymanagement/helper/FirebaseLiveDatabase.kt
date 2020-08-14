package com.liao.propertymanagement.helper

import com.google.firebase.database.*
import com.liao.propertymanagement.model.TodoList

class FirebaseLiveDatabase() {
    companion object {
        fun getTableByName(tableName: String): DatabaseReference {
            return FirebaseDatabase.getInstance().getReference(tableName)
        }

        fun insertTodoList(tableName: String, todoList: TodoList) {
            var databaseReference = getTableByName(tableName)
            databaseReference.child(todoList.num!!).setValue(todoList)
        }

        fun updateTodoListStatus(tableName: String, todoList: TodoList) {
            var databaseReference = getTableByName(tableName)
            databaseReference.child(todoList.num!!).setValue(todoList)
        }

        fun deleteTodoList(tableName: String, todoList: TodoList) {
            var databaseReference = getTableByName(tableName)
            databaseReference.child(todoList.num!!).removeValue()
        }

    }
}