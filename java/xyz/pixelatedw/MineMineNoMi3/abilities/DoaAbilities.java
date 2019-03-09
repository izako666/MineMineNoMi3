package xyz.pixelatedw.MineMineNoMi3.abilities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedEntityData;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSync;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSyncInfo;

public class DoaAbilities {

    public static Ability[] abilitiesArray = new Ability[]{new AirDoor()};

    public static class AirDoor extends Ability {
        public AirDoor() {
            super(ListAttributes.AIRDOOR);
        }

        public void passive(EntityPlayer player) {
            ExtendedEntityData propz = ExtendedEntityData.get(player);
            propz.setInAirWorld(true);
            WyNetworkHelper.sendTo(new PacketSync(propz), (EntityPlayerMP) player);
            WyNetworkHelper.sendToAll(new PacketSyncInfo(player.getEntityId(), propz));
            super.passive(player);
        }

        public void duringPassive(EntityPlayer player, int timer) {
            if (timer > 40 * 8) {
                this.setPassiveActive(false);
                this.setCooldownActive(true);
                this.endPassive(player);
            }
        }

        public void endPassive(EntityPlayer player) {
            this.startCooldown();
            this.startExtUpdate(player);
            ExtendedEntityData propz = ExtendedEntityData.get(player);
            propz.setInAirWorld(false);
            WyNetworkHelper.sendTo(new PacketSync(propz), (EntityPlayerMP) player);
            WyNetworkHelper.sendToAll(new PacketSyncInfo(player.getEntityId(), propz));
        }

    }
}
