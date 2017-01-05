// this class is used to send data
// Name: Yan Hao
// Student ID: 100026051

import java.io.*;
import java.net.*;

public class UDPClient {
    private DatagramSocket dataSocket;
    private DatagramPacket dataPacket;
    private byte sendDataByte[];
    private String sendStr;
    
    /**
     * this is a send method with two parameters
     *  @param message, ip
     *  @return no return
     *  @exception Exception
     *  @author Yan Hao
     *  @version 19/05/2015
     */
    public void send(String message,String ip){
        try{
            // this code has been adapted from code found at this source
            // http://www.coderpanda.com/java-socket-programming-using-udp/, accessed 22 May, 2015
            DatagramSocket socket = new DatagramSocket();  
            byte[] buf = new byte[1024];  
            InetAddress clientIP = InetAddress.getByName(ip);  // target
            byte[] data = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(data,data.length,clientIP,4008);  
            socket.send(sendPacket);  
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
