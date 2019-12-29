import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class DescargarArchivoServ {

    private ServerSocket serverSocket = null;

    public DescargarArchivoServ() throws IOException {
        serverSocket = new ServerSocket(21);
        System.out.println("Esperando peticiones para descargar archivo...");
    }

    public void startServer() {
        while (true) {
            try {
                Socket forClient = serverSocket.accept();

                //entrada para obtener nombre archivo
                BufferedReader fentrada = new BufferedReader(new InputStreamReader(forClient.getInputStream()));
                String nombreArchivo = fentrada.readLine();

                //comprobación nombre archivo
                File f = new File("./archivos");
                File[] fileNames = f.listFiles();
                boolean nombreArchivoValido = false;

                for (File fileName : fileNames) {
                    if (nombreArchivo.equals(fileName.getName())) {
                        nombreArchivoValido = true;
                    }
                }

                if (nombreArchivoValido) {
                    //entrada para obtener bytes archivo
                    FileInputStream inputStream = new FileInputStream("./archivos/" + nombreArchivo);
                    //salida para guardar fichero en ruta establecida
                    FileOutputStream fileOS = new FileOutputStream(System.getProperty("user.home") + "/Desktop/" + nombreArchivo);

                    byte[] data = new byte[1024];
                    int byteContent;
                    while ((byteContent = inputStream.read(data, 0, 1024)) != -1) {
                        fileOS.write(data, 0, byteContent);
                    }
                    PrintWriter fsalida = new PrintWriter(forClient.getOutputStream(), true);
                    fsalida.println("¡Descargado satisfactoriamente en tu escritorio!");

                } else {
                    PrintWriter fsalida = new PrintWriter(forClient.getOutputStream(), true);
                    fsalida.println("¡Nombre archivo no válido o no existe!");
                }
            } catch (IOException e) {
                System.out.println("Error -> " + e.getMessage());
            }
        }

    }

    public static void main(String[] args) throws IOException {
        new DescargarArchivoServ().startServer();
    }
}
