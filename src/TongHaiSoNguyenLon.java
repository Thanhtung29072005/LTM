
import java.io.IOException;
import java.math.BigInteger;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.*;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Dell
 */
public class TongHaiSoNguyenLon {
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        String host="36.50.135.242";
        int port=2207;
        String msv="B23DCCN902";
        String ma="2sIjAYaU";
        String res=msv+ma;
        DatagramSocket socket= new DatagramSocket();
        InetAddress address=  InetAddress.getByName(host);
        byte[] sendData= res.getBytes();
        DatagramPacket sendPacket= new DatagramPacket(sendData, sendData.length,address,port);
        socket.send(sendPacket);
        
        byte[] receiveData= new byte[4096];
        DatagramPacket receivePacket= new DatagramPacket(receiveData, receiveData.length);
        socket.receive(receivePacket);
        String response= new String(receivePacket.getData(),0,receivePacket.getLength() );
        String[] tmp=response.split(";");
        BigInteger a= new BigInteger(tmp[1]);
        BigInteger b= new BigInteger(tmp[2]);
        BigInteger sum=a.add(b);
        BigInteger diff= a.subtract(b);
        String result= tmp[0]+";"+sum+";"+diff;
         System.out.println("Gửi server: " + result);
        byte[] resultData= result.getBytes();
        DatagramPacket resultPacket= new DatagramPacket(resultData, resultData.length,address,port);
        socket.send(resultPacket);
    }
}
