package com.kryptography.newworld.integration;

import com.kryptography.newworld.common.blocks.FirCabinetBlock;
import com.kryptography.newworld.common.blocks.entity.FirCabinetBlockEntity;
import com.kryptography.newworld.init.NWBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class FDIntegration {

    public static  Block cabinetBlock() {
        return new FirCabinetBlock(BlockBehaviour.Properties.copy(Blocks.BARREL));
    }

    public static Supplier<? extends BlockEntityType<?>> cabinetBlockEntity() {
        return () -> BlockEntityType.Builder.of(FirCabinetBlockEntity::new,
                NWBlocks.FIR_CABINET.get()
        ).build(null);
    }

    public static final TagKey<Item> WOODEN_CABINETS = farmersDelightItemTag("cabinets/wooden");

    private static TagKey<Item> farmersDelightItemTag(String path) {
        return ItemTags.create(new ResourceLocation("farmersdelight", path));
    }
}
