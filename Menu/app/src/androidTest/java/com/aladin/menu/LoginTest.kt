package com.aladin.menu

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.aladin.menu.login.ui.LoginActivity
import com.aladin.menu.robot.LoginAct
import com.aladin.menu.robot.LoginAst
import com.aladin.menu.robot.LoginDefinition
import com.aladin.menu.robot.completeDef
import com.aladin.menu.robot.launch
import org.junit.After
import org.junit.Before
import org.junit.Test

class LoginTest : ActivityRobot() {
    override val activity =  LoginActivity::class
    override val arrangement =
        completeDef<LoginDefinition, LoginAct, LoginAst>()


    @Before
    override fun setup(){
        super.setup()
        Intents.init()
    }

    @After
    fun tearDown(){
     Intents.release()
    }

    @Test
    fun test() = arrangement.launch {
        setup {
            super.setup()
        }
        assert {
            onView(withId(R.id.container)).check(matches(isDisplayed()))
        }
    }

}