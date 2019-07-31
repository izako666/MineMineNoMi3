package xyz.pixelatedw.mineminenomi.abilities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.Values;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.FishKarateProjectiles;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.packets.PacketParticles;

public class FishKarateAbilities 
{
	static
	{
		Values.abilityWebAppExtraParams.put("uchimizu", new String[] {"desc", "The user hurls big and fast water droplets at the opponent.", "dorikiRequiredForFishman", "800"});
		Values.abilityWebAppExtraParams.put("murasame", new String[] {"desc", "The user fires densely compressed shark-shaped waterbullet at the opponent.", "dorikiRequiredForFishman", "2000"});
		Values.abilityWebAppExtraParams.put("samehadashotei", new String[] {"desc", "The user concentrates their power to their palms, protecting themselves from attacks.", "dorikiRequiredForFishman", "3000"});
		Values.abilityWebAppExtraParams.put("karakusagawaraseiken", new String[] {"desc", "The user punches the air, which sends a shockwave through water vapor in the air.", "dorikiRequiredForFishman", "7500"});
		Values.abilityWebAppExtraParams.put("kachiagehaisoku", new String[] {"desc", "The user delivers a powerful kick to the opponent's chin.", "dorikiRequiredForFishman", "2500"});
	}
	
	public static Ability UCHIMIZU = new Uchimizu();
	public static Ability MURASAME = new Murasame();
	public static Ability KACHIAGE_HAISOKU = new KachiageHaisoku();
	public static Ability SAMEHADA_SHOTEI = new SamehadaShotei();
	public static Ability KARAKUSAGAWARA_SEIKEN = new KarakusagawaraSeiken();
	
	public static Ability[] abilitiesArray = new Ability[] {UCHIMIZU, MURASAME, SAMEHADA_SHOTEI, KARAKUSAGAWARA_SEIKEN, KACHIAGE_HAISOKU};
	
	public static class Uchimizu extends Ability
	{
		public Uchimizu() 
		{
			super(ModAttributes.UCHIMIZU); 
		}
			
		@Override
		public void use(PlayerEntity player)
		{
			this.projectile = new FishKarateProjectiles.Uchimizu(player.world, player, attr);
			super.use(player);
		}
	}
	
	public static class Murasame extends Ability
	{
		public Murasame() 
		{
			super(ModAttributes.MURASAME); 
		}
			
		@Override
		public void use(PlayerEntity player)
		{
			this.projectile = new FishKarateProjectiles.Murasame(player.world, player, attr);
			super.use(player);
		}
	}
	
	public static class KachiageHaisoku extends Ability
	{
		public KachiageHaisoku() 
		{
			super(ModAttributes.KACHIAGE_HAISOKU); 
		}
			
		@Override
		public void hitEntity(PlayerEntity player, LivingEntity target) 
		{
			super.hitEntity(player, target);
			int damage = 10;
			if(player.isInWater())
				damage = 40;
			target.attackEntityFrom(DamageSource.causePlayerDamage(player), damage);
		}
	}
	
	public static class SamehadaShotei extends Ability
	{
		public SamehadaShotei() 
		{
			super(ModAttributes.SAMEHADA_SHOTEI); 
		}
		
		@Override
		public void startPassive(PlayerEntity player) 
		{
			super.startPassive(player);
		}
		
		@Override
		public void duringPassive(PlayerEntity player, int passiveTimer) 
		{
			ModNetwork.sendToAllAround(new PacketParticles(ID.PARTICLEFX_SAMEHADA, player), player);
		}
	}
	
	public static class KarakusagawaraSeiken extends Ability
	{
		public KarakusagawaraSeiken() 
		{
			super(ModAttributes.KARAKUSAGAWARA_SEIKEN); 
		}
			
		@Override
		public void use(PlayerEntity player)
		{	
			if(!this.isOnCooldown)
			{
				for(LivingEntity elb : WyHelper.getEntitiesNear(player, 10))
				{
					elb.attackEntityFrom(DamageSource.causePlayerDamage(player), 20);
				}
				super.use(player);
			}			
		}
	}
}
