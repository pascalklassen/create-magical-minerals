package de.dummtrottel.magical_minerals.common.block.geode;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public abstract class BaseGeodeBlock extends Block
{
    private final @NotNull MineralType type;

    public BaseGeodeBlock(@NotNull MineralType type, Properties properties)
    {
        super(properties);
        this.type = type;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onProjectileHit(Level level,
                                @NotNull BlockState state,
                                @NotNull BlockHitResult result,
                                @NotNull Projectile projectile)
    {
        if (!level.isClientSide())
        {
            var pos = result.getBlockPos();

            level.playSound(null, pos, SoundEvents.AMETHYST_BLOCK_HIT, SoundSource.BLOCKS, 1.0F, 0.5F + level.getRandom().nextFloat() * 1.2F);
            level.playSound(null, pos, SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 1.0F, 0.5F + level.getRandom().nextFloat() * 1.2F);
        }
    }

    public @NotNull MineralType getType()
    {
        return type;
    }
}
