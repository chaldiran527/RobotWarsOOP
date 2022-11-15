package controllers;


import gui.ArenaGamePanel;
import gui.ArenaMainFrame;
import model.common.arena.Arena;
import model.common.arena.arenaTraps.DiscusTrap;
import model.common.arena.arenaTraps.FireTrap;
import model.common.robotbase.ORIENTATION;
import model.common.robotbase.RobotFighter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import static model.common.IConstants.ROBOT_SPEED_DEFAULT;
import static model.common.IConstantsV2.BLASTER_CONSUMPTION;
import static model.common.robotbase.MOVEMENT.*;


public class ArenaMainController implements KeyListener, ActionListener {

    private ArenaMainFrame controlledFrame;
    private ArenaGamePanel arenaPanel;
    private Arena arena;
    private RobotFighter robot;
    private ArrayList<FireTrap> fireTrampas;

    private DiscusTrap diskTrap;
    private boolean shot;
    private boolean strike;
    private boolean shotPressed;
    private boolean strikePressed;

    public ArenaMainController(RobotFighter pRobot){
        this.robot = pRobot;
        this.arena = new Arena("RobotWarsArena");
        fireTrampas = arena.getFireTraps();
        diskTrap = arena.getDiskTrap();
    }

    public void setWindow(ArenaGamePanel pPanel) {
        this.arenaPanel = pPanel;
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    public RobotFighter getRobot() {
        return robot;
    }
    public void update(Graphics g){
        updateRobotFighter(g);
        //////////////////////////////
        if(this.shot == true && this.shotPressed == false && robot.getEnergy() > 0){
            this.robot.addBlaster(robot.getPosX(),robot.getPosY());
            this.robot.setEnergy(BLASTER_CONSUMPTION);
            this.shotPressed = true;
        }
        if(this.strike == true && this.strikePressed == false && robot.getEnergy() > 0){
            this.robot.startStrike();
            this.robot.setEnergy(robot.getSlasher().getEnergyConsumption());
            this.strikePressed = true;
        }
        this.arena.getDiskTrap().setHitBox(arena.getDiskTrap().getX(),arena.getDiskTrap().getY());
        this.arena.getDiskTrap().moveDiscus();
        checkColissions();
    }

    public void checkColissions(){
        ArrayList<FireTrap> fires = this.arena.getFireTraps();
        fires.stream().filter(i -> colission(robot.getHitBox().x,robot.getHitBox().y,robot.getHitBox().width,robot.getHitBox().height,
                        i.getHitBox().x,i.getHitBox().y,i.getHitBox().width,i.getHitBox().height))
                        .forEach(i-> robot.setEnergy(i.getTrapDamage()));
        if(colission(robot.getHitBox().x,robot.getHitBox().y,robot.getHitBox().width,robot.getHitBox().height,
                arena.getDiskTrap().getX(),arena.getDiskTrap().getY(),arena.getDiskTrap().getWidth(),arena.getDiskTrap().getHeight())){
            robot.setEnergy(arena.getDiskTrap().getTrapDamage());
        }
    }

    public boolean colission(  int Ax, int Ay, int Aw, int Ah,
                               int Bx, int By, int Bw, int Bh){
        return    Bx + Bw > Ax &&
                By + Bh > Ay &&
                Ax + Aw > Bx &&
                Ay + Ah > By;
    }

    public void updateRobotFighter(Graphics g){
        //this.robot.move(this.robot.getCurrentMovement(),null,g);
        this.robot.update(this.robot.getCurrentMovement());
        this.robot.setCurrentMovement(null);
        this.robot.setHitBox(robot.getPosX(),robot.getPosY());
        this.robot.loadCoolDown();
        this.robot.moveBlasters();
        this.robot.moveStrikes();
    }

    public ArrayList<FireTrap> getFireTraps(){
        return this.fireTrampas;
    }

    public DiscusTrap getDiskTraps(){
        return diskTrap;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(robot.getEnergy() > 0)
        {
            if (code == KeyEvent.VK_UP) {
                robot.setCurrentMovement(UP);
                robot.setSpeed(ROBOT_SPEED_DEFAULT);
                robot.setCurrentOrientation(ORIENTATION.NORTH);
            }
            if (code == KeyEvent.VK_DOWN) {
                robot.setCurrentMovement(DOWN);
                robot.setSpeed(ROBOT_SPEED_DEFAULT);
                robot.setCurrentOrientation(ORIENTATION.SOUTH);
            }
            if (code == KeyEvent.VK_LEFT) {
                robot.setCurrentMovement(LEFT);
                robot.setSpeed(ROBOT_SPEED_DEFAULT);
                robot.setCurrentOrientation(ORIENTATION.EAST);
            }
            if (code == KeyEvent.VK_RIGHT) {
                robot.setCurrentMovement(RIGHT);
                robot.setSpeed(ROBOT_SPEED_DEFAULT);
                robot.setCurrentOrientation(ORIENTATION.WEST);
            }
            if (code == KeyEvent.VK_SPACE) {
                this.shot = true;
            }
            if (code == KeyEvent.VK_F) {
                this.strike = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_UP) {
            robot.setSpeed(0);
            robot.setCurrentMovement(null);
        }
        if(code == KeyEvent.VK_DOWN) {
            robot.setSpeed(0);
            robot.setCurrentMovement(null);
        }
        if(code == KeyEvent.VK_LEFT) {
            robot.setSpeed(0);
            robot.setCurrentMovement(null);
        }
        if(code == KeyEvent.VK_RIGHT) {
            robot.setSpeed(0);
            robot.setCurrentMovement(null);
        }
        if (code == KeyEvent.VK_SPACE){
            this.shotPressed = false;
        }
        if (code == KeyEvent.VK_F){
            this.strikePressed = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        arenaPanel.repaint();
    }
}
