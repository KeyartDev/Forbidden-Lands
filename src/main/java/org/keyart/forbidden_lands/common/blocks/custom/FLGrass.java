package org.keyart.forbidden_lands.common.blocks.custom;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.keyart.forbidden_lands.common.blocks.abstr.AbstractFLPlant;

public class FLGrass extends AbstractFLPlant {
    public FLGrass() {
        super(BlockBehaviour.Properties.copy(Blocks.GRASS).replaceable());
    }
}
