package com.natamus.difficultylock;

import com.natamus.difficultylock.events.DifficultyLockEvent;
import net.fabricmc.api.ClientModInitializer;
import com.natamus.difficultylock.util.Reference;
import com.natamus.collective.check.ShouldLoadCheck;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;

public class ModFabricClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() { 
		if (!ShouldLoadCheck.shouldLoad(Reference.MOD_ID)) {
			return;
		}

		registerEvents();
	}
	
	private void registerEvents() {
		ClientTickEvents.END_CLIENT_TICK.register((Minecraft mc) -> {
			DifficultyLockEvent.onClientTick(mc);
		});
	}
}
