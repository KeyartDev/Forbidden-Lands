package org.keyart.forbidden_lands.core.datagen.providers.models;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.keyart.forbidden_lands.ForbiddenLands;
import org.keyart.forbidden_lands.core.registries.FLBlocks;

public class FLBlockStateProvider extends BlockStateProvider {
    public FLBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ForbiddenLands.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        //blockWithItem(FLBlocks.GROUND_BLOCK);
        simpleBlockWithItem(FLBlocks.GROUND_BLOCK.get(), models().cubeBottomTop(ForgeRegistries.BLOCKS.getKey(FLBlocks.GROUND_BLOCK.get()).getPath(),
                ResourceLocation.fromNamespaceAndPath(ForbiddenLands.MODID, "block/texture_side"),
                ResourceLocation.fromNamespaceAndPath(ForbiddenLands.MODID, "block/texture_bottom"),
                ResourceLocation.fromNamespaceAndPath(ForbiddenLands.MODID, "block/texture_top")));
    }

    private void saplingBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), ResourceLocation.parse("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(ForbiddenLands.MODID +
                ":block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
