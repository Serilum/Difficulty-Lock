package com.natamus.difficultylock.forge.events;

import com.natamus.difficultylock.data.Constants;
import com.natamus.difficultylock.events.DifficultyLockEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(value = Dist.CLIENT)
public class ForgeDifficultyLockClientEvent {
	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent e) {
		if (!e.phase.equals(TickEvent.Phase.END)) {
			return;
		}

		DifficultyLockEvent.onClientTick(Constants.mc);
	}
}
