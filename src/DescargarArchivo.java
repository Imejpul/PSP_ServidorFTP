import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class DescargarArchivo {

    public void descargarArchivo() throws IOException {
        InetAddress direccion = InetAddress.getByName("localhost");
        Socket socket = new Socket(direccion, 21);
        socket.setSoTimeout(2000);

        //salida con nombre
        PrintWriter fsalida = new PrintWriter(socket.getOutputStream(), true);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Introduzca nombre archivo: ");
        String nombreArchivo = br.readLine();
        fsalida.println(nombreArchivo);

        //entrada para recibir respuesta servidor
        BufferedReader fentrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String respuesta = fentrada.readLine();
        System.out.println(respuesta);
    }

    public static void main(String[] args) throws IOException {
        DescargarArchivo da = new DescargarArchivo();
        da.descargarArchivo();
    }
}
