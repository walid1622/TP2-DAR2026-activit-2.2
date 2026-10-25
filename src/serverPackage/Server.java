package serverPackage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import operationPackage.Operation;
public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Serveur en attente de connexion...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connecté.");
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());

            while (true) {
                Operation op = (Operation) in.readObject();
                int a = op.getOp1();
                int b = op.getOp2();
                char o = op.getOperateur();
                if (a == 0 && b == 0 && o == '0') break;

                double result = 0;
                boolean valide = true;
                switch (o) {
                    case '+': result = a + b; break;
                    case '-': result = a - b; break;
                    case '*': result = a * b; break;
                    case '/':
                        if (b != 0) result = (double) a / b;
                        else {
                            out.writeUTF("Erreur : division par zéro !");
                            valide = false;
                        }
                        break;
                    default:
                        out.writeUTF("Erreur : opérateur invalide !");
                        valide = false;
                }

                if (valide)
                    out.writeUTF("Résultat = " + result);

                System.out.println("Opération traitée : " + a + " " + o + " " + b);
            }
            in.close();
            out.close();
            clientSocket.close();
            System.out.println("Serveur arrêté.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
