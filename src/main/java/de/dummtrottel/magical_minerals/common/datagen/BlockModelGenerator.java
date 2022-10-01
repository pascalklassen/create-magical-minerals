package de.dummtrottel.magical_minerals.common.datagen;

import de.dummtrottel.magical_minerals.MagicalMinerals;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

public final class BlockModelGenerator extends BlockModelProvider
{
    private BlockModelGenerator(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper)
    {
        super(generator, modid, existingFileHelper);
    }

    @Override
    protected void registerModels()
    {
    }

    private void block(@NotNull Block block)
    {
        var name = block.getRegistryName();
        var path = name.getPath();

        cubeAll(path, modLoc("%s/%s".formatted(BLOCK_FOLDER, path)));
    }

    public static BlockModelGenerator create(DataGenerator generator, ExistingFileHelper existingFileHelper)
    {
        return new BlockModelGenerator(generator, MagicalMinerals.ID, existingFileHelper);
    }
}
