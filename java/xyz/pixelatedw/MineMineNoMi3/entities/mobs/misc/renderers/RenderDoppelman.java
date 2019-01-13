package xyz.pixelatedw.MineMineNoMi3.entities.mobs.misc.renderers;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedEntityData;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.MobRenderer;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.marines.models.ModelMarine;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.misc.EntityDoppelman;

@SideOnly(Side.CLIENT)
public class RenderDoppelman extends MobRenderer
{

	public RenderDoppelman()
	{
		super(new ModelMarine(), "doppelman");
	}
	
	protected void preRenderCallback(EntityLivingBase livingBase, float f) 
	{
		float scale = 1 + ((float)ExtendedEntityData.get(livingBase).getDoriki() / 7);

		if(scale < 1)
			scale = 1;
		
		GL11.glScalef(scale, scale, scale);
	}
	
}
