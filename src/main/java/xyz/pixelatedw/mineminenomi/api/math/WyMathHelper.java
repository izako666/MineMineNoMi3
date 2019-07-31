package xyz.pixelatedw.mineminenomi.api.math;

import java.util.Random;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.WyHelper;

public class WyMathHelper
{	
	public static double percentage(double i, double j) { return (i / 100) * j; }
	
    public static double randomWithRange (int min, int max) { return new Random().nextInt(max + 1 - min) + min;}

    public static double randomDouble () { return new Random().nextDouble() * 2 - 1;}
    
    public static float clampf(float val, float min, float max) { return Math.max(min, Math.min(max, val)); }
    
    public static double clampd(double val, double min, double max) { return Math.max(min, Math.min(max, val)); }
    
    public static int clampi(int val, int min, int max) { return Math.max(min, Math.min(max, val)); }
    
    public static double lerp(double a, double b, double f) { return a + (b - a) * f; }

    public static int[] moveAway (PlayerEntity player, int[] current) {
        WyHelper.Direction dir = WyHelper.get8Directions(player);
        if (dir == WyHelper.Direction.NORTH) {
            current[2] -=1;
        } else if (dir == WyHelper.Direction.NORTH_EAST){
            current[0] +=1;
            current[2] -=1;
        } else if (dir == WyHelper.Direction.EAST) {
            current[0] += 1;
        } else if (dir == WyHelper.Direction.SOUTH_EAST) {
            current[0] +=1;
            current[2] +=1;
        } else if (dir == WyHelper.Direction.SOUTH) {
            current [2] +=1;
        } else if (dir == WyHelper.Direction.SOUTH_WEST) {
            current[0] -=1;
            current[2] +=1;
        } else if(dir == WyHelper.Direction.WEST) {
            current[0] -= 1;
        } else if (dir == WyHelper.Direction.NORTH_WEST) {
            current[0] -= 1;
            current[2] -= 1;
        }
        return current;
    }
}
