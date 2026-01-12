package rings_of_saturn.github.io.saturns_origins.rendering.models;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import rings_of_saturn.github.io.saturns_origins.entity.custom.FeatherProjectileEntity;

// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class FeatherProjectileModel extends EntityModel<FeatherProjectileEntity> {
	private final ModelPart bb_main;
	public FeatherProjectileModel(ModelPart root) {
		this.bb_main = root.getChild("bb_main");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(32, 20).cuboid(7.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(26, 29).cuboid(6.0F, -1.0F, -2.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 29).cuboid(5.0F, -1.0F, -3.0F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
		.uv(0, 20).cuboid(3.0F, -1.0F, -3.0F, 2.0F, 1.0F, 6.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-1.0F, -1.0F, -4.0F, 4.0F, 1.0F, 7.0F, new Dilation(0.0F))
		.uv(17, 20).cuboid(-2.0F, -1.0F, -3.0F, 1.0F, 1.0F, 6.0F, new Dilation(0.0F))
		.uv(0, 10).cuboid(-3.0F, -1.0F, -4.0F, 1.0F, 1.0F, 7.0F, new Dilation(0.0F))
		.uv(23, 0).cuboid(-4.0F, -1.0F, -4.0F, 1.0F, 1.0F, 6.0F, new Dilation(0.0F))
		.uv(17, 10).cuboid(-5.0F, -1.0F, -4.0F, 1.0F, 1.0F, 7.0F, new Dilation(0.0F))
		.uv(10, 57).cuboid(-6.0F, -1.0F, -3.0F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
		.uv(56, 59).cuboid(-7.0F, -1.0F, -2.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void setAngles(FeatherProjectileEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		bb_main.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}