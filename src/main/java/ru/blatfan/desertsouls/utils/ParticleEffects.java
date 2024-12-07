package ru.blatfan.desertsouls.utils;

import mod.maxbogomol.fluffy_fur.client.particle.ParticleBuilder;
import mod.maxbogomol.fluffy_fur.client.particle.data.ColorParticleData;
import mod.maxbogomol.fluffy_fur.client.particle.data.GenericParticleData;
import mod.maxbogomol.fluffy_fur.client.particle.data.LightParticleData;
import mod.maxbogomol.fluffy_fur.client.particle.data.SpinParticleData;
import mod.maxbogomol.fluffy_fur.registry.client.FluffyFurParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.awt.*;

@OnlyIn(Dist.CLIENT)
public class ParticleEffects {
    private static final ParticleBuilder wisp =
        ParticleBuilder.create(FluffyFurParticles.WISP)
            .setLightData(LightParticleData.GLOW)
            .setTransparencyData(GenericParticleData.create(0.5f, 0).build())
            .setScaleData(GenericParticleData.create(0.25f, 0).build())
            .setSpinData(SpinParticleData.create().randomSpin(0.5f).build())
            .setLifetime(30)
            .randomOffset(0.125f);
    private static final ParticleBuilder star = ParticleBuilder.create(FluffyFurParticles.STAR)
        .setTransparencyData(GenericParticleData.create(0.5f, 0).build())
        .setScaleData(GenericParticleData.create(0.15f, 0).build())
        .setSpinData(SpinParticleData.create().randomSpin(0.5f).build())
        .setLifetime(30)
        .randomOffset(0.125f);
    private static final ParticleBuilder sparkle =
        ParticleBuilder.create(FluffyFurParticles.SPARKLE)
            .setTransparencyData(GenericParticleData.create(0.5f, 0).build())
            .setScaleData(GenericParticleData.create(0.15f, 0).build())
            .setSpinData(SpinParticleData.create().randomSpin(0.5f).build())
            .setLifetime(30)
            .randomOffset(0.125f);
    
    public static void spawnWisp(double x, double y, double z, Color color){
        spawnWisp(x, y, z, 0, 0, 0, color);
    }
    public static void spawnWisp(double x, double y, double z, double vx, double vy, double vz, Color color){
        ClientLevel level = Minecraft.getInstance().level;
        wisp.setColorData(ColorParticleData.create(color).build())
            .setVelocity(vx, vy, vz)
            .spawn(level, x, y, z);
    }
    public static void spawnFire(BlockPos pos){
        ClientLevel level = Minecraft.getInstance().level;
        ParticleBuilder.create(FluffyFurParticles.WISP)
            .setLightData(LightParticleData.GLOW)
            .setTransparencyData(GenericParticleData.create(0.5f, 0).build())
            .setScaleData(GenericParticleData.create(0.25f, 0).build())
            .setSpinData(SpinParticleData.create().randomSpin(0.5f).build())
            .setLifetime(30)
            .randomOffset(0.125f)
            .setColorData(ColorParticleData.create(Color.RED).build())
            .setVelocity(0, 0.005, 0)
            .spawn(level, pos.getX()+0.5, pos.getY()+6, pos.getZ()+0.5);
        ParticleBuilder.create(FluffyFurParticles.WISP)
            .setLightData(LightParticleData.GLOW)
            .setTransparencyData(GenericParticleData.create(0.5f, 0).build())
            .setScaleData(GenericParticleData.create(0.25f, 0).build())
            .setSpinData(SpinParticleData.create().randomSpin(0.5f).build())
            .setLifetime(60)
            .randomOffset(0.125f)
            .setColorData(ColorParticleData.create(Color.BLACK).build())
            .setVelocity(0, 0.05, 0)
            .spawn(level, pos.getX()+0.5, pos.getY()+0.6, pos.getZ()+0.5);
    }
    
    public static void spawnStar(double x, double y, double z, Color color){
        spawnStar(x, y, z, 0, 0, 0, color);
    }
    public static void spawnStar(BlockPos pos, double vx, double vy, double vz, Color color){
        double x2 = 0, z2 = 0;
        if(vx>0) x2 = pos.getX() + 1;
        if(vx<0) x2 = pos.getX();
        if(vz>0) z2 = pos.getZ() + 1;
        if(vz<0) z2 = pos.getZ();
        ClientLevel level = Minecraft.getInstance().level;
        star.setColorData(ColorParticleData.create(color).build())
            .setHasPhysics(true)
            .setVelocity(vx, vy, vz)
            .spawn(level, x2, pos.getY()+1, z2);
    }
    public static void spawnStar(double x, double y, double z, double vx, double vy, double vz, Color color){
        ClientLevel level = Minecraft.getInstance().level;
        star.setColorData(ColorParticleData.create(color).build())
            .setVelocity(vx, vy, vz)
            .spawn(level, x, y, z);
    }
    
    public static void spawnSparkle(double x, double y, double z, Color color){
        spawnSparkle(x, y, z, 0, 0, 0, color);
    }
    public static void spawnSparkle(double x, double y, double z, double vx, double vy, double vz, Color color){
        ClientLevel level = Minecraft.getInstance().level;
        sparkle.setColorData(ColorParticleData.create(color).build())
            .setVelocity(vx, vy, vz)
            .spawn(level, x, y, z);
    }
    
    public static void spawnSparkleTrail(double x, double y, double z, double vx, double vy, double vz, Color color){
        ClientLevel level = Minecraft.getInstance().level;
        sparkle.setColorData(ColorParticleData.create(color).build())
            .setTransparencyData(GenericParticleData.create(0.5f).build())
            .setScaleData(GenericParticleData.create(0.25f).build())
            .setVelocity(vx, vy, vz)
            .spawn(level, x, y, z);
    }
    
    public static void spawnDustsParticles(double x, double y, double z, Color color) {
        spawnDustsParticles(x, y, z, 0, 0, 0, color);
    }
    public static void spawnDustsParticles(double x, double y, double z, double vx, double vy, double vz, Color color) {
        spawnSparkle(x, y, z, vx, vy, vz, color);
        spawnWisp(x, y, z, vx, vy, vz, color);
    }
}