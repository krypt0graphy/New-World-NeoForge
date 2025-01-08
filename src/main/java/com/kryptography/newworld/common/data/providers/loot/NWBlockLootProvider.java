package com.kryptography.newworld.common.data.providers.loot;

import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.init.NWBlocks;
import com.kryptography.newworld.integration.Mods;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.stream.Collectors;

public class NWBlockLootProvider extends BlockLootSubProvider {

    protected static final LootItemCondition.Builder HAS_SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))));
    protected static final LootItemCondition.Builder HAS_SHEARS = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS));
    protected static final LootItemCondition.Builder HAS_SILK_TOUCH_OR_SHEARS = HAS_SHEARS.or(HAS_SILK_TOUCH);

    public NWBlockLootProvider() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }


    @Override
    protected void generate() {
        this.dropSelf(NWBlocks.FIR_LOG.get());
        this.dropSelf(NWBlocks.FIR_WOOD.get());
        this.dropSelf(NWBlocks.STRIPPED_FIR_LOG.get());
        this.dropSelf(NWBlocks.STRIPPED_FIR_WOOD.get());
        this.dropSelf(NWBlocks.FIR_PLANKS.get());
        this.dropSelf(NWBlocks.FIR_SAPLING.get());
        this.dropPottedContents(NWBlocks.POTTED_FIR_SAPLING.get());
        this.add(NWBlocks.FIR_SLAB.get(), createSlabItemTable(NWBlocks.FIR_SLAB.get()));
        this.dropSelf(NWBlocks.FIR_STAIRS.get());
        this.add(NWBlocks.FIR_DOOR.get(), createDoorTable(NWBlocks.FIR_DOOR.get()));
        this.dropSelf(NWBlocks.FIR_TRAPDOOR.get());
        this.dropSelf(NWBlocks.FIR_PRESSURE_PLATE.get());
        this.dropSelf(NWBlocks.FIR_BUTTON.get());
        this.dropSelf(NWBlocks.FIR_FENCE.get());
        this.dropSelf(NWBlocks.FIR_FENCE_GATE.get());
        this.dropSelf(NWBlocks.FIR_SIGN.get());
        this.dropOther(NWBlocks.FIR_WALL_SIGN.get(), NWBlocks.FIR_SIGN.get());
        this.dropSelf(NWBlocks.FIR_HANGING_SIGN.get());
        this.dropOther(NWBlocks.FIR_WALL_HANGING_SIGN.get(), NWBlocks.FIR_HANGING_SIGN.get());

        this.add(NWBlocks.FIR_LEAVES.get(), (block) -> createLeavesDrops(block, NWBlocks.FIR_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        this.dropSelf(NWBlocks.LOAM.get());
        this.dropSelf(NWBlocks.LOAM_STAIRS.get());
        this.add(NWBlocks.LOAM_SLAB.get(), createSlabItemTable(NWBlocks.LOAM_SLAB.get()));
        this.dropSelf(NWBlocks.LOAM_WALL.get());
        this.dropSelf(NWBlocks.LOAM_BRICKS.get());
        this.dropSelf(NWBlocks.LOAM_BRICK_STAIRS.get());
        this.add(NWBlocks.LOAM_BRICK_SLAB.get(), createSlabItemTable(NWBlocks.LOAM_BRICK_SLAB.get()));
        this.dropSelf(NWBlocks.LOAM_BRICK_WALL.get());
        this.dropSelf(NWBlocks.LOAM_TILES.get());
        this.dropSelf(NWBlocks.LOAM_TILE_STAIRS.get());
        this.add(NWBlocks.LOAM_TILE_SLAB.get(), createSlabItemTable(NWBlocks.LOAM_TILE_SLAB.get()));
        this.dropSelf(NWBlocks.LOAM_TILE_WALL.get());

        this.dropPottedContents(NWBlocks.POTTED_POINTED_DRIPSTONE.get());

        this.dropSelf(NWBlocks.TOMBSTONE.get());

        this.add(NWBlocks.MOSS_SPROUTS.get(), createShearsOnlyDrop(NWBlocks.MOSS_SPROUTS.get()));

        dropNamedContainer(NWBlocks.FIR_CABINET.get());
    }


    protected void dropNamedContainer(Block block) {
        add(block, this::createNameableBlockEntityTable);
    }

    protected static LootTable.Builder createShearsOnlyDrop(ItemLike p_124287_) {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(HAS_SHEARS).add(LootItem.lootTableItem(p_124287_)));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ForgeRegistries.BLOCKS.getValues().stream().filter(block -> ForgeRegistries.BLOCKS.getKey(block).getNamespace().equals(NewWorld.MOD_ID)).collect(Collectors.toList());
    }
}
