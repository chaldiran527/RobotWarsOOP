package model.common.arena.arenaTraps;

import java.awt.*;

import static model.common.IConstants.ARENA_HEIGTH;
import static model.common.IConstants.ARENA_WIDTH;
import static model.common.IConstantsV2.Y_AXIS_BORDER;

public class DiscusTrap extends Itrap{

    private Image discusTrapImage;

    private int xSpeed;
    private int ySpeed;

    public DiscusTrap(int pX, int pY) {
        super(pX, pY);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        this.discusTrapImage = toolkit.getImage("src/main/java/images/spikus.gif");
        this.width = discusTrapImage.getWidth(null);
        this.height = discusTrapImage.getHeight(null);
        this.hitBox = new Rectangle(pX,pY,width,height);
        this.damage = 4;
        this.xSpeed = 2;
        this.ySpeed = 1;
    }

    public void moveDiscus(){
        if(posX < 0 || posX > ARENA_WIDTH - this.width){//-55 //original 545
            xSpeed =-xSpeed;
        }
        if(posY < 0 || posY > Y_AXIS_BORDER - this.height){//-70 //original 320
            ySpeed =-ySpeed;
        }
        posX += xSpeed;
        posY += ySpeed;
    }

    public void setHitBox(int pX, int pY){
        this.hitBox.x = pX;
        this.hitBox.y = pY;
    }

    public Image getDiscusTrapImage(){
        return discusTrapImage;
    }

    public int getTrapDamage(){
        return this.damage;
    }

    public Rectangle getHitBox(){
        return this.hitBox;
    }

    public int getX(){
        return (int) this.posX;
    }

    public int getY(){
        return (int) this.posY;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

}
