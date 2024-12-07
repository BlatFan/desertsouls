package ru.blatfan.desertsouls;

import mod.maxbogomol.fluffy_fur.FluffyFurClient;
import mod.maxbogomol.fluffy_fur.client.gui.screen.FluffyFurMod;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import ru.blatfan.desertsouls.common.block.sun_altar.SunAltarRenderer;
import ru.blatfan.desertsouls.common.block.sun_pedestal.SunPedestalRenderer;
import ru.blatfan.desertsouls.common.entity.gin.blue_gin.BlueGin;
import ru.blatfan.desertsouls.common.entity.gin.blue_gin.BlueGinRenderer;
import ru.blatfan.desertsouls.common.entity.gin.red_gin.RedGin;
import ru.blatfan.desertsouls.common.entity.gin.red_gin.RedGinRenderer;
import ru.blatfan.desertsouls.common.entity.spell.SpellBallEntity;
import ru.blatfan.desertsouls.common.entity.spell.SpellBallRenderer;
import ru.blatfan.desertsouls.common.gui.gin.GinScreen;
import ru.blatfan.desertsouls.registry.BlockEntityRegistry;
import ru.blatfan.desertsouls.registry.EntityTypeRegistry;
import ru.blatfan.desertsouls.registry.MenuRegistry;
import ru.blatfan.desertsouls.registry.MobEffectRegistry;
import ru.blatfan.desertsouls.utils.ModColors;

public class Events {
    @Mod.EventBusSubscriber(modid = DesertSouls.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ffClient();
            MenuScreens.register(MenuRegistry.GIN_MENU.get(), GinScreen::new);
        }
        public static void ffClient() {
            FluffyFurMod MOD_INSTANCE = new FluffyFurMod(DesertSouls.MODID, "DesertSouls", "0.1")
                .setDev("BlatFan, LostSouls").setItem(new ItemStack(Items.SANDSTONE))
                .setEdition(1).setNameColor(ModColors.DESERT).setVersionColor(ModColors.DESERT)
                .setDescription(Component.translatable("mod_description.desertsouls"))
                .addLink(Component.translatable("link.desertsouls"), "https://lostsouls.su/");
            //FluffyFurPanorama MORA_PANORAMA = new FluffyFurPanorama(MOD_ID + ":panorama", Component.translatable("panorama.moracraft"))
            //    .setMod(MOD_INSTANCE).setItem(new ItemStack(ModBlocks.MORA_TABLE.get()))
            //    .setTexture(new ResourceLocation(MOD_ID, "textures/gui/title/panorama"))
            //    .setLogo(new ResourceLocation(MOD_ID, "textures/gui/title/moracraft.png"));
            
            FluffyFurClient.registerMod(MOD_INSTANCE);
            //FluffyFurClient.registerPanorama(MORA_PANORAMA);
        }
        @SubscribeEvent
        public static void rendererRegister(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(EntityTypeRegistry.SPELL_BALL.get(), SpellBallRenderer::new);
        }
        @SubscribeEvent
        public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(SpellBallRenderer.MODEL_LAYER_LOCATION, SpellBallRenderer::createBodyLayer);
        }
    }
    @Mod.EventBusSubscriber(modid = DesertSouls.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEvents {
        public void commonSetup(final FMLCommonSetupEvent event) {
        
        }
        
        @SubscribeEvent
        public void addCreative(BuildCreativeModeTabContentsEvent event) {
        
        }
        
        @SubscribeEvent
        public void onServerStarting(ServerStartingEvent event) {
        
        }
        @SubscribeEvent
        public void onBlockColors(RegisterColorHandlersEvent.Block event) {
            //event.register(new IColored.BlockColors(),
            //);
        }
        
        @SubscribeEvent
        public void onItemColors(RegisterColorHandlersEvent.Item event) {
            //for(RegistryObject<Item> item : ModItems.getAll())
            //event.register(new IColored.ItemBlockColors(),
            //);
        }
        @SubscribeEvent
        public static void registerRenderer(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(BlockEntityRegistry.SUN_ALTAR.get(), SunAltarRenderer::new);
            event.registerBlockEntityRenderer(BlockEntityRegistry.SUN_PEDESTAL.get(), SunPedestalRenderer::new);
            
            event.registerEntityRenderer(EntityTypeRegistry.BLUE_GIN.get(), BlueGinRenderer::new);
            event.registerEntityRenderer(EntityTypeRegistry.RED_GIN.get(), RedGinRenderer::new);
        }
        @SubscribeEvent
        public static void entityAttribute(EntityAttributeCreationEvent event){
            event.put(EntityTypeRegistry.BLUE_GIN.get(), BlueGin.createAttributes());
            event.put(EntityTypeRegistry.RED_GIN.get(), RedGin.createAttributes());
        }
    }
    @Mod.EventBusSubscriber(modid = DesertSouls.MODID)
    public static class ServerEvents {
        @SubscribeEvent
        public static void entityHurted(LivingHurtEvent event){
            if(event.getSource() == event.getEntity().level().damageSources().inFire() || event.getSource() == event.getEntity().level().damageSources().onFire())
                return;
            if(event.getEntity().hasEffect(MobEffectRegistry.GINS_BLESSING.get()))
                event.setAmount(event.getAmount()/2);
            if(event.getEntity().hasEffect(MobEffectRegistry.GINS_CURSE.get()))
                event.setAmount(event.getAmount()*2);
        }
    }
}
