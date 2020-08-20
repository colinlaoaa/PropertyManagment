package com.liao.propertymanagement.model

data class UploadImage(
    val `data`: ImageResponse,
    val error: Boolean,
    val message: String
)

data class ImageResponse(
    val acl: String,
    val bucket: String,
    val contentDisposition: Any,
    val contentType: String,
    val encoding: String,
    val etag: String,
    val fieldname: String,
    val key: String,
    val location: String,
    val metadata: Metadata,
    val mimetype: String,
    val originalname: String,
    val serverSideEncryption: Any,
    val size: Int,
    val storageClass: String
)

data class Metadata(
    val fieldName: String
)