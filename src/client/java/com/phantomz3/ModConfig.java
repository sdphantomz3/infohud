package com.phantomz3;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ModConfig {
	public static boolean showFPS = true;
	public static boolean showCordinates = true;
	public static boolean showDayCount = true;
	public static boolean showServerName = true;
	public static boolean showWorldName = true;
	public static boolean showVehicleSpeed = true;
	public static boolean showElytraSpeed = true;

    public static Screen createConfigScreen(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
            .setParentScreen(parent)
            .setTitle(Text.of("InfoHud Config"));

        ConfigCategory general = builder.getOrCreateCategory(Text.of("General"));

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        general.addEntry(entryBuilder.startBooleanToggle(Text.of("Show FPS"), showFPS)
            .setDefaultValue(true)
            .setSaveConsumer(newValue -> showFPS = newValue)
            .build());

        general.addEntry(entryBuilder.startBooleanToggle(Text.of("Show Cordinates"), showCordinates)
            .setDefaultValue(true)
            .setSaveConsumer(newValue -> showCordinates = newValue)
            .build());

        general.addEntry(entryBuilder.startBooleanToggle(Text.of("Show Day Count"), showDayCount)
            .setDefaultValue(true)
            .setSaveConsumer(newValue -> showDayCount = newValue)
            .build());

        general.addEntry(entryBuilder.startBooleanToggle(Text.of("Show Server Name"), showServerName)
            .setDefaultValue(true)
            .setSaveConsumer(newValue -> showServerName = newValue)
            .build());

        general.addEntry(entryBuilder.startBooleanToggle(Text.of("Show World Name"), showWorldName)
            .setDefaultValue(true)
            .setSaveConsumer(newValue -> showWorldName = newValue)
            .build());

        general.addEntry(entryBuilder.startBooleanToggle(Text.of("Show Vehicle Speed"), showVehicleSpeed)
            .setDefaultValue(true)
            .setSaveConsumer(newValue -> showVehicleSpeed = newValue)
            .build());

        general.addEntry(entryBuilder.startBooleanToggle(Text.of("Show Elytra Speed"), showElytraSpeed)
            .setDefaultValue(true)
            .setSaveConsumer(newValue -> showElytraSpeed = newValue)
            .build());

        return builder.build();
    }
}
