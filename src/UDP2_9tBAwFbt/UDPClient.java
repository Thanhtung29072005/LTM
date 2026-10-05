/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP2_9tBAwFbt;
import java.net.*;
import java.io.*;
import UDP.Customer;
/**
 *
 * @author Dell
 */
public class UDPClient {
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException, ClassNotFoundException {
        String host="36.50.135.242";
        int port=2209;
        String msv="B23DCCN902";
        String ma="9tBAwFbt";
        
        DatagramSocket socket= new DatagramSocket();
        InetAddress address= InetAddress.getByName(host);
        String res=";"+msv+";"+ma;
        byte[] sendData= res.getBytes();
        DatagramPacket sendPacket= new DatagramPacket(sendData, sendData.length, address, port);
        socket.send(sendPacket);
        
        byte[] receiveData= new byte[4096];
        DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
        socket.receive(receivePacket);
        byte[] reID=new byte[8];
        System.arraycopy(receivePacket.getData(), 0, reID, 0, 8);
        ByteArrayInputStream bais= new ByteArrayInputStream(receivePacket.getData(), 8,receivePacket.getLength()-8 );
        ObjectInputStream ois= new ObjectInputStream(bais);
        Customer customer= (Customer) ois.readObject();
        String y=customer.getName();
        customer.setName(chuanHoaTen(customer.getName()));
        customer.setDayOfBirth(chuanHoaNS(customer.getDayOfBirth()));
        customer.setUserName(taoName(y));
        ByteArrayOutputStream baos= new ByteArrayOutputStream();
        ObjectOutputStream oos= new ObjectOutputStream(baos);
        oos.writeObject(customer);
        oos.flush();
        byte[] customerData= baos.toByteArray();
        byte[] result= new byte[8+customerData.length];
        System.arraycopy(reID, 0, result, 0, 8);
        System.arraycopy(customerData, 0, result, 8, customerData.length);
        DatagramPacket resultPacket= new DatagramPacket(result,result.length,address,port);
        socket.send(resultPacket);
        socket.close();
        
        
    }
    public static String chuanHoaTen(String x){
        String[] words= x.trim().toLowerCase().split(" ");
        String a=words[words.length-1].toUpperCase()+", ";
        for(int i=0;i<words.length-1;i++){
            a+=Character.toUpperCase(words[i].charAt(0));
            for(int j=1;j<words[i].length();j++){
                a+=words[i].charAt(j);
            }
            a+=" ";
        }
        return a.trim();
        
    }
    public static String chuanHoaNS(String x){
        String[] words= x.split("-");
        String a=words[1]+"/"+words[0]+"/"+words[2];
        return a;
    }
    public static String taoName(String x){
        String[] words= x.trim().toLowerCase().split(" ");
        String a="";
        for(int i=0;i<words.length-1;i++){
            a+=words[i].charAt(0);
        }
        a+=words[words.length-1];
        return a;
    }
}
