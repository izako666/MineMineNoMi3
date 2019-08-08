package xyz.pixelatedw.mineminenomi.events;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityViewRenderEvent.CameraSetup;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.IAbilityData;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.entity.extraeffects.ExtraEffectCapability;
import xyz.pixelatedw.mineminenomi.data.entity.extraeffects.IExtraEffect;
import xyz.pixelatedw.mineminenomi.entities.zoan.RenderZoanMorph;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZoanInfo;
import xyz.pixelatedw.mineminenomi.helpers.CombatHelper;
import xyz.pixelatedw.mineminenomi.helpers.MorphsHelper;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.packets.PacketClientSyncAll;

@OnlyIn(Dist.CLIENT)
public class EventsMorphs
{

	private Minecraft mc;

	// private RenderAbareHimatsuri abareHimatsuri = new RenderAbareHimatsuri(new ModelAbareHimatsuri());

	// private EntityRenderer renderer, prevRenderer;
	private String prevZoanPoint;

	public EventsMorphs()
	{
		this.mc = Minecraft.getInstance();
	}

	/*
	 * @SubscribeEvent
	 * public void onRenderPlayerEvent(RenderPlayerEvent.Pre event)
	 * {
	 * ExtendedEntityData propz = ExtendedEntityData.get(event.PlayerEntity);
	 * if (propz.isInAirWorld())
	 * {
	 * event.setCanceled(true);
	 * }
	 * }
	 */

	@SubscribeEvent
	public void onEntityRendered(RenderLivingEvent.Pre event)
	{
		IDevilFruit props = DevilFruitCapability.get(event.getEntity());
		IAbilityData abilityProps = AbilityDataCapability.get(event.getEntity());

		if (!(event.getEntity() instanceof PlayerEntity))
			return;

		if (!WyHelper.isNullOrEmpty(props.getZoanPoint()))
		{
			if (event.getEntity().hurtTime > 0)
			{
				GL11.glPushMatrix();
				GL11.glColor3f(1.0f, 0, 0);
				GL11.glPopMatrix();
			}

			event.setCanceled(true);

			for (ZoanInfo info : MorphsHelper.getZoanInfoList())
			{
				if (!info.getDevilFruit().equalsIgnoreCase(props.getDevilFruit()))
					continue;

				if (!info.getForm().equalsIgnoreCase(props.getZoanPoint()))
					continue;

				if (CombatHelper.isPassiveActive(abilityProps, info.getAttribute()))
				{
					RenderZoanMorph render = (RenderZoanMorph) info.getFactory().createRenderFor(Minecraft.getInstance().getRenderManager());
					this.doRenderZoanMorph(render, event.getX(), event.getY(), event.getZ(), event.getEntity());
					break;
				}
			}
		}

		if (props.getDevilFruit().equalsIgnoreCase("sukesuke") && event.getEntity().isInvisible())
			event.setCanceled(true);

		IExtraEffect extraEffectProps = ExtraEffectCapability.get(event.getEntity());

		if (event.getEntity() instanceof PlayerEntity)
		{
			if (extraEffectProps.hasExtraEffect(ID.EXTRAEFFECT_ABAREHIMATSURI))
			{
				if (event.getEntity().onGround)
				{
					/*
					 * IBlockState block = event.getEntity().world.getBlockState(new BlockPos(event.getEntity().posX, event.getEntity().posY - 2, event.getEntity().posZ));
					 * String texture = Blocks.DIRT.getIcon(1, 0).getIconName();
					 * //int blockTint = event.getEntity().world.getBlockState(new BlockPos(event.getEntity().posX, event.getEntity().posY - 2, event.getEntity().posZ));//.colorMultiplier(event.entity.worldObj, (int) event.entity.posX, (int) event.entity.posY - 2, (int) event.entity.posZ);
					 * if (block.getIcon(1, 0) != null)
					 * texture = block.getIcon(1, 0).getIconName();
					 * abareHimatsuri.setTextureAndTint(texture, blockTint);
					 */
				}

				// if (!event.getEntity().onGround)
				// abareHimatsuri.doRender(event.getEntity(), event.getX(), event.getY(), event.getZ(), 0F, 0.0625F);
			}
		}
	}

	private void doRenderZoanMorph(RenderZoanMorph render, double x, double y, double z, LivingEntity entity)
	{
		if (Minecraft.getInstance().player.equals(entity))
			render.doRender(entity, 0D, -1.625D, 0D, 0F, 0.0625F);
		else
			render.doRender(entity, x, y, z, 0F, 0.0625F);
	}

	@SubscribeEvent
	public void onEntityConstructing(EntityJoinWorldEvent event)
	{
		if (event.getEntity() instanceof PlayerEntity)
		{
			PlayerEntity owner = (PlayerEntity) event.getEntity();
			IDevilFruit props = DevilFruitCapability.get(owner);

			if (!WyHelper.isNullOrEmpty(props.getZoanPoint()) && !props.getZoanPoint().equalsIgnoreCase("yomi"))
			{
				props.setZoanPoint("");

				byte syncedData = 0b0000001;
				ModNetwork.sendToAll(new PacketClientSyncAll(owner.getEntityId(), props, syncedData));
			}
		}
	}

	@SubscribeEvent
	public void morphHandRendering(RenderHandEvent event)
	{
		PlayerEntity player = Minecraft.getInstance().player;

		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
		IEntityStats entityStatsProps = EntityStatsCapability.get(player);
		IAbilityData abilityDataProps = AbilityDataCapability.get(player);

		boolean renderHandFlag = false;
		boolean renderHandEffectFlag = false;

		boolean hasHotBoilingSpecial = CombatHelper.isPassiveActive(abilityDataProps, ModAttributes.HOT_BOILING_SPECIAL);
		boolean hasBusoshokuHaki = CombatHelper.isPassiveActive(abilityDataProps, ModAttributes.BUSOSHOKU_HAKI);

		if (player.getHeldItemMainhand() == null && (hasBusoshokuHaki || hasHotBoilingSpecial))
		{
			renderHandFlag = true;
		}

		for (ZoanInfo info : MorphsHelper.getZoanInfoList())
		{
			if (!info.getDevilFruit().equalsIgnoreCase(devilFruitProps.getDevilFruit()))
				continue;

			if (!info.getForm().equalsIgnoreCase(devilFruitProps.getZoanPoint()))
				continue;

			if (CombatHelper.isPassiveActive(abilityDataProps, info.getAttribute()))
			{
				renderHandFlag = true;
				break;
			}
		}

		GL11.glPushMatrix();
		{
			int x = 0, y = 0, u = 16, v = 16;
		}
		GL11.glPopMatrix();

		if (renderHandFlag)
		{
			event.setCanceled(true);
			// HandRendererHelper.renderHand((EntityClientPlayerMP) player);
		}

		/*
		 * for(Object[] x :
		 * HandEffectsHelper.getMap().get(props.getUsedFruit())) {
		 * }
		 */
	}

	@SubscribeEvent
	public void onZoanSizeChange(CameraSetup event)
	{
		/*
		 * if(event.getEntity() instanceof PlayerEntity)
		 * {
		 * PlayerEntity player = (PlayerEntity) event.getEntity();
		 * IDevilFruit props = DevilFruitCapability.get(player);
		 * IAbilityData abilityProps = AbilityDataCapability.get(player);
		 * if(WyHelper.isNullOrEmpty(props.getDevilFruit()))
		 * return;
		 * float eyeHeight = player.getDefaultEyeHeight();
		 * for(ZoanInfo info : MorphsHelper.geZoanInfoList())
		 * {
		 * if(!info.getDevilFruit().equalsIgnoreCase(props.getDevilFruit()))
		 * continue;
		 * if(!info.getForm().equalsIgnoreCase(props.getZoanPoint()))
		 * continue;
		 * if(CombatHelper.isPassiveActive(abilityProps, info.getAttribute()))
		 * {
		 * eyeHeight = (float) (player.getDefaultEyeHeight() (info.getHeight() / 1.75));
		 * eyeHeight = MathHelper.clamp(eyeHeight, 0.22F, eyeHeight);
		 * break;
		 * }
		 * }
		 * ObfuscationReflectionHelper.setPrivateValue(PlayerEntity.class, player, eyeHeight, "eyeHeight");
		 * }
		 */
	}

	@SubscribeEvent
	public void onZoanSizeChange(TickEvent.PlayerTickEvent event)
	{
		if (event.phase == TickEvent.Phase.END)
		{
			PlayerEntity player = event.player;
			IDevilFruit props = DevilFruitCapability.get(player);
			IAbilityData abilityProps = AbilityDataCapability.get(player);

			if (WyHelper.isNullOrEmpty(props.getDevilFruit()))
				return;

			double posX = player.posX;
			double posY = player.posY;
			double posZ = player.posZ;

			double width = 0.6F / 2;
			double height = 1.8F;

			float eyeHeight = player.getEyeHeight();

			for (ZoanInfo info : MorphsHelper.getZoanInfoList())
			{
				if (!info.getDevilFruit().equalsIgnoreCase(props.getDevilFruit()))
					continue;

				if (!info.getForm().equalsIgnoreCase(props.getZoanPoint()))
					continue;

				if (CombatHelper.isPassiveActive(abilityProps, info.getAttribute()))
				{
					width = info.getWidth() / 2;
					height = info.getHeight();

					eyeHeight = (float) (player.getEyeHeight() * (info.getHeight() / 1.75));
					eyeHeight = MathHelper.clamp(eyeHeight, 0.22F, eyeHeight);

					break;
				}
			}

			player.setBoundingBox(new AxisAlignedBB(posX - width, posY, posZ - width, posX + width, posY + height, posZ + width));
			ObfuscationReflectionHelper.setPrivateValue(PlayerEntity.class, player, eyeHeight, "eyeHeight");
		}
	}
}
