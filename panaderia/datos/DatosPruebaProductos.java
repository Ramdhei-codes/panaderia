package panaderia.datos;

import java.util.ArrayList;
import java.util.List;

/**
 * Un clase usada para hacer pruebas.
 * Permite obtener una lista de tiendas fijas
 * (sin tener que leer un archivo externo).
 * 
 * @version 1.0
 */
public class DatosPruebaProductos implements IFuenteDatos {

	@Override
	public List<String[]> obtenerDatosBase() {
		String[] producto1 = {"100","Pan de Chocolate", "15000"};
		String[] producto2 = {"200","Croissant", "2000"};
		String[] producto3 = {"300","Buñuelo", "1200"};
		String[] producto4 = {"400","Empanada de Carne", "1500"};
		String[] producto5 = {"300","Empanada de Pollo", "1600"};


		List<String[]> productos = new ArrayList<>();
		productos.add(producto1);
		productos.add(producto2);
		productos.add(producto3);
		productos.add(producto4);
		productos.add(producto5);
		return productos;
	}
}