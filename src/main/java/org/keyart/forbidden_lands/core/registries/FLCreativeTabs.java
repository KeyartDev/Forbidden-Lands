package org.keyart.forbidden_lands.core.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.keyart.forbidden_lands.ForbiddenLands;

public class FLCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ForbiddenLands.MODID);

    public static final RegistryObject<CreativeModeTab> FL_CREATIVE_TAB = CREATIVE_TABS.register("fl_creative_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("creative_tab.forbidden_lands.fl_creative_tab"))
            .icon(() -> new ItemStack(FLBlocks.GROUND_BLOCK.get()))
            .displayItems(((pParameters, pOutput) -> {
                pOutput.accept(FLBlocks.GROUND_BLOCK.get());
            })).build());

    public static void register(IEventBus bus) {
        CREATIVE_TABS.register(bus);
    }
}
