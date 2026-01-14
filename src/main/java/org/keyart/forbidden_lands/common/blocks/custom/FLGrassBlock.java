package org.keyart.forbidden_lands.common.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.IPlantable;
import org.keyart.dev_utils.utils.ParticleUtils;
import org.keyart.forbidden_lands.core.registries.FLBlocks;
import org.keyart.forbidden_lands.core.worldgen.FLConfiguredFeatures;
import org.keyart.forbidden_lands.core.worldgen.FLPlacedFeatures;

import java.awt.*;
import java.util.List;
import java.util.Optional;

public class FLGrassBlock extends Block implements BonemealableBlock {
    private static final List<Color> COLORS = List.of(
            new Color(8, 37, 200),
            new Color(86, 95, 218)
    );

    public FLGrassBlock() {
        super(Properties.copy(Blocks.GRASS_BLOCK).randomTicks());
    }

    @Override
    public void fallOn(Level pLevel, BlockState pState, BlockPos pPos, Entity pEntity, float pFallDistance) {
        super.fallOn(pLevel, pState, pPos, pEntity, pFallDistance);

        if (!pLevel.isClientSide)
            return;

        if (pEntity instanceof Player player && pFallDistance <= 3F) {
            Vec3 pos = player.position();
            for (int i = 0; i < 15; i++) {
                makeShortParticles(pLevel, pos);
            }
            for (int i = 0; i < 15; i++) {
                RandomSource source = RandomSource.create();
                makeLongParticles(pLevel, source, pos);
            }
        }
    }

    private void makeShortParticles(Level level, Vec3 pos) {
        int colorId = level.random.nextInt(0, COLORS.size());
        Color color = COLORS.get(colorId);

        ParticleUtils.createColoredParticle(level, color,
                0.25F, 60+level.random.nextInt(0, 10),
                (float) 0, ((float) pos.x + ((float) level.random.nextInt(0, 4) / 10)), ((float) pos.y), ((float) pos.z + ((float) level.random.nextInt(0, 4) / 10)),
                (float) (pos.x + ((double) level.random.nextInt(0, 4) / 10) + (double) (level.random.nextInt(0, 10) - 5) / 10),
                (float) (pos.y + (double) (level.random.nextInt(0, 5)) / 10),
                (float) (pos.z + ((double) level.random.nextInt(0, 4) / 10) + (double) (level.random.nextInt(0, 10) - 5) / 10), ParticleUtils.SizeBehavior.FALLING, 0.15F, true);
    }

    private void makeLongParticles(Level level, RandomSource source, Vec3 pos) {
        int colorId = source.nextInt(0, COLORS.size());
        Color color = COLORS.get(colorId);

        ParticleUtils.createColoredParticle(level, color,
                0.2F, 20+source.nextInt(0, 10),
                (float) 0, ((float) pos.x), ((float) pos.y), ((float) pos.z),
                (float) (pos.x + ((double) source.nextInt(0, 4) / 10) + (double) (source.nextInt(0, 30) - 15) / 10),
                (float) (pos.y + (double) (source.nextInt(0, 30) - 15) / 10),
                (float) (pos.z + ((double) source.nextInt(0, 4) / 10) + (double) (source.nextInt(0, 30) - 15) / 10), ParticleUtils.SizeBehavior.FALLING);
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable) {
        if (plantable instanceof SaplingBlock)
            return true;

        return false;
    }


    @Override
    public boolean isValidBonemealTarget(LevelReader pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
        return pLevel.getBlockState(pPos.above()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(Level pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) { // TODO: ПЕРЕДЕЛАТЬ НАХУЙ ЭТО ВСЁ
        BlockPos blockpos = pPos.above();
        BlockState blockstate = FLBlocks.FL_GRASS_BLOCK.get().defaultBlockState();
        Optional<Holder.Reference<PlacedFeature>> optional = pLevel.registryAccess().registryOrThrow(Registries.PLACED_FEATURE).getHolder(FLPlacedFeatures.FL_GRASS_SINGLE_KEY);

        label49:
        for(int i = 0; i < 128; ++i) {
            BlockPos blockpos1 = blockpos;

            for(int j = 0; j < i / 16; ++j) {
                blockpos1 = blockpos1.offset(pRandom.nextInt(3) - 1, (pRandom.nextInt(3) - 1) * pRandom.nextInt(3) / 2, pRandom.nextInt(3) - 1);
                if (!pLevel.getBlockState(blockpos1.below()).is(this) || pLevel.getBlockState(blockpos1).isCollisionShapeFullBlock(pLevel, blockpos1)) {
                    continue label49;
                }
            }

            BlockState blockstate1 = pLevel.getBlockState(blockpos1);
            if (blockstate1.is(blockstate.getBlock()) && pRandom.nextInt(10) == 0) {
                ((BonemealableBlock)blockstate.getBlock()).performBonemeal(pLevel, pRandom, blockpos1, blockstate1);
            }

            if (blockstate1.isAir()) {
                Holder<PlacedFeature> holder;
                if (pRandom.nextInt(8) == 0) {
                    Registry<ConfiguredFeature<?, ?>> modFeatures = pLevel.registryAccess().registryOrThrow(Registries.CONFIGURED_FEATURE);
                    List<ConfiguredFeature<?, ?>> list = List.of(
                            modFeatures.getOrThrow(FLConfiguredFeatures.FL_LUMIR_PATCH_KEY)
                    );
                    if (list.isEmpty()) {
                        continue;
                    }

                    holder = ((RandomPatchConfiguration)list.get(0).config()).feature();
                } else {
                    if (!optional.isPresent()) {
                        continue;
                    }

                    holder = optional.get();
                }

                holder.value().place(pLevel, pLevel.getChunkSource().getGenerator(), pRandom, blockpos1);
            }
        }

    }
}
