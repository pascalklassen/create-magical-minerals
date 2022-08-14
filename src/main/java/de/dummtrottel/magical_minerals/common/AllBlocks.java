package de.dummtrottel.magical_minerals.common;

import de.dummtrottel.magical_minerals.MagicalMinerals;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class AllBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MagicalMinerals.ID);

    public static final RegistryObject<Block> LAZULITE_BLOCK = registerBlock("lazulite_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.AMETHYST).sound(SoundType.AMETHYST)));

    public static final RegistryObject<Block> LAZULITE_CLUSTER = registerBlock("lazulite_cluster",
            () -> new Block(BlockBehaviour.Properties.of(Material.AMETHYST).sound(SoundType.AMETHYST_CLUSTER)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block)
    {
        var obj = BLOCKS.register(name, block);
        registerBlockItem(name, obj);
        return obj;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block)
    {
        AllItems.ITEMS.register(
                name,
                () -> new BlockItem(block.get(), new Item.Properties().tab(MagicalMinerals.MINERAL_TAB))
        );
    }

    public static void register(IEventBus bus)
    {
        BLOCKS.register(bus);
    }
}
