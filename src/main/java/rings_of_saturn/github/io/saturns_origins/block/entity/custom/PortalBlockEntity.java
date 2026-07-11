package rings_of_saturn.github.io.saturns_origins.block.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
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
    private String dim = "minecraft:overworld";
    private int[] pos = new int[3];
    private int timer = 40*20;

    public String getPlayerName() {
        return this.playerName;
    }

    public void setPlayerName(String newName) {
        this.playerName = newName;
        markDirty();
    }

    public void setDim(String newDim) {
        this.dim = newDim;
        markDirty();
    }

    public void setPos(int[] newPos) {
        this.pos = newPos;
        markDirty();
    }

    public int[] getTPPos() {
        return this.pos;
    }

    public int getTimer() {
        return this.timer;
    }

    public String getDim() {
        return this.dim;
    }

    public void decrementTimer() {
        this.timer--;
        markDirty();
    }

    @Override
    protected void writeData(WriteView view) {
        view.putString("playerName", this.playerName);
        view.putString("dim", this.dim);
        view.putIntArray("pos", this.pos);
        view.putInt("timer", this.timer);

        super.writeData(view);
    }

    @Override
    protected void readData(ReadView view) {
        this.playerName = view.getString("playerName", "Saturns_Rings_");
        this.dim = view.getString("dim", "minecraft:overworld");
        this.timer = view.getInt("timer", 0);
        if(view.getOptionalIntArray("pos").isPresent())
            this.pos = view.getOptionalIntArray("pos").get();

        super.readData(view);
    }
}
