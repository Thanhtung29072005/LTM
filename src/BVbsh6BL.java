/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import UDP.Student;
import java.net.*;
import java.io.*;

/**
 *
 * @author Dell
 */
public class BVbsh6BL {
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException, ClassNotFoundException {
        String host="36.50.135.242";
        int port=2209;
        String msv="B23DCCN902";
        String ma="BVbsh6BL";
        
        DatagramSocket socket= new DatagramSocket();
        InetAddress address= InetAddress.getByName(host);
        String res=";"+msv+";"+ma;
        byte[] sendData= res.getBytes();
        DatagramPacket sendPacket=new DatagramPacket(sendData, sendData.length,address,port);
        socket.send(sendPacket);
        
        byte[] receiveData= new byte[4096];
        DatagramPacket receivePacket= new DatagramPacket(receiveData, receiveData.length);
        socket.receive(receivePacket);
        byte[] reID= new byte[8];
        System.arraycopy(receivePacket.getData(), 0, reID, 0, 8);
        ByteArrayInputStream bais=new ByteArrayInputStream(receivePacket.getData(),8,receivePacket.getLength()-8);
        ObjectInputStream ois= new ObjectInputStream(bais);
        Student student= (Student) ois.readObject();
        String name=student.getName();
        String[] words= name.trim().toLowerCase().split(" ");
        String s="";
        for(String x: words){
            s+=Character.toUpperCase(x.charAt(0));
            for(int i=1;i<x.length();i++){
                s+=(x.charAt(i));
            }
            s+=" ";
        }
        student.setName(s.trim());
        String lastName=words[words.length-1];
        for(int i=0;i<words.length-1;i++){
            lastName+=words[i].charAt(0);
        }
        lastName+="@ptit.edu.vn";
        student.setEmail(lastName);
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        ObjectOutputStream oos= new ObjectOutputStream(baos);
        oos.writeObject(student);
        oos.flush();
        byte[] studentData= baos.toByteArray();
        byte[] result= new byte[8+studentData.length];
        System.arraycopy(reID, 0, result, 0, 8);
        System.arraycopy(studentData, 0, result, 8, studentData.length);
        DatagramPacket resultPacket= new DatagramPacket(result,result.length,address,port);
        socket.send(resultPacket);
        socket.close();
    }
}
