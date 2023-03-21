import java.io.*;
import java.net.*;

public class Sender {

    public static void main(String[] args) throws IOException {

        MulticastSocket socket = new MulticastSocket();
        InetAddress address = InetAddress.getByName("224.0.0.1");
        File file = new File("file.txt");
        byte[] buffer = new byte[1024];

        FileInputStream inputStream = new FileInputStream(file);
        DatagramPacket packet;

        while (inputStream.read(buffer) != -1) {
            packet = new DatagramPacket(buffer, buffer.length, address, 5000);
            socket.send(packet);
            System.out.println("Sent " + buffer.length + " bytes.");
        }

        inputStream.close();
        socket.close();
        System.out.println("File sent.");
    }
}