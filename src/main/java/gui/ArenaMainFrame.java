package gui;//3535

import model.common.IConstants;
import controllers.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ArenaMainFrame extends JFrame implements IConstants, ActionListener, KeyListener {
    private ArenaMainController controller;
    public ArenaGamePanel arenaPanel;
    public ArenaMainFrame(String pTitle, ArenaMainController pController){
        super(pTitle);
        this.arenaPanel = new ArenaGamePanel(pController);
        this.add(arenaPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(ARENA_WIDTH,ARENA_HEIGTH);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.GREEN);
        this.setVisible(true);
    }


    private void initComponents() {
        //INitialize main assets
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
