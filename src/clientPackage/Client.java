package clientPackage;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import operationPackage.Operation;
public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1234);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             DataInputStream in = new DataInputStream(socket.getInputStream());
             Scanner sc = new Scanner(System.in)) {

            System.out.println("Client connecté au serveur.");

            while (true) {
                System.out.print("Entrez une opération (ex: 55 * 25) ou 0 0 0 pour quitter : ");
                int op1 = sc.nextInt();
                char operateur = sc.next().charAt(0);
                int op2 = sc.nextInt();

                Operation operation = new Operation(op1, operateur, op2);
                out.writeObject(operation);
                out.flush();

                if (op1 == 0 && op2 == 0 && operateur == '0') {
                    System.out.println("Fin de communication.");
                    break;
                }

                String resultat = in.readUTF();
                System.out.println("Réponse du serveur : " + resultat);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } }}