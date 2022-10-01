package de.dummtrottel.magical_minerals.common;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GeodeBlockSettings;
import net.minecraft.world.level.levelgen.GeodeCrackSettings;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.GeodeConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public final class AllFeatures
{
    public static final Holder<ConfiguredFeature<GeodeConfiguration, ?>> LAZULITE_GEODE =
            FeatureUtils.register("lazulite_geode", Feature.GEODE,
                    new GeodeConfiguration(
                            new GeodeBlockSettings(
                                    /* filling layer */
                                    BlockStateProvider.simple(Blocks.AIR),
                                    /* inner layer */
                                    BlockStateProvider.simple(AllBlocks.LAZULITE_BLOCK.get()),
                                    /* alternative inner layer */
                                    BlockStateProvider.simple(AllBlocks.BUDDING_LAZULITE.get()),
                                    /* middle layer */
                                    BlockStateProvider.simple(Blocks.CALCITE),
                                    /* outer layer */
                                    BlockStateProvider.simple(Blocks.SMOOTH_BASALT),
                                    /* inner placements */
                                    List.of(
                                            AllBlocks.SMALL_LAZULITE_BUD.get().defaultBlockState(),
                                            AllBlocks.MEDIUM_LAZULITE_BUD.get().defaultBlockState(),
                                            AllBlocks.LARGE_LAZULITE_BUD.get().defaultBlockState(),
                                            AllBlocks.LAZULITE_CLUSTER.get().defaultBlockState()
                                    ),
                                    BlockTags.FEATURES_CANNOT_REPLACE, BlockTags.GEODE_INVALID_BLOCKS
                            ),
                            new GeodeLayerSettings(1.7D, 2.2D, 3.2D, 4.2D),
                            new GeodeCrackSettings(0.95D, 2.0D, 2),
                            /* potential placement chance */
                            0.35D,
                            /* alternative layer chance */
                            0.04D,
                            /* placements require layer0 alternate */
                            true,
                            /* outer wall distance */
                            UniformInt.of(4, 6),
                            /* distribution points */
                            UniformInt.of(3, 4),
                            /* point offset */
                            UniformInt.of(1, 2),
                            /* min gen offset */
                            -16,
                            /* max gen offset */
                            16,
                            /* noise multiplier */
                            0.05D,
                            /* invalid blocks threshold */
                            1)
            );

    public static final Holder<PlacedFeature> LAZULITE_GEODE_PLACED = PlacementUtils.register("lazulite_geode_placed",
            LAZULITE_GEODE,
            RarityFilter.onAverageOnceEvery(100),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-45), VerticalAnchor.absolute(-25)),
            BiomeFilter.biome());
}
