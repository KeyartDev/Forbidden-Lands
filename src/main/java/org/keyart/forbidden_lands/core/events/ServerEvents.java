package org.keyart.forbidden_lands.core.events;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import org.keyart.forbidden_lands.ForbiddenLands;

public class ServerEvents {
    @Mod.EventBusSubscriber(modid = ForbiddenLands.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.DEDICATED_SERVER)
    public static class ServerModBusEvents {

    }

    @Mod.EventBusSubscriber(modid = ForbiddenLands.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.DEDICATED_SERVER)
    public static class ServerForgeBusEvents {

    }
}
