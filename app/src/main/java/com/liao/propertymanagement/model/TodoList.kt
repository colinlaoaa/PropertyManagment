package com.liao.propertymanagement.model

import java.io.Serializable

data class TodoList(
    var num: String? = null,
    var status: String? = null,
    var message: String? = null,
    var description: String? = null
) :Serializable{

}