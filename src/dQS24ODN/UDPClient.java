/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dQS24ODN;
import java.io.IOException;
import java.net.*;
/**
 *
 * @author Dell
 */
public class UDPClient {
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        String host="36.50.135.242";
        int port=2207;
        String msv="B23DCCN902";
        String ma="dQS24ODN";
        
        DatagramSocket socket= new DatagramSocket();
        InetAddress address= InetAddress.getByName(host);
        String res=";"+msv+";"+ma;
        byte[] sendData = res.getBytes();
        DatagramPacket sendPacket= new DatagramPacket(sendData, sendData.length,address,port);
        socket.send(sendPacket);
        
        byte[] receiveData= new byte[4096];
        DatagramPacket receivePacket= new DatagramPacket(receiveData, receiveData.length);
        socket.receive(receivePacket);
        String response= new String(receivePacket.getData(),0,receivePacket.getLength());
        String[] parts=response.split(";");
        String stID=parts[0];
        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;
        String[] soNguyen=parts[1].split(",");
        for(String x: soNguyen){
            int y=Integer.parseInt(x);
            if(y<=min){
                min=y;
            }
            if(y>=max){
                max=y;
            }
        }
        String result=stID+";"+max+","+min;
        System.out.println(result);
        byte[] resultData = result.getBytes();
        DatagramPacket resultPacket= new DatagramPacket(resultData, resultData.length,address,port);
        socket.send(resultPacket);
        socket.close();
        
    }
}
