package org.keyart.forbidden_lands.core.worldgen.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import org.keyart.forbidden_lands.core.registries.FLBlocks;
import org.keyart.forbidden_lands.core.registries.FLFoliagePlacers;

import java.util.function.Predicate;

public class FLVerusFoliagePlacer extends FoliagePlacer {
    public static final Codec<FLVerusFoliagePlacer> CODEC = RecordCodecBuilder.create(verusFoliagePlacerInstance ->
            foliagePlacerParts(verusFoliagePlacerInstance).and(Codec.intRange(0, 16).fieldOf("height")
                    .forGetter(fp -> fp.height)).apply(verusFoliagePlacerInstance, FLVerusFoliagePlacer::new));
    private Integer height;

    public FLVerusFoliagePlacer(IntProvider pRadius, IntProvider pOffset, int height) {
        super(pRadius, pOffset);
        this.height = height;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return FLFoliagePlacers.VERUS_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader pLevel, FoliageSetter pBlockSetter, RandomSource pRandom, TreeConfiguration pConfig, int pMaxFreeTreeHeight, FoliageAttachment pAttachment, int pFoliageHeight, int pFoliageRadius, int pOffset) {
        if (pAttachment.radiusOffset() == 0) {
            placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().above(), 0, 0, pAttachment.doubleTrunk());
            placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().below(3), 1, 0, pAttachment.doubleTrunk());

            for (int i = 0; i < 3; i++) placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().north(), 0, -i, pAttachment.doubleTrunk());
            for (int i = 0; i < 3; i++) placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().south(), 0, -i, pAttachment.doubleTrunk());
            for (int i = 0; i < 3; i++) placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().west(), 0, -i, pAttachment.doubleTrunk());
            for (int i = 0; i < 3; i++) placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().east(), 0, -i, pAttachment.doubleTrunk());
        } else if (pAttachment.radiusOffset() == 1) {
            placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().above(), 0, 0, pAttachment.doubleTrunk());

            if (pLevel.isStateAtPosition(pAttachment.pos().east(), (state) -> state.is(BlockTags.LOGS_THAT_BURN))) {
                for (int i = 0; i < 2; i++) placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().west(), 0, -i, pAttachment.doubleTrunk());
                for (int i = 0; i < 2; i++) placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().east(), 0, -i, pAttachment.doubleTrunk());
                for (int i = 0; i < 2; i++) placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().south(), 0, -i, pAttachment.doubleTrunk());
                for (int i = 0; i < 2; i++) placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().north(), 0, -i, pAttachment.doubleTrunk());

                for (int i = 0; i < 2; i++) placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().north().east(), 0, -i, pAttachment.doubleTrunk());
                for (int i = 0; i < 2; i++) placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().south().east(), 0, -i, pAttachment.doubleTrunk());
            }
            if (pLevel.isStateAtPosition(pAttachment.pos().west(), (state) -> state.is(BlockTags.LOGS_THAT_BURN))) {
                for (int i = 0; i < 2; i++) placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().west(), 0, -i, pAttachment.doubleTrunk());
                for (int i = 0; i < 2; i++) placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().east(), 0, -i, pAttachment.doubleTrunk());
                for (int i = 0; i < 2; i++) placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().south(), 0, -i, pAttachment.doubleTrunk());
                for (int i = 0; i < 2; i++) placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().north(), 0, -i, pAttachment.doubleTrunk());

                for (int i = 0; i < 2; i++) placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().north().west(), 0, -i, pAttachment.doubleTrunk());
                for (int i = 0; i < 2; i++) placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().south().west(), 0, -i, pAttachment.doubleTrunk());
            }
            if (pLevel.isStateAtPosition(pAttachment.pos().north(), (state) -> state.is(BlockTags.LOGS_THAT_BURN))) {
                for (int i = 0; i < 2; i++) placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().west(), 0, -i, pAttachment.doubleTrunk());
                for (int i = 0; i < 2; i++) placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().east(), 0, -i, pAttachment.doubleTrunk());
                for (int i = 0; i < 2; i++) placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().south(), 0, -i, pAttachment.doubleTrunk());
                for (int i = 0; i < 2; i++) placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().north(), 0, -i, pAttachment.doubleTrunk());

                for (int i = 0; i < 2; i++) placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().east().north(), 0, -i, pAttachment.doubleTrunk());
                for (int i = 0; i < 2; i++) placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().west().north(), 0, -i, pAttachment.doubleTrunk());
            }
            if (pLevel.isStateAtPosition(pAttachment.pos().south(), (state) -> state.is(BlockTags.LOGS_THAT_BURN))) {
                for (int i = 0; i < 2; i++) placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().west(), 0, -i, pAttachment.doubleTrunk());
                for (int i = 0; i < 2; i++) placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().east(), 0, -i, pAttachment.doubleTrunk());
                for (int i = 0; i < 2; i++) placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().south(), 0, -i, pAttachment.doubleTrunk());
                for (int i = 0; i < 2; i++) placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().north(), 0, -i, pAttachment.doubleTrunk());

                for (int i = 0; i < 2; i++) placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().east().south(), 0, -i, pAttachment.doubleTrunk());
                for (int i = 0; i < 2; i++) placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().west().south(), 0, -i, pAttachment.doubleTrunk());
            }

            for (int i = 0; i < 2 + pRandom.nextInt(0, 2); i++) placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().below(), 0, -i, pAttachment.doubleTrunk());
        }
    }

    @Override
    public int foliageHeight(RandomSource pRandom, int pHeight, TreeConfiguration pConfig) {
        return this.height;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource pRandom, int pLocalX, int pLocalY, int pLocalZ, int pRange, boolean pLarge) {
        return false;
    }
}
