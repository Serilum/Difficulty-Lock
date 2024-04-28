package com.natamus.difficultylock;

import com.natamus.difficultylock.events.DifficultyLockEvent;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;

public class ModFabricClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() { 
		registerEvents();
	}
	
	private void registerEvents() {
		ClientTickEvents.END_CLIENT_TICK.register((Minecraft mc) -> {
			DifficultyLockEvent.onClientTick(mc);
		});
	}
}
