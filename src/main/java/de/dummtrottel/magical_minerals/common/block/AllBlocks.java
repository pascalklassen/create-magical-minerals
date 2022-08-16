package de.dummtrottel.magical_minerals.common.block;

import de.dummtrottel.magical_minerals.MagicalMinerals;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AllBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MagicalMinerals.ID);

    public static final RegistryObject<Block> LAZULITE_BLOCK = BLOCKS.register("lazulite_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.AMETHYST).sound(SoundType.AMETHYST)));

    public static final RegistryObject<Block> LAZULITE_CLUSTER = BLOCKS.register("lazulite_cluster",
            () -> new LazuliteClusterBlock(7, 3, BlockBehaviour.Properties
                    .of(Material.AMETHYST)
                    .randomTicks()
                    .sound(SoundType.AMETHYST_CLUSTER)
                    .noOcclusion()
                    .strength(1.5F)
                    .lightLevel((state) -> 5)));

    public static void register(IEventBus bus)
    {
        BLOCKS.register(bus);
    }
}
