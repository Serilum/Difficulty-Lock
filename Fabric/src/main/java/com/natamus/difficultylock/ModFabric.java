package com.natamus.difficultylock;

import com.natamus.collective.check.RegisterMod;
import com.natamus.difficultylock.events.DifficultyLockEvent;
import com.natamus.difficultylock.util.Reference;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;

public class ModFabric implements ModInitializer {
	
	@Override
	public void onInitialize() {
		setGlobalConstants();
		ModCommon.init();

		loadEvents();

		RegisterMod.register(Reference.NAME, Reference.MOD_ID, Reference.VERSION, Reference.ACCEPTED_VERSIONS);
	}

	private void loadEvents() {
		ServerWorldEvents.LOAD.register((MinecraftServer server, ServerLevel world) -> {
			DifficultyLockEvent.onWorldLoad(world);
		});
	}

	private static void setGlobalConstants() {

	}
}
