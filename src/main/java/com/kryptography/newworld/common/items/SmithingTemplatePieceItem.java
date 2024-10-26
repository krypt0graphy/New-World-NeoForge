package com.kryptography.newworld.common.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class SmithingTemplatePieceItem extends Item {
    public SmithingTemplatePieceItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        pTooltipComponents.add(getDescription().copy().withStyle(ChatFormatting.GRAY));
    }

    @Override
    public Component getDescription() {
        return Component.translatable(this.getDescriptionId() + ".desc");
    }
}
