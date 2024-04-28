package com.natamus.difficultylock.neoforge.events;

import com.natamus.difficultylock.events.DifficultyLockEvent;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.TickEvent;

@EventBusSubscriber(value = Dist.CLIENT)
public class NeoForgeDifficultyLockClientEvent {
	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent e) {
		if (!e.phase.equals(TickEvent.Phase.END)) {
			return;
		}

		DifficultyLockEvent.onClientTick(Minecraft.getInstance());
	}
}
