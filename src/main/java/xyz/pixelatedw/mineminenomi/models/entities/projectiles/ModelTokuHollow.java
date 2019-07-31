package xyz.pixelatedw.mineminenomi.models.entities.projectiles;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;

/**
 * Toku Hollow.tcn - TechneToTabulaImporter
 * Created using Tabula 7.0.0
 */
public class ModelTokuHollow extends EntityModel {
    public RendererModel head;
    public RendererModel head2;
    public RendererModel head3;
    public RendererModel head4;
    public RendererModel body;
    public RendererModel rightarm;
    public RendererModel leftarm;
    public RendererModel rightleg;
    public RendererModel leftleg;

    public ModelTokuHollow() {
        this.textureWidth = 256;
        this.textureHeight = 128;
        this.head2 = new RendererModel(this, 0, 50);
        this.head2.setRotationPoint(-11.0F, -7.0F, -5.5F);
        this.head2.addBox(0.0F, 0.0F, 0.0F, 22, 22, 25, 0.0F);
        this.head3 = new RendererModel(this, 98, 50);
        this.head3.setRotationPoint(-12.5F, -7.0F, -4.0F);
        this.head3.addBox(0.0F, 0.0F, 0.0F, 25, 22, 22, 0.0F);
        this.head4 = new RendererModel(this, 98, 0);
        this.head4.setRotationPoint(-11.0F, -8.5F, -4.0F);
        this.head4.addBox(0.0F, 0.0F, 0.0F, 22, 25, 22, 0.0F);
        this.rightarm = new RendererModel(this, 188, 22);
        this.rightarm.setRotationPoint(-17.0F, 8.0F, -9.0F);
        this.rightarm.addBox(0.0F, 0.0F, 0.0F, 3, 3, 12, 0.0F);
        this.setRotateAngle(rightarm, 0.1396263390779495F, 0.4188790321350098F, 0.0F);
        this.head = new RendererModel(this, 0, 0);
        this.head.setRotationPoint(-12.0F, -8.0F, -5.0F);
        this.head.addBox(0.0F, 0.0F, 0.0F, 24, 24, 24, 0.0F);
        this.rightleg = new RendererModel(this, 188, 14);
        this.rightleg.setRotationPoint(-4.0F, 5.0F, 28.5F);
        this.rightleg.addBox(0.0F, 0.0F, 0.0F, 2, 2, 4, 0.0F);
        this.body = new RendererModel(this, 188, 0);
        this.body.setRotationPoint(-4.0F, 4.0F, 19.5F);
        this.body.addBox(0.0F, 0.0F, 0.0F, 8, 4, 9, 0.0F);
        this.leftarm = new RendererModel(this, 188, 22);
        this.leftarm.setRotationPoint(14.199999809265137F, 8.0F, -10.0F);
        this.leftarm.addBox(0.0F, 0.0F, 0.0F, 3, 3, 12, 0.0F);
        this.setRotateAngle(leftarm, 0.1396263390779495F, -0.4188790321350098F, 0.0F);
        this.leftleg = new RendererModel(this, 188, 14);
        this.leftleg.setRotationPoint(2.0F, 5.0F, 28.5F);
        this.leftleg.addBox(0.0F, 0.0F, 0.0F, 2, 2, 4, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.head2.render(f5);
        this.head3.render(f5);
        this.head4.render(f5);
        this.rightarm.render(f5);
        this.head.render(f5);
        this.rightleg.render(f5);
        this.body.render(f5);
        this.leftarm.render(f5);
        this.leftleg.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(RendererModel RendererModel, float x, float y, float z) {
        RendererModel.rotateAngleX = x;
        RendererModel.rotateAngleY = y;
        RendererModel.rotateAngleZ = z;
    }
}
