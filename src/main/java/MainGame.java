import controllers.ArenaMainController;
import gui.ArenaGamePanel;
import gui.ArenaMainFrame;
import model.common.robotbase.RobotFighter;


public class MainGame implements Runnable{
    private Thread gameLoop;

    public void startGameLoop(){
        gameLoop = new Thread(this);
        gameLoop.start();
    }

    @Override
    public void run() {
        ArenaMainController controller = new ArenaMainController(new RobotFighter("Krakow"));
        ArenaMainFrame arenaFrame = new ArenaMainFrame("BotWars", controller);
        ArenaGamePanel gameArena= new ArenaGamePanel(controller);
        arenaFrame.add(gameArena);

        double gameDrawingInterval = 1000000000 / 100;//Replace 60 with a frames per second constant
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawingCounter = 0;

        double drawInterval = 1000000000 / 100;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while(gameLoop != null)//Mientras el ciclo infinito de este juego se esta ejecutando...
        {

            currentTime = System.nanoTime();//check current time
            //substract how much time has passed and add it to delta
            delta += (currentTime - lastTime) / gameDrawingInterval;
            lastTime = currentTime;//Last time is the current time now
            if(delta >= 1){
                gameArena.repaint();
                delta--;
                drawingCounter++;
            }
        }
    }

    public static void main(String[] args) {
        //Se instancia y se llama a la clase mainGame que ejecuta el ciclo infinito
        MainGame robotWars = new MainGame();
        robotWars.startGameLoop();
    }


}
