package de.dummtrottel.magical_minerals.common;

import de.dummtrottel.magical_minerals.MagicalMinerals;
import de.dummtrottel.magical_minerals.common.block.geode.lazulite.cluster.LargeLazuliteBudBlock;
import de.dummtrottel.magical_minerals.common.block.geode.lazulite.cluster.LazuliteClusterBlock;
import de.dummtrottel.magical_minerals.common.block.geode.lazulite.LazuliteBlock;
import de.dummtrottel.magical_minerals.common.block.geode.lazulite.cluster.MediumLazuliteBudBlock;
import de.dummtrottel.magical_minerals.common.block.geode.lazulite.cluster.SmallLazuliteBudBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class AllBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MagicalMinerals.ID);

    public static final RegistryObject<Block> LAZULITE_BLOCK = BLOCKS.register("lazulite_block", LazuliteBlock::new);

    public static final RegistryObject<Block> LAZULITE_CLUSTER = BLOCKS.register("lazulite_cluster", LazuliteClusterBlock::new);
    public static final RegistryObject<Block> SMALL_LAZULITE_BUD = BLOCKS.register("small_lazulite_bud", SmallLazuliteBudBlock::new);
    public static final RegistryObject<Block> MEDIUM_LAZULITE_BUD = BLOCKS.register("medium_lazulite_bud", MediumLazuliteBudBlock::new);
    public static final RegistryObject<Block> LARGE_LAZULITE_BUD = BLOCKS.register("large_lazulite_bud", LargeLazuliteBudBlock::new);
    public static final RegistryObject<Block> BUDDING_LAZULITE = BLOCKS.register("budding_lazulite", LazuliteBlock::new);

    private static final class AllBlocksModelProvider extends BlockModelProvider
    {
        private AllBlocksModelProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper)
        {
            super(generator, modid, existingFileHelper);
        }

        @Override
        protected void registerModels()
        {
            cubeAll("lazulite_block", modLoc("block/" + LAZULITE_BLOCK.get().getRegistryName().getPath()));
            cubeAll("budding_lazulite", modLoc("block/" + BUDDING_LAZULITE.get().getRegistryName().getPath()));

            cross("lazulite_cluster", modLoc("block/" + LAZULITE_CLUSTER.get().getRegistryName().getPath()));
            cross("small_lazulite_bud", modLoc("block/" + SMALL_LAZULITE_BUD.get().getRegistryName().getPath()));
            cross("medium_lazulite_bud", modLoc("block/" + MEDIUM_LAZULITE_BUD.get().getRegistryName().getPath()));
            cross("large_lazulite_bud", modLoc("block/" + LARGE_LAZULITE_BUD.get().getRegistryName().getPath()));
        }
    }

    private static final class AllBlocksStateModelProvider extends BlockStateProvider
    {
        private AllBlocksStateModelProvider(DataGenerator gen, String modid, ExistingFileHelper exFileHelper)
        {
            super(gen, modid, exFileHelper);
        }

        @Override
        protected void registerStatesAndModels()
        {
            directionalCross(LAZULITE_CLUSTER.get());
            directionalCross(SMALL_LAZULITE_BUD.get());
            directionalCross(MEDIUM_LAZULITE_BUD.get());
            directionalCross(LARGE_LAZULITE_BUD.get());
        }

        private void directionalCross(Block block)
        {
            var name = block.getRegistryName();
            var path = name.getPath();

            directionalBlock(block, models().cross(path, modLoc("block/" + path)));
        }
    }

    public static BlockModelProvider models(DataGenerator generator, ExistingFileHelper helper)
    {
        return new AllBlocksModelProvider(generator, MagicalMinerals.ID, helper);
    }

    public static BlockStateProvider states(DataGenerator generator, ExistingFileHelper helper)
    {
        return new AllBlocksStateModelProvider(generator, MagicalMinerals.ID, helper);
    }

    public static void register(IEventBus bus)
    {
        BLOCKS.register(bus);
    }
}
