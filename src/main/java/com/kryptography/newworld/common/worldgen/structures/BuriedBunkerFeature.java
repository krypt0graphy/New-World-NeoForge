package com.kryptography.newworld.common.worldgen.structures;

import com.kryptography.newworld.init.worldgen.structure.NWStructurePools;
import com.kryptography.newworld.init.worldgen.structure.NWStructureTypes;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.DimensionPadding;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.pools.alias.PoolAliasLookup;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;

import java.util.Optional;

public class BuriedBunkerFeature extends Structure {
    public BuriedBunkerFeature(StructureSettings codec) {
        super(codec);
    }


    public static final MapCodec<BuriedBunkerFeature> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(Structure.settingsCodec(instance)).apply(instance, BuriedBunkerFeature::new));

    public static boolean spawnChecks(Structure.GenerationContext context) {
        ChunkPos chunkPos = context.chunkPos();
        RandomSource random = context.random();
        LevelHeightAccessor heightAccessor = context.heightAccessor();

        return context.chunkGenerator().getFirstOccupiedHeight(
                chunkPos.getMinBlockX() + random.nextInt(16),
                chunkPos.getMinBlockZ() + random.nextInt(16),
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                heightAccessor,
                context.randomState()

        ) < 150;
    }

    @Override
    protected Optional<GenerationStub> findGenerationPoint(GenerationContext pContext) {
        ChunkPos chunkPos = pContext.chunkPos();
        BlockPos pos = new BlockPos(chunkPos.getMiddleBlockPosition(0));
        Registry<StructureTemplatePool> pools = pContext.registryAccess().registryOrThrow(Registries.TEMPLATE_POOL);
        HolderGetter<StructureTemplatePool> poolGetter = pools.asLookup();
        if(!spawnChecks(pContext)) {
            return Optional.empty();
        }
        Optional<GenerationStub> structurePiecesGenerator = JigsawPlacement.addPieces(
                pContext,
                poolGetter.getOrThrow(NWStructurePools.BURIED_BUNKER),
                Optional.empty(),
                1,
                pos.below(6),
                false,
                Optional.of(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES),
                80,
                PoolAliasLookup.EMPTY,
                DimensionPadding.ZERO,
                LiquidSettings.APPLY_WATERLOGGING
        );
        return structurePiecesGenerator;
    }

    

    @Override
    public StructureType<?> type() {
        return NWStructureTypes.BURIED_BUNKER.get();
    }


}
