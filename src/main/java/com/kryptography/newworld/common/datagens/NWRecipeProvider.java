package com.kryptography.newworld.common.datagens;

import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.init.NWBlocks;
import com.kryptography.newworld.init.NWItems;
import com.kryptography.newworld.init.data.tags.NWItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class NWRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public NWRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput, HolderLookup.Provider holderLookup) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICKS, 4)
                .pattern("XX")
                .pattern("XX")
                .define('X', NWBlocks.LOAM)
                .unlockedBy("has_loam", has(NWBlocks.LOAM))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILES, 4)
                .pattern("XX")
                .pattern("XX")
                .define('X', NWBlocks.LOAM_BRICKS)
                .unlockedBy("has_loam_bricks", has(NWBlocks.LOAM_BRICKS))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM, 4)
                .pattern("CD")
                .pattern("DC")
                .define('C', Items.CLAY)
                .define('D', Items.DIRT)
                .unlockedBy("has:loam", has(NWBlocks.LOAM))
                .save(pRecipeOutput);

        planksFromLog(pRecipeOutput, NWBlocks.FIR_PLANKS, NWItemTags.FIR_LOGS, 4);
        stairBuilder(NWBlocks.FIR_STAIRS, Ingredient.of(NWBlocks.FIR_PLANKS)).unlockedBy("has_fir_planks", has(NWBlocks.FIR_PLANKS)).save(pRecipeOutput);
        slab(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.FIR_SLAB, NWBlocks.FIR_PLANKS);
        fenceBuilder(NWBlocks.FIR_FENCE, Ingredient.of(NWBlocks.FIR_PLANKS)).unlockedBy("has_fir_planks", has(NWBlocks.FIR_PLANKS)).save(pRecipeOutput);
        fenceGateBuilder(NWBlocks.FIR_FENCE_GATE, Ingredient.of(NWBlocks.FIR_PLANKS)).unlockedBy("has_fir_planks", has(NWBlocks.FIR_PLANKS)).save(pRecipeOutput);
        woodFromLogs(pRecipeOutput, NWBlocks.FIR_WOOD, NWBlocks.FIR_LOG);
        woodFromLogs(pRecipeOutput, NWBlocks.STRIPPED_FIR_WOOD, NWBlocks.STRIPPED_FIR_LOG);
        woodenBoat(pRecipeOutput, NWItems.FIR_BOAT, NWBlocks.FIR_PLANKS);
        chestBoat(pRecipeOutput, NWItems.FIR_CHEST_BOAT, NWBlocks.FIR_PLANKS);
        hangingSign(pRecipeOutput, NWBlocks.FIR_HANGING_SIGN, NWBlocks.STRIPPED_FIR_LOG);
        signBuilder(NWItems.FIR_SIGN, Ingredient.of(NWBlocks.FIR_PLANKS)).unlockedBy("has_fir_planks", has(NWBlocks.FIR_PLANKS)).save(pRecipeOutput);
        doorBuilder(NWBlocks.FIR_DOOR, Ingredient.of(NWBlocks.FIR_PLANKS)).unlockedBy("has_fir_planks", has(NWBlocks.FIR_PLANKS)).save(pRecipeOutput);
        trapdoorBuilder(NWBlocks.FIR_TRAPDOOR, Ingredient.of(NWBlocks.FIR_PLANKS)).unlockedBy("has_fir_planks", has(NWBlocks.FIR_PLANKS)).save(pRecipeOutput);
        buttonBuilder(NWBlocks.FIR_BUTTON, Ingredient.of(NWBlocks.FIR_PLANKS)).unlockedBy("has_fir_planks", has(NWBlocks.FIR_PLANKS)).save(pRecipeOutput);
        pressurePlate(pRecipeOutput, NWBlocks.FIR_PRESSURE_PLATE, NWBlocks.FIR_PLANKS);

        stoneSetRecipes(pRecipeOutput, NWBlocks.LOAM, NWBlocks.LOAM_STAIRS, NWBlocks.LOAM_SLAB, NWBlocks.LOAM_WALL);
        stoneSetRecipes(pRecipeOutput, NWBlocks.LOAM_BRICKS, NWBlocks.LOAM_BRICK_STAIRS, NWBlocks.LOAM_BRICK_SLAB, NWBlocks.LOAM_BRICK_WALL);
        stoneSetRecipes(pRecipeOutput, NWBlocks.LOAM_TILES, NWBlocks.LOAM_TILE_STAIRS, NWBlocks.LOAM_TILE_SLAB, NWBlocks.LOAM_TILE_WALL);

        stonecutterRecipe(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_STAIRS, NWBlocks.LOAM);
        stonecutterRecipe(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_SLAB, NWBlocks.LOAM, 2);
        stonecutterRecipe(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_WALL, NWBlocks.LOAM);
        stonecutterRecipe(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICKS, NWBlocks.LOAM);
        stonecutterRecipe(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICK_STAIRS, NWBlocks.LOAM);
        stonecutterRecipe(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICK_SLAB, NWBlocks.LOAM, 2);
        stonecutterRecipe(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICK_WALL, NWBlocks.LOAM);
        stonecutterRecipe(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILES, NWBlocks.LOAM);
        stonecutterRecipe(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_STAIRS, NWBlocks.LOAM);
        stonecutterRecipe(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_SLAB, NWBlocks.LOAM, 2);
        stonecutterRecipe(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_WALL, NWBlocks.LOAM);

        stonecutterRecipe(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICK_STAIRS, NWBlocks.LOAM_BRICKS);
        stonecutterRecipe(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICK_SLAB, NWBlocks.LOAM_BRICKS, 2);
        stonecutterRecipe(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICK_WALL, NWBlocks.LOAM_BRICKS);
        stonecutterRecipe(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILES, NWBlocks.LOAM_BRICKS);
        stonecutterRecipe(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_STAIRS, NWBlocks.LOAM_BRICKS);
        stonecutterRecipe(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_SLAB, NWBlocks.LOAM_BRICKS, 2);
        stonecutterRecipe(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_WALL, NWBlocks.LOAM_BRICKS);

        stonecutterRecipe(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_STAIRS, NWBlocks.LOAM_TILES);
        stonecutterRecipe(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_SLAB, NWBlocks.LOAM_TILES, 2);
        stonecutterRecipe(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_WALL, NWBlocks.LOAM_TILES);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, NWItems.MATTOCK_CRAFTING_TEMPLATE).requires(NWItems.MATTOCK_CRAFTING_TEMPLATE_HEAD).requires(NWItems.MATTOCK_CRAFTING_TEMPLATE_SHAFT).unlockedBy("has_mattock_crafting_template_piece", has(NWItemTags.MATTOCK_PIECES)).save(pRecipeOutput, NWItems.MATTOCK_CRAFTING_TEMPLATE.getRegisteredName() + "_from_piece_combination");

        SmithingTransformRecipeBuilder
                .smithing(Ingredient.of(NWItems.MATTOCK_CRAFTING_TEMPLATE),
                        Ingredient.of(Items.STICK),
                        Ingredient.of(Items.FLINT),
                        RecipeCategory.TOOLS,
                        NWItems.ANCIENT_MATTOCK.asItem())
                .unlocks("has_mattock_crafting_template", has(NWItems.MATTOCK_CRAFTING_TEMPLATE))
                .save(pRecipeOutput, NewWorld.id("ancient_mattock_smithing"));
    }

    public void stoneSetRecipes(RecipeOutput pRecipeOutput, ItemLike base, ItemLike stairs, ItemLike slab, ItemLike wall) {
        stairBuilder(stairs, Ingredient.of(base)).unlockedBy("has_" + base.asItem().getDescriptionId(), has(base)).save(pRecipeOutput);
        slab(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, slab, base);
        wall(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, wall, base);
    }


    public void stonecutterRecipe(RecipeOutput pRecipeOutput, RecipeCategory category, ItemLike output, ItemLike input) {
        this.stonecutterRecipe(pRecipeOutput, category, output, input, 1);
    }

    public void stonecutterRecipe(RecipeOutput pRecipeOutput, RecipeCategory category, ItemLike output, ItemLike input, int count) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(input), category, output, count).unlockedBy(getHasName(input), has(input)).save(pRecipeOutput, ResourceLocation.fromNamespaceAndPath(NewWorld.MOD_ID, getConversionRecipeName(output, input)) + "_stonecutting");
    }
}
