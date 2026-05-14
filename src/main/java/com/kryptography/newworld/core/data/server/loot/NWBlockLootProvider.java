package com.kryptography.newworld.core.data.server.loot;

import com.kryptography.newworld.core.NewWorld;
import com.kryptography.newworld.integration.NMLIntegration;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.BlockFamily.Variant;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.neoforged.neoforge.common.Tags;

import java.util.Set;
import java.util.stream.Collectors;

import static com.kryptography.newworld.core.other.NWBlockFamilies.FIR_PLANKS_FAMILY;
import static com.kryptography.newworld.core.registry.NWBlocks.*;

public class NWBlockLootProvider extends BlockLootSubProvider {
	public NWBlockLootProvider(HolderLookup.Provider registries) {
		super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
	}

	public final float[] FIR_LEAVES_SAPLING_CHANCES = new float[]{1/16F, 1/12F, 1/10F, 1/6F };

	@Override
	protected void generate() {

		this.blockFamily(FIR_PLANKS_FAMILY);
		this.dropSelf(FIR_LOG.get());
		this.dropSelf(FIR_WOOD.get());
		this.dropSelf(STRIPPED_FIR_LOG.get());
		this.dropSelf(STRIPPED_FIR_WOOD.get());
		this.dropSelf(FIR_SIGNS.getFirst().get());
		this.dropSelf(FIR_HANGING_SIGNS.getFirst().get());
		this.dropSelf(FIR_PRESSURE_PLATE.get());
		this.dropSelf(FIR_TRAPDOOR.get());
		this.dropSelf(FIR_BUTTON.get());
		this.dropSelf(FIR_FENCE.get());
		this.dropSelf(FIR_FENCE_GATE.get());
		this.dropSelf(FIR_BOARDS.get());
		this.dropSelf(FIR_LADDER.get());
		this.add(FIR_DOOR.get(), this::createDoorTable);
		this.add(FIR_BEEHIVE.get(), this::createBeeHiveDrop);
		this.add(FIR_CHEST.get(), this::createNameableBlockEntityTable);
		this.add(TRAPPED_FIR_CHEST.get(), this::createNameableBlockEntityTable);
		this.add(FIR_BOOKSHELF.get(), (block) -> createSingleItemTableWithSilkTouch(block, Items.BOOK, ConstantValue.exactly(3.0F)));
		this.dropWhenSilkTouch(CHISELED_FIR_BOOKSHELF.get());

		this.dropSelf(FIR_SAPLING.get());
		this.dropPottedContents(POTTED_FIR_SAPLING.get());

		this.add(FIR_LEAVES.get(), createLeavesDrops(FIR_LEAVES.get(), FIR_SAPLING.get(), FIR_LEAVES_SAPLING_CHANCES));

		this.dropPottedContents(POTTED_POINTED_DRIPSTONE.get());
		this.dropSelf(LOAM.get());
		this.add(LOAM_BRICK_SLAB.get(), this::createSlabItemTable);
		this.dropSelf(LOAM_BRICK_STAIRS.get());
		this.dropSelf(LOAM_BRICK_WALL.get());
		this.dropSelf(LOAM_BRICKS.get());
		this.add(LOAM_SLAB.get(), this::createSlabItemTable);
		this.dropSelf(LOAM_STAIRS.get());
		this.add(LOAM_TILE_SLAB.get(), this::createSlabItemTable);
		this.dropSelf(LOAM_TILE_STAIRS.get());
		this.dropSelf(LOAM_TILE_WALL.get());
		this.dropSelf(LOAM_TILES.get());
		this.dropSelf(LOAM_WALL.get());
		this.add(MOSS_SPROUTS.get(), createShearsOnlyDrop(MOSS_SPROUTS.get()));


		this.dropSelf(TOMBSTONE.get());

		this.add(FIR_BOOKSHELF.get(), createSingleItemTableWithSilkTouch(FIR_BOOKSHELF.get(), Items.BOOK, ConstantValue.exactly(3.0F)));
		this.dropSelf(TRIMMED_FIR_PLANKS.get());
		this.dropSelf(FIR_BOARDS.get());
		this.dropSelf(FIR_LADDER.get());
		this.dropWhenSilkTouch(CHISELED_FIR_BOOKSHELF.get());
		this.add(FIR_BEEHIVE.get(), this::createBeeHiveDrop);
		this.add(FIR_CHEST.get(), this::createNameableBlockEntityTable);
		this.add(TRAPPED_FIR_CHEST.get(), this::createNameableBlockEntityTable);
		this.add(FIR_LEAF_PILE.get(), this::createLeafPileDrops);
		this.add(FIR_CABINET.get(), createNameableBlockEntityTable(FIR_CABINET.get()));
		this.dropSelf(STRIPPED_SPIKED_FIR_PALISADE.get());
		this.dropSelf(STRIPPED_FIR_PALISADE.get());
		this.dropSelf(SPIKED_FIR_PALISADE.get());
		this.dropSelf(FIR_SEAT.get());
		this.dropSelf(FIR_PALISADE.get());
	}


	protected LootTable.Builder createLeafPileDrops(Block block) {
		return createMultifaceBlockDrops(block, MatchTool.toolMatches(ItemPredicate.Builder.item().of(Tags.Items.TOOLS_SHEAR)));
	}

	public void blockFamily(BlockFamily family) {
		this.dropSelf(family.getBaseBlock());

		if (family.getVariants().containsKey(Variant.STAIRS)) {
			this.dropSelf(family.get(Variant.STAIRS));
		}

		if (family.getVariants().containsKey(Variant.SLAB)) {
			this.add(family.get(Variant.SLAB), this::createSlabItemTable);
		}

		if (family.getVariants().containsKey(Variant.WALL)) {
			this.dropSelf(family.get(Variant.WALL));
		}

		if (family.getVariants().containsKey(Variant.CHISELED)) {
			this.dropSelf(family.get(Variant.CHISELED));
		}
	}
	
	@Override
	protected Iterable<Block> getKnownBlocks() {
		return BuiltInRegistries.BLOCK.stream().filter(block -> NewWorld.MOD_ID.equals(BuiltInRegistries.BLOCK.getKey(block).getNamespace())).collect(Collectors.toList());
	}

}