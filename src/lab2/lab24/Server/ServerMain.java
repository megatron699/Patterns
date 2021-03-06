package lab2.lab24.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            while (true) {
                System.out.println("Waiting for connection ");

                Socket socket = serverSocket.accept();
                System.out.println("Connection was successful ");

                try (BufferedReader inputSocketStream = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    try (PrintWriter outputSocketStream = new PrintWriter(socket.getOutputStream())) {

                        ArrayList<Double> doubles = new ArrayList<Double>();
                        String line = inputSocketStream.readLine();
                        while (line != null && line.length() > 0) {
                            doubles.add(Double.parseDouble(line));
                            line = inputSocketStream.readLine();
                        }

                        System.out.printf("First number: %s%nSecond number: %s%n", doubles.get(0), doubles.get(1));
                        double mul = doubles.get(0) * doubles.get(1);
                        System.out.println("Mul result: " + mul);

                        outputSocketStream.write(String.valueOf(mul));
                        outputSocketStream.flush();

                        System.out.println("Connection closed");
                    }
                }
            }
        }
    }
}
