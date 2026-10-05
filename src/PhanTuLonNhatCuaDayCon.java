/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.net.*;
import java.io.*;
import java.util.ArrayList;
/**
 *
 * @author Dell
 */
public class PhanTuLonNhatCuaDayCon {
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        String host="...";
        int port=2207;
        String msv="B23DCCN902";
        String ma="...";
        
        DatagramSocket socket= new DatagramSocket();
        InetAddress address= InetAddress.getByName(host);
        String res= msv+ma;
        byte[] sendData=res.getBytes();
        DatagramPacket sendPacket= new DatagramPacket(sendData,sendData.length,address,port);
        socket.send(sendPacket);
        
        byte[] receiveData= new byte[4096];
        DatagramPacket receivePacket= new DatagramPacket(receiveData, receiveData.length);
        socket.receive(receivePacket);
        String response= new String(receivePacket.getData(),0,receivePacket.getLength());
        String[] tmps=response.split(";");
        int n=Integer.parseInt(tmps[1]);
        int k=Integer.parseInt(tmps[2]);
        String[] digit=tmps[3].split(",");
        ArrayList<Integer> arr= new ArrayList<>();
        ArrayList<Integer> arr1= new ArrayList<>();
        for ( String x: digit){
            arr.add(Integer.parseInt(x));
        }
        for(int i=0;i<=n-k;i++){
            int max=arr.get(i);
            for(int j=i;j<i+k;j++){
                if(arr.get(j)>max){
                    max=arr.get(j);
                }
            }
            arr1.add(max);
        }
        String result="";
        for(int i=0;i<arr1.size();i++){
            result+=arr1.get(i)+",";
        }
        result=result.substring(0,result.length()-1);
        byte[] resultData=result.getBytes();
        DatagramPacket resultPacket= new DatagramPacket(resultData,resultData.length,address,port);
        socket.send(resultPacket);
        
        
    }
}
