package com.kryptography.newworld.common.data.providers;

import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.init.NWBlocks;
import com.kryptography.newworld.init.NWItems;
import com.kryptography.newworld.init.data.tags.NWItemTags;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class NWRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public NWRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pRecipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICKS.get(), 4)
                .pattern("XX")
                .pattern("XX")
                .define('X', NWBlocks.LOAM.get())
                .unlockedBy("has_loam", has(NWBlocks.LOAM.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILES.get(), 4)
                .pattern("XX")
                .pattern("XX")
                .define('X', NWBlocks.LOAM_BRICKS.get())
                .unlockedBy("has_loam_bricks", has(NWBlocks.LOAM_BRICKS.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM.get(), 4)
                .pattern("CD")
                .pattern("DC")
                .define('C', Items.CLAY)
                .define('D', Items.DIRT)
                .unlockedBy("has:loam", has(NWBlocks.LOAM.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, NWBlocks.TOMBSTONE.get())
                .pattern("SDS")
                .pattern("SAS")
                .pattern("STS")
                .define('S', Items.POLISHED_DEEPSLATE)
                .define('D', Items.DIAMOND)
                .define('A', NWItems.ILLAGER_TOME.get())
                .define('T', NWBlocks.TOMBSTONE.get())
                .unlockedBy("has_tombstone_or_illager_tome", has(NWItemTags.TOMBSTONE_MATERIALS))
                .showNotification(true)
                .save(pRecipeOutput);

        planksFromLog(pRecipeOutput, NWBlocks.FIR_PLANKS.get(), NWItemTags.FIR_LOGS, 4);
        stairBuilder(NWBlocks.FIR_STAIRS.get(), Ingredient.of(NWBlocks.FIR_PLANKS.get())).unlockedBy("has_fir_planks", has(NWBlocks.FIR_PLANKS.get())).save(pRecipeOutput);
        slab(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.FIR_SLAB.get(), NWBlocks.FIR_PLANKS.get());
        fenceBuilder(NWBlocks.FIR_FENCE.get(), Ingredient.of(NWBlocks.FIR_PLANKS.get())).unlockedBy("has_fir_planks", has(NWBlocks.FIR_PLANKS.get())).save(pRecipeOutput);
        fenceGateBuilder(NWBlocks.FIR_FENCE_GATE.get(), Ingredient.of(NWBlocks.FIR_PLANKS.get())).unlockedBy("has_fir_planks", has(NWBlocks.FIR_PLANKS.get())).save(pRecipeOutput);
        woodFromLogs(pRecipeOutput, NWBlocks.FIR_WOOD.get(), NWBlocks.FIR_LOG.get());
        woodFromLogs(pRecipeOutput, NWBlocks.STRIPPED_FIR_WOOD.get(), NWBlocks.STRIPPED_FIR_LOG.get());
        woodenBoat(pRecipeOutput, NWItems.FIR_BOAT.get(), NWBlocks.FIR_PLANKS.get());
        chestBoat(pRecipeOutput, NWItems.FIR_CHEST_BOAT.get(), NWItems.FIR_BOAT.get());
        hangingSign(pRecipeOutput, NWBlocks.FIR_HANGING_SIGN.get(), NWBlocks.STRIPPED_FIR_LOG.get());
        signBuilder(NWItems.FIR_SIGN.get(), Ingredient.of(NWBlocks.FIR_PLANKS.get())).unlockedBy("has_fir_planks", has(NWBlocks.FIR_PLANKS.get())).save(pRecipeOutput);
        doorBuilder(NWBlocks.FIR_DOOR.get(), Ingredient.of(NWBlocks.FIR_PLANKS.get())).unlockedBy("has_fir_planks", has(NWBlocks.FIR_PLANKS.get())).save(pRecipeOutput);
        trapdoorBuilder(NWBlocks.FIR_TRAPDOOR.get(), Ingredient.of(NWBlocks.FIR_PLANKS.get())).unlockedBy("has_fir_planks", has(NWBlocks.FIR_PLANKS.get())).save(pRecipeOutput);
        buttonBuilder(NWBlocks.FIR_BUTTON.get(), Ingredient.of(NWBlocks.FIR_PLANKS.get())).unlockedBy("has_fir_planks", has(NWBlocks.FIR_PLANKS.get())).save(pRecipeOutput);
        pressurePlate(pRecipeOutput, NWBlocks.FIR_PRESSURE_PLATE.get(), NWBlocks.FIR_PLANKS.get());

        stoneSetRecipes(pRecipeOutput, NWBlocks.LOAM.get(), NWBlocks.LOAM_STAIRS.get(), NWBlocks.LOAM_SLAB.get(), NWBlocks.LOAM_WALL.get());
        stoneSetRecipes(pRecipeOutput, NWBlocks.LOAM_BRICKS.get(), NWBlocks.LOAM_BRICK_STAIRS.get(), NWBlocks.LOAM_BRICK_SLAB.get(), NWBlocks.LOAM_BRICK_WALL.get());
        stoneSetRecipes(pRecipeOutput, NWBlocks.LOAM_TILES.get(), NWBlocks.LOAM_TILE_STAIRS.get(), NWBlocks.LOAM_TILE_SLAB.get(), NWBlocks.LOAM_TILE_WALL.get());

        stonecutterRecipe(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_STAIRS.get(), NWBlocks.LOAM.get());
        stonecutterRecipe(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_SLAB.get(), NWBlocks.LOAM.get(), 2);
        stonecutterRecipe(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_WALL.get(), NWBlocks.LOAM.get());
        stonecutterRecipe(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICKS.get(), NWBlocks.LOAM.get());
        stonecutterRecipe(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICK_STAIRS.get(), NWBlocks.LOAM.get());
        stonecutterRecipe(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICK_SLAB.get(), NWBlocks.LOAM.get(), 2);
        stonecutterRecipe(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICK_WALL.get(), NWBlocks.LOAM.get());
        stonecutterRecipe(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILES.get(), NWBlocks.LOAM.get());
        stonecutterRecipe(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_STAIRS.get(), NWBlocks.LOAM.get());
        stonecutterRecipe(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_SLAB.get(), NWBlocks.LOAM.get(), 2);
        stonecutterRecipe(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_WALL.get(), NWBlocks.LOAM.get());

        stonecutterRecipe(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICK_STAIRS.get(), NWBlocks.LOAM_BRICKS.get());
        stonecutterRecipe(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICK_SLAB.get(), NWBlocks.LOAM_BRICKS.get(), 2);
        stonecutterRecipe(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICK_WALL.get(), NWBlocks.LOAM_BRICKS.get());
        stonecutterRecipe(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILES.get(), NWBlocks.LOAM_BRICKS.get());
        stonecutterRecipe(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_STAIRS.get(), NWBlocks.LOAM_BRICKS.get());
        stonecutterRecipe(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_SLAB.get(), NWBlocks.LOAM_BRICKS.get(), 2);
        stonecutterRecipe(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_WALL.get(), NWBlocks.LOAM_BRICKS.get());

        stonecutterRecipe(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_STAIRS.get(), NWBlocks.LOAM_TILES.get());
        stonecutterRecipe(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_SLAB.get(), NWBlocks.LOAM_TILES.get(), 2);
        stonecutterRecipe(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_WALL.get(), NWBlocks.LOAM_TILES.get());

        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, NWItems.MATTOCK_CRAFTING_TEMPLATE.get()).requires(NWItems.MATTOCK_CRAFTING_TEMPLATE_HEAD.get()).requires(NWItems.MATTOCK_CRAFTING_TEMPLATE_SHAFT.get()).unlockedBy("has_mattock_crafting_template_piece", has(NWItemTags.MATTOCK_PIECES)).save(pRecipeOutput, NWItems.MATTOCK_CRAFTING_TEMPLATE.getId() + "_from_piece_combination");

        SmithingTransformRecipeBuilder
                .smithing(Ingredient.of(NWItems.MATTOCK_CRAFTING_TEMPLATE.get()),
                        Ingredient.of(Items.STICK),
                        Ingredient.of(Items.FLINT),
                        RecipeCategory.TOOLS,
                        NWItems.ANCIENT_MATTOCK.get())
                .unlocks("has_mattock_crafting_template", has(NWItems.MATTOCK_CRAFTING_TEMPLATE.get()))
                .save(pRecipeOutput, NewWorld.id("ancient_mattock_smithing"));


        ConditionalRecipe.builder().addCondition(new ModLoadedCondition("farmersdelight")).addRecipe(c ->
                ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, NWBlocks.FIR_CABINET.get())
                        .pattern("___")
                        .pattern("D D")
                        .pattern("___")
                        .define('_', NWBlocks.FIR_SLAB.get())
                        .define('D', NWBlocks.FIR_TRAPDOOR.get())
                        .unlockedBy("has_fir_trapdoor", has(NWBlocks.FIR_TRAPDOOR.get()))
                        .group("fd_cabinet")
                        .save(c)
        ).generateAdvancement().build(pRecipeOutput, NewWorld.id("fir_cabinet"));
    }

    public void stoneSetRecipes(Consumer<FinishedRecipe> pRecipeOutput, ItemLike base, ItemLike stairs, ItemLike slab, ItemLike wall) {
        stairBuilder(stairs, Ingredient.of(base)).unlockedBy("has_" + base.asItem().getDescriptionId(), has(base)).save(pRecipeOutput);
        slab(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, slab, base);
        wall(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, wall, base);
    }


    public void stonecutterRecipe(Consumer<FinishedRecipe> pRecipeOutput, RecipeCategory category, ItemLike output, ItemLike input) {
        this.stonecutterRecipe(pRecipeOutput, category, output, input, 1);
    }

    public void stonecutterRecipe(Consumer<FinishedRecipe> pRecipeOutput, RecipeCategory category, ItemLike output, ItemLike input, int count) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(input), category, output, count).unlockedBy(getHasName(input), has(input)).save(pRecipeOutput, NewWorld.id( getConversionRecipeName(output, input)) + "_stonecutting");
    }
}
