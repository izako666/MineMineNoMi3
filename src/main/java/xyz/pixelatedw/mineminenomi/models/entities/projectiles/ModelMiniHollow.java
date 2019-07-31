package xyz.pixelatedw.mineminenomi.models.entities.projectiles;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;

public class ModelMiniHollow extends EntityModel 
{
    public RendererModel head;
    public RendererModel body;
    public RendererModel rightarm;
    public RendererModel leftarm;
    public RendererModel rightleg;
    public RendererModel rightleg_1;

    public ModelMiniHollow() 
    {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.rightleg_1 = new RendererModel(this, 19, 0);
        this.rightleg_1.setRotationPoint(0.6000000238418579F, 10.100000381469727F, 0.5F);
        this.rightleg_1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.setRotateAngle(rightleg_1, 0.0F, -0.0F, -0.6108652353286743F);
        this.rightarm = new RendererModel(this, 14, 0);
        this.rightarm.setRotationPoint(0.5F, 7.699999809265137F, 0.5F);
        this.rightarm.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(rightarm, 0.0F, -0.0F, 0.942477822303772F);
        this.body = new RendererModel(this, 9, 0);
        this.body.setRotationPoint(0.5F, 8.0F, 0.5F);
        this.body.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.head = new RendererModel(this, 0, 0);
        this.head.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.head.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
        this.rightleg = new RendererModel(this, 19, 0);
        this.rightleg.setRotationPoint(0.6000000238418579F, 9.5F, 0.5F);
        this.rightleg.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.setRotateAngle(rightleg, 0.0F, -0.0F, 0.6108652353286743F);
        this.leftarm = new RendererModel(this, 14, 0);
        this.leftarm.setRotationPoint(1.0F, 8.5F, 0.5F);
        this.leftarm.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(leftarm, 0.0F, -0.0F, -0.942477822303772F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) 
    { 
        this.rightleg_1.render(f5);
        this.rightarm.render(f5);
        this.body.render(f5);
        this.head.render(f5);
        this.rightleg.render(f5);
        this.leftarm.render(f5);
    }

    public void setRotateAngle(RendererModel RendererModel, float x, float y, float z) 
    {
        RendererModel.rotateAngleX = x;
        RendererModel.rotateAngleY = y;
        RendererModel.rotateAngleZ = z;
    }
}

