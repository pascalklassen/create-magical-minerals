package de.dummtrottel.magical_minerals.common;

import de.dummtrottel.magical_minerals.MagicalMinerals;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
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
    public static final RegistryObject<Item> LAZULITE_POWDER = ITEMS.register("lazulite_powder",
            () -> new Item(new Item.Properties().tab(MagicalMinerals.MINERAL_TAB).rarity(Rarity.RARE)));

    public static void register(IEventBus bus)
    {
        ITEMS.register(bus);
    }
}
