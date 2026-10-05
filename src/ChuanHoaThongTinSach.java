/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import UDP.Book;
import java.io.*;
import java.net.*;
/**
 *
 * @author Dell
 */
public class ChuanHoaThongTinSach {
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException, ClassNotFoundException {
        String host="...";
        int port=2209;
        String ma="...";
        String msv="B23DCCN902";
        
        DatagramSocket socket= new DatagramSocket();
        InetAddress address= InetAddress.getByName(host);
        String res=msv+ma;
        byte[] sendData= res.getBytes();
        DatagramPacket sendPacket= new DatagramPacket(sendData, sendData.length,address, port);
        socket.send(sendPacket);
        
        byte[] receiveData= new byte[4096];
        DatagramPacket receivePacket= new DatagramPacket(receiveData, receiveData.length);
        socket.receive(receivePacket);
        byte[] reID= new byte[8];
        System.arraycopy(receivePacket.getData(), 0, reID, 0, 8);
        ByteArrayInputStream bais= new ByteArrayInputStream(receivePacket.getData(),8,receivePacket.getLength()-8);
        ObjectInputStream ois= new ObjectInputStream(bais);
        Book book= (Book) ois.readObject();
        //
        // Xu ly logic o day
        //
        ByteArrayOutputStream baos= new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(book);
        oos.flush();
        byte[] bookData= baos.toByteArray();
        byte[] result= new byte[8+bookData.length];
        System.arraycopy(reID, 0, result, 0, 8);
        System.arraycopy(bookData, 0, result, 8, bookData.length);
        DatagramPacket resultPacket= new DatagramPacket(result,result.length,address,port);
        socket.send(resultPacket);
        socket.close();
    }
}
