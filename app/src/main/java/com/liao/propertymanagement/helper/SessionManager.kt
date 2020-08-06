package com.liao.propertymanagement.helper

import android.content.Context

class SessionManager(mContext: Context) {
    var sharedPreferences = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    var editor = sharedPreferences.edit()


    companion object {
        const val FILE_NAME = "my_file"
        const val KEY_NAME = "firstName"

    }

    fun register(firstName: String) {
        editor.putString(KEY_NAME, firstName)
        editor.commit()
    }
    fun logout() {
        editor.clear()
        editor.commit()
    }
    fun getUserName(): String?{
        return sharedPreferences.getString(KEY_NAME,"")
    }




}