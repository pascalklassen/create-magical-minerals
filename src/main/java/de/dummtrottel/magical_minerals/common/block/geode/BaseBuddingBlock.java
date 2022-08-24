package de.dummtrottel.magical_minerals.common.block.geode;

import de.dummtrottel.magical_minerals.common.block.geode.lazulite.cluster.LazuliteClusterBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public abstract class BaseBuddingBlock extends BaseGeodeBlock implements BuddingStateProvider
{
    private static final Direction[] DIRECTIONS = Direction.values();

    protected final int growthChance;

    public BaseBuddingBlock(@NotNull MineralType type, BlockBehaviour.Properties properties, int growthChance)
    {
        super(type, properties.randomTicks());
        this.growthChance = growthChance;
    }

    public int getGrowthChance()
    {
        return growthChance;
    }

    /**
     * @deprecated call via net.minecraft.world.level.block.state.BlockBehavior.BlockStateBase#getPistonPushReaction
     * whenever possible.
     * Implementing/overriding is fine.
     */
    @SuppressWarnings("deprecation")
    @Override
    public @NotNull PushReaction getPistonPushReaction(@NotNull BlockState state)
    {
        return PushReaction.DESTROY;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void randomTick(@NotNull BlockState state,
                           @NotNull ServerLevel level,
                           @NotNull BlockPos pos,
                           Random random)
    {
        if (random.nextInt(getGrowthChance()) == 0)
        {
            var direction = DIRECTIONS[random.nextInt(DIRECTIONS.length)];
            var blockpos = pos.relative(direction);
            var blockstate = level.getBlockState(blockpos);
            Block block = null;

            if (canClusterGrowAtState(blockstate))
            {
                block = small();
            }
            else if (blockstate.is(small()) && blockstate.getValue(BaseClusterBlock.FACING) == direction)
            {
                block = medium();
            }
            else if (blockstate.is(medium()) && blockstate.getValue(BaseClusterBlock.FACING) == direction)
            {
                block = large();
            }
            else if (blockstate.is(large()) && blockstate.getValue(BaseClusterBlock.FACING) == direction)
            {
                block = full();
            }

            if (block != null)
            {
                BlockState newState = block
                        .defaultBlockState()
                        .setValue(LazuliteClusterBlock.FACING, direction)
                        .setValue(LazuliteClusterBlock.WATERLOGGED, blockstate.getFluidState().getType() == Fluids.WATER);

                level.setBlockAndUpdate(blockpos, newState);
            }
        }
    }

    public static boolean canClusterGrowAtState(BlockState state)
    {
        return state.isAir() || state.is(Blocks.WATER) && state.getFluidState().getAmount() == 8;
    }
}
