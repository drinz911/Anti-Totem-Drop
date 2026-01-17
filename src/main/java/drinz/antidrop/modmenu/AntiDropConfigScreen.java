package drinz.antidrop.modmenu;

import drinz.antidrop.config.TotemDropConfig;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class AntiDropConfigScreen {

    public static Screen create(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.literal("Anti Totem Drop"));

        ConfigCategory general = builder.getOrCreateCategory(Text.literal("General"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        general.addEntry(entryBuilder
                .startBooleanToggle(
                        Text.literal("Enable the Mod"),
                        TotemDropConfig.enableMod
                )
                .setDefaultValue(true)
                .setSaveConsumer(value -> TotemDropConfig.enableMod = value)
                .build()
        );

        return builder.build();
    }
}
