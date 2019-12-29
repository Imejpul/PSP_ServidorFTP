import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ListarFicheros {

    private void listFicherosFromServer() throws IOException {
        InetAddress direccion = InetAddress.getByName("localhost");
        Socket socket = new Socket(direccion, 21);
        socket.setSoTimeout(2000);

        //DataInputStream dis = new DataInputStream(socket.getInputStream());
        BufferedReader fentrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        System.out.println(fentrada.readLine());

        fentrada.close();
        socket.close();
    }

    public static void main(String[] args) throws IOException {
        ListarFicheros lf = new ListarFicheros();
        lf.listFicherosFromServer();
    }
}
