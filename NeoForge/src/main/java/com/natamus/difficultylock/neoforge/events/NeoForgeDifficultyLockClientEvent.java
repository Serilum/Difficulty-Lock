package com.natamus.difficultylock.neoforge.events;

import com.natamus.difficultylock.events.DifficultyLockEvent;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;

@EventBusSubscriber(value = Dist.CLIENT)
public class NeoForgeDifficultyLockClientEvent {
	@SubscribeEvent
	public void onClientTick(ClientTickEvent.Post e) {
		DifficultyLockEvent.onClientTick(Minecraft.getInstance());
	}
}
