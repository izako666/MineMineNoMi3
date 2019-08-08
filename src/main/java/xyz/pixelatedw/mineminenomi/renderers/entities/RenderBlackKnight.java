package xyz.pixelatedw.MineMineNoMi3.entities.mobs.misc.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.model.ModelPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.MobRenderer;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.misc.EntityBlackKnight;

@OnlyIn(Dist.CLIENT)
public class RenderBlackKnight extends MobRenderer
{

	public RenderBlackKnight(RenderManager renderManager)
	{
		super(renderManager, new ModelPlayer(0, true));
	}
		
	protected ResourceLocation getEntityTexture(Entity entity) 
	{
        Minecraft minecraft = Minecraft.getInstance();
        ResourceLocation rs = ((AbstractClientPlayer)minecraft.player).getLocationSkin();
        
		return rs;
	}
	
	public static class Factory implements IRenderFactory<EntityBlackKnight>
	{
		public Factory() {}
		
		@Override
		public Render<? super EntityBlackKnight> createRenderFor(RenderManager manager)
		{
			return new RenderBlackKnight(manager);
		}
	}
}
