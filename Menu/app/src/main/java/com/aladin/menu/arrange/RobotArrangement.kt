package com.aladin.menu.arrange

import com.aladin.menu.contract.Arrangement
import com.aladin.menu.contract.RobotAction
import com.aladin.menu.contract.RobotAssert
import com.aladin.menu.contract.RobotDefinition
import com.aladin.menu.contract.RobotScenarioScope
import kotlin.reflect.KClass

class RobotArrangement<DEF: RobotDefinition, ACT: RobotAction, AST: RobotAssert>(
    definition: KClass<DEF>? = null,
    private val action: KClass<ACT>? = null,
    private val assert: KClass<AST>? = null
) : Arrangement {

    private var isStarted = false
    private var scenarioScope: RobotScenarioScope? = null
    private val definitionScope: DEF? = definition?.java?.getDeclaredConstructor()?.newInstance()

    override fun defineScope(scenarioScope: RobotScenarioScope) {
        definitionScope?.loadDefaultConfigs()
        this.scenarioScope = scenarioScope
    }

    fun setup(onDefinition: DEF.() -> Unit) : RobotArrangement<DEF, ACT, AST> {
        definitionScope?.run(onDefinition)
        startIfAllowed()
        return this
    }

    fun action(onAction: ACT.() -> Unit) : RobotArrangement<DEF, ACT, AST> {
        startIfAllowed()
        action?.java?.getDeclaredConstructor()?.newInstance()?.run(onAction)
        return this
    }

    fun assert(onAssert: AST.() -> Unit) : RobotArrangement<DEF, ACT, AST> {
        startIfAllowed()
        assert?.java?.getDeclaredConstructor()?.newInstance()?.run(onAssert)
        return this
    }

    private fun startIfAllowed() {
        if (isStarted.not()) {
            scenarioScope?.start()
            isStarted = true
        }
    }
}