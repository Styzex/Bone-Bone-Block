package com.viktoreeej.block;

import com.viktoreeej.BoneBoneBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;

public class MyBlocks {
    private static final AbstractBlock.Settings BONE_BONE_SETTINGS = AbstractBlock.Settings.create()
            .mapColor(MapColor.PALE_YELLOW)
            .instrument(NoteBlockInstrument.XYLOPHONE)
            .requiresTool()
            .strength(2.0F)
            .sounds(BlockSoundGroup.BONE);

    public static final Pair<Block, BlockItem> BONE_BONE_BLOCK = registerBlockWithItem("bone_bone_block", BONE_BONE_SETTINGS);

    private static Pair<Block, BlockItem> registerBlockWithItem(String name, AbstractBlock.Settings settings) {
        RegistryKey<Block> blockKey = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(BoneBoneBlock.MOD_ID, name));
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(BoneBoneBlock.MOD_ID, name));

        BoneBoneBlock.LOGGER.info("The registry key is " + blockKey);

        Block block = Registry.register(
                Registries.BLOCK,
                blockKey,
                new Block(settings.registryKey(blockKey))
        );

        BlockItem item = Registry.register(
                Registries.ITEM,
                itemKey,
                new BlockItem(block, new Item.Settings().registryKey(itemKey))
        );

        return new Pair<>(block, item);
    }

    public static void registerModBlocks() {
        BoneBoneBlock.LOGGER.info("Registering Mod Blocks for " + BoneBoneBlock.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> entries.add(BONE_BONE_BLOCK.getLeft()));
    }
}