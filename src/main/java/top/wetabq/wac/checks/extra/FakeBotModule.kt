package top.wetabq.wac.checks.extra

import cn.nukkit.Player
import cn.nukkit.Server
import cn.nukkit.command.CommandSender
import cn.nukkit.command.data.CommandParameter
import cn.nukkit.scheduler.AsyncTask
import cn.nukkit.utils.TextFormat
import top.wetabq.wac.WAntiCheatPro
import top.wetabq.wac.checks.CheckType
import top.wetabq.wac.checks.utils.NoCheckUtils
import top.wetabq.wac.checks.utils.RandomUtils
import top.wetabq.wac.command.CommandArgument
import top.wetabq.wac.command.WSubCommand
import top.wetabq.wac.config.module.DefaultConfig
import top.wetabq.wac.module.group.GroupModule
import top.wetabq.wac.module.group.RegGroupModule

/**
 * WAntiCheatPro
 *
 * @author WetABQ Copyright (c) 2019.07
 * @version 1.0
 */
class FakeBotModule : GroupModule() {

    companion object {
        val reportTime = hashMapOf<String,Long>()
    }

    override fun <T> register(registry: T) {
        super.register(registry)
        if (registry is RegGroupModule) {
            registry.addCommand(
                            object : WSubCommand("wreport"){
                                override fun getDescription(): String {
                                    return "举报作弊（黑客）行为"
                                }

                                override fun getAliases(): Array<String> {
                                    return arrayOf("wdr","wr","wacr","acreport","r")
                                }

                                override fun getArguments(): Array<CommandArgument>? {
                                    return arrayOf(CommandArgument("playerName","rawtext",false))
                                }

                                override fun execute(sender: CommandSender, label: String, args: Array<out String>, df: DefaultConfig): Boolean {
                                    if (args.size == 2) {
                                        val targetPlayer = Server.getInstance().getPlayer(args[1])
                                        if (targetPlayer is Player) {
                                            if (!targetPlayer.name.equals(sender.name)) {
                                                if ((reportTime[targetPlayer.name]
                                                        ?: System.currentTimeMillis()) <= System.currentTimeMillis()
                                                ) {
                                                    reportTime[targetPlayer.name] = System.currentTimeMillis() + 1000*60*2
                                                    sender.sendMessage(WAntiCheatPro.translateMessage("{WACTitle} &a&l感谢您的举报, &r&e任何邪恶终将绳之以法！"))
                                                    registry.addAsyncTask(object : AsyncTask() {
                                                        override fun onRun() {
                                                            Thread.sleep(RandomUtils.r(1000 * 1, 1000 * 15).toLong())
                                                            BotHelper().createBot(targetPlayer)
                                                        }
                                                    })
                                                    NoCheckUtils.setAllNoCheck(targetPlayer,0)
                                                    NoCheckUtils.setNoCheck(CheckType.MOVING_SPEED,targetPlayer,0)
                                                    NoCheckUtils.setNoCheck(CheckType.MOVING_HIGHJUMP,targetPlayer,0)
                                                    NoCheckUtils.setNoCheck(CheckType.MOVING_NOFALL,targetPlayer,0)
                                                } else {
                                                    sender.sendMessage(WAntiCheatPro.translateMessage("{WACTitle} &c&l你已经举报过这名玩家啦."))
                                                }
                                            } else {
                                                sender.sendMessage(WAntiCheatPro.translateMessage("{WACTitle} &c&l赫赫，你不能举报你自己哦"))
                                            }
                                        } else {
                                            sender.sendMessage(WAntiCheatPro.translateMessage("{WACTitle} &c&l该玩家不在线"))
                                        }
                                    } else {
                                        sender.sendMessage("${TextFormat.RED} 用法: /wr <玩家ID>")
                                        return false
                                    }
                                    return true
                                }
                            }
                    )
                    .registerCommands()
                    .setModuleAuthor("WetABQ")
                    .setModuleName("FakeBotModule")
                    .context()
        }
    }

}