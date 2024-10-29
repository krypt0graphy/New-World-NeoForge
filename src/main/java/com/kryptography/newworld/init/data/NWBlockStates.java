package com.kryptography.newworld.init.data;

import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.init.NWBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class NWBlockStates extends BlockStateProvider {
    public NWBlockStates(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, NewWorld.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        this.blockWithItem(NWBlocks.FIR_PLANKS);
        this.blockWithItem(NWBlocks.FIR_LEAVES);
        this.logBlock(NWBlocks.FIR_LOG.get());
        this.blockItem(NWBlocks.FIR_LOG);
        this.logBlock(NWBlocks.STRIPPED_FIR_LOG.get());
        this.blockItem(NWBlocks.STRIPPED_FIR_LOG);
        this.axisBlock(NWBlocks.FIR_WOOD.get(), blockTexture(NWBlocks.FIR_LOG.get()), blockTexture(NWBlocks.FIR_LOG.get()));
        this.blockItem(NWBlocks.FIR_WOOD);
        this.axisBlock(NWBlocks.STRIPPED_FIR_WOOD.get(), blockTexture(NWBlocks.STRIPPED_FIR_LOG.get()), blockTexture(NWBlocks.STRIPPED_FIR_LOG.get()));
        this.blockItem(NWBlocks.STRIPPED_FIR_WOOD);

        this.stairsBlock(NWBlocks.FIR_STAIRS.get(), blockTexture(NWBlocks.FIR_PLANKS.get()));
        this.slabBlock(NWBlocks.FIR_SLAB.get(), blockTexture(NWBlocks.FIR_PLANKS.get()), blockTexture(NWBlocks.FIR_PLANKS.get()));
        this.fenceBlock(NWBlocks.FIR_FENCE.get(), blockTexture(NWBlocks.FIR_PLANKS.get()));
        this.fenceGateBlock(NWBlocks.FIR_FENCE_GATE.get(), blockTexture(NWBlocks.FIR_PLANKS.get()));
        this.buttonBlock(NWBlocks.FIR_BUTTON.get(), blockTexture(NWBlocks.FIR_PLANKS.get()));
        this.pressurePlateBlock(NWBlocks.FIR_PRESSURE_PLATE.get(), blockTexture(NWBlocks.FIR_PLANKS.get()));

        this.doorBlock(NWBlocks.FIR_DOOR.get(), modLoc("block/fir_door_bottom"), modLoc("block/fir_door_top"));
        this.trapdoorBlock(NWBlocks.FIR_TRAPDOOR.get(), modLoc("block/fir_trapdoor"), true);

        this.simpleBlock(NWBlocks.FIR_SAPLING.get(), models().cross(NWBlocks.FIR_SAPLING.getRegisteredName(), blockTexture(NWBlocks.FIR_SAPLING.get())).renderType("cutout"));
        this.simpleBlockWithItem(NWBlocks.POTTED_FIR_SAPLING.get(), models().singleTexture("potted_fir_sapling", ResourceLocation.fromNamespaceAndPath("minecraft", "block/flower_pot_cross"), "plant", blockTexture(NWBlocks.FIR_SAPLING.get())).renderType("cutout"));

        this.simpleBlockWithItem(NWBlocks.POTTED_POINTED_DRIPSTONE.get(), models().singleTexture("potted_pointed_dripstone", ResourceLocation.fromNamespaceAndPath("minecraft", "block/flower_pot_cross"), "plant", blockTexture(Blocks.POINTED_DRIPSTONE).withSuffix("_up_tip")).renderType("cutout"));

        this.signBlock((StandingSignBlock) NWBlocks.FIR_SIGN.get(), ((WallSignBlock) NWBlocks.FIR_WALL_SIGN.get()), blockTexture(NWBlocks.FIR_PLANKS.get()));
        this.hangingSignBlock(NWBlocks.FIR_HANGING_SIGN.get(), NWBlocks.FIR_WALL_HANGING_SIGN.get(), blockTexture(NWBlocks.STRIPPED_FIR_LOG.get()));

        blockItem(NWBlocks.FIR_STAIRS);
        blockItem(NWBlocks.FIR_SLAB);
        blockItem(NWBlocks.FIR_FENCE_GATE);
        blockItem(NWBlocks.FIR_PRESSURE_PLATE);
        blockItem(NWBlocks.FIR_TRAPDOOR, "_bottom");


        this.blockWithItem(NWBlocks.LOAM);
        this.blockWithItem(NWBlocks.LOAM_BRICKS);
        this.blockWithItem(NWBlocks.LOAM_TILES);
        this.stairsBlock(NWBlocks.LOAM_STAIRS.get(), blockTexture(NWBlocks.LOAM.get()));
        this.stairsBlock(NWBlocks.LOAM_BRICK_STAIRS.get(), blockTexture(NWBlocks.LOAM_BRICKS.get()));
        this.stairsBlock(NWBlocks.LOAM_TILE_STAIRS.get(), blockTexture(NWBlocks.LOAM_TILES.get()));
        this.slabBlock(NWBlocks.LOAM_SLAB.get(), blockTexture(NWBlocks.LOAM.get()), blockTexture(NWBlocks.LOAM.get()));
        this.slabBlock(NWBlocks.LOAM_BRICK_SLAB.get(), blockTexture(NWBlocks.LOAM_BRICKS.get()), blockTexture(NWBlocks.LOAM_BRICKS.get()));
        this.slabBlock(NWBlocks.LOAM_TILE_SLAB.get(), blockTexture(NWBlocks.LOAM_TILES.get()), blockTexture(NWBlocks.LOAM_TILES.get()));
        this.wallBlock(NWBlocks.LOAM_WALL.get(), blockTexture(NWBlocks.LOAM.get()));
        this.wallBlock(NWBlocks.LOAM_BRICK_WALL.get(), blockTexture(NWBlocks.LOAM_BRICKS.get()));
        this.wallBlock(NWBlocks.LOAM_TILE_WALL.get(), blockTexture(NWBlocks.LOAM_TILES.get()));

        blockItem(NWBlocks.LOAM_STAIRS);
        blockItem(NWBlocks.LOAM_BRICK_STAIRS);
        blockItem(NWBlocks.LOAM_TILE_STAIRS);
        blockItem(NWBlocks.LOAM_SLAB);
        blockItem(NWBlocks.LOAM_BRICK_SLAB);
        blockItem(NWBlocks.LOAM_TILE_SLAB);


        mossSproutBlock(NWBlocks.MOSS_SPROUTS.get(), true);


    }


    private void blockWithItem(DeferredBlock<?> block) {
        simpleBlockWithItem(block.get(), cubeAll(block.get()));
    }

    private void blockItem(DeferredBlock<?> block) {
        this.simpleBlockItem(block.get(), new ModelFile.UncheckedModelFile("newworld:block/" + block.getId().getPath()));
    }

    private void blockItem(DeferredBlock<?> block, String appendix) {
        this.simpleBlockItem(block.get(), new ModelFile.UncheckedModelFile("newworld:block/" + block.getId().getPath() + appendix));
    }

    private void mossSproutBlock (Block moss, boolean tint) {
        this.simpleBlock(NWBlocks.MOSS_SPROUTS.get(), models().withExistingParent(NWBlocks.MOSS_SPROUTS.getRegisteredName(), "block/tinted_cross").renderType("cutout").texture("cross", blockTexture(NWBlocks.MOSS_SPROUTS.get())));

    }

    private void hangingSignBlock(Block signBlock, Block wallSignBlock, ResourceLocation texture) {
        ModelFile sign = models().sign(name(signBlock), texture);
        hangingSignBlock(signBlock, wallSignBlock, sign);
    }

    private void hangingSignBlock(Block signBlock, Block wallSignBlock, ModelFile sign) {
        simpleBlock(signBlock, sign);
        simpleBlock(wallSignBlock, sign);
    }

    private String name(Block block) {
        return key(block).getPath();
    }

    private ResourceLocation key(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }




}

