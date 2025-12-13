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
        return this.playerName;
    }

    public void setPlayerName(String newName) {
        this.playerName = newName;
        markDirty();
    }



    private int timer = 40*20;

    public int getTimer() {
        return this.timer;
    }

    public void decrementTimer() {
        this.timer--;
        markDirty();
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        nbt.putString("playerName", this.playerName);
        nbt.putInt("timer", this.timer);

        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        this.playerName = nbt.getString("playerName");
        this.timer = nbt.getInt("timer");

        super.readNbt(nbt);
    }
}
