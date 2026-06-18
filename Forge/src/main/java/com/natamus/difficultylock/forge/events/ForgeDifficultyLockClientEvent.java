package com.natamus.difficultylock.forge.events;

import com.natamus.difficultylock.events.DifficultyLockEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;

import java.lang.invoke.MethodHandles;

public class ForgeDifficultyLockClientEvent {
	public static void registerEventsInBus() {
		// BusGroup.DEFAULT.register(MethodHandles.lookup(), ForgeDifficultyLockClientEvent.class);

		TickEvent.ClientTickEvent.Post.BUS.addListener(ForgeDifficultyLockClientEvent::onClientTick);
	}

	@SubscribeEvent
	public static void onClientTick(TickEvent.ClientTickEvent.Post e) {
		DifficultyLockEvent.onClientTick(Minecraft.getInstance());
	}
}
