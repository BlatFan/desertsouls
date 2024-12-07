package ru.blatfan.desertsouls.common.entity.spell;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import ru.blatfan.desertsouls.DesertSouls;

public class SpellBallRenderer extends EntityRenderer<SpellBallEntity> { 
    private static final ResourceLocation SOLID_TEXTURE = DesertSouls.loc("textures/entity/spell_ball/solid.png");
    private static final ResourceLocation FRAME_TEXTURE = DesertSouls.loc("textures/entity/spell_ball/frame.png");
    
    public static final ModelLayerLocation MODEL_LAYER_LOCATION = new ModelLayerLocation(DesertSouls.loc("spell_ball_model"), "main");

    private final ModelPart orb;
    private final ModelPart swirl;
    
    public SpellBallRenderer(EntityRendererProvider.Context context) {
        super(context);
        ModelPart modelpart = context.bakeLayer(MODEL_LAYER_LOCATION);
        this.orb = modelpart.getChild("orb");
        this.swirl = modelpart.getChild("swirl");
    }
    
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("orb", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F), PartPose.ZERO);
        partdefinition.addOrReplaceChild("swirl", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F), PartPose.ZERO);
        partdefinition.addOrReplaceChild("swirl2", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F), PartPose.ZERO);
        return LayerDefinition.create(meshdefinition, 8, 8);
    }
    
    @Override
    public void render(SpellBallEntity entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int light) {
        float r = 0.9f;
        float g = 0.9f;
        float b = 0.2f;
        
        poseStack.pushPose();
        poseStack.translate(0, entity.getBoundingBox().getYsize() * .5f, 0);
        float scale = 1 + Mth.sin((entity.tickCount + partialTicks) * .06f) * .05f;
        poseStack.scale(scale, scale, scale);
        poseStack.scale(.6f, .6f, .6f);
        float f = entity.tickCount + partialTicks;
        poseStack.pushPose();
        poseStack.scale(.4f, .4f, .4f);
        float swirlX = Mth.cos(.05f * f) * 90;
        float swirlY = Mth.sin(.05f * f) * 90;
        float swirlZ = Mth.cos(.05f * f + 5464) * 90;
        
        poseStack.mulPose(Axis.XP.rotationDegrees(swirlX * .45f));
        poseStack.mulPose(Axis.YP.rotationDegrees(swirlY * .45f));
        poseStack.mulPose(Axis.ZP.rotationDegrees(swirlZ * .45f));
        VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(SOLID_TEXTURE));
        this.orb.render(poseStack, consumer, LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY, r, g, b, 1f);
        poseStack.popPose();
        
        consumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(getTextureLocation(entity)));
        
        poseStack.mulPose(Axis.XP.rotationDegrees(swirlX));
        poseStack.mulPose(Axis.YP.rotationDegrees(swirlY));
        poseStack.mulPose(Axis.ZP.rotationDegrees(swirlZ));
        this.orb.render(poseStack, consumer, LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY, r, g, b, 0.6f);
        
        poseStack.mulPose(Axis.XP.rotationDegrees(swirlZ));
        poseStack.mulPose(Axis.YP.rotationDegrees(swirlX));
        poseStack.mulPose(Axis.ZP.rotationDegrees(swirlY));
        poseStack.scale(1.5f, 1.5f, 1.5f);
        this.swirl.render(poseStack, consumer, LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY, r, g, b, 0.8f);
        
        poseStack.mulPose(Axis.XP.rotationDegrees(swirlY));
        poseStack.mulPose(Axis.YP.rotationDegrees(swirlX));
        poseStack.mulPose(Axis.ZP.rotationDegrees(swirlZ));
        poseStack.scale(1.8f, 1.8f, 1.8f);
        this.swirl.render(poseStack, consumer, LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY, r, g, b, 1f);
        poseStack.popPose();
    }
    
    @Override
    public ResourceLocation getTextureLocation(SpellBallEntity spellBallEntity) {
        return FRAME_TEXTURE;
    }
}
