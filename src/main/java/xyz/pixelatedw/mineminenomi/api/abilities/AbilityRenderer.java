package xyz.pixelatedw.mineminenomi.api.abilities;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.ModelArrow;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.ModelBazooka;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.ModelBrickBat;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.ModelFist;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.ModelHeart;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.ModelHydra;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.ModelMeigo;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.ModelMiniHollow;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.ModelNegativeHollow;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.ModelNoroNoroBeam;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.ModelPaw;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.ModelPheasant;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.ModelShark;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.ModelSpear;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.ModelTokuHollow;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.ModelTrident;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.ModelYukiRabi;

@OnlyIn(Dist.CLIENT)
public class AbilityRenderer extends EntityRenderer<AbilityProjectile>
{
	private double scaleX, scaleY, scaleZ, rotAngle, rotX, rotY, rotZ, red, blue, green, renderPosX, renderPosY, renderPosZ;
	private float alpha;
	private EntityModel model;
	private AbilityAttribute ablAttr;
	private ResourceLocation texture;

	public AbilityRenderer(EntityRendererManager renderManager, AbilityAttribute attr)
	{
		super(renderManager);
		this.ablAttr = attr;
		this.texture = ablAttr.getProjectileTexture();
	}

	@Override
	public void doRender(AbilityProjectile entity, double par2, double par4, double par6, float par8, float par9)
	{
    	GL11.glPushMatrix();
		{
			this.scaleX = this.ablAttr.getProjectileSize()[0];
			this.scaleY = this.ablAttr.getProjectileSize()[1];
			this.scaleZ = this.ablAttr.getProjectileSize()[2];
	
			this.red = this.ablAttr.getProjectileColor().getRed();
			this.green = this.ablAttr.getProjectileColor().getGreen();
			this.blue = this.ablAttr.getProjectileColor().getBlue();
			this.alpha = this.ablAttr.getProjectileAlpha();
	
			this.rotX = this.ablAttr.getProjectileXRotation();
			this.rotY = this.ablAttr.getProjectileYRotation();
			this.rotZ = this.ablAttr.getProjectileZRotation();
	
			this.renderPosX = this.ablAttr.getModelOffsets()[0];
			this.renderPosY = this.ablAttr.getModelOffsets()[1];
			this.renderPosZ = this.ablAttr.getModelOffsets()[2];
			
			//System.out.println(this.model);
			
			switch(this.ablAttr.getProjectileModel())
			{
				case CUBE:
					this.model = new ModelCube(); break;
				case SPHERE:
					this.model = new ModelSphere(); break;
				case FIST:
					this.model = new ModelFist(); break;
				case ARROW:
					this.model = new ModelArrow(); break;	
				case HEART:
					this.model = new ModelHeart(); break;	
				case SPEAR:
					this.model = new ModelSpear(); break;	
				case HYDRA:
					this.model = new ModelHydra(); break;	
				case PAW:
					this.model = new ModelPaw(); break;				
				case TRIDENT:
					this.model = new ModelTrident(); break;	
				case SHARK:
					this.model = new ModelShark(); break;	
				case PHEASANT:
					this.model = new ModelPheasant(); break;	
					
				case NORO_NORO_BEAM:
					this.model = new ModelNoroNoroBeam(); break;	
				case MEIGO:
					this.model = new ModelMeigo(); break;	
				case BRICK_BAT:
					this.model = new ModelBrickBat(); break;	
				case NEGATIVE_HOLLOW:
					this.model = new ModelNegativeHollow(); break;	
				case MINI_HOLLOW:
					this.model = new ModelMiniHollow(); break;	
				case TOKU_HOLLOW:
					this.model = new ModelTokuHollow(); break;	
				case YUKI_RABI:
					this.model = new ModelYukiRabi(); break;	
				case BAZOOKA:
					this.model = new ModelBazooka(); break;
					
				default:
					this.model = new ModelCube();
	
			}
			
			GL11.glPushMatrix();
	
			GL11.glTranslated(par2 + renderPosX, par4 + renderPosY, par6 + renderPosZ);
			if (this.texture == null)
				GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	
			GL11.glRotatef(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * par9 - 180.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * par9, 1.0F, 0.0F, 0.0F);
	
			GL11.glRotatef(180, 0, 0, 1);
	
			if (rotX != 0)
				GL11.glRotated(rotX, 1, 0, 0);
			if (rotY != 0)
				GL11.glRotated(rotY, 0, 1, 0);
			if (rotZ != 0)
				GL11.glRotated(rotZ, 0, 0, 1);
	
			GL11.glColor4f((float) this.red / 255, (float) this.green / 255, (float) this.blue / 255, this.alpha / 255);
			GL11.glScaled(this.scaleX, this.scaleY, this.scaleZ);
	
			if (this.texture != null)
				Minecraft.getInstance().textureManager.bindTexture(this.getEntityTexture(entity));
	
			if (this.model != null)
				this.model.render(entity, (float) par2, (float) par4, (float) par6, 0.0F, 0.0F, 0.0625F);
	
			GL11.glDisable(GL11.GL_BLEND);
			if (this.texture == null)
				GL11.glEnable(GL11.GL_TEXTURE_2D);
	
			GL11.glPopMatrix();
		}
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(AbilityProjectile entity)
	{
		return this.texture;
	}

	public static class Factory implements IRenderFactory<AbilityProjectile>
	{
		private AbilityAttribute attr;
		
		public Factory() {}
		
		public Factory(AbilityAttribute attr)
		{
			this.attr = attr;
		}

		@Override
		public EntityRenderer<? super AbilityProjectile> createRenderFor(EntityRendererManager manager)
		{
			return new AbilityRenderer(manager, this.attr);
		}
	}

}
