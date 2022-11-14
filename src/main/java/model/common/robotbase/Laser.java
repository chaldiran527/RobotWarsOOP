package model.common.robotbase;

import javax.swing.*;
import java.awt.*;

import static model.common.IConstants.ARENA_HEIGTH;
import static model.common.IConstants.ARENA_WIDTH;
import static model.common.robotbase.ORIENTATION.*;

public class Laser extends Weapon{

    private Image laserImage;

    private boolean visible;
    private ORIENTATION laserOrientation;


    protected Laser(int pSpeed, ORIENTATION pORIENTATION, int pPosX, int pPosY) {
        super(pSpeed);
        if(pORIENTATION == EAST || pORIENTATION == WEST){
            this.laserImage = new ImageIcon("src/main/java/images/blaster1.png").getImage();
        }
        else if(pORIENTATION == NORTH || pORIENTATION == SOUTH){
            this.laserImage = new ImageIcon("src/main/java/images/blaster2.png").getImage();
        }

        this.visible = true;
        this.laserOrientation = pORIENTATION;
        this.setPosX(pPosX);
        this.setPosY(pPosY);
    }

    @Override
    public void triggerWeapon(int pPosX, int pPosY, ORIENTATION pDirection) {
        //define specific triggerweapon method of this class
        if(checkVisibility() == true)
        {
            if(laserOrientation == WEST) {
                this.setPosX(this.getPosX()+this.getSpeed());
            }
            if(laserOrientation == EAST) {
                this.setPosX(this.getPosX()-this.getSpeed());
            }
            if(laserOrientation == NORTH) {
                this.setPosY(this.getPosY()-this.getSpeed());
            }
            if(laserOrientation == SOUTH) {
                this.setPosY(this.getPosY()+this.getSpeed());
            }
        }
    }

    public void move(){
        fire(0,0,null);
    }

    public Image getLaserImage(){
        return this.laserImage;
    }

    public boolean checkVisibility(){
        if(this.getPosX() > ARENA_WIDTH || this.getPosX() < 0){
            this.visible = false;
            return false;
        }
        else if(this.getPosY() > ARENA_HEIGTH || this.getPosY() < 0){
            this.visible = false;
            return false;
        }
        else {
            this.visible = true;
            return true;
        }
    }
}
