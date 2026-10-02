package com.serilum.difficultylock.forge.events;

import com.serilum.difficultylock.events.DifficultyLockEvent;
import net.minecraft.client.Minecraft;
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
