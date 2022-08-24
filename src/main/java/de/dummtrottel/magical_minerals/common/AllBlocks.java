package de.dummtrottel.magical_minerals.common;

import de.dummtrottel.magical_minerals.MagicalMinerals;
import de.dummtrottel.magical_minerals.common.block.geode.BaseBuddingBlock;
import de.dummtrottel.magical_minerals.common.block.geode.BaseClusterBlock;
import de.dummtrottel.magical_minerals.common.block.geode.BaseGeodeBlock;
import de.dummtrottel.magical_minerals.common.block.geode.lazulite.BuddingLazuliteBlock;
import de.dummtrottel.magical_minerals.common.block.geode.lazulite.cluster.LargeLazuliteBudBlock;
import de.dummtrottel.magical_minerals.common.block.geode.lazulite.cluster.LazuliteClusterBlock;
import de.dummtrottel.magical_minerals.common.block.geode.lazulite.LazuliteBlock;
import de.dummtrottel.magical_minerals.common.block.geode.lazulite.cluster.MediumLazuliteBudBlock;
import de.dummtrottel.magical_minerals.common.block.geode.lazulite.cluster.SmallLazuliteBudBlock;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import static net.minecraftforge.client.model.generators.ModelProvider.BLOCK_FOLDER;

public final class AllBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MagicalMinerals.ID);

    public static final RegistryObject<LazuliteBlock> LAZULITE_BLOCK = BLOCKS.register("lazulite_block", LazuliteBlock::new);

    public static final RegistryObject<LazuliteClusterBlock> LAZULITE_CLUSTER = BLOCKS.register("lazulite_cluster", LazuliteClusterBlock::new);
    public static final RegistryObject<SmallLazuliteBudBlock> SMALL_LAZULITE_BUD = BLOCKS.register("small_lazulite_bud", SmallLazuliteBudBlock::new);
    public static final RegistryObject<MediumLazuliteBudBlock> MEDIUM_LAZULITE_BUD = BLOCKS.register("medium_lazulite_bud", MediumLazuliteBudBlock::new);
    public static final RegistryObject<LargeLazuliteBudBlock> LARGE_LAZULITE_BUD = BLOCKS.register("large_lazulite_bud", LargeLazuliteBudBlock::new);
    public static final RegistryObject<BuddingLazuliteBlock> BUDDING_LAZULITE = BLOCKS.register("budding_lazulite", BuddingLazuliteBlock::new);

    private static final class AllBlocksModelProvider extends BlockModelProvider
    {
        private AllBlocksModelProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper)
        {
            super(generator, modid, existingFileHelper);
        }

        @Override
        protected void registerModels()
        {
            block(LAZULITE_BLOCK.get());
        }

        private void block(@NotNull Block block)
        {
            var name = block.getRegistryName();
            var path = name.getPath();

            cubeAll(path, modLoc("%s/%s".formatted(BLOCK_FOLDER, path)));
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
            block(LAZULITE_BLOCK.get());
            geode(BUDDING_LAZULITE.get());
        }

        private void block(@NotNull Block block)
        {
            var name = block.getRegistryName();
            var path = name.getPath();
            var parent = modLoc("%s/%s".formatted(BLOCK_FOLDER, path));

            /* blockstates */
            simpleBlock(block);

            /* blockmodel */
            models().cubeAll(path, parent);

            /* itemmodel */
            itemModels().withExistingParent(path, parent);
        }

        private void geode(@NotNull BaseBuddingBlock block)
        {
            var name = block.getRegistryName();
            var path = name.getPath();
            var parent = bud(block);

            block(block);

            cluster(block.full());
            smallBud(block.small(), parent);
            mediumBud(block.medium(), parent);
            largeBud(block.large(), parent);
        }

        private void cluster(@NotNull BaseClusterBlock block)
        {
            var name = block.getRegistryName();
            var path = name.getPath();

            directionalCross(block);

            itemModels()
                    .withExistingParent(path, mcLoc("item/generated"))
                    .texture(TextureSlot.LAYER0.getId(), modLoc("%s/%s".formatted(BLOCK_FOLDER, path)))
                    .transforms()
                    .transform(ItemTransforms.TransformType.HEAD)
                    .translation(0, 14, -5);
        }

        private ItemModelBuilder bud(@NotNull BaseGeodeBlock block)
        {
            return itemModels()
                    .withExistingParent("%s_bud".formatted(block.getType().getId()), mcLoc("item/generated"))
                    .transforms()
                    .transform(ItemTransforms.TransformType.FIRST_PERSON_RIGHT_HAND)
                    .rotation(0, -90, 25)
                    .translation(0, 5, 0)
                    .scale(0.68F, 0.68F, 0.68F)
                    .end().transform(ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND)
                    .translation(0, 4, 1)
                    .scale(0.55F, 0.55F, 0.55F)
                    .end().transform(ItemTransforms.TransformType.HEAD)
                    .translation(0, 14, -5)
                    .end().transform(ItemTransforms.TransformType.GUI)
                    .translation(0, 2, 0)
                    .end().end();
        }

        private void smallBud(@NotNull Block block, @NotNull ModelFile parent)
        {
            var name = block.getRegistryName();
            var path = name.getPath();

            directionalCross(block);

            itemModels()
                    .withExistingParent(path, parent.getLocation())
                    .texture(TextureSlot.LAYER0.getId(), modLoc("%s/%s".formatted(BLOCK_FOLDER, path)))
                    .transforms()
                    .transform(ItemTransforms.TransformType.FIRST_PERSON_RIGHT_HAND)
                    .rotation(0, -90, 25)
                    .translation(0, 6, 0)
                    .scale(0.68F, 0.68F, 0.68F)
                    .end().transform(ItemTransforms.TransformType.FIXED)
                    .translation(0, 7, 0)
                    .end().end();
        }

        private void mediumBud(@NotNull Block block, @NotNull ModelFile parent)
        {
            var name = block.getRegistryName();
            var path = name.getPath();

            directionalCross(block);

            itemModels()
                    .withExistingParent(path, parent.getLocation())
                    .texture(TextureSlot.LAYER0.getId(), modLoc("%s/%s".formatted(BLOCK_FOLDER, path)))
                    .transforms()
                    .transform(ItemTransforms.TransformType.FIXED)
                    .translation(0, 6, 0)
                    .end().end();
        }

        private void largeBud(@NotNull Block block, @NotNull ModelFile parent)
        {
            var name = block.getRegistryName();
            var path = name.getPath();

            directionalCross(block);

            itemModels()
                    .withExistingParent(path, parent.getLocation())
                    .texture(TextureSlot.LAYER0.getId(), modLoc("%s/%s".formatted(BLOCK_FOLDER, path)))
                    .transforms()
                    .transform(ItemTransforms.TransformType.FIXED)
                    .translation(0, 4, 0)
                    .end().end();
        }

        private void directionalCross(@NotNull Block block)
        {
            var name = block.getRegistryName();
            var path = name.getPath();

            directionalBlock(block, models().cross(path, modLoc("%s/%s".formatted(BLOCK_FOLDER, path))));
        }
    }

    public static BlockModelProvider models(DataGenerator generator, ExistingFileHelper helper)
    {
        return new AllBlocksModelProvider(generator, MagicalMinerals.ID, helper);
    }

    public static BlockStateProvider statesAndModels(DataGenerator generator, ExistingFileHelper helper)
    {
        return new AllBlocksStateModelProvider(generator, MagicalMinerals.ID, helper);
    }

    public static void register(IEventBus bus)
    {
        BLOCKS.register(bus);
    }
}
