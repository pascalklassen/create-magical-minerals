package de.dummtrottel.magical_minerals.common.block.geode;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public abstract class BaseClusterBlock extends BaseGeodeBlock
{
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    protected final VoxelShape boxNorth;
    protected final VoxelShape boxSouth;
    protected final VoxelShape boxEast;
    protected final VoxelShape boxWest;
    protected final VoxelShape boxUp;
    protected final VoxelShape boxDown;
    
    private static final double BOX_GROUND = 0;
    private static final double BOX_FULL_BLOCK = 16;

    public BaseClusterBlock(@NotNull MineralType type, int size, int offset, BlockBehaviour.Properties properties)
    {
        super(type, properties);

        registerDefaultState(
                defaultBlockState()
                        .setValue(WATERLOGGED, Boolean.FALSE)
                        .setValue(FACING, Direction.UP)
        );

        boxUp = Block.box(offset, BOX_GROUND, offset, BOX_FULL_BLOCK - offset, size, BOX_FULL_BLOCK - offset);
        boxDown = Block.box(offset, BOX_FULL_BLOCK - size, offset, BOX_FULL_BLOCK - offset, BOX_FULL_BLOCK, BOX_FULL_BLOCK - offset);

        boxNorth = Block.box(offset, offset, BOX_FULL_BLOCK - size, BOX_FULL_BLOCK - offset, BOX_FULL_BLOCK - offset, BOX_FULL_BLOCK);
        boxSouth = Block.box(offset, offset, BOX_GROUND, BOX_FULL_BLOCK - offset, BOX_FULL_BLOCK - offset, size);

        boxEast = Block.box(BOX_GROUND, offset, offset, size, BOX_FULL_BLOCK - offset, BOX_FULL_BLOCK - offset);
        boxWest = Block.box(BOX_FULL_BLOCK - size, offset, offset, BOX_FULL_BLOCK, BOX_FULL_BLOCK - offset, BOX_FULL_BLOCK - offset);
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull VoxelShape getShape(BlockState state,
                                        @NotNull BlockGetter level,
                                        @NotNull BlockPos pos,
                                        @NotNull CollisionContext context)
    {
        var direction = state.getValue(FACING);

        return switch (direction)
                {
                    case NORTH -> boxNorth;
                    case SOUTH -> boxSouth;
                    case EAST -> boxEast;
                    case WEST -> boxWest;
                    case DOWN -> boxDown;
                    case UP -> boxUp;
                };
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos)
    {
        var direction = state.getValue(FACING);
        var opposite = pos.relative(direction.getOpposite());

        return level
                .getBlockState(opposite)
                .isFaceSturdy(level, opposite, direction);
    }

    /**
     * Update the provided state given the provided neighbor direction and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
     * returns its solidified counterpart.
     * Note that this method should ideally consider only the specific direction passed in.
     */
    @SuppressWarnings("deprecation")
    @Override
    public @NotNull BlockState updateShape(BlockState state,
                                           @NotNull Direction direction,
                                           @NotNull BlockState neighborState,
                                           @NotNull LevelAccessor level,
                                           @NotNull BlockPos pos,
                                           @NotNull BlockPos neighborPos)
    {
        if (state.getValue(WATERLOGGED))
        {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        return direction == state.getValue(FACING).getOpposite() &&
                !state.canSurvive(level, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        var level = context.getLevel();
        var pos = context.getClickedPos();

        return defaultBlockState()
                .setValue(WATERLOGGED, level.getFluidState(pos).getType() == Fluids.WATER)
                .setValue(FACING, context.getClickedFace());
    }

    /**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     * @deprecated call via net.minecraft.world.level.block.state.BlockBehavior.BlockStateBase#rotate whenever
     * possible. Implementing/overriding is fine.
     */
    @SuppressWarnings("deprecation")
    @Override
    public @NotNull BlockState rotate(BlockState state, Rotation rotation)
    {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    /**
     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     * @deprecated call via net.minecraft.world.level.block.state.BlockBehavior.BlockStateBase#mirror whenever
     * possible. Implementing/overriding is fine.
     */
    @SuppressWarnings("deprecation")
    @Override
    public @NotNull BlockState mirror(BlockState state, Mirror mirror)
    {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull FluidState getFluidState(BlockState state)
    {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(WATERLOGGED, FACING);
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
}
