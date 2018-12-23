package xyz.pixelatedw.MineMineNoMi3.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketParticles;

public class EventsKilo {

    @SubscribeEvent
    public void onEntityAttack(LivingHurtEvent event) {
        Entity entity = event.source.getSourceOfDamage();
        Entity attacked = event.entity;
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            Object[] effects = player.getActivePotionEffects().toArray();

            for (int i = 0; i < effects.length; i++) {
                PotionEffect currentEffect = (PotionEffect) effects[i];
                if (currentEffect.getPotionID() == 5 && currentEffect.getAmplifier() == 8) {
                    WyNetworkHelper.sendToAllAround(new PacketParticles(ID.PARTICLEFX_KILO, attacked.posX, attacked.posY, attacked.posZ), player.dimension, player.posX, player.posY, player.posZ, ID.GENERIC_PARTICLES_RENDER_DISTANCE);
                    player.removePotionEffect(5);
                    attacked.motionY += 1;


                }
            }

        }
    }
}
