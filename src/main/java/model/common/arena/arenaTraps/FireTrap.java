package common.arena.arenaTraps;

import java.awt.*;

public class FireTrap extends Itrap{
    private Image fireTrapImage;

    public FireTrap(int pX, int pY){
        super(pX,pY);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        this.fireTrapImage = toolkit.getImage("src/main/java/images/fireTrap.gif");
    }


    public void draw(Graphics g){
        drawTrap(g,fireTrapImage);
    }

    public Image getTrapImage(){
        return this.fireTrapImage;
    }

    public double getX(){
        return this.posX;
    }

    public double getY(){
        return this.posY;
    }
}
