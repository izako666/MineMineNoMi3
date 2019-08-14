package xyz.pixelatedw.MineMineNoMi3.events.devilfruits;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedEntityData;

public class MochiKaradaEvent {

	@SubscribeEvent
	public void onAttacked(LivingAttackEvent e) {

		EntityLivingBase entity = e.entityLiving;
		
			ExtendedEntityData props = ExtendedEntityData.get(entity);
			if(props.isMochiFocused()) {
				e.setCanceled(true);
			}

		
	}
}
