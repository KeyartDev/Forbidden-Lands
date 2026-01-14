package org.keyart.forbidden_lands.core.worldgen.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.Blocks;
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
        {
            // MAIN TRUNK
            for (int i = 0; i < 2; i++) placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().above(i), 2, -1, pAttachment.doubleTrunk());
            placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().above(), 1, 0, pAttachment.doubleTrunk());

            placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().above(2), 0, 0, pAttachment.doubleTrunk());
            placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().above(2).north(), 0, 0, pAttachment.doubleTrunk());
            placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().above(2).south(), 0, 0, pAttachment.doubleTrunk());
            placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().above(2).west(), 0, 0, pAttachment.doubleTrunk());
            placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().above(2).east(), 0, 0, pAttachment.doubleTrunk());

            placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().above(3), 0, 0, pAttachment.doubleTrunk());
        }

        {
            // SECOND TRUNKS
            placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().below().north().west(), 1, 0, pAttachment.doubleTrunk());
            placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().below().north().west(), 0, 1, pAttachment.doubleTrunk());

            placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().below().north().east(), 1, 0, pAttachment.doubleTrunk());
            placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().below().north().east(), 0, 1, pAttachment.doubleTrunk());

            placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().below().south().west(), 1, 0, pAttachment.doubleTrunk());
            placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().below().south().west(), 0, 1, pAttachment.doubleTrunk());

            placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().below().south().east(), 1, 0, pAttachment.doubleTrunk());
            placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().below().south().east(), 0, 1, pAttachment.doubleTrunk());
        }

        {
            // THIRD TRUNKS
            placeLeavesRowCurved(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().above().north(2).west(2));
            placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().above().north(2).west(2), 0, 1, pAttachment.doubleTrunk());
            placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().above().north(2).west(2), 1, -1, pAttachment.doubleTrunk());
            for (int i = 0; i < pRandom.nextInt(2, 4); i++) placeLeavesRowCurved(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().below(i).north(2).west(2));

            placeLeavesRowCurved(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().above().north(2).east(2));
            placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().above().north(2).east(2), 0, 1, pAttachment.doubleTrunk());
            placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().above().north(2).east(2), 1, -1, pAttachment.doubleTrunk());
            for (int i = 0; i < pRandom.nextInt(2, 4); i++) placeLeavesRowCurved(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().below(i).north(2).east(2));

            placeLeavesRowCurved(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().above().south(2).west(2));
            placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().above().south(2).west(2), 0, 1, pAttachment.doubleTrunk());
            placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().above().south(2).west(2), 1, -1, pAttachment.doubleTrunk());
            for (int i = 0; i < pRandom.nextInt(2, 4); i++) placeLeavesRowCurved(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().below(i).south(2).west(2));

            placeLeavesRowCurved(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().above().south(2).east(2));
            placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().above().south(2).east(2), 0, 1, pAttachment.doubleTrunk());
            placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().above().south(2).east(2), 1, -1, pAttachment.doubleTrunk());
            for (int i = 0; i < pRandom.nextInt(2, 4); i++) placeLeavesRowCurved(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().below(i).south(2).east(2));
        }

    }

    private void placeLeavesRowCurved(LevelSimulatedReader pLevel, FoliageSetter pBlockSetter, RandomSource pRandom, TreeConfiguration pConfig, BlockPos pos) {
        placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pos, 0, 0, false);
        placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pos.north(), 0, 0, false);
        placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pos.south(), 0, 0, false);
        placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pos.west(), 0, 0, false);
        placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pos.east(), 0, 0, false);
        placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pos.below(), 0, 0, false);
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
