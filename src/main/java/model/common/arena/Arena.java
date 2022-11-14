package common.arena;

import common.arena.arenaTraps.FireTrap;
import common.robotbase.RobotFighter;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.*;

public class Arena {
    private ArrayList<FireTrap> fireTraps;
    private ArrayList<RobotFighter> robots;

    private String arenaName;

    public Arena(String pArenaName)
    {
        //Se inicializan las trampas
        this.arenaName = pArenaName;
        this.fireTraps = new ArrayList<FireTrap>();
        this.fireTraps.add(new FireTrap(300,10));
        this.fireTraps.add(new FireTrap(400,20));
        this.fireTraps.add(new FireTrap(500,30));
    }

    public void addRobot(RobotFighter pRobot){
        this.robots.add(pRobot);
    }

    public String getArenaName(){
        return this.arenaName;
    }

    public ArrayList getFireTraps(){
        return this.fireTraps;
    }
}
