package com.natamus.difficultylock.neoforge.events;

import com.natamus.difficultylock.events.DifficultyLockEvent;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;

public class NeoForgeDifficultyLockClientEvent {
	@SubscribeEvent
	public static void onClientTick(ClientTickEvent.Post e) {
		DifficultyLockEvent.onClientTick(Minecraft.getInstance());
	}
}
