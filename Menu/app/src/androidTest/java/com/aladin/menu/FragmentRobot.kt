package com.aladin.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.FragmentScenario
import com.aladin.menu.contract.Arrangement
import com.aladin.menu.contract.RobotScenarioScope
import org.junit.Test
import kotlin.reflect.KClass

abstract class FragmentRobot : RobotScenarioScope {

    private var fragmentScenario: FragmentScenario<*>? = null

    protected var bundle: Bundle? = null
    protected var themeResId: Int = 0
    protected var fragmentFactory: FragmentFactory? = null

    abstract val fragment: KClass<out Fragment>
    abstract val arrangement: Arrangement


    @Test
    open fun setup() {
        arrangement.defineScope(this)
    }

    final override fun start() {
        fragmentScenario =
            FragmentScenario.launchInContainer(fragment.java, bundle, themeResId, fragmentFactory)
    }

    fun onScenario(scenario: FragmentScenario<*>.() -> Unit) {
        fragmentScenario?.run(scenario)
    }
}