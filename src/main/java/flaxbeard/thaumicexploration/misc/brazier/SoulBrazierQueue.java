package flaxbeard.thaumicexploration.misc.brazier;

import java.util.UUID;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraft.entity.player.EntityPlayer;
import thaumcraft.common.Thaumcraft;

public class SoulBrazierQueue {	

	@SubscribeEvent
	public void onConnected(PlayerLoggedInEvent event) {
		EntityPlayer aJoiningPlayer = event.player;
		UUID aPlayerUUID = SoulBrazierUtils.getPlayerUUID(aJoiningPlayer);
		if (SoulBrazierUtils.doesPlayerHaveWarpQueued(aPlayerUUID)) {
			SoulBrazierQueueData aQueueDaata = SoulBrazierUtils.getPlayerDataFromWarpQueue(aPlayerUUID);
			String aPlayerName = aJoiningPlayer.getGameProfile().getName();
			int aCurrentWarp = Thaumcraft.proxy.getPlayerKnowledge().getWarpPerm(aPlayerName);
			int aTotalWarp = aCurrentWarp + aQueueDaata.aQueuedWarpToAdd;
			Thaumcraft.proxy.getPlayerKnowledge().setWarpPerm(aPlayerName, aTotalWarp);	
			SoulBrazierUtils.removePlayerDataFromWarpQueue(aPlayerUUID, aQueueDaata.aTileX, aQueueDaata.aTileY, aQueueDaata.aTileZ);			
		}
	}	
	
}
