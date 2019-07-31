package xyz.pixelatedw.mineminenomi.abilities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.Values;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.WyHelper.Direction;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.NikyuProjectiles;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;

public class NikyuAbilities 
{

	static
	{
		Values.abilityWebAppExtraParams.put("ursusshock", new String[] {"desc", "The user compresses air and sends it towards the opponent to create a massive explosion."});
		Values.abilityWebAppExtraParams.put("padho", new String[] {"desc", "Launches a paw-shaped shockwave at the opponent."});
		Values.abilityWebAppExtraParams.put("tsupparipadho", new String[] {"desc", "Similar to 'Pad Ho', but fires a barrage of shockwaves."});
		Values.abilityWebAppExtraParams.put("hanpatsu", new String[] {"desc", "Anyone the user punches gets sent flying far into the air."});
	}
	
	public static Ability[] abilitiesArray = new Ability[] {new PadHo(), new TsuppariPadHo(), new Hanpatsu(), new UrsusShock()};
	
	public static class Hanpatsu extends Ability
	{
		public Hanpatsu() 
		{
			super(ModAttributes.HANPATSU); 
		}
		
		@Override
		public void hitEntity(PlayerEntity player, LivingEntity target) 
		{			
			double newPosX = 0, newPosY = 0, newPosZ = 0;
			
			newPosY += 55;
			Direction dir = WyHelper.get4Directions(player);
			if(dir == WyHelper.Direction.SOUTH)
				newPosX += WyMathHelper.randomWithRange(-200, 200);
			else if(dir == WyHelper.Direction.EAST)
				newPosX -= WyMathHelper.randomWithRange(-200, 200);
			else if(dir == WyHelper.Direction.NORTH)
				newPosZ += WyMathHelper.randomWithRange(-200, 200);
			else if(dir == WyHelper.Direction.WEST)  
				newPosZ -= WyMathHelper.randomWithRange(-200, 200);

			target.setPositionAndUpdate(target.posX + newPosX, target.posY + newPosY, target.posZ + newPosZ);

			super.hitEntity(player, target);
		}
	}
	
	public static class TsuppariPadHo extends Ability
	{
		public TsuppariPadHo() 
		{
			super(ModAttributes.TSUPPARI_PAD_HO); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{		
			this.projectile = new NikyuProjectiles.PadHo(player.world, player, attr);
			super.use(player);
		}
	}
	
	public static class PadHo extends Ability
	{
		public PadHo() 
		{
			super(ModAttributes.PAD_HO); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{		
			this.projectile = new NikyuProjectiles.PadHo(player.world, player, attr);
			super.use(player);
		}
	}
	
	public static class UrsusShock extends Ability
	{
		public UrsusShock() 
		{
			super(ModAttributes.URSUS_SHOCK); 
		}
		
		@Override
		public void endCharging(PlayerEntity player)
		{		
			this.projectile = new NikyuProjectiles.UrsusShock(player.world, player, attr);
			super.endCharging(player);
		}
	}
}
