package rings_of_saturn.github.io.saturns_origins.block.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import rings_of_saturn.github.io.saturns_origins.block.entity.ModBlockEntities;

public class PortalBlockEntity extends BlockEntity {
    public PortalBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public PortalBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PORTAL, pos, state);
    }


    private String playerName = "";

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String newName) {
        playerName = newName;
        markDirty();
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        nbt.putString("playerName", playerName);

        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        playerName = nbt.getString("playerName");

        super.readNbt(nbt);
    }
}
