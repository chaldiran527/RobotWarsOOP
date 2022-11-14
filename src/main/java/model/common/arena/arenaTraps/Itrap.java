package common.arena.arenaTraps;

import java.awt.*;

public abstract class  Itrap {
    protected double posX;
    protected double posY;

    public Itrap(int pX, int pY){
        this.posX = pX;
        this.posY = pY;
    }

    public void drawTrap(Graphics g, Image pTrapImage){
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(pTrapImage,(int) posX,(int) posY,null);
    }

}
