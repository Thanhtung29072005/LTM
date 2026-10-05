/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uqqIzk0p;

import java.io.IOException;
import java.net.*;


/**
 *
 * @author Dell
 */
public class UDPClient {
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        String host="36.50.135.242";
        int port=2208;
        String msv="B23DCCN902";
        String ma="uqqIzk0p";
        
        DatagramSocket socket=new DatagramSocket();
        InetAddress address= InetAddress.getByName(host);
        String res=";"+msv+";"+ma;
        byte[] sendData=res.getBytes();
        DatagramPacket sendPacket=new DatagramPacket(sendData,sendData.length,address,port);
        socket.send(sendPacket);
        
        byte[] receiveData=new byte[4096];
        DatagramPacket receivePacket= new DatagramPacket(receiveData,receiveData.length);
        socket.receive(receivePacket);
        String response=new String(receivePacket.getData(),0,receivePacket.getLength());
        String[] parts=response.split(";");
        String[] words=parts[1].split(" ");
        String tmp="";
        for(String x: words){
            tmp+=Character.toUpperCase(x.charAt(0));
            for(int i=1;i<x.length();i++){
                tmp+=Character.toLowerCase(x.charAt(i));
            }
            tmp+=" ";
        }
        tmp=tmp.trim();
        String result=parts[0]+";"+tmp;
        byte[] sendResult=result.getBytes();
        DatagramPacket resultPacket=new DatagramPacket(sendResult,sendResult.length,address,port);
        socket.send(resultPacket);
        socket.close();
    }
}
