package com.liao.propertymanagement.view_model.user_profile

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.liao.propertymanagement.helper.SessionManager

class UserProfileViewModel : ViewModel() {
    val facebook = ObservableField<String?>()
    val linkedIn = ObservableField<String?>()
    val github = ObservableField<String?>()
    val twitter = ObservableField<String?>()
    val description = ObservableField<String?>()

    fun getUserEmail(): String? {
        return SessionManager.getEmail()
    }

    fun getUserName(): String? {
        return SessionManager.getName()
    }

    fun getUserType(): String? {
        return SessionManager.getUserType()
    }

    fun getfb(): String? {
        return SessionManager.getfb()
    }

    fun getTwitter(): String? {
        return SessionManager.getTwitter()
    }

    fun getGit(): String? {
        return SessionManager.getGit()
    }

    fun getLinkedIn(): String? {
        return SessionManager.getLinkedIn()
    }
    fun getDes(): String? {
        return SessionManager.getDes()
    }

    fun confirmButtonClicked(){
        var fb = facebook.get()
        var linkedIn = linkedIn.get()
        var github = github.get()
        var tw = twitter.get()
        var des = description.get()
        SessionManager.saveInfoProfile(fb,linkedIn,tw,github,des)
    }

    fun setContent() {
        facebook.set(SessionManager.getfb())
        linkedIn.set(SessionManager.getLinkedIn())
        github.set(SessionManager.getGit())
        twitter.set(SessionManager.getTwitter())
        description.set(SessionManager.getDes())
    }
}