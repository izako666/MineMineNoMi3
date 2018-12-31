package xyz.pixelatedw.MineMineNoMi3.events;

import java.util.List;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import xyz.pixelatedw.MineMineNoMi3.MainConfig;
import xyz.pixelatedw.MineMineNoMi3.MainMod;
import xyz.pixelatedw.MineMineNoMi3.Values;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.extra.AbilityProperties;
import xyz.pixelatedw.MineMineNoMi3.api.network.PacketQuestSync;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.api.quests.Quest;
import xyz.pixelatedw.MineMineNoMi3.api.quests.QuestProperties;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedEntityData;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.misc.EntityDojoSensei;
import xyz.pixelatedw.MineMineNoMi3.helpers.QuestLogicHelper;
import xyz.pixelatedw.MineMineNoMi3.quests.EnumQuestlines;
import xyz.pixelatedw.MineMineNoMi3.quests.IHitCounterQuest;
import xyz.pixelatedw.MineMineNoMi3.quests.IInteractQuest;
import xyz.pixelatedw.MineMineNoMi3.quests.IKillQuest;
import xyz.pixelatedw.MineMineNoMi3.quests.ITimedQuest;

public class EventsQuestsProgress
{

	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event)
	{
		if (event.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			ExtendedEntityData props = ExtendedEntityData.get(player);
			AbilityProperties abilityProps = AbilityProperties.get(player);
			ItemStack heldItem = player.getHeldItem();

			QuestProperties questProps = QuestProperties.get(player);

			List<ITimedQuest> timedQuests = QuestLogicHelper.checkForITimedQuests(questProps);
			
			for(ITimedQuest quest : timedQuests)
				quest.onTimePassEvent(player);
		}
	}

	@SubscribeEvent
	public void onEntityInteract(EntityInteractEvent event)
	{
		EntityPlayer player = (EntityPlayer) event.entityPlayer;
		ExtendedEntityData props = ExtendedEntityData.get(player);
		QuestProperties questProps = QuestProperties.get(player);
		EntityLivingBase target = null;
		if (event.target instanceof EntityLivingBase)
			target = (EntityLivingBase) event.target;

		if (target != null && MainConfig.enableQuests)
		{
			//TODO Find a general solution for interacting with a quest giver and recieving either the current quest in the questline or opening a UI with available quests
			// Swordsman Progression Questline Logic
			if (target instanceof EntityDojoSensei)
			{
				// Turning in every quest based on the given questline
				if (questProps.questsInProgress() > 0 && !player.worldObj.isRemote)
				{
					if (QuestLogicHelper.turnInQuestlineQuest(player, EnumQuestlines.SWORDSMANPROGRESSION.getQuests()) > 0)
						return;
				}

				// Starting the next quest in the questline
				if (questProps.questsInProgress() < Values.MAX_ACTIVITIES)
				{
					Quest currentProgressionQuest = QuestLogicHelper.getQuestlineCurrentQuest(EnumQuestlines.SWORDSMANPROGRESSION.getQuests(), questProps);

					if (currentProgressionQuest != null && !questProps.hasQuestInTracker(currentProgressionQuest))
					{
						player.openGui(MainMod.getMineMineNoMi(), 6, player.worldObj, (int) target.posX, (int) target.posY, (int) target.posZ);
						return;
					}
				}
			}

			// General logic for progressing throught 'interact' activties
			if (questProps.questsInProgress() > 0 && !player.worldObj.isRemote)
			{
				for (int i = 0; i < Values.MAX_ACTIVITIES; i++)
				{
					if (questProps.getQuestIndexFromTracker(i) != null && questProps.getQuestIndexFromTracker(i) instanceof IInteractQuest)
					{
						if (((IInteractQuest) questProps.getQuestIndexFromTracker(i)).isTarget(player, target))
						{
							questProps.alterQuestProgress(questProps.getQuestIndexFromTracker(i), 1);
							break;
						}
					}
				}
			}

			if (!player.worldObj.isRemote)
				WyNetworkHelper.sendTo(new PacketQuestSync(questProps), (EntityPlayerMP) player);
		}

	}

	@SubscribeEvent
	public void onEntityDeath(LivingDeathEvent event)
	{
		if (event.source.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.source.getEntity();
			ExtendedEntityData props = ExtendedEntityData.get(player);
			QuestProperties questProps = QuestProperties.get(player);
			EntityLivingBase target = event.entityLiving;

			// General logic for progressing throught 'kill' activties
			if (questProps.questsInProgress() > 0)
			{
				for (int i = 0; i < Values.MAX_ACTIVITIES; i++)
				{
					if (questProps.getQuestIndexFromTracker(i) != null && questProps.getQuestIndexFromTracker(i) instanceof IKillQuest)
					{
						if (((IKillQuest) questProps.getQuestIndexFromTracker(i)).isTarget(player, target))
						{
							questProps.alterQuestProgress(questProps.getQuestIndexFromTracker(i), 1);
							// break;
						}
					}
				}
			}

			WyNetworkHelper.sendTo(new PacketQuestSync(questProps), (EntityPlayerMP) player);

		}
	}

	@SubscribeEvent
	public void onEntityAttackEvent(LivingHurtEvent event)
	{
		if (event.source.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.source.getEntity();
			ExtendedEntityData props = ExtendedEntityData.get(player);
			QuestProperties questProps = QuestProperties.get(player);
			EntityLivingBase target = event.entityLiving;

			// General logic for progressing throught 'hit counter' activties
			if (questProps.questsInProgress() > 0)
			{
				for (int i = 0; i < Values.MAX_ACTIVITIES; i++)
				{
					if (questProps.getQuestIndexFromTracker(i) != null && questProps.getQuestIndexFromTracker(i) instanceof IHitCounterQuest)
					{
						if (((IHitCounterQuest) questProps.getQuestIndexFromTracker(i)).checkHit(player, target, event.source))
						{
							questProps.alterQuestProgress(questProps.getQuestIndexFromTracker(i), 1);
						}
					}
				}
			}

			WyNetworkHelper.sendTo(new PacketQuestSync(questProps), (EntityPlayerMP) player);

		}
	}
}
