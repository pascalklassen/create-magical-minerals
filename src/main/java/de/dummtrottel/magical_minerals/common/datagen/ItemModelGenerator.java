package de.dummtrottel.magical_minerals.common.datagen;

import de.dummtrottel.magical_minerals.MagicalMinerals;
import de.dummtrottel.magical_minerals.common.AllItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public final class ItemModelGenerator extends ItemModelProvider
{
    private ItemModelGenerator(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper)
    {
        super(generator, modid, existingFileHelper);
    }

    @Override
    protected void registerModels()
    {
        basicItem(AllItems.LAZULITE_SHARD.get());
        basicItem(AllItems.LAZULITE_POWDER.get());
    }

    public static ItemModelGenerator create(DataGenerator generator, ExistingFileHelper existingFileHelper)
    {
        return new ItemModelGenerator(generator, MagicalMinerals.ID, existingFileHelper);
    }
}
