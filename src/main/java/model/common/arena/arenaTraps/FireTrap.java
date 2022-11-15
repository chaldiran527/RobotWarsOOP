package model.common.arena.arenaTraps;

import java.awt.*;

public class FireTrap extends Itrap{
    private Image fireTrapImage;

    public FireTrap(int pX, int pY){
        super(pX,pY);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        this.fireTrapImage = toolkit.getImage("src/main/java/images/fireTrap.gif");
        this.width = fireTrapImage.getWidth(null);
        this.height = fireTrapImage.getHeight(null);
        //this.hitBox = new Rectangle(pX - width/2, pY - height, width, height);
        this.hitBox = new Rectangle(pX,pY,width,height);
        this.damage = 1;
    }


    public void draw(Graphics g){
        drawTrap(g,fireTrapImage);
    }

    public Image getTrapImage(){
        return this.fireTrapImage;
    }

    public int getTrapDamage(){
        return this.damage;
    }

    public double getX(){
        return this.posX;
    }

    public Rectangle getHitBox(){
        return this.hitBox;
    }

    public double getY(){
        return this.posY;
    }

    public void setHitBox(int pX, int pY){
        this.hitBox.x = pX;
        this.hitBox.y = pY;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }
}
