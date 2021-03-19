package com.andriikozakov.buttontoaction.util

import android.content.Context
import android.content.SharedPreferences
import com.andriikozakov.buttontoaction.data.model.Action
import com.andriikozakov.buttontoaction.util.Constants.Companion.PREFERENCE_MAIN
import com.andriikozakov.buttontoaction.util.Constants.Network.Params.Companion.ACTION
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPreferenceHelper(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCE_MAIN, Context.MODE_PRIVATE)
    private val mGson = Gson()

    var action: Action?
        get() {
            val jsonString = sharedPreferences.getString(ACTION, null)
            val type = object : TypeToken<Action>() {}.type
            return mGson.fromJson<Action>(jsonString, type)
        }
        set(value) {
            val jsonString = mGson.toJson(value)
            sharedPreferences.edit().putString(ACTION, jsonString).apply()
        }
}