package de.dummtrottel.magical_minerals.common.datagen;

import de.dummtrottel.magical_minerals.MagicalMinerals;
import de.dummtrottel.magical_minerals.common.AllBlocks;
import de.dummtrottel.magical_minerals.common.block.geode.BaseBuddingBlock;
import de.dummtrottel.magical_minerals.common.block.geode.BaseClusterBlock;
import de.dummtrottel.magical_minerals.common.block.geode.BaseGeodeBlock;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import static net.minecraftforge.client.model.generators.ModelProvider.BLOCK_FOLDER;

public final class BlockStateGenerator extends BlockStateProvider
{
    private BlockStateGenerator(DataGenerator gen, String modid, ExistingFileHelper exFileHelper)
    {
        super(gen, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels()
    {
        block(AllBlocks.LAZULITE_BLOCK.get());
        geode(AllBlocks.BUDDING_LAZULITE.get());
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

    public static BlockStateGenerator create(DataGenerator gen, ExistingFileHelper exFileHelper)
    {
        return new BlockStateGenerator(gen, MagicalMinerals.ID, exFileHelper);
    }
}
