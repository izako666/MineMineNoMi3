package xyz.pixelatedw.MineMineNoMi3.abilities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketPlayer;

public class KiloAbilities {

    public static Ability[] abilitiesArray = new Ability[]{new Weightless()};

    public static class Weightless extends Ability {

        public Weightless() {
            super(ListAttributes.WEIGHTLESS);
        }

        public void passive(EntityPlayer player) {
            super.passive(player);

        }

        public void duringPassive(EntityPlayer player, int passiveTimer) {
                if (player.onGround) {
                    this.setPassiveActive(false);
                    this.setCooldownActive(true);
                    this.endPassive(player);
                } else if (player.getHeldItem() != null && player.getHeldItem().getItem() == ListMisc.Umbrella) {
                    player.fallDistance = 0;
                    double xGo = (WyMathHelper.randomWithRange(-9,9) / 40 );
                    double yGo = (WyMathHelper.randomWithRange(-9,9) / 40 );
                    double chance = (WyMathHelper.randomWithRange(1,1000));

                    if (chance > 930) {
                        movePlayer("+",xGo,0,yGo,player);
                    } else if(player.motionX == 0.0000 && player.motionZ == 0.0000) {
                        movePlayer("=",0,-0.2,0,player);
                    }
                }


        }

        public void endPassive(EntityPlayer player) {
            this.startCooldown();
            this.startExtUpdate(player);

        }


        private static void movePlayer(String c, double x, double y, double z, EntityPlayer p) {
            WyNetworkHelper.sendTo(new PacketPlayer("motion" + c, x, y, z), (EntityPlayerMP) p);
        }

    }
}

