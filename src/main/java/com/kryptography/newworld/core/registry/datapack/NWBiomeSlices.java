package com.kryptography.newworld.core.registry.datapack;

import com.mojang.datafixers.util.Pair;
import com.kryptography.newworld.core.NewWorld;
import com.kryptography.newworld.core.registry.NWBiomes;
import com.teamabnormals.blueprint.common.world.modification.ModdedBiomeSlice;
import com.teamabnormals.blueprint.core.registry.BlueprintBiomes;
import com.teamabnormals.blueprint.core.registry.BlueprintDataPackRegistries;
import com.teamabnormals.blueprint.core.util.BiomeUtil.MultiNoiseModdedBiomeProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.dimension.LevelStem;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class NWBiomeSlices {
	public static final ResourceKey<ModdedBiomeSlice> WOODED_MEADOW_SLICE = createKey("wooded_meadow");

	public static final ResourceKey<Biome> WOODED_MEADOW_AREA = NWBiomes.registerKey("wooded_meadow");

	public static void bootstrap(BootstrapContext<ModdedBiomeSlice> context) {
		List<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> entries = new ArrayList<>();
		new NewWorldBiomeBuilder().addBiomes(entries::add);

		context.register(WOODED_MEADOW_SLICE, new ModdedBiomeSlice(20,
				MultiNoiseModdedBiomeProvider.builder()
						.biomes(entries::forEach)
						.area(WOODED_MEADOW_AREA, NWBiomes.WOODED_MEADOW)
						.build(), LevelStem.OVERWORLD));
	}

	public static ResourceKey<ModdedBiomeSlice> createKey(String name) {
		return ResourceKey.create(BlueprintDataPackRegistries.MODDED_BIOME_SLICES, NewWorld.id(name));
	}

	private static final class NewWorldBiomeBuilder {
		private static final ResourceKey<Biome> VANILLA = BlueprintBiomes.ORIGINAL_SOURCE_MARKER;

		private final Climate.Parameter FULL_RANGE = Climate.Parameter.span(-1.0F, 1.0F);

		private final Climate.Parameter[] temperatures = {
			Climate.Parameter.span(-1.0F, -0.45F),
			Climate.Parameter.span(-0.45F, -0.15F),
			Climate.Parameter.span(-0.15F, 0.2F),
			Climate.Parameter.span(0.2F, 0.55F),
			Climate.Parameter.span(0.55F, 1.0F)
		};

		private final Climate.Parameter[] humidities = {
			Climate.Parameter.span(-1.0F, -0.35F),
			Climate.Parameter.span(-0.35F, -0.1F),
			Climate.Parameter.span(-0.1F, 0.1F),
			Climate.Parameter.span(0.1F, 0.3F),
			Climate.Parameter.span(0.3F, 1.0F)
		};

		private final Climate.Parameter[] erosions = {
			Climate.Parameter.span(-1.0F, -0.78F),
			Climate.Parameter.span(-0.78F, -0.375F),
			Climate.Parameter.span(-0.375F, -0.2225F),
			Climate.Parameter.span(-0.2225F, 0.05F),
			Climate.Parameter.span(0.05F, 0.45F),
			Climate.Parameter.span(0.45F, 0.55F),
			Climate.Parameter.span(0.55F, 1.0F)
		};

		private final Climate.Parameter coastContinentalness = Climate.Parameter.span(-0.19F, -0.11F);
		private final Climate.Parameter nearInlandContinentalness = Climate.Parameter.span(-0.11F, 0.03F);
		private final Climate.Parameter midInlandContinentalness = Climate.Parameter.span(0.03F, 0.3F);
		private final Climate.Parameter farInlandContinentalness = Climate.Parameter.span(0.3F, 1.0F);


		private final ResourceKey<Biome>[][] PLATEAU_BIOMES = new ResourceKey[][]{
			{VANILLA, VANILLA, VANILLA, VANILLA, VANILLA},
			{WOODED_MEADOW_AREA, WOODED_MEADOW_AREA, VANILLA, VANILLA, VANILLA},
			{WOODED_MEADOW_AREA, WOODED_MEADOW_AREA, WOODED_MEADOW_AREA, WOODED_MEADOW_AREA, VANILLA},
			{VANILLA, VANILLA, VANILLA, VANILLA, VANILLA},
			{VANILLA, VANILLA, VANILLA, VANILLA, VANILLA}
		};

		private final ResourceKey<Biome>[][] PLATEAU_BIOMES_VARIANT = new ResourceKey[][]{
			{null, null, null, null, null},
			{null, null, WOODED_MEADOW_AREA, WOODED_MEADOW_AREA, null},
			{VANILLA, VANILLA, VANILLA, VANILLA, null},
			{null, null, null, null, null},
			{null, null, null, null, null}
		};

		private void addBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer) {
			addOffCoastBiomes(consumer);
			addInlandBiomes(consumer);
			addUndergroundBiomes(consumer);
		}

		private void addOffCoastBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer) {
			addSurfaceBiome(consumer, FULL_RANGE, FULL_RANGE,
				Climate.Parameter.span(-1.2F, -0.19F), FULL_RANGE, FULL_RANGE, 0.0F, VANILLA);
		}

		private void addInlandBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer) {
			addMidSlice(consumer, Climate.Parameter.span(-1.0F, -0.93333334F));
			addHighSlice(consumer, Climate.Parameter.span(-0.93333334F, -0.7666667F));
			addPeaks(consumer, Climate.Parameter.span(-0.7666667F, -0.56666666F));
			addHighSlice(consumer, Climate.Parameter.span(-0.56666666F, -0.4F));
			addMidSlice(consumer, Climate.Parameter.span(-0.4F, -0.26666668F));
			addLowSlice(consumer, Climate.Parameter.span(-0.26666668F, -0.05F));
			addValleys(consumer, Climate.Parameter.span(-0.05F, 0.05F));
			addLowSlice(consumer, Climate.Parameter.span(0.05F, 0.26666668F));
			addMidSlice(consumer, Climate.Parameter.span(0.26666668F, 0.4F));
			addHighSlice(consumer, Climate.Parameter.span(0.4F, 0.56666666F));
			addPeaks(consumer, Climate.Parameter.span(0.56666666F, 0.7666667F));
			addHighSlice(consumer, Climate.Parameter.span(0.7666667F, 0.93333334F));
			addMidSlice(consumer, Climate.Parameter.span(0.93333334F, 1.0F));
		}

		private void addPeaks(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter weirdness) {
			for (int i = 0; i < temperatures.length; i++) {
				Climate.Parameter temp = temperatures[i];
				for (int j = 0; j < humidities.length; j++) {
					Climate.Parameter humidity = humidities[j];
					ResourceKey<Biome> plateau = pickPlateauBiome(i, j, weirdness);

					addSurfaceBiome(consumer, temp, humidity, Climate.Parameter.span(coastContinentalness, farInlandContinentalness), erosions[0], weirdness, 0.0F, VANILLA);
					addSurfaceBiome(consumer, temp, humidity, Climate.Parameter.span(coastContinentalness, nearInlandContinentalness), erosions[1], weirdness, 0.0F, VANILLA);
					addSurfaceBiome(consumer, temp, humidity, Climate.Parameter.span(midInlandContinentalness, farInlandContinentalness), erosions[1], weirdness, 0.0F, VANILLA);
					addSurfaceBiome(consumer, temp, humidity, Climate.Parameter.span(coastContinentalness, nearInlandContinentalness), Climate.Parameter.span(erosions[2], erosions[3]), weirdness, 0.0F, VANILLA);
					addSurfaceBiome(consumer, temp, humidity, Climate.Parameter.span(midInlandContinentalness, farInlandContinentalness), erosions[2], weirdness, 0.0F, plateau);
					addSurfaceBiome(consumer, temp, humidity, midInlandContinentalness, erosions[3], weirdness, 0.0F, VANILLA);
					addSurfaceBiome(consumer, temp, humidity, farInlandContinentalness, erosions[3], weirdness, 0.0F, plateau);
					addSurfaceBiome(consumer, temp, humidity, Climate.Parameter.span(coastContinentalness, farInlandContinentalness), erosions[4], weirdness, 0.0F, VANILLA);
					addSurfaceBiome(consumer, temp, humidity, Climate.Parameter.span(coastContinentalness, nearInlandContinentalness), erosions[5], weirdness, 0.0F, VANILLA);
					addSurfaceBiome(consumer, temp, humidity, Climate.Parameter.span(midInlandContinentalness, farInlandContinentalness), erosions[5], weirdness, 0.0F, VANILLA);
					addSurfaceBiome(consumer, temp, humidity, Climate.Parameter.span(coastContinentalness, farInlandContinentalness), erosions[6], weirdness, 0.0F, VANILLA);
				}
			}
		}

		private void addHighSlice(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter weirdness) {
			for (int i = 0; i < temperatures.length; i++) {
				Climate.Parameter temp = temperatures[i];
				for (int j = 0; j < humidities.length; j++) {
					Climate.Parameter humidity = humidities[j];
					ResourceKey<Biome> plateau = pickPlateauBiome(i, j, weirdness);

					addSurfaceBiome(consumer, temp, humidity, coastContinentalness, Climate.Parameter.span(erosions[0], erosions[1]), weirdness, 0.0F, VANILLA);
					addSurfaceBiome(consumer, temp, humidity, nearInlandContinentalness, erosions[0], weirdness, 0.0F, VANILLA);
					addSurfaceBiome(consumer, temp, humidity, Climate.Parameter.span(midInlandContinentalness, farInlandContinentalness), erosions[0], weirdness, 0.0F, VANILLA);
					addSurfaceBiome(consumer, temp, humidity, nearInlandContinentalness, erosions[1], weirdness, 0.0F, VANILLA);
					addSurfaceBiome(consumer, temp, humidity, Climate.Parameter.span(midInlandContinentalness, farInlandContinentalness), erosions[1], weirdness, 0.0F, VANILLA);
					addSurfaceBiome(consumer, temp, humidity, Climate.Parameter.span(coastContinentalness, nearInlandContinentalness), Climate.Parameter.span(erosions[2], erosions[3]), weirdness, 0.0F, VANILLA);
					addSurfaceBiome(consumer, temp, humidity, Climate.Parameter.span(midInlandContinentalness, farInlandContinentalness), erosions[2], weirdness, 0.0F, plateau);
					addSurfaceBiome(consumer, temp, humidity, midInlandContinentalness, erosions[3], weirdness, 0.0F, VANILLA);
					addSurfaceBiome(consumer, temp, humidity, farInlandContinentalness, erosions[3], weirdness, 0.0F, plateau);
					addSurfaceBiome(consumer, temp, humidity, Climate.Parameter.span(coastContinentalness, farInlandContinentalness), erosions[4], weirdness, 0.0F, VANILLA);
					addSurfaceBiome(consumer, temp, humidity, Climate.Parameter.span(coastContinentalness, nearInlandContinentalness), erosions[5], weirdness, 0.0F, VANILLA);
					addSurfaceBiome(consumer, temp, humidity, Climate.Parameter.span(midInlandContinentalness, farInlandContinentalness), erosions[5], weirdness, 0.0F, VANILLA);
					addSurfaceBiome(consumer, temp, humidity, Climate.Parameter.span(coastContinentalness, farInlandContinentalness), erosions[6], weirdness, 0.0F, VANILLA);
				}
			}
		}

		private void addMidSlice(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter weirdness) {
			addSurfaceBiome(consumer, FULL_RANGE, FULL_RANGE, coastContinentalness, Climate.Parameter.span(erosions[0], erosions[2]), weirdness, 0.0F, VANILLA);
			addSurfaceBiome(consumer, Climate.Parameter.span(temperatures[1], temperatures[2]), FULL_RANGE, Climate.Parameter.span(nearInlandContinentalness, farInlandContinentalness), erosions[6], weirdness, 0.0F, VANILLA);
			addSurfaceBiome(consumer, Climate.Parameter.span(temperatures[3], temperatures[4]), FULL_RANGE, Climate.Parameter.span(nearInlandContinentalness, farInlandContinentalness), erosions[6], weirdness, 0.0F, VANILLA);

			for (int i = 0; i < temperatures.length; i++) {
				Climate.Parameter temp = temperatures[i];
				for (int j = 0; j < humidities.length; j++) {
					Climate.Parameter humidity = humidities[j];
					ResourceKey<Biome> plateau = pickPlateauBiome(i, j, weirdness);

					addSurfaceBiome(consumer, temp, humidity, Climate.Parameter.span(nearInlandContinentalness, farInlandContinentalness), erosions[0], weirdness, 0.0F, VANILLA);
					addSurfaceBiome(consumer, temp, humidity, Climate.Parameter.span(nearInlandContinentalness, midInlandContinentalness), erosions[1], weirdness, 0.0F, VANILLA);
					addSurfaceBiome(consumer, temp, humidity, farInlandContinentalness, erosions[1], weirdness, 0.0F, plateau);
					addSurfaceBiome(consumer, temp, humidity, nearInlandContinentalness, erosions[2], weirdness, 0.0F, VANILLA);
					addSurfaceBiome(consumer, temp, humidity, midInlandContinentalness, erosions[2], weirdness, 0.0F, VANILLA);
					addSurfaceBiome(consumer, temp, humidity, farInlandContinentalness, erosions[2], weirdness, 0.0F, plateau);
					addSurfaceBiome(consumer, temp, humidity, Climate.Parameter.span(coastContinentalness, nearInlandContinentalness), erosions[3], weirdness, 0.0F, VANILLA);
					addSurfaceBiome(consumer, temp, humidity, Climate.Parameter.span(midInlandContinentalness, farInlandContinentalness), erosions[3], weirdness, 0.0F, VANILLA);
					addSurfaceBiome(consumer, temp, humidity, Climate.Parameter.span(coastContinentalness, farInlandContinentalness), erosions[4], weirdness, 0.0F, VANILLA);
					addSurfaceBiome(consumer, temp, humidity, coastContinentalness, erosions[5], weirdness, 0.0F, VANILLA);
					addSurfaceBiome(consumer, temp, humidity, nearInlandContinentalness, erosions[5], weirdness, 0.0F, VANILLA);
					addSurfaceBiome(consumer, temp, humidity, Climate.Parameter.span(midInlandContinentalness, farInlandContinentalness), erosions[5], weirdness, 0.0F, VANILLA);
					addSurfaceBiome(consumer, temp, humidity, coastContinentalness, erosions[6], weirdness, 0.0F, VANILLA);

					if (i == 0) {
						addSurfaceBiome(consumer, temp, humidity, Climate.Parameter.span(nearInlandContinentalness, farInlandContinentalness), erosions[6], weirdness, 0.0F, VANILLA);
					}
				}
			}
		}

		private void addLowSlice(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter weirdness) {
			addSurfaceBiome(consumer, FULL_RANGE, FULL_RANGE, Climate.Parameter.span(-0.19F, 1.0F), FULL_RANGE, weirdness, 0.0F, VANILLA);
		}

		private void addValleys(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter weirdness) {
			addSurfaceBiome(consumer, FULL_RANGE, FULL_RANGE, Climate.Parameter.span(-0.19F, 1.0F), FULL_RANGE, weirdness, 0.0F, VANILLA);
		}

		private void addUndergroundBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer) {
			addUndergroundBiome(consumer, FULL_RANGE, FULL_RANGE, FULL_RANGE, FULL_RANGE, FULL_RANGE, 0.0F, VANILLA);
		}

		private ResourceKey<Biome> pickPlateauBiome(int temperature, int humidity, Climate.Parameter weirdness) {
			if (weirdness.max() >= 0L) {
				ResourceKey<Biome> variant = PLATEAU_BIOMES_VARIANT[temperature][humidity];
				if (variant != null) return variant;
			}
			return PLATEAU_BIOMES[temperature][humidity];
		}

		private void addSurfaceBiome(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer,
			Climate.Parameter temperature, Climate.Parameter humidity, Climate.Parameter continentalness,
			Climate.Parameter erosion, Climate.Parameter weirdness, float offset, ResourceKey<Biome> key) {
			consumer.accept(Pair.of(Climate.parameters(temperature, humidity, continentalness, erosion, Climate.Parameter.point(0.0F), weirdness, offset), key));
			consumer.accept(Pair.of(Climate.parameters(temperature, humidity, continentalness, erosion, Climate.Parameter.point(1.0F), weirdness, offset), key));
		}

		private void addUndergroundBiome(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer,
			Climate.Parameter temperature, Climate.Parameter humidity, Climate.Parameter continentalness,
			Climate.Parameter erosion, Climate.Parameter weirdness, float offset, ResourceKey<Biome> key) {
			consumer.accept(Pair.of(Climate.parameters(temperature, humidity, continentalness, erosion, Climate.Parameter.span(0.2F, 0.9F), weirdness, offset), key));
		}
	}
}