package communication;

import java.io.*;
import java.net.*;


public class GameServer {
    private ServerSocket ss;
    private int numPlayers;//I suppose we only need two NO these are current players
    private int maxPlayers;//2
    private Socket p1Socket;
    private Socket p2Socket;
    private ReadFromClients p1ReadRunnable;
    private ReadFromClients p2ReadRunnable;
    private WriteToClients p1WriteRunnable;
    private WriteToClients p2WriteRunnable;

    private double p1x, p1y,p2x,p2y;

    public GameServer(){
        System.out.println("====== GAME_SERVER ======");
        this.numPlayers = 0;
        this.maxPlayers = 2;

        p1x = 40;
        p1y = 60;
        p2x = 980;
        p2y = 60;

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

                ReadFromClients rfc = new ReadFromClients(numPlayers,in);
                WriteToClients wtc = new WriteToClients(numPlayers,out);

                if(numPlayers == 1){
                    p1Socket = s;
                    p1ReadRunnable = rfc;
                    p1WriteRunnable = wtc;
                } else{
                    p2Socket = s;
                    p2ReadRunnable = rfc;
                    p2WriteRunnable = wtc;
                    p1WriteRunnable.sendStartMsg();
                    p2WriteRunnable.sendStartMsg();
                    Thread readThread1 = new Thread(p1ReadRunnable);
                    Thread readThread2 = new Thread(p2ReadRunnable);
                    readThread1.start();
                    readThread2.start();
                    Thread writeThread1 = new Thread(p1WriteRunnable);
                    Thread writeThread2 = new Thread(p2WriteRunnable);
                    writeThread1.start();
                    writeThread2.start();
                }
            }

            System.out.println("No longer accepting connecetions!");
        }catch(IOException e){
            System.out.println("IOException from GameServer acceptConnections()");
        }
    }

    private class ReadFromClients implements Runnable{
        private int playerID;
        private DataInputStream dataIn;

        public ReadFromClients(int pId,DataInputStream in){
            playerID = pId;
            dataIn = in;
            System.out.println("Read_From_Client " + playerID + " Runnable created");
        }
        @Override
        public void run() {
            try{
                while(true){
                    if(playerID == 1){
                        p1x = dataIn.readDouble();
                        p1y = dataIn.readDouble();
                    } else {
                        p2x = dataIn.readDouble();
                        p2y = dataIn.readDouble();
                    }
                }
            }catch(IOException e){
                System.out.println("IOException from RFC run()");
            }
        }
    }

    private class WriteToClients implements Runnable{
        private int playerID;
        private DataOutputStream dataOut;

        public WriteToClients(int pId,DataOutputStream out){
            playerID = pId;
            dataOut = out;
            System.out.println("Write_To_Client " + playerID + " Runnable created");
        }
        @Override
        public void run() {
            try{
                while(true){
                    if(playerID == 1){
                        dataOut.writeDouble(p2x);
                        dataOut.writeDouble(p2y);
                        dataOut.flush();
                    } else{
                        dataOut.writeDouble(p1x);
                        dataOut.writeDouble(p1y);
                        dataOut.flush();
                    }
                    try{
                        Thread.sleep(25);
                    } catch(InterruptedException e){
                        System.out.println("InterruptedException from WTC run()");
                    }
                }
            } catch(IOException e){
                System.out.println("IOException from WTC run()");
            }
        }

        public void sendStartMsg(){
            try{
                dataOut.writeUTF("We now have 2 players. Let's Go!");
            } catch(IOException ex){
                System.out.println("IOException from sendStartMsg()");
            }
        }

    }


    public static void main(String[] args){
        GameServer robotWarsServer = new GameServer();
        robotWarsServer.acceptConnections();
    }
}
