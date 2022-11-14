package model.common.robotbase;

import gui.ArenaGamePanel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.util.ArrayList;

import static model.common.IConstantsV2.LASER_1_SPEED;
import static model.common.IConstantsV2.SLASH_SPEED;


public class RobotFighter extends IRobot {
    private String name;
    private Image robotImg;
    private int width;
    private int height;
    private double energyLife;
    private double xVelocity;
    private double yVelocity;
    private ArrayList<Laser> blasters;
    private MurasamaSlash slasher;

    public RobotFighter(String pName){
        super();
        this.blasters = new ArrayList<Laser>();
        this.name = pName;
        this.posX = 40;
        this.posY = 60;
        this.energyLife = 100;
        this.robotImg = new ImageIcon("src/main/java/images/robotRock.png").getImage();
        Image modRobot = robotImg.getScaledInstance(90,90, Image.SCALE_SMOOTH);
        this.robotImg = new ImageIcon(modRobot).getImage();
        this.width = robotImg.getWidth(null);
        this.width = robotImg.getHeight(null);
        this.slasher = new MurasamaSlash(SLASH_SPEED);
    }

    public void setxVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    public void setyVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
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
    public String getName(){return name;}

    public double getxVelocity() {
        return xVelocity;
    }

    public double getyVelocity() {
        return yVelocity;
    }

    public Image getRobotImg(){
        return this.robotImg;
    }

    public String getEnergyLife(){
        String energy = String.valueOf(energyLife);
        return energy;
    }

    public MOVEMENT getCurrentMovement(){
        return this.currentMovement;
    }

    public ArrayList<Laser> getBlasters(){
        return this.blasters;
    }

    public MurasamaSlash getSlasher(){
        return this.slasher;
    }
    public boolean collision(int x1, int x2, int y1, int y2, int r1, int r2){
        //function for circle collision
        return false;
    }

    public void startStrike(){
        if(slasher.isEnabled())
            this.slasher.startAttack(this.currentOrientation);
    }
    public void moveStrikes(){
        if(slasher.isAttacking()){
            slasher.attacking();
        }
    }

    public boolean weaponActive(String pWeaponName){
        if(pWeaponName == "slasher"){
            if(slasher.isAttacking() == true)
                return true;
        }
        if(pWeaponName == "trident"){
            return true;
        }
        if(pWeaponName == "sword"){
            return true;
        }
        else {
            return false;
        }
    }

    public void moveBlasters(){
        this.blasters.stream()
                .filter(laser->laser.checkVisibility())
                .forEach(laser->laser.move());
    }

    public void addBlaster(int pX, int pY){
        this.blasters.add(new Laser(LASER_1_SPEED,currentOrientation,pX,pY));
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
