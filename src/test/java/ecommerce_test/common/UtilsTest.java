package ecommerce_test.common;

import java.io.IOException;

public class UtilsTest {
    public static void killAllProcess() throws IOException {
        Runtime.getRuntime().exec("killall node");
    }

    public static void startEmulator() throws IOException, InterruptedException {
        Runtime.getRuntime().exec(System.getProperty("user.dir") + "/bash/startEmulator.bat");
        Thread.sleep(5000);
    }

    //    public static boolean checkServerRunning(int port){
//            boolean isServerRunning = false;
//            ServerSocket serverSocket;
//            try{
//                serverSocket = new ServerSocket(port);
//                serverSocket.close();
//            } catch (IOException e){
//                isServerRunning = true;
//            } finally {
//                serverSocket = null;
//            }
//
//            return isServerRunning;
//    }
}