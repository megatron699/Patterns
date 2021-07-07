package lab2.lab24.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class MultiplicationProxy {
    public static double Multiplication(double first, double second) throws Exception {
        try (Socket socket = new Socket("localhost", 5000)) {
            try (PrintStream outputSocketStream = new PrintStream(socket.getOutputStream())) {
                try (BufferedReader inputSocketStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
                    outputSocketStream.println(first);
                    outputSocketStream.println(second);
                    outputSocketStream.println();

                    String line = inputSocketStream.readLine();
                    if (line != null) {
                        return Double.parseDouble(line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new Exception("Операцию умножения выполнить не удалось");
    }

}
