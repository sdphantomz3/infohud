package com.phantomz3;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.WorldSavePath;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

import static com.phantomz3.ModConfig.*;

public class InfoHudClient implements ClientModInitializer {
	public static final String MOD_ID = "info-hud";

	@Override
	public void onInitializeClient() {
		registerHUD();
	}

	private void registerHUD() {
		HudRenderCallback.EVENT.register((drawContext, tickDeltaManager) -> {
			MinecraftClient client = MinecraftClient.getInstance();
			int fps = client.getCurrentFps();
			int x = (int) client.player.getX();
			int y = (int) client.player.getY();
			int z = (int) client.player.getZ();
			String day = String.valueOf(client.world.getTimeOfDay() / 24000);
			int entityCount = client.world.getRegularEntityCount();

			if (showFPS) {
				drawContext.drawText(client.textRenderer, "FPS: " + fps, 2, 2, 0xFFFFFF, true);
			}

			if (showCordinates) {
				drawContext.drawText(client.textRenderer, "X: " + x + " Y: " + y + " Z: " + z, 2, 12, 0xFFFFFF, true);
			}

			if (showDayCount) {
				drawContext.drawText(client.textRenderer, "Day: " + day, 2, 22, 0xFFFFFF, true);
			}

			if (client.isInSingleplayer()) {
				if (showWorldName) {
					drawContext.drawText(client.textRenderer, client.getServer().getSavePath(WorldSavePath.ROOT).getParent().getFileName().toString(), client.getWindow().getScaledWidth() - 2 - client.textRenderer.getWidth(client.getServer().getSavePath(WorldSavePath.ROOT).getParent().getFileName().toString()), 2, 0xFFFFFF, true);
				}
			} else {
				if (showServerName) {
					drawContext.drawText(client.textRenderer, client.getCurrentServerEntry().address, client.getWindow().getScaledWidth() - 2 - client.textRenderer.getWidth(client.getCurrentServerEntry().address), 2, 0xFFFFFF, true);
				}
			}

			// showing the player vehicle speed on the player action bar
			if (showVehicleSpeed) {
				if (client.player.hasVehicle()) {
					if (client.player.getVehicle() instanceof net.minecraft.entity.vehicle.BoatEntity) {
						net.minecraft.entity.vehicle.BoatEntity boat = (net.minecraft.entity.vehicle.BoatEntity) client.player.getVehicle();
						drawContext.drawText(client.textRenderer, "Speed: " + String.format("%.2f", boat.getVelocity().length() * 20) + " m/s", 2, client.getWindow().getScaledHeight() - 30, 0xFFFFFF, true);
					} else if (client.player.getVehicle() instanceof net.minecraft.entity.vehicle.MinecartEntity) {
						net.minecraft.entity.vehicle.MinecartEntity minecart = (net.minecraft.entity.vehicle.MinecartEntity) client.player.getVehicle();
						drawContext.drawText(client.textRenderer, "Speed: " + String.format("%.2f", minecart.getVelocity().length() * 20) + " m/s", 2, client.getWindow().getScaledHeight() - 30, 0xFFFFFF, true);
					}
				}
			}

			// showing the player elytra speed on the player action bar
			if (showElytraSpeed) {
				if (client.player.isFallFlying()) {
					drawContext.drawText(client.textRenderer, "Speed: " + String.format("%.2f", client.player.getVelocity().length() * 20) + " m/s", 2, client.getWindow().getScaledHeight() - 30, 0xFFFFFF, true);
				}
			}
		});
	}
}