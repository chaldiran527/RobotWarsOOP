package game;

import controllers.ArenaMainController;
import gui.ArenaGamePanel;
import gui.ArenaMainFrame;
import model.common.robotbase.RobotFighter;
//
import java.io.*;
import java.net.*;
//
public class MainGame implements Runnable{
    private Thread gameLoop;
    //
    private Socket socket;
    //
    private int playerID = 0;

    public void startGameLoop(){
        gameLoop = new Thread(this);
        gameLoop.start();
    }
//
    private void connectToServer(){
        try{
            socket = new Socket("Localhost",45371);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            playerID = in.readInt();
            System.out.println("You are player #" + playerID);
            if(playerID == 1){
                System.out.println("Waiting for player #2 to connect...");
            }
        }catch(IOException e){
            System.out.println("IOException from connectToServer()");
        }
    }

    @Override
    public void run() {
        ArenaMainController controller = new ArenaMainController(new RobotFighter("Krakow"), playerID);
        ArenaMainFrame arenaFrame = new ArenaMainFrame("RobotWars", controller);
        ArenaGamePanel gameArena= new ArenaGamePanel(controller);
        arenaFrame.add(gameArena);

        double gameDrawingInterval = 1000000000 / 100;//Replace 60 with a frames per second constant
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameLoop != null)//Mientras el ciclo infinito de este juego se esta ejecutando...
        {
            currentTime = System.nanoTime();//check current time
            //substract how much time has passed and add it to delta
            delta += (currentTime - lastTime) / gameDrawingInterval;
            lastTime = currentTime;//Last time is the current time now
            if(delta >= 1){
                gameArena.repaint();
                delta--;
            }
        }
    }

    public static void main(String[] args) {
        //Se instancia y se llama a la clase mainGame que ejecuta el ciclo infinito
        MainGame robotWars = new MainGame();
        robotWars.connectToServer();
        robotWars.startGameLoop();
    }


}
