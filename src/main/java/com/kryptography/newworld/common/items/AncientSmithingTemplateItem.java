package com.kryptography.newworld.common.items;

import com.kryptography.newworld.NewWorld;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SmithingTemplateItem;


import java.util.List;

public class AncientSmithingTemplateItem extends SmithingTemplateItem {
    private static final ChatFormatting TITLE_FORMATTING = ChatFormatting.GRAY;
    private static final ChatFormatting DESCRIPTION_FORMATTING = ChatFormatting.BLUE;


    public AncientSmithingTemplateItem(String name) {
        super(makeAppliesToText(name), makeIngredientText(name), makeTitleText(name), makeBaseSlotDesc(name), makeAdditionalSlotDesc(name), emptyList(), emptyList());
    }

    public static Component makeAdditionalSlotDesc(String name) {
        return Component.translatable(Util.makeDescriptionId("item", NewWorld.id( "smithing_template." + name + ".additional_slot_description")));
    }
    public static Component makeBaseSlotDesc(String name) {
        return Component.translatable(Util.makeDescriptionId("item", NewWorld.id( "smithing_template." + name + ".base_slot_description")));
    }
    public static Component makeIngredientText(String name) {
        return Component.translatable(Util.makeDescriptionId("item", NewWorld.id( "smithing_template." + name + ".ingredients"))).withStyle(DESCRIPTION_FORMATTING);
    }
    public static Component makeAppliesToText(String name) {
        return Component.translatable(Util.makeDescriptionId("item", NewWorld.id( "smithing_template." + name + ".applies_to"))).withStyle(DESCRIPTION_FORMATTING);
    }
    public static Component makeTitleText(String name) {
        return Component.translatable(Util.makeDescriptionId("upgrade", NewWorld.id(name))).withStyle(TITLE_FORMATTING);
    }

    public static List<ResourceLocation> emptyList() {
        return List.of();
    }

}
