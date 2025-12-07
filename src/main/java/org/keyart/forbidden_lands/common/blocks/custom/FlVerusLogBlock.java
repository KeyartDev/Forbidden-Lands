package org.keyart.forbidden_lands.common.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.keyart.forbidden_lands.core.registries.FLBlocks;

public class FlVerusLogBlock extends FLRotatedPillarFlammableBlock {
    public FlVerusLogBlock() {
        super(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.NETHER_WOOD));
    }
}
