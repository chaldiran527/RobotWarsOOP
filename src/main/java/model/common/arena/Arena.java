package model.common.arena;

import model.common.arena.arenaTraps.DiscusTrap;
import model.common.arena.arenaTraps.FireTrap;
import model.common.robotbase.RobotFighter;

import java.util.*;

public class Arena {
    private ArrayList<FireTrap> fireTraps;
    private DiscusTrap diskTrap;
    private ArrayList<RobotFighter> robots;

    private String arenaName;

    public Arena(String pArenaName)
    {
        //Se inicializan las trampas
        this.arenaName = pArenaName;
        this.fireTraps = new ArrayList<FireTrap>();
        this.fireTraps.add(new FireTrap(300,400));
        this.fireTraps.add(new FireTrap(440,310));
        this.fireTraps.add(new FireTrap(650,85));
        this.fireTraps.add(new FireTrap(770,600));
        this.diskTrap = new DiscusTrap(200,300);
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
    public DiscusTrap getDiskTrap(){ return this.diskTrap;}
}
