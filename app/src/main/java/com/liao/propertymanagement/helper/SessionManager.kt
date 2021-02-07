package com.liao.propertymanagement.helper

import android.content.Context
import android.content.SharedPreferences

object SessionManager {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor


    private const val FILE_NAME = "my_file"
    private const val KEY_TOKEN = "token"
    private const val KEY_EMAIL = "email"
    private const val KEY_USER_ID = "userid"
    private const val KEY_USER_TYPE = "usertype"
    private const val KEY_USER_NAME = "name"
    private const val KEY_LANDLORD_EMAIL = "landlord_email"

    private const val KEY_FB = "face_book"
    private const val KEY_TWITTER = "twitter"
    private const val KEY_LINKED_IN = "linkedIn"
    private const val KEY_GIT_HUB = "github"
    private const val KEY_DES = "description"
    private const val KEY_PROFILE_IMAGE_LINK = "profile_image_link"


    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

    }

    fun login(
        token: String?,
        email: String?,
        userId: String?,
        userType: String?,
        userName: String?,
        landlordEmail: String? = null
    ) {
        editor.putString(KEY_TOKEN, token)
        editor.putString(KEY_EMAIL, email)
        editor.putString(KEY_USER_ID, userId)
        editor.putString(KEY_USER_TYPE, userType)
        editor.putString(KEY_USER_NAME, userName)
        editor.putString(KEY_LANDLORD_EMAIL, landlordEmail)
        editor.commit()
    }

    fun saveProfileImage(link:String){
        editor.putString(KEY_PROFILE_IMAGE_LINK,link)
        editor.commit()
    }

    fun readProfileImage():String?{
        return sharedPreferences.getString(KEY_PROFILE_IMAGE_LINK,"")
    }

    fun checkLogin(): Boolean {
        return sharedPreferences.getString(KEY_TOKEN, "") != ""
    }

    fun logout() {
        for (key in sharedPreferences.all) {
            if (key.key != KEY_EMAIL) {
                editor.remove(key.key).commit()
            }
        }
    }

    fun getLandlordEmail(): String? {
        if (sharedPreferences.getString(KEY_LANDLORD_EMAIL, "") == "") {
            return getEmail()
        }
        return sharedPreferences.getString(KEY_LANDLORD_EMAIL, "")
    }

     fun getEmail(): String? {
        return sharedPreferences.getString(KEY_EMAIL, "")
    }

    fun isLandlord(): Boolean {
        return sharedPreferences.getString(KEY_LANDLORD_EMAIL, "") == ""
    }

    fun getName(): String? {
        return sharedPreferences.getString(KEY_USER_NAME, "GUEST")
    }

    fun getUserId(): String? {
        return sharedPreferences.getString(KEY_USER_ID, "")
    }

    fun getUserType(): String? {
        return sharedPreferences.getString(KEY_USER_TYPE, "")
    }


    fun saveInfoProfile(
        fb: String?,
        linkedin: String?=null,
        twitter: String?=null,
        github: String?=null,
        des: String?=null
    ){
        editor.putString(KEY_FB, fb)
        editor.putString(KEY_LINKED_IN, linkedin)
        editor.putString(KEY_TWITTER, twitter)
        editor.putString(KEY_GIT_HUB, github)
        editor.putString(KEY_DES, des)
        editor.commit()
    }

    fun getfb(): String? {
        return sharedPreferences.getString(KEY_FB, "")
    }

    fun getTwitter(): String? {
        return sharedPreferences.getString(KEY_TWITTER, "")
    }

    fun getGit(): String? {
        return sharedPreferences.getString(KEY_GIT_HUB, "")
    }

    fun getLinkedIn(): String? {
        return sharedPreferences.getString(KEY_LINKED_IN, "")
    }
    fun getDes(): String? {
        return sharedPreferences.getString(KEY_DES, "")
    }

}