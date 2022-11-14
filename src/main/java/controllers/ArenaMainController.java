package controllers;


import gui.ArenaGamePanel;
import gui.ArenaMainFrame;
import model.common.arena.Arena;
import model.common.robotbase.ORIENTATION;
import model.common.robotbase.RobotFighter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static model.common.IConstants.ROBOT_SPEED_DEFAULT;
import static model.common.robotbase.MOVEMENT.*;


public class ArenaMainController implements KeyListener, ActionListener {

    private ArenaMainFrame controlledFrame;
    private ArenaGamePanel arenaPanel;
    private Arena arena;
    private RobotFighter robot;
    private boolean shot;
    private boolean shotPressed;

    public ArenaMainController(RobotFighter pRobot){
        this.robot = pRobot;
        this.arena = new Arena("RobotWarsArena");
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
        this.robot.move(this.robot.getCurrentMovement(),null,g);
        this.robot.setCurrentMovement(null);
        if(this.shot == true && this.shotPressed == false){
            this.robot.addBlaster(robot.getPosX(),robot.getPosY());
            this.shotPressed = true;
        }
        this.robot.moveBlasters();
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

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
        if (code == KeyEvent.VK_SPACE){
            this.shot = true;
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        arenaPanel.repaint();
    }
}