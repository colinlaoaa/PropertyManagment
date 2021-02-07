package com.liao.propertymanagement.model

data class Register(
    val `data`: RegisterResponse,
    val error: Boolean,
    val message: String
)

data class RegisterResponse(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val email: String,
    val name: String,
    val landlordEmail: String? = null,
    val password: String,
    val type: String
)