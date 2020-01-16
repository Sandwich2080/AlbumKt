package com.example.albumkt.util

import android.app.Activity
import java.util.*

class ActivityStack private constructor() {

    companion object {
        val ins = ActivityStack()
    }

    private var activityStack = Stack<Activity>()

    fun push(activity: Activity) {
        activityStack.push(activity)
    }

    fun pop() {
        val activity = activityStack.pop()
        if (!activity.isFinishing) {
            activity.finish()
        }
    }

    fun popAll(){
        do {
            pop()
        }while (!activityStack.isEmpty())
    }

}