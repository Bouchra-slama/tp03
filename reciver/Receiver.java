package reciver;
import java.io.*;
import java.net.*;

public class Receiver {

    public static void main(String[] args) throws IOException {

        MulticastSocket socket = new MulticastSocket(5000);
        InetAddress address = InetAddress.getByName("224.0.0.1");
        socket.joinGroup(address);
        byte[] buffer = new byte[1024];

        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        FileOutputStream outputStream = new FileOutputStream("receivedFile.txt");
        int bytesRead = 0;
        while (true) {
            socket.receive(packet);
            bytesRead = packet.getLength();
            if (bytesRead == -1) {
                break;
            }
            outputStream.write(buffer, 0, bytesRead);
            System.out.println("Received " + bytesRead + " bytes.");
        }

        outputStream.close();
        socket.leaveGroup(address);
        socket.close();
        System.out.println("File received and saved.");
    }
}