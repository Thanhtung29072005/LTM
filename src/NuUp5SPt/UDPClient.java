/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NuUp5SPt;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
/**
 *
 * @author Dell
 */
public class UDPClient {
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        String host="36.50.135.242";
        int port=2208;
        String msv="B23DCCN902";
        String ma="NuUp5SPt";
        
        DatagramSocket socket=new DatagramSocket();
        InetAddress address= InetAddress.getByName(host);
        String res=";"+msv+";"+ma;
        byte[] sendData=res.getBytes();
        DatagramPacket sendPacket= new DatagramPacket(sendData, sendData.length,address,port);
        socket.send(sendPacket);
        
        byte[] receiveData= new byte[4096];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        socket.receive(receivePacket);
        String response = new String(receivePacket.getData(),0,receivePacket.getLength());
        String[] parts=response.split(";");
        HashMap<Character,Integer> mp= new HashMap<>();
        for(char x:parts[1].toCharArray()){
            if(mp.containsKey(x)){
                mp.put(x, mp.get(x)+1);
            }
            else{
                mp.put(x, 1);
            }
        }
        char c=parts[1].charAt(0);
        int max=mp.get(c);
        for(int i=0;i<parts[1].length();i++){
            char x=parts[1].charAt(i);
            if(mp.get(x)>max){
                c=x;
                max=mp.get(x);
            }
        }
        StringBuilder arr=new StringBuilder();
        for(int i=0;i<parts[1].length();i++){
            if(parts[1].charAt(i)==c){
                arr.append(i+1).append(",");
            }
        }
        String result=parts[0]+";"+c+":"+arr;
        byte[] sendResult=result.getBytes();
        DatagramPacket resultPacket= new DatagramPacket(sendResult, sendResult.length,address,port);
        socket.send(resultPacket);
        socket.close();
    }
}
