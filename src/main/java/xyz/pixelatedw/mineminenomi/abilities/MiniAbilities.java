package xyz.pixelatedw.mineminenomi.abilities;

public class MiniAbilities
{

	static
	{
	}

	/*public static Ability[] abilitiesArray = new Ability[] {new MiniMiniNoFullRebound()};

	public static class MiniMiniNoFullRebound extends Ability
	{
		public MiniMiniNoFullRebound()
		{
			super(ModAttributes.MINI_MINI_NO_FULL_REBOUND);
		}

		@Override
		public void passive(PlayerEntity player)
		{
			ExtendedEntityData props = ExtendedEntityData.get(player);

			if (!this.isOnCooldown && (props.getZoanPoint().equalsIgnoreCase("n/a") || props.getZoanPoint().equalsIgnoreCase("mini")))
			{
				super.passive(player);
			}
		}

		@Override
		public void startPassive(PlayerEntity player)
		{
			ExtendedEntityData props = ExtendedEntityData.get(player);

			if (props.getZoanPoint().isEmpty())
				props.setZoanPoint("n/a");

			WyNetworkHelper.sendTo(new PacketNewAABB(0.2F, 0.3F), (ServerPlayerEntity) player);

			props.setZoanPoint("mini");
			WyNetworkHelper.sendTo(new PacketSync(props), (ServerPlayerEntity) player);
			WyNetworkHelper.sendToAll(new PacketSyncInfo(player.getDisplayName(), props));
		}

		@Override
		public void endPassive(PlayerEntity player)
		{
			ExtendedEntityData props = ExtendedEntityData.get(player);

			WyNetworkHelper.sendTo(new PacketNewAABB(0.6F, 1.8F), (ServerPlayerEntity) player);

			props.setZoanPoint("n/a");
			WyNetworkHelper.sendTo(new PacketSync(props), (ServerPlayerEntity) player);
			WyNetworkHelper.sendToAll(new PacketSyncInfo(player.getDisplayName(), props));
		}
	}*/

}
