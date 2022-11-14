package common.robotbase;

import gui.ArenaGamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalTime;

import static common.robotbase.ORIENTATION.EAST;

public class RobotFighter extends IRobot {
    private String name;
    private Image robotImg;
    private int width;
    private int height;
    private double energyLife;
    private double xVelocity;
    private double yVelocity;

    public void setxVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    public void setyVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    public RobotFighter(String pName){
        super();
        this.name = pName;
        this.posX = 40;
        this.posY = 60;
        this.energyLife = 100;
        this.robotImg = new ImageIcon("src/main/java/images/robotRock.png").getImage();
        Image modRobot = robotImg.getScaledInstance(90,90, Image.SCALE_SMOOTH);
        this.robotImg = new ImageIcon(modRobot).getImage();
        this.width = robotImg.getWidth(null);
        this.width = robotImg.getHeight(null);
    }

    public int getPosX(){
        return this.posX;
    }

    public int getPosY(){
        return this.posY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getxVelocity() {
        return xVelocity;
    }

    public double getyVelocity() {
        return yVelocity;
    }

    public Image getRobotImg(){
        return this.robotImg;
    }
   /* public RobotFighter(Weapon pdirections, DamageLevel pweapons) {
        super(pdirections, pweapons);
    }*/

    public MOVEMENT getCurrentMovement(){
        return this.currentMovement;
    }

    public boolean collision(int x1, int x2, int y1, int y2, int r1, int r2){
        //function for circle collision
        return false;
    }

    public void actionPerformed(ActionEvent e) {
        posX += xVelocity;
        posY += yVelocity;

       //repaint();
    }

    public void draw(Graphics g, ArenaGamePanel pObserver){
        move(currentMovement,null,g);
        g.drawImage(this.robotImg,posX,posY,pObserver);
    }

    public void setCurrentOrientation(ORIENTATION pOrientation){
        this.currentOrientation = pOrientation;
    }

    public void setCurrentMovement(MOVEMENT pMov){
        if(pMov != null) {
            this.currentMovement = pMov;
        }
    }

    public void setSpeed(int pSpeed){
        speed = pSpeed;
    }


    @Override
    protected void refreshMove(MOVEMENT pMove, LocalTime pActionTime, Graphics g) {
    }
}
