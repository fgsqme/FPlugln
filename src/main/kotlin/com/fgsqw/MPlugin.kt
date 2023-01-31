package com.fgsqw

import net.rwhps.server.data.global.Data
import net.rwhps.server.data.player.Player
import net.rwhps.server.func.StrCons
import net.rwhps.server.game.GameUnitType
import net.rwhps.server.plugin.Plugin
import net.rwhps.server.plugin.event.AbstractEvent
import net.rwhps.server.util.log.Log.clog

class MPlugin : Plugin() {

    override fun init() {
//        clog("ddddddddddddddddddddddd")
//        Data.SERVER_COMMAND.handleMessage("uplist add", StrCons { str -> clog(str) })
    }

    override fun registerEvents(): AbstractEvent {
        return MEvent()
    }
}

/**实验工厂禁用*/
class MEvent : AbstractEvent {
    override fun registerPlayerOperationUnitEvent(
        player: Player,
        gameActions: GameUnitType.GameActions,
        gameUnits: GameUnitType.GameUnits,
        x: Float,
        y: Float
    ): Boolean {
        if (gameActions == GameUnitType.GameActions.BUILD) {
            // 单位过滤开启判断
            when (gameUnits) {
                GameUnitType.GameUnits.experimentalLandFactory -> {
                    if (Data.game.income >= 50) {
                        player.sendSystemMessage("经济倍率太高，已经禁用实验工厂")
                        return false
                    }
                }
                else -> return true
            }
        }
        return true
    }

}