package com.natamus.difficultylock.forge.events;

import com.natamus.difficultylock.events.DifficultyLockEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ForgeDifficultyLockClientEvent {
	@SubscribeEvent
	public static void onClientTick(TickEvent.ClientTickEvent e) {
		if (!e.phase.equals(TickEvent.Phase.END)) {
			return;
		}

		DifficultyLockEvent.onClientTick(Minecraft.getInstance());
	}
}
