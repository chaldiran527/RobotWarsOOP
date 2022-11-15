package model.common.robotbase;

import gui.ArenaGamePanel;
import model.common.IConstantsV2;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.time.LocalTime;
import java.util.ArrayList;

import static model.common.IConstantsV2.*;


public class RobotFighter extends IRobot {
    private String name;
    private Image robotImg;
    private int width;
    private int height;
    private double xVelocity;
    private double yVelocity;
    private int coolDown;
    private int movement;
    private Rectangle hitBox;
    private ArrayList<Laser> blasters;
    private MurasamaSlash slasher;

    public RobotFighter(String pName){
        super();
        this.blasters = new ArrayList<Laser>();
        this.name = pName;
        this.posX = 40;
        this.posY = 60;
        this.energy = 100;
        this.robotImg = new ImageIcon("src/main/java/images/robotRock.png").getImage();
        Image modRobot = robotImg.getScaledInstance(90,90, Image.SCALE_SMOOTH);
        this.robotImg = new ImageIcon(modRobot).getImage();
        this.width = robotImg.getWidth(null);
        this.height = robotImg.getHeight(null);
        this.slasher = new MurasamaSlash(SLASH_SPEED);
        this.hitBox = new Rectangle(posX,posY,width,height);
        this.coolDown = -999;
        this.movement = 0;
    }

    public void setxVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    public void setyVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    public void setMovement(int pMov){this.movement += pMov;}
    public int  getMovement(int pMov){return movement;}

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
        String energy = String.valueOf(this.energy);
        return energy;
    }

    public int getEnergy(){
        return this.energy;
    }

    public void setEnergy(int pLevel){
        if(coolDown < 0) {
            this.coolDown = ROBOT_FIGHTER_COOLDOWN;
            this.damage(pLevel);
        }
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

    public void setHitBox(int pX, int pY){
        this.hitBox.x = pX;
        this.hitBox.y = pY;
    }

    public void loadCoolDown(){
        this.coolDown--;
    }

    public Rectangle getHitBox(){
        return hitBox;
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

    public void update(MOVEMENT pMove){
        if(pMove != null) {
            this.movement += Math.abs(this.speed);
            move(pMove, null, null);
        }
        if(this.movement > 1000){
            this.setEnergy(1);
            this.movement = 0;
        }
    }

    @Override
    protected void refreshMove(MOVEMENT pMove, LocalTime pActionTime, Graphics g) {
        move(pMove,null,null);
    }
}
