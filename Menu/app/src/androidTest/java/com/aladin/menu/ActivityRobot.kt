package com.aladin.menu

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.FragmentScenario
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import com.aladin.menu.contract.Arrangement
import com.aladin.menu.contract.RobotScenarioScope
import org.junit.Test
import org.junit.runner.manipulation.Ordering.Context
import kotlin.reflect.KClass

abstract class ActivityRobot : RobotScenarioScope {

    abstract val activity: KClass<out Activity>
    abstract val arrangement: Arrangement

    private var activityScenario: ActivityScenario<*>? = null

    private val context =  ApplicationProvider.getApplicationContext<android.content.Context>()

    private lateinit var intent: Intent

    @Test
    open fun setup() {
        intent = Intent(context, activity.java)
        arrangement.defineScope(this)
    }

    final override fun start() {
        activityScenario =
            ActivityScenario.launch<Activity>(intent)
    }

    fun onIntent(scope: Intent.() -> Unit) {
        intent.apply(scope)
    }

    fun onScenario(scenario: ActivityScenario<*>.() -> Unit) {
        activityScenario?.run(scenario)
    }
}