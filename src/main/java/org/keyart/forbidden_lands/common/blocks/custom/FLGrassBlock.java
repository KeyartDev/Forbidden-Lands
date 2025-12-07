package org.keyart.forbidden_lands.common.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.IPlantable;
import org.keyart.dev_utils.utils.ParticleUtils;

import java.awt.*;
import java.util.List;

public class FLGrassBlock extends Block {
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


}
