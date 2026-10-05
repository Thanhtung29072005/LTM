/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ThucHanh_UDP.FpSwZVOw;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author Dell
 */
public class UDPClient {
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        String host = "36.50.135.242";
        int port= 2207;
        String msv="B23DCCN902";
        String ma= "FpSwZVOw";
        
        DatagramSocket socket = new DatagramSocket();
        String res= ";"+msv+";"+ma;
        byte[] sendData= res.getBytes();
        InetAddress address = InetAddress.getByName(host);
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
        socket.send(sendPacket);
        
        byte[] receiveData = new byte[4096];
        DatagramPacket receivePacket= new DatagramPacket(receiveData, receiveData.length);
        socket.receive(receivePacket);
        String response= new String(receivePacket.getData(),0,receivePacket.getLength());
        String[] parts = response.split(";");

            String requestId = parts[0];

            int n = Integer.parseInt(parts[1]);

            String numberString = parts[2];

            String[] numbers =
                    numberString.split(",");

            // ==================================
            // 5. Đánh dấu các số đã xuất hiện
            // ==================================

            boolean[] appeared =  new boolean[n + 1];
            for (String number : numbers) {
                int x = Integer.parseInt(number);
                if (x >= 1 && x <= n) {
                    appeared[x] = true;
                }
            }
            // ==================================
            // 6. Tìm các số còn thiếu
            // ==================================
            StringBuilder missing =
                    new StringBuilder();
            for (int i = 1; i <= n; i++) {
                if (!appeared[i]) {
                    if (missing.length() > 0) {
                        missing.append(",");
                    }
                    missing.append(i);
                }
            }
            // ==================================
            // 7. Tạo kết quả
            // ==================================

            String result =  requestId + ";" + missing;

            

            // ==================================
            // 8. Gửi kết quả
            // ==================================

            byte[] resultData =result.getBytes();

            DatagramPacket resultPacket =
                    new DatagramPacket(
                            resultData,
                            resultData.length,
                            address,
                            port
                    );

            socket.send(resultPacket);

            // ==================================
            // 9. Đóng socket
            // ==================================

            socket.close();
        
    }
}
