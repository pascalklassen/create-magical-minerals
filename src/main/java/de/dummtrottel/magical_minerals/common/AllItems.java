package de.dummtrottel.magical_minerals.common;

import de.dummtrottel.magical_minerals.MagicalMinerals;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class AllItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MagicalMinerals.ID);

    public static final RegistryObject<BlockItem> LAZULITE_BLOCK = ITEMS.register("lazulite_block",
            () -> new BlockItem(AllBlocks.LAZULITE_BLOCK.get(), new Item.Properties().tab(MagicalMinerals.MINERAL_TAB).rarity(Rarity.RARE)));

    public static final RegistryObject<BlockItem> BUDDING_LAZULITE = ITEMS.register("budding_lazulite",
            () -> new BlockItem(AllBlocks.BUDDING_LAZULITE.get(), new Item.Properties().tab(MagicalMinerals.MINERAL_TAB).rarity(Rarity.RARE)));
    public static final RegistryObject<BlockItem> LAZULITE_CLUSTER = ITEMS.register("lazulite_cluster",
            () -> new BlockItem(AllBlocks.LAZULITE_CLUSTER.get(), new Item.Properties().tab(MagicalMinerals.MINERAL_TAB).rarity(Rarity.RARE)));

    public static final RegistryObject<BlockItem> SMALL_LAZULITE_BUD = ITEMS.register("small_lazulite_bud",
            () -> new BlockItem(AllBlocks.SMALL_LAZULITE_BUD.get(), new Item.Properties().tab(MagicalMinerals.MINERAL_TAB).rarity(Rarity.RARE)));

    public static final RegistryObject<BlockItem> MEDIUM_LAZULITE_BUD = ITEMS.register("medium_lazulite_bud",
            () -> new BlockItem(AllBlocks.MEDIUM_LAZULITE_BUD.get(), new Item.Properties().tab(MagicalMinerals.MINERAL_TAB).rarity(Rarity.RARE)));

    public static final RegistryObject<BlockItem> LARGE_LAZULITE_BUD = ITEMS.register("large_lazulite_bud",
            () -> new BlockItem(AllBlocks.LARGE_LAZULITE_BUD.get(), new Item.Properties().tab(MagicalMinerals.MINERAL_TAB).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> LAZULITE_SHARD = ITEMS.register("lazulite_shard",
            () -> new Item(new Item.Properties().tab(MagicalMinerals.MINERAL_TAB).rarity(Rarity.RARE)));

    private static final class AllItemModelProvider extends ItemModelProvider
    {
        public AllItemModelProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper)
        {
            super(generator, modid, existingFileHelper);
        }

        @Override
        protected void registerModels()
        {
            blockItem(AllBlocks.LAZULITE_BLOCK.get(), modLoc("block/lazulite_block"));
            blockItem(AllBlocks.BUDDING_LAZULITE.get(), modLoc("block/budding_lazulite"));

            cluster(
                    AllBlocks.LAZULITE_CLUSTER.get(),
                    AllBlocks.SMALL_LAZULITE_BUD.get(),
                    AllBlocks.MEDIUM_LAZULITE_BUD.get(),
                    AllBlocks.LARGE_LAZULITE_BUD.get()
            );

            basicItem(LAZULITE_SHARD.get());
        }

        private void cluster(Block cluster, Block smallBud, Block mediumBud, Block largeBud)
        {
            var name = cluster.getRegistryName();
            var path = name.getPath();

            var parent = bud(path.split("_")[0]);

            withExistingParent(path, mcLoc("item/generated"))
                    .texture("layer0", modLoc("block/" + path))
                    .transforms()
                    .transform(ItemTransforms.TransformType.HEAD)
                    .translation(0, 14, -5);

            smallBud(smallBud, parent);
            mediumBud(mediumBud, parent);
            largeBud(largeBud, parent);
        }

        private ItemModelBuilder bud(String name)
        {
            return withExistingParent(name + "_bud", mcLoc("item/generated"))
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

        private ItemModelBuilder smallBud(Block block, ModelFile parent)
        {
            var name = block.getRegistryName();
            var path = name.getPath();

            return withExistingParent(path, parent.getLocation())
                    .texture("layer0", modLoc("block/" + path))
                    .transforms()
                    .transform(ItemTransforms.TransformType.FIRST_PERSON_RIGHT_HAND)
                    .rotation(0, -90, 25)
                    .translation(0, 6, 0)
                    .scale(0.68F, 0.68F, 0.68F)
                    .end().transform(ItemTransforms.TransformType.FIXED)
                    .translation(0, 7, 0)
                    .end().end();
        }

        private ItemModelBuilder mediumBud(Block block, ModelFile parent)
        {
            var name = block.getRegistryName();
            var path = name.getPath();

            return withExistingParent(path, parent.getLocation())
                    .texture("layer0", modLoc("block/" + path))
                    .transforms()
                    .transform(ItemTransforms.TransformType.FIXED)
                    .translation(0, 6, 0)
                    .end().end();
        }

        private ItemModelBuilder largeBud(Block block, ModelFile parent)
        {
            var name = block.getRegistryName();
            var path = name.getPath();

            return withExistingParent(path, parent.getLocation())
                    .texture("layer0", modLoc("block/" + path))
                    .transforms()
                    .transform(ItemTransforms.TransformType.FIXED)
                    .translation(0, 4, 0)
                    .end().end();
        }

        private ItemModelBuilder blockItem(Block block, ResourceLocation parent)
        {
            var name = block.getRegistryName();
            var path = name.getPath();

            return withExistingParent(path, parent);
        }
    }

    public static ItemModelProvider models(DataGenerator generator, ExistingFileHelper helper)
    {
        return new AllItemModelProvider(generator, MagicalMinerals.ID, helper);
    }

    public static void register(IEventBus bus)
    {
        ITEMS.register(bus);
    }
}
