package gui;


import controllers.ArenaMainController;
import controllers.KeyboardController;
import model.common.IConstants;
import model.common.arena.Arena;
import model.common.arena.arenaTraps.FireTrap;
import model.common.robotbase.Laser;
import model.common.robotbase.RobotFighter;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ArenaGamePanel extends JPanel implements IConstants {

    private ArenaMainController panelController;
    private KeyboardController keyboard = new KeyboardController();
    private Arena gameArena;

    private Image arenaBackground;
    private RobotFighter robot;
    private Image fire;


    public ArenaGamePanel(ArenaMainController pPanelController){
       setFocusTraversalKeysEnabled(false);
       setDoubleBuffered(true);
       this.setFocusable(true);

       //Background de este panel
       this.setBackground(Color.RED);
       this.setOpaque(true);
       this.arenaBackground = new ImageIcon("src/main/java/images/FondoDePelea.png").getImage();
       gameArena = new Arena("RobotWarsArena");
       this.panelController = pPanelController;
       this.robot = this.panelController.getRobot();
       this.panelController.setWindow(this);
       this.addKeyListener(pPanelController);
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        doDrawing(g);//Funcion en la que se dibujan en orden de prioridad los assets de la arena
        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        List<FireTrap> fireTraps = gameArena.getFireTraps();//Lista con todas las trampas
        //
        g2d.drawImage(arenaBackground,0,0,IConstants.ARENA_WIDTH,IConstants.ARENA_HEIGTH,null);
        //Se dibbujan por medio de un stream function todas las trampas de esta respectiva arena
        fireTraps.stream().forEach(fireTrap ->
                g2d.drawImage(fireTrap.getTrapImage(),(int)fireTrap.getX(),(int)fireTrap.getY(),this));

        panelController.update(g);//Se actualizan los valores del robot y las trampas en el controller
        List<Laser> blasters = panelController.getRobot().getBlasters();
        blasters.stream().filter(blaster -> blaster.checkVisibility())
                .forEach(blaster -> g2d.drawImage(blaster.getLaserImage(),blaster.getPosX(),blaster.getPosY(),this));
        g2d.drawImage(panelController.getRobot().getRobotImg(), panelController.getRobot().getPosX(), panelController.getRobot().getPosY(),null);//Se dibuja el robot con sus atributos actuales

        g2d.drawString(panelController.getRobot().getName(),panelController.getRobot().getPosX(),panelController.getRobot().getPosY()-22);


        g2d.dispose();//Dispose como buena practica despues de dibujar en graphics
    }

}
