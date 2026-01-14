package org.keyart.forbidden_lands.core.worldgen.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.keyart.forbidden_lands.core.registries.FLBlocks;
import org.keyart.forbidden_lands.core.registries.FLTrunkPlacers;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class FLVerusTrunkPlacer extends TrunkPlacer {
    public static final Codec<FLVerusTrunkPlacer> CODEC = RecordCodecBuilder.create(verusTrunkPlacerInstance ->
            trunkPlacerParts(verusTrunkPlacerInstance).apply(verusTrunkPlacerInstance, FLVerusTrunkPlacer::new));

    public FLVerusTrunkPlacer(int pBaseHeight, int pHeightRandA, int pHeightRandB) {
        super(pBaseHeight, pHeightRandA, pHeightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return FLTrunkPlacers.VERUS_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter, RandomSource pRandom, int pFreeTreeHeight, BlockPos pPos, TreeConfiguration pConfig) {
        if (pLevel.isStateAtPosition(pPos.below(), (state -> state.is(FLBlocks.FL_GRASS_BLOCK.get())))) {
            pBlockSetter.accept(pPos.below(), FLBlocks.FL_DIRT.get().defaultBlockState());
        } else if (pLevel.isStateAtPosition(pPos.below(), (state -> state.is(FLBlocks.FL_DIRT.get())))) {
            pBlockSetter.accept(pPos.below(), FLBlocks.FL_DIRT.get().defaultBlockState());
        } else {
            setDirtAt(pLevel, pBlockSetter, pRandom, pPos.below(), pConfig);
        }
        int height = this.baseHeight + pRandom.nextInt(0, this.heightRandA);

        pBlockSetter.accept(pPos.north(), FLBlocks.FL_VERUS_LOG.get().defaultBlockState());
        pBlockSetter.accept(pPos.south(), FLBlocks.FL_VERUS_LOG.get().defaultBlockState());
        pBlockSetter.accept(pPos.west(), FLBlocks.FL_VERUS_LOG.get().defaultBlockState());
        pBlockSetter.accept(pPos.east(), FLBlocks.FL_VERUS_LOG.get().defaultBlockState());

        for (int i = 0; i < height; i++) {
            placeLog(pLevel, pBlockSetter, pRandom, pPos.above(i), pConfig);
        }

        placeLog(pLevel, pBlockSetter, pRandom, pPos.above(height).north().west(), pConfig);
        placeLog(pLevel, pBlockSetter, pRandom, pPos.above(height).north().east(), pConfig);
        placeLog(pLevel, pBlockSetter, pRandom, pPos.above(height).south().west(), pConfig);
        placeLog(pLevel, pBlockSetter, pRandom, pPos.above(height).south().east(), pConfig);

        placeLog(pLevel, pBlockSetter, pRandom, pPos.above(height+1).north(2).west(2), pConfig);
        placeLog(pLevel, pBlockSetter, pRandom, pPos.above(height+1).north(2).east(2), pConfig);
        placeLog(pLevel, pBlockSetter, pRandom, pPos.above(height+1).south(2).west(2), pConfig);
        placeLog(pLevel, pBlockSetter, pRandom, pPos.above(height+1).south(2).east(2), pConfig);



        return List.of(new FoliagePlacer.FoliageAttachment(pPos.above(height), 0, false));
    }
}
