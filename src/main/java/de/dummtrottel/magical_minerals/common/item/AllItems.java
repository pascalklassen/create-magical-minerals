package de.dummtrottel.magical_minerals.common.item;

import de.dummtrottel.magical_minerals.MagicalMinerals;
import de.dummtrottel.magical_minerals.common.block.AllBlocks;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AllItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MagicalMinerals.ID);

    public static final RegistryObject<BlockItem> LAZULITE_BLOCK = ITEMS.register("lazulite_block",
            () -> new BlockItem(AllBlocks.LAZULITE_BLOCK.get(), new Item.Properties().tab(MagicalMinerals.MINERAL_TAB).rarity(Rarity.RARE)));

    public static final RegistryObject<BlockItem> LAZULITE_CLUSTER = ITEMS.register("lazulite_cluster",
            () -> new BlockItem(AllBlocks.LAZULITE_CLUSTER.get(), new Item.Properties().tab(MagicalMinerals.MINERAL_TAB).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> LAZULITE_SHARD = ITEMS.register("lazulite_shard",
            () -> new Item(new Item.Properties().tab(MagicalMinerals.MINERAL_TAB).rarity(Rarity.RARE)));

    private static final class AllItemModelsProvider extends ItemModelProvider
    {
        public AllItemModelsProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper)
        {
            super(generator, modid, existingFileHelper);
        }

        @Override
        protected void registerModels()
        {
            blockItem(AllBlocks.LAZULITE_BLOCK.get(), modLoc("block/lazulite_block"));
            blockItem(AllBlocks.LAZULITE_CLUSTER.get(), mcLoc("item/generated"))
                    .texture("layer0", modLoc("block/lazulite_cluster"))
                    .transforms()
                    .transform(ItemTransforms.TransformType.HEAD)
                    .translation(0, 14, -5);

            basicItem(LAZULITE_SHARD.get());
        }

        private ItemModelBuilder blockItem(Block block, ResourceLocation parent)
        {
            var name = block.getRegistryName();
            var path = name.getPath();

            return withExistingParent(path, parent);
        }
    }

    public static ItemModelProvider provider(DataGenerator generator, ExistingFileHelper helper)
    {
        return new AllItemModelsProvider(generator, MagicalMinerals.ID, helper);
    }

    public static void register(IEventBus bus)
    {
        ITEMS.register(bus);
    }


}
