package com.liao.propertymanagement.model

data class PropertiesGet(
    val count: Int,
    val `data`: List<Properties>,
    val error: Boolean
)

