package de.dummtrottel.magical_minerals.common;

import de.dummtrottel.magical_minerals.MagicalMinerals;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AllItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MagicalMinerals.ID);

    public static final RegistryObject<Item> LAZULITE_SHARD = ITEMS.register("lazulite_shard",
            () -> new Item(new Item.Properties().tab(MagicalMinerals.MINERAL_TAB)));

    public static void register(IEventBus bus)
    {
        ITEMS.register(bus);
    }
}
