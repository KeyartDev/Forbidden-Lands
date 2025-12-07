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
        } else {
            setDirtAt(pLevel, pBlockSetter, pRandom, pPos.below(), pConfig);
        }
        int height = this.baseHeight + pRandom.nextInt(0, this.heightRandA);


        BlockPos pos1 = pPos.above(height-1);
        BlockPos pos2 = null;
        BlockPos pos3 = null;

        for (int i = 0; i < height; i++) {
            placeLog(pLevel, pBlockSetter, pRandom, pPos.above(i), pConfig);

            if (i == height - 3) {
                if (pRandom.nextFloat() > 0.5F) {
                    pBlockSetter.accept(pPos.above(i).east().north(),
                            ((BlockState) Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.Y))));

                    pBlockSetter.accept(pPos.above(i).east().north(2),
                            ((BlockState) Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z))));

                    pos2 = pPos.above(i).east().north(2);

                    pBlockSetter.accept(pPos.above(i).west().south(),
                            ((BlockState) Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.Y))));

                    pBlockSetter.accept(pPos.above(i).west().south(2),
                            ((BlockState) Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z))));

                    pos3 = pPos.above(i).west().south(2);
                } else {
                    pBlockSetter.accept(pPos.above(i).west().north(),
                            ((BlockState) Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.Y))));

                    pBlockSetter.accept(pPos.above(i).west().north(2),
                            ((BlockState) Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.X))));

                    pos2 = pPos.above(i).west().north(2);

                    pBlockSetter.accept(pPos.above(i).east().south(),
                            ((BlockState) Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.Y))));

                    pBlockSetter.accept(pPos.above(i).east().south(2),
                            ((BlockState) Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.X))));

                    pos3 = pPos.above(i).east().south(2);
                }
            }
        }

        return List.of(new FoliagePlacer.FoliageAttachment(pos1, 0, false),
                new FoliagePlacer.FoliageAttachment(pos2, 1, false),
                new FoliagePlacer.FoliageAttachment(pos3, 1, false));
    }
}
