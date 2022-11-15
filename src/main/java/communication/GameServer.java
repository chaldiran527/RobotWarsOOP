package communication;

import java.io.*;
import java.net.*;


public class GameServer {
    private ServerSocket ss;
    private int numPlayers;//I suppose we only need two NO these are current players
    private int maxPlayers;//2

    public GameServer(){
        System.out.println("====== GAME_SERVER ======");
        this.numPlayers = 0;
        this.maxPlayers = 2;

        try{
            ss = new ServerSocket(45371);
        }catch(IOException e){
            System.out.println("IOException from GameServer constructor!");
        }
    }

    public void acceptConnections(){
        try{
            System.out.println("Waiting for connections...");

            while(numPlayers < maxPlayers)//loop that executes until the two players have connected
            {//program will stay in this line until a client connect
                Socket s = ss.accept();//tells server to begin accepting the connection
                //Now that a client has connected: We get In/Out streams
                DataInputStream in = new DataInputStream(s.getInputStream());//stream to receive
                DataOutputStream out = new DataOutputStream(s.getOutputStream());//stream to send
                numPlayers++;//A player has connected so we increase number of players
                out.writeInt(numPlayers);//this allows to send an integer to the client->send number of the player
                System.out.println("Player #" + numPlayers + " has connected");
            }

            System.out.println("No longer accepting connecetions!");
        }catch(IOException e){
            System.out.println("IOExcetion from GameServer acceptConnections()");
        }
    }

    public static void main(String[] args){
        GameServer robotWarsServer = new GameServer();
        robotWarsServer.acceptConnections();
    }
}
