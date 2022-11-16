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
    private ReadFromServer rfsRunnable;
    private WriteToServer wtsRunnable;
    private ArenaMainController controller;
    private ArenaMainFrame arenaFrame;
    private ArenaGamePanel gameArena;

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
            rfsRunnable = new ReadFromServer(in);
            wtsRunnable = new WriteToServer(out);
            rfsRunnable.waitForStartMsg();
        }catch(IOException e){
            System.out.println("IOException from connectToServer()");
        }
    }


    @Override
    public void run() {
        controller = new ArenaMainController(new RobotFighter("Krakow"), playerID);
        arenaFrame = new ArenaMainFrame("RobotWars", controller);
        gameArena= new ArenaGamePanel(controller);
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

    private class ReadFromServer implements Runnable{

        private DataInputStream dataIn;

        public ReadFromServer(DataInputStream in){
            dataIn = in;
            System.out.println("RFS Runnable created");
        }
        @Override
        public void run() {
            try{
                while(true){
                    if(controller != null) {
                        controller.getRobot2().setPosX((int) dataIn.readDouble());
                        controller.getRobot2().setPosY((int) dataIn.readDouble());
                    }
                }
            } catch(IOException ex){
                System.out.println("IOException from RFS run()");
            }
        }

        public void waitForStartMsg(){
            try{
                String startMsg = dataIn.readUTF();
                System.out.println("Message from server: " + startMsg);
                Thread readThread = new Thread(rfsRunnable);
                Thread writeThread = new Thread(wtsRunnable);
                readThread.start();
                writeThread.start();
            } catch(IOException e){
                System.out.println("IOException from waitForStartMsg()");
            }
        }
    }

    private class WriteToServer implements Runnable{
        private DataOutputStream dataOut;
        public WriteToServer(DataOutputStream out){
            dataOut = out;
            System.out.println("WTS Runnable created");
        }
        @Override
        public void run() {
            try{
                while(true) {
                    if(controller != null) {
                        dataOut.writeDouble(controller.getRobot().getPosX());
                        dataOut.writeDouble(controller.getRobot().getPosY());
                        dataOut.flush();//flush the toilet
                    }
                    try{
                        Thread.sleep(25);
                    }catch(InterruptedException e){
                        System.out.println("InterruptedException at WTS run()");
                    }
                }
            }catch(IOException ex){
                System.out.println("IOException at WTS run()");
            }
        }
    }

    public static void main(String[] args) {
        //Se instancia y se llama a la clase mainGame que ejecuta el ciclo infinito
        MainGame robotWars = new MainGame();
        robotWars.startGameLoop();
        robotWars.connectToServer();
    }

}
