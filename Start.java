// this class is used to show data and interface to user
// Name: Yan Hao
// Student ID: 100026051

/**
 * this class is used to start the chat program
 *  run the UDP server
 *  create User Interface
 * 
 * @author Yan Hao
 * @version 19/05/2015
 */
public class Start
{    
    /**
     * this method is used to run the UDP server and create UI
     * 
     * @author Yan Hao
     * @version 19/05/2015
     * 
     */
    public static void main(String[]args){
        try{
            // start server thread
            Thread udpThread = new UDPServer();
            udpThread.start();
            
            // initial ui
            UserInterface UI = new UserInterface();
            UI.main();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
