package panaderia.datos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LectorArchivos implements IFuenteDatos {

    private String nombreArchivo;

    public LectorArchivos(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    @Override
    public List<String[]> obtenerDatosBase() throws Exception {
        List<String[]> listaDatos = new ArrayList<>();
        File lectorArchivo = new File("./archivos/datosBase/" + nombreArchivo);
        BufferedReader lectorBufer = new BufferedReader(new FileReader(lectorArchivo));

        String lineaArchivo;
        // Condition holds true till
        // there is character in a string
        while ((lineaArchivo = lectorBufer.readLine()) != null) {
            listaDatos.add(lineaArchivo.split(";"));
        }

        // Print the string
        lectorBufer.close();

        return listaDatos;
    }

}
