package com.kryptography.newworld.core.registry;

import com.kryptography.newworld.common.blocks.ChiseledFirBookshelfBlock;
import com.kryptography.newworld.common.blocks.MossSproutsBlock;
import com.kryptography.newworld.common.blocks.TombstoneBlock;
import com.kryptography.newworld.common.worldgen.tree.FirTreeGrower;
import com.kryptography.newworld.core.NewWorld;
import com.kryptography.newworld.integration.BBIntegration;
import com.kryptography.newworld.integration.FDIntegration;
import com.kryptography.newworld.integration.Mods;
import com.kryptography.newworld.integration.NMLIntegration;
import com.mojang.datafixers.util.Pair;
import com.teamabnormals.blueprint.common.block.BlueprintBeehiveBlock;
import com.teamabnormals.blueprint.common.block.LeafPileBlock;
import com.teamabnormals.blueprint.common.block.LogBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintChestBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintTrappedChestBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintCeilingHangingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintStandingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintWallHangingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintWallSignBlock;
import com.teamabnormals.blueprint.core.api.BlockSetTypeRegistryHelper;
import com.teamabnormals.blueprint.core.api.WoodTypeRegistryHelper;
import com.teamabnormals.blueprint.core.util.PropertyUtil;
import com.teamabnormals.blueprint.core.util.PropertyUtil.WoodSetProperties;
import com.teamabnormals.blueprint.core.util.item.CreativeModeTabContentsPopulator;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Predicate;

import static com.kryptography.newworld.core.registry.NWBlocks.NWProperties.FIR_SET;
import static com.kryptography.newworld.core.registry.NWBlocks.NWProperties.FIR_WOOD_TYPE;
import static net.minecraft.world.item.CreativeModeTabs.*;
import static net.minecraft.world.item.crafting.Ingredient.of;

public class NWBlocks {

	public static final BlockSubRegistryHelper BLOCKS = NewWorld.REGISTRY_HELPER.getBlockSubHelper();

	public static final DeferredBlock<Block> STRIPPED_FIR_LOG = BLOCKS.createBlock("stripped_fir_log", () -> new RotatedPillarBlock(NWProperties.FIR.log()));
	public static final DeferredBlock<Block> STRIPPED_FIR_WOOD = BLOCKS.createBlock("stripped_fir_wood", () -> new RotatedPillarBlock(NWProperties.FIR.log()));
	public static final DeferredBlock<Block> FIR_LOG = BLOCKS.createBlock("fir_log", () -> new LogBlock(STRIPPED_FIR_LOG, NWProperties.FIR.log()));
	public static final DeferredBlock<Block> FIR_WOOD = BLOCKS.createBlock("fir_wood", () -> new LogBlock(STRIPPED_FIR_WOOD, NWProperties.FIR.log()));
	public static final DeferredBlock<Block> FIR_PLANKS = BLOCKS.createBlock("fir_planks", () -> new Block(NWProperties.FIR.planks()));
	public static final DeferredBlock<StairBlock> FIR_STAIRS = BLOCKS.createBlock("fir_stairs", () -> new StairBlock(FIR_PLANKS.get().defaultBlockState(), NWProperties.FIR.planks()));
	public static final DeferredBlock<SlabBlock> FIR_SLAB = BLOCKS.createBlock("fir_slab", () -> new SlabBlock(NWProperties.FIR.planks()));
	public static final DeferredBlock<DoorBlock> FIR_DOOR = BLOCKS.createBlock("fir_door", () -> new DoorBlock(FIR_SET, NWProperties.FIR.door()));
	public static final DeferredBlock<TrapDoorBlock> FIR_TRAPDOOR = BLOCKS.createBlock("fir_trapdoor", () -> new TrapDoorBlock(FIR_SET, NWProperties.FIR.trapdoor()));
	public static final DeferredBlock<ButtonBlock> FIR_BUTTON = BLOCKS.createBlock("fir_button", () -> new ButtonBlock(FIR_SET, 30, NWProperties.FIR.button()));
	public static final DeferredBlock<PressurePlateBlock> FIR_PRESSURE_PLATE = BLOCKS.createBlock("fir_pressure_plate", () -> new PressurePlateBlock(FIR_SET, NWProperties.FIR.pressurePlate()));
	public static final DeferredBlock<FenceBlock> FIR_FENCE = BLOCKS.createBlock("fir_fence", () -> new FenceBlock(NWProperties.FIR.planks()));
	public static final DeferredBlock<FenceGateBlock> FIR_FENCE_GATE = BLOCKS.createBlock("fir_fence_gate", () -> new FenceGateBlock(FIR_WOOD_TYPE, NWProperties.FIR.planks()));

	public static final DeferredBlock<LeavesBlock> FIR_LEAVES = BLOCKS.createBlock("fir_leaves", () -> new LeavesBlock(NWProperties.FIR.leaves()));

	public static final DeferredBlock<SaplingBlock> FIR_SAPLING = BLOCKS.createBlock("fir_sapling", () -> new SaplingBlock(FirTreeGrower.FIR, NWProperties.FIR.sapling()));
	public static final DeferredBlock<FlowerPotBlock> POTTED_FIR_SAPLING = BLOCKS.createBlockNoItem("potted_fir_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, FIR_SAPLING, PropertyUtil.flowerPot()));

	public static final Pair<DeferredBlock<BlueprintStandingSignBlock>, DeferredBlock<BlueprintWallSignBlock>> FIR_SIGNS =
			BLOCKS.createSignBlock("fir", FIR_WOOD_TYPE, NWProperties.FIR.sign());


	public static final Pair<DeferredBlock<BlueprintCeilingHangingSignBlock>, DeferredBlock<BlueprintWallHangingSignBlock>> FIR_HANGING_SIGNS =
			BLOCKS.createHangingSignBlock("fir", FIR_WOOD_TYPE, NWProperties.FIR.hangingSign());



	public static final DeferredBlock<Block> LOAM = BLOCKS.createBlock("loam", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).strength(1.2F, 3f).sound(SoundType.PACKED_MUD)));
	public static final DeferredBlock<StairBlock> LOAM_STAIRS = BLOCKS.createBlock("loam_stairs", () -> new StairBlock(LOAM.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(LOAM.get())));
	public static final DeferredBlock<SlabBlock> LOAM_SLAB = BLOCKS.createBlock("loam_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(LOAM.get())));
	public static final DeferredBlock<WallBlock> LOAM_WALL = BLOCKS.createBlock("loam_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(LOAM.get())));

	public static final DeferredBlock<Block> LOAM_BRICKS = BLOCKS.createBlock("loam_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(LOAM.get()).sound(SoundType.MUD_BRICKS)));
	public static final DeferredBlock<StairBlock> LOAM_BRICK_STAIRS = BLOCKS.createBlock("loam_brick_stairs", () -> new StairBlock(LOAM_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(LOAM_BRICKS.get())));
	public static final DeferredBlock<SlabBlock> LOAM_BRICK_SLAB = BLOCKS.createBlock("loam_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(LOAM_BRICKS.get())));
	public static final DeferredBlock<WallBlock> LOAM_BRICK_WALL = BLOCKS.createBlock("loam_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(LOAM_BRICKS.get())));

	public static final DeferredBlock<Block> LOAM_TILES = BLOCKS.createBlock("loam_tiles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(LOAM.get()).sound(SoundType.MUD_BRICKS)));
	public static final DeferredBlock<StairBlock> LOAM_TILE_STAIRS = BLOCKS.createBlock("loam_tile_stairs", () -> new StairBlock(LOAM_TILES.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(LOAM_TILES.get())));
	public static final DeferredBlock<SlabBlock> LOAM_TILE_SLAB = BLOCKS.createBlock("loam_tile_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(LOAM_TILES.get())));
	public static final DeferredBlock<WallBlock> LOAM_TILE_WALL = BLOCKS.createBlock("loam_tile_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(LOAM_TILES.get())));

	public static final DeferredBlock<Block> MOSS_SPROUTS = BLOCKS.createBlock("moss_sprouts", () -> new MossSproutsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).offsetType(BlockBehaviour.OffsetType.XZ).sound(SoundType.MOSS_CARPET)));
	public static final DeferredBlock<TombstoneBlock> TOMBSTONE = BLOCKS.createBlockNoItem("tombstone", () -> new TombstoneBlock(BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).strength(0.7F, 1200).pushReaction(PushReaction.IGNORE).isSuffocating(((pState, pLevel, pPos) -> false)).sound(SoundType.POLISHED_DEEPSLATE).noOcclusion()));
	public static final DeferredBlock<FlowerPotBlock> POTTED_POINTED_DRIPSTONE = BLOCKS.createBlockNoItem("potted_pointed_dripstone", () -> new FlowerPotBlock(Blocks.POINTED_DRIPSTONE, BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_ACACIA_SAPLING).noOcclusion()));

	//WW
	public static final DeferredBlock<Block> FIR_LEAF_PILE = BLOCKS.createBlock("fir_leaf_pile", () -> new LeafPileBlock(NWProperties.FIR.leafPile()));
	public static final DeferredBlock<Block> FIR_BOARDS = BLOCKS.createBlock("fir_boards", () -> new RotatedPillarBlock(NWProperties.FIR.planks()));
	public static final DeferredBlock<Block> FIR_BOOKSHELF = BLOCKS.createBlock("fir_bookshelf", () -> new Block(NWProperties.FIR.bookshelf()));
	public static final DeferredBlock<Block> CHISELED_FIR_BOOKSHELF = BLOCKS.createBlock("chiseled_fir_bookshelf", () -> new ChiseledFirBookshelfBlock(NWProperties.FIR.chiseledBookshelf()));
	public static final DeferredBlock<Block> FIR_LADDER = BLOCKS.createBlock("fir_ladder", () -> new LadderBlock(NWProperties.FIR.ladder()));
	public static final DeferredBlock<Block> FIR_BEEHIVE = BLOCKS.createBlock("fir_beehive", () -> new BlueprintBeehiveBlock(NWProperties.FIR.beehive()));
	public static final DeferredBlock<BlueprintChestBlock> FIR_CHEST = BLOCKS.createChestBlock("fir", NWProperties.FIR.chest());
	public static final DeferredBlock<BlueprintTrappedChestBlock> TRAPPED_FIR_CHEST = BLOCKS.createTrappedChestBlock("fir", NWProperties.FIR.chest());

	//NML
	public static final DeferredBlock<Block> TRIMMED_FIR_PLANKS = BLOCKS.createBlock("trimmed_fir_planks", Mods.NOMANSLAND.isLoaded() ? NMLIntegration.TRIMMED_FIR_PLANKS : () -> new Block(NWProperties.FIR.planks()));

	//BB
	public static final DeferredBlock<Block> STRIPPED_SPIKED_FIR_PALISADE = BLOCKS.createBlock("stripped_spiked_fir_palisade", Mods.BLOCKBOX.isLoaded() ? BBIntegration.STRIPPED_SPIKED_FIR_PALISADE : () -> new Block(BlockBehaviour.Properties.of()));
	public static final DeferredBlock<Block> SPIKED_FIR_PALISADE = BLOCKS.createBlock("spiked_fir_palisade", Mods.BLOCKBOX.isLoaded() ? BBIntegration.SPIKED_FIR_PALISADE : () -> new Block(BlockBehaviour.Properties.of()));
	public static final DeferredBlock<Block> STRIPPED_FIR_PALISADE = BLOCKS.createBlock("stripped_fir_palisade", Mods.BLOCKBOX.isLoaded() ? BBIntegration.STRIPPED_FIR_PALISADE : () -> new Block(BlockBehaviour.Properties.of()));
	public static final DeferredBlock<Block> FIR_PALISADE = BLOCKS.createBlock("fir_palisade", Mods.BLOCKBOX.isLoaded() ? BBIntegration.FIR_PALISADE : () -> new Block(BlockBehaviour.Properties.of()));
	public static final DeferredBlock<Block> FIR_SEAT = BLOCKS.createBlock("fir_seat", Mods.BLOCKBOX.isLoaded() ? BBIntegration.FIR_SEAT : () -> new Block(BlockBehaviour.Properties.of()));

	//FD
	public static final DeferredBlock<Block> FIR_CABINET = BLOCKS.createBlock("fir_cabinet", Mods.FARMERSDELIGHT.isLoaded() ? FDIntegration.FIR_CABINET : () -> new Block(BlockBehaviour.Properties.of()));


	public static void setupTabEditors() {
		CreativeModeTabContentsPopulator.mod(NewWorld.MOD_ID)
				.tab(BUILDING_BLOCKS)
				.addItemsBefore(of(Blocks.BIRCH_LOG), FIR_LOG, FIR_WOOD, STRIPPED_FIR_LOG, STRIPPED_FIR_WOOD, FIR_PLANKS, FIR_BOARDS, FIR_STAIRS, FIR_SLAB, FIR_FENCE, FIR_FENCE_GATE, FIR_DOOR, FIR_TRAPDOOR, FIR_PRESSURE_PLATE, FIR_BUTTON)
				.addItemsAfter(modLoaded(FIR_SLAB, "nomansland"), FIR_BOOKSHELF, TRIMMED_FIR_PLANKS)
				.addItemsBefore(modLoaded(Blocks.BIRCH_LOG, "blockbox"), FIR_PALISADE, SPIKED_FIR_PALISADE, STRIPPED_FIR_PALISADE, STRIPPED_SPIKED_FIR_PALISADE)
				.addItemsAfter(modLoaded(FIR_SLAB, "farmersdelight"), FIR_CABINET)
				.addItemsAfter(of(Blocks.MUD_BRICK_WALL), LOAM, LOAM_STAIRS, LOAM_SLAB, LOAM_WALL, LOAM_BRICKS, LOAM_BRICK_STAIRS, LOAM_BRICK_SLAB, LOAM_BRICK_WALL, LOAM_TILES, LOAM_TILE_STAIRS, LOAM_TILE_SLAB, LOAM_TILE_WALL)
				.tab(NATURAL_BLOCKS)
				.addItemsBefore(of(Blocks.BIRCH_LOG), FIR_LOG)
				.addItemsBefore(of(Blocks.BIRCH_LEAVES), FIR_LEAVES)
				.addItemsBefore(modLoaded(Blocks.BIRCH_LEAVES, "woodworks"), FIR_LEAF_PILE)
				.addItemsBefore(of(Blocks.BIRCH_SAPLING), FIR_SAPLING)
				.addItemsAfter(of(Blocks.SHORT_GRASS), MOSS_SPROUTS)
				.addItemsAfter(of(Blocks.MUD), LOAM)
				.tab(FUNCTIONAL_BLOCKS)
				.addItemsBefore(of(Items.BIRCH_SIGN), FIR_SIGNS.getFirst(), FIR_HANGING_SIGNS.getFirst())
				.addItemsBefore(modLoaded(Items.RESPAWN_ANCHOR, "blockbox"), FIR_SEAT)
				.addItemsAfter(of(Items.RESPAWN_ANCHOR), TOMBSTONE)
				.tab(TOOLS_AND_UTILITIES)
				.addItemsBefore(of(Items.BIRCH_BOAT), NWItems.FIR_BOAT.getFirst(), NWItems.FIR_BOAT.getSecond())
				.addItemsBefore(modLoaded(Items.BIRCH_BOAT, "boatload"), NWItems.FIR_FURNACE_BOAT, NWItems.LARGE_FIR_BOAT)
				.addItemsAfter(of(Items.NETHERITE_HOE), NWItems.ANCIENT_MATTOCK)
				.tab(INGREDIENTS)
				.addItemsAfter(of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), NWItems.MATTOCK_CRAFTING_TEMPLATE, NWItems.MATTOCK_CRAFTING_TEMPLATE_HEAD, NWItems.MATTOCK_CRAFTING_TEMPLATE_SHAFT, NWItems.OMINOUS_TOME);

		CreativeModeTabContentsPopulator.mod("woodworks_1")
				.tab(FUNCTIONAL_BLOCKS)
				.addItemsBefore(ofID(ResourceLocation.fromNamespaceAndPath("woodworks", "birch_ladder")), FIR_LADDER)
				.addItemsBefore(ofID(ResourceLocation.fromNamespaceAndPath("woodworks", "birch_bookshelf")), FIR_BOOKSHELF)
				.addItemsBefore(ofID(ResourceLocation.fromNamespaceAndPath("woodworks", "birch_beehive")), FIR_BEEHIVE)
				.addItemsBefore(ofID(ResourceLocation.fromNamespaceAndPath("woodworks", "birch_bookshelf")), CHISELED_FIR_BOOKSHELF)
				.addItemsBefore(ofID(ResourceLocation.fromNamespaceAndPath("woodworks", "birch_chest")), FIR_CHEST)
				.tab(REDSTONE_BLOCKS)
				.addItemsBefore(ofID(ResourceLocation.fromNamespaceAndPath("woodworks", "trapped_birch_chest")), TRAPPED_FIR_CHEST);
	}

	public static Predicate<ItemStack> modLoaded(ItemLike item, String... modids) {
		return stack -> of(item).test(stack) && BlockSubRegistryHelper.areModsLoaded(modids);
	}

	public static Predicate<ItemStack> modLoadedAny(ItemLike item, String... modids) {
		return stack -> {
			if (!of(item).test(stack)) return false;
			for (String modid : modids) {
				if (ModList.get().isLoaded(modid)) return true;
			}
			return false;
		};
	}

	public static Predicate<ItemStack> ofID(ResourceLocation location) {
		return stack -> BuiltInRegistries.ITEM.getOptional(location).map(item -> of(item).test(stack)).orElse(false);
	}

	public static final class NWProperties {
		public static final BlockSetType FIR_SET = BlockSetTypeRegistryHelper.register(new BlockSetType(NewWorld.MOD_ID + ":fir"));
		public static final WoodType FIR_WOOD_TYPE = WoodTypeRegistryHelper.registerWoodType(new WoodType(NewWorld.MOD_ID + ":fir", FIR_SET));

		public static final WoodSetProperties FIR = WoodSetProperties.builder(MapColor.DEEPSLATE, MapColor.WOOD).leavesSound(SoundType.AZALEA_LEAVES).build();
	}
}