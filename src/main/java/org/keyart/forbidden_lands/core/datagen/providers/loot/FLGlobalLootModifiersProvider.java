package org.keyart.forbidden_lands.core.datagen.providers.loot;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import org.keyart.forbidden_lands.ForbiddenLands;

public class FLGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public FLGlobalLootModifiersProvider(PackOutput output) {
        super(output, ForbiddenLands.MODID);
    }

    @Override
    protected void start() {

    }
}
