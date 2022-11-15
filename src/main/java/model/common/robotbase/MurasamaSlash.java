package model.common.robotbase;

import javax.swing.*;
import java.awt.*;

import static model.common.IConstantsV2.*;
import static model.common.robotbase.ORIENTATION.*;

public class MurasamaSlash extends Weapon{
    private Image image;
    private Image spriteR1;
    private Image spriteR2;
    private Image spriteR3;
    private Image spriteL1;
    private Image spriteL2;
    private Image spriteL3;
    private ORIENTATION currORIENTATION;
    private int damage;
    private int energyConsumption;
    private int spriteCounter;
    private boolean isActive;
    private int strikeGapXAxis;
    private int strikeGapYAxis;


    protected MurasamaSlash(int pSpeed) {
        super(pSpeed);
        this.spriteL1 = new ImageIcon("src/main/java/images/slashL1.png").getImage();
        this.spriteL2 = new ImageIcon("src/main/java/images/slashL2.png").getImage();
        this.spriteL3 = new ImageIcon("src/main/java/images/slashL3.png").getImage();
        this.spriteR1 = new ImageIcon("src/main/java/images/slashR1.png").getImage();
        this.spriteR2 = new ImageIcon("src/main/java/images/slashR2.png").getImage();
        this.spriteR3 = new ImageIcon("src/main/java/images/slashR3.png").getImage();
        this.spriteCounter = -999;//num ridiculoso
        this.strikeGapYAxis = STRIKE_GAP_Y_Axis;
        this.isActive = false;
        this.damage = SLASH_DAMAGE;
        this.energyConsumption = SLASH_CONSUMPTION;
    }

    @Override
    protected void triggerWeapon(int pPosX, int pPosY, ORIENTATION pDirection) {
        if(spriteCounter <= 25 && spriteCounter > 20){
            if(pDirection == EAST || pDirection == SOUTH) this.image = spriteL1;
            if(pDirection == WEST || pDirection == NORTH) this.image = spriteR1;
        }
        if(spriteCounter <= 20 && spriteCounter > 15){
            if(pDirection == EAST || pDirection == SOUTH) this.image = spriteL2;
            if(pDirection == WEST || pDirection == NORTH) this.image = spriteR2;
        }
        if(spriteCounter <= 15 && spriteCounter > 0){
            if(pDirection == EAST || pDirection == SOUTH) this.image = spriteL3;
            if(pDirection == WEST || pDirection == NORTH) this.image = spriteR3;
        }
        spriteCounter -= this.getSpeed();
    }

    public void attacking(){
        if(isAttacking())
            fire(0,0,this.currORIENTATION);
    }

    public void startAttack(ORIENTATION pORIENTATION){
        if(isAttacking() == false){
            this.spriteCounter = 25;
            this.currORIENTATION = pORIENTATION;
            if(pORIENTATION == EAST || pORIENTATION == SOUTH) {
                strikeGapXAxis = STRIKE_GAP_X_Axis_L;
            }
            if(pORIENTATION == WEST || pORIENTATION == NORTH){
                strikeGapXAxis = STRIKE_GAP_X_Axis_R;
            }
        }
    }

    public boolean isAttacking(){
        if(spriteCounter > 0 && spriteCounter <= 25){
            isActive = true;
            return true;
        }
        else  {
            isActive = false;
            return false;
        }
    }

    public int getDamage(){
        return this.damage;
    }

    public int getEnergyConsumption(){
        return this.energyConsumption;
    }

    public int getStrikeGapX(){
        return this.strikeGapXAxis;
    }

    public int getStrikeGapY(){
        return this.strikeGapYAxis;
    }

    public Image getImage(){
        return this.image;
    }
}
