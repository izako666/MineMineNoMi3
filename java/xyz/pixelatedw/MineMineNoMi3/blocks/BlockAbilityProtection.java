package xyz.pixelatedw.MineMineNoMi3.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.blocks.tileentities.TileEntityAbilityProtection;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedWorldData;

public class BlockAbilityProtection extends BlockContainer
{

	public BlockAbilityProtection()
	{
		super(Material.iron);
		this.setTickRandomly(true);
	}

    public void onBlockAdded(World world, int posX, int posY, int posZ) 
    {
		ExtendedWorldData worldData = ExtendedWorldData.get(world);
		TileEntityAbilityProtection centerData = ((TileEntityAbilityProtection) world.getTileEntity(posX, posY, posZ));
		System.out.println("2 : " + centerData.getRadius());
		
		int radius = centerData.getRadius();
    	int minPosX = posX - radius;
    	int minPosY = posY - radius;
    	int minPosZ = posZ - radius;
    	int maxPosX = posX + radius;
    	int maxPosY = posY + radius;
    	int maxPosZ = posZ + radius;
    	
    	worldData.addRestrictedArea(new int[] {minPosX, minPosY, minPosZ}, new int[] {maxPosX, maxPosY, maxPosZ});
    }
    
    public void onBlockDestroyedByPlayer(World world, int posX, int posY, int posZ, int metaData) 
    {
    	ExtendedWorldData worldData = ExtendedWorldData.get(world);

    	worldData.removeRestrictedArea(posX, posY, posZ);
    }

	public TileEntity createNewTileEntity(World world, int i) { return new TileEntityAbilityProtection();}
	
    
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {return WyHelper.NULL_AABB;} 
	
	public boolean isOpaqueCube() {return false;}

    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass() { return 1; }

    public boolean renderAsNormalBlock() { return false; }
      
    public int getRenderType() { return 0; }

}
