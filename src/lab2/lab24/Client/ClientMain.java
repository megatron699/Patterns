package lab2.lab24.Client;

public class ClientMain {
    public static void main(String[] args) throws Exception {
        double first = 15;
        double second = 20;
        double result = MultiplicationProxy.Multiplication(first, second);

        System.out.printf("%s * %s = %s", first, second, result);

    }
}
