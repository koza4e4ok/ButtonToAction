package com.andriikozakov.buttontoaction.data.model

import com.google.gson.annotations.SerializedName

data class Action(
    @SerializedName("type")
    var type: String? = null,
    @SerializedName("enabled")
    var enabled: Boolean = false,
    @SerializedName("priority")
    var priority: Int = 0,
    @SerializedName("valid_days")
    var validDays: List<Int>? = null,
    @SerializedName("cool_down")
    var coolDown: Long = 0,
    var lastActionCoolDown: Long = 0
)