package xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles;

import java.util.ArrayList;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityAttribute;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityProjectile;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;

public class JuryoProjectiles
{
	public static ArrayList<Object[]> abilitiesClassesArray = new ArrayList();
	
	static
	{
		abilitiesClassesArray.add(new Object[] {SagariNoRyusei.class, ListAttributes.SAGARINORYUSEI});
	}
	
	/**FORGOLD Some particle effects, maybe some dark smoke */
	public static class SagariNoRyusei extends AbilityProjectile
	{
		public SagariNoRyusei(World world)
		{super(world);}
		
		public SagariNoRyusei(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public SagariNoRyusei(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}
	}
}
