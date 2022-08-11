package com.cuongngo.hotel_booking.local

import android.content.Context
import android.content.SharedPreferences
import com.cuongngo.hotel_booking.App
import com.google.gson.GsonBuilder

object AppPreferences {

    var preferences: SharedPreferences
    private var editor: SharedPreferences.Editor
    const val REFERENCES_NAME = "AppPreferences"
    const val KEY_USER_ACCESS_TOKEN = "USER_ACCESS_TOKEN"
    const val KEY_NICK_NAME = "key_nick_name"

    init {
        preferences = App.getInstance().getSharedPreferences(REFERENCES_NAME, Context.MODE_PRIVATE)
        editor = preferences.edit()
    }

    fun getUserAccessToken(): String {
        return preferences.getString(KEY_USER_ACCESS_TOKEN, "") ?: ""
    }

    fun setUserAccessToken(token: String) {
        editor.also {
            it.putString(KEY_USER_ACCESS_TOKEN, token)
            it.commit()
        }
    }

    fun getNickName():String{
        return preferences.getString(KEY_NICK_NAME, "") ?: ""
    }
    fun setNickName(nickName: String){
        editor.also {
            it.putString(KEY_NICK_NAME, nickName)
            it.commit()
        }
    }

    /**
     * Saves object into the Preferences.
     *
     * @param `object` Object of model class (of type [T]) to save
     * @param key Key with which Shared preferences to
     **/
    fun putObject(`object`: Any?, key: String) {
        //Convert object to JSON String.
        try {
            val jsonString = GsonBuilder().create().toJson(`object`)
            //Save that String in SharedPreferences
            preferences.edit().putString(key, jsonString).apply()
        } catch (e: Throwable) {
        }
    }

    fun clearPreference(key: String) {
        preferences.edit().remove(key).apply()
    }

    /**
     * Used to retrieve object from the Preferences.
     *
     * @param key Shared Preference key with which object was saved.
     **/
    inline fun <reified T> getObject(key: String): T? {
        //We read JSON String which was saved.
        val value = preferences.getString(key, null)
        //JSON String was found which means object can be read.
        //We convert this JSON String to model object. Parameter "c" (of
        //type Class < T >" is used to cast.
        try {
            return GsonBuilder().create().fromJson(value, T::class.java)
        } catch (e: Throwable) {
            return null
        }
    }
}