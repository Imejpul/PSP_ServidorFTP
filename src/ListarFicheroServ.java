import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ListarFicheroServ {

    private ServerSocket serverSocket = null;

    public ListarFicheroServ() throws IOException {
        serverSocket = new ServerSocket(21);
        System.out.println("Esperando peticiones...");
    }

    public void startServer() {
        while (true) {
            try {
                Socket forClient = serverSocket.accept();
                PrintStream salida = new PrintStream(forClient.getOutputStream(), true);

                File f = new File("./archivos");
                File[] fileNames = f.listFiles();

                for (int i = 0; i < fileNames.length; i++) {
                    salida.print(fileNames[i].getName() + " ");
                }
                //dos.close();
                salida.close();
                forClient.close();

            } catch (IOException e) {
                System.out.println("Error -> " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new ListarFicheroServ().startServer();
    }
}
