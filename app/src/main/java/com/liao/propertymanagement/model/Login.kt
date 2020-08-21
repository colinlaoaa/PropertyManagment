package com.liao.propertymanagement.model

data class Login(
    val error: Boolean? = true,
    val message: String? = null,
    val token: String,
    val user: User
)

data class User(
    val __v: Int? = null,
    val _id: String? = null,
    val createdAt: String? = null,
    var email: String? = null,
    var name: String? = null,
    var landlordEmail: String? = null,
    var password: String? = null,
    var type: String? = null
)