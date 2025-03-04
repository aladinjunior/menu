package com.aladin.menu.robot

import com.aladin.menu.arrange.RobotArrangement
import com.aladin.menu.contract.RobotAction
import com.aladin.menu.contract.RobotAssert
import com.aladin.menu.contract.RobotDefinition

inline fun <reified DEF : RobotDefinition, reified ACT : RobotAction, reified AST : RobotAssert> completeDef(): RobotArrangement<DEF, ACT, AST> {
    return RobotArrangement(
        definition = DEF::class,
        action = ACT::class,
        assert = AST::class
    )
}

inline fun <DEF : RobotDefinition, ACT : RobotAction, AST : RobotAssert>
        RobotArrangement<DEF, ACT, AST>.launch(block: RobotArrangement<DEF, ACT, AST>.() -> Unit) {
    apply(block)
}