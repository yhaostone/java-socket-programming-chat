// this class is used to run to recieve message from client
// Name: Yan Hao
// Student ID: 100026051

import java.net.*;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class UDPServer extends Thread {
    
    /**
     * this is a run method to recieve data
     * @param no parameter
     * @return mo return 
     * @exception Exception
     * @author Yan Hao
     * @version 20/05/2015
     */
    public void run(){
        DatagramSocket ds=null ;
        String unknownIPs="";   // used to store unauthorized IP
        try{
            // this code has been adapted from code found at this source
            // http://www.coderpanda.com/java-socket-programming-using-udp/, accessed 22 May, 2015
            ds= new DatagramSocket(4008);  // create listener for port
            ArrayList<String> IPs = readPeers();    // read peers information from file
             
            while(true)
            {
                byte[] buf = new byte[1024]; 
                DatagramPacket dp = new DatagramPacket(buf,buf.length);
                ds.receive(dp);
                String message = new String (dp.getData());
                InetAddress IPAddress = dp.getAddress();
                String sourceIP = IPAddress.getHostAddress();
                if(IPs.contains(sourceIP)){
                    System.out.println(message);
                }else if(unknownIPs.contains(sourceIP)){
                    
                }else{
                    System.out.println("Unauthorized chat request from <IP "+sourceIP+">");
                    // if the IP is not known, add to unknownIPs
                    unknownIPs += IPAddress;    
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(ds!=null){
                ds.close();  
            }
        }
    }
    
    /**
     * method readPeers; read a list of valid peers from file
     * based on link: http://outofmemory.cn/code-snippet/2320/java-usage-BufferedReader-class-duqu-wenbenwenjian
     * @param no parameter
     * @return ArrayList<String>
     * @exception Exception
     * @author Yan HAO
     * @version 22/05/2015
     */
    public ArrayList<String> readPeers(){
        ArrayList<String> ipList=new ArrayList<String>();
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader("Peers.txt"));
            String line = null;
            String ip;
            while ((line = br.readLine()) != null) {
                // get IP from the string format "Peer 1, Fred <IP 192.168.1.1>"
                ip = line.substring(line.indexOf("<IP")+4,line.indexOf(">"));
                ipList.add(ip); // add to ipList, used to be target ip
            }
            System.out.println("=================================");
            return ipList;
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if (br != null) {
                try {
                    br.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}

