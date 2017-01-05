// this class is used to show data and interface to user
// Name: Yan Hao
// Student ID: 100026051

import java.util.Scanner;
import java.io.Console;
import java.util.ArrayList;

/**
 * Write a description of class UserInterface here.
 * 
 * @author Yan Hao
 * @version 15/05/2015
 */
public class UserInterface
{
    /**
     * this method is the main method to run the project
     *  show peers read from file
     *  get message from user type 
     *  send message to all peers
     *  
     * @param no parameter
     * @return no return
     * @exception Exception
     * @author Yan Hao
     * @version 19/05/2015
     */
    public void main(){
        try{
            OperateFile operF = new OperateFile();
            System.out.println("=================================");
            ArrayList<String> lineList=new ArrayList<String>();
            lineList=operF.readPeers();
            System.out.println("Please enter your message below.");
            Scanner sc=new Scanner(System.in);    
            while (sc.hasNextLine()) { 
                String str = sc.nextLine(); 
                if(str.equals("")){
                    System.out.println("No words, please enter something.");
                }else{
                    sendMessageToAll(str,lineList);   // send message to all peers
                    System.out.println("===========================================");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * this method is used to send message to all peers from file
     * 
     * @param message, ipList
     * @return no return
     * @exception Exception
     * @author Yan Hao
     * @version 11/05/2015
     */
    public static void sendMessageToAll(String message, ArrayList lineList) {
        String ip="";
        String line="";
        String hostName="";
        UDPClient client = new UDPClient();
        try{
            for(int i=0;i<lineList.size();i++){
                line=(String)lineList.get(i);
                ip = line.substring(line.indexOf("<IP")+4,line.indexOf(">"));
                hostName = line.substring(line.indexOf(",")+2,line.indexOf("<"));
                String displayMessage = hostName+"<IP "+ip+"> "+message;
                client.send(displayMessage,ip);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
