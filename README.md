## WAntiChatPro

> An outdated or modern anti-cheat plugin for Minecraft: Bedrock Edition servers (NukkitX).

> Following description is excerpt from previous purchase page.

- 简介

WACPro，即 WAntiCheatPro。是 WetABQ 用 Kotlin 编写的可用于 NukkiX 平台的反作弊插件。

- 可检测项目
迄今为止，本插件可检测包括加速、飞行、快速挖掘、长臂猿、高跳、Bhop、杀戮光环、穿墙、自瞄等在内的十余种作弊项目。
不仅如此，本插件使用方差算法及后文将提及的 VL 系统很好地解决了误判问题。
WACPro 能稳定运行数日且精准判定以上的事实在实际使用过程中得到了证明，WACPro 提供的配置文件也能够在 PVP、生存、小游戏等各种类型的运用场景中得到充分利用。

- 特殊机制与部分检测原理
  - VL (Violation Level) 值：
    VL值机制，是指能够供反作弊系统进行相关反作弊操作以参考的数值。本机制最初由NCP实现。在实际运行过程中，每当WAC检测到玩家可能有违规行为，便会添加一定的 VL 值；反之，若玩家通过违规检查，VL 值会时间缓慢衰减，大大加强了检测的有效性。
  - 封波 (Ban Wave) 系统：
    封波系统，即当对玩家执行封禁操作时，并不立刻封禁，而是随机等待一段时间后执行，以至于作弊者不知其用了哪种作弊而被封禁。本系统是对 Hypxiel 封禁系统的一个猜测与复现。
  - 动态范围检测：
    该机制即，设置的最高限值不是一个定点，而是一个范围。如果玩家的攻击距离定点在这个动态范围里面则不会判断违规操作。此机制能有效降低误判。
  - 追踪器系统：
    该系统是用于追踪玩家位置和移动数据包值的一个工具。其追踪器每秒 / 每次收包会收集一次数据，追踪器越大则越不容易误判，同样的会降低检测精度，使得部分作弊玩家绕过检测。
  - 基于收发包数量的抗「变速齿轮」检测：
    该机制根据的是每秒客户端和服务端接收和处理的数据包数量是一定的，而使用了「变速齿轮」的玩家数据包发包量会远远超过普通数值，这些玩家每秒发包量一般会超过 40。
  - 防杀戮光环机器人：
    该方法是当有玩家通过举报指令举报疑似作弊者或当 WACPro 检测到时，会生成一个在玩家身旁不断旋转的机器人。若玩家，在该过程中攻击到太多次机器人，那么WAC将会采取相应措施。
  - 假矿系统
