package panaderia.logica;

import java.util.logging.Logger;
import java.util.List;

import panaderia.datos.DatosPruebaProductos;
import panaderia.datos.DatosPruebaTiendas;
import panaderia.datos.IFuenteDatos;
import panaderia.entidades.base.Producto;
import panaderia.entidades.base.Recorrido;
import panaderia.entidades.base.Tienda;

/**
 * Se encarga de crear los objetos a partir
 * de arreglos de cadenas de texto con la información
 * (esas cadenas pueden provenir de diferentes
 * fuentes, por eso usa una interfaz,
 * para que se puedan cambiar fácilmente).
 * 
 * @version 1.0
 */
public class CargadorDatos {
	private Recorrido recorrido;

	public CargadorDatos(Recorrido recorrido) {
		this.recorrido = recorrido;
	}

	/**
	 * Carga los datos iniciales que necesita el programa:
	 * tiendas y productos.
	 */
	public void cargarDatosIniciales() {
		// SE DEBE CAMBIAR ESTA FUENTE, QUE ES DE PRUEBA
		// POR UNA QUE LEA LOS DATOS DE UN ARCHIVO.

		try {
			IFuenteDatos fuenteDatosTiendas = new DatosPruebaTiendas();

			List<String[]> datosBaseTiendas = fuenteDatosTiendas.obtenerDatosBase();
			this.cargarDatosTiendas(datosBaseTiendas);

			// FALTA CARGAR LOS DATOS DE LOS PRODUCTOS:
			// PRIMERO HACERLO CON LA CLASE DE PRUEBA,
			// Y LUEGO CON LA QUE LEE DE UN ARCHIVO.
			IFuenteDatos fuenteDatosProductos = new DatosPruebaProductos(); // CAMBIAR POR EL OBJETO CORRESPONDIENTE
			List<String[]> datosBaseProductos = fuenteDatosProductos.obtenerDatosBase();
			this.cargarDatosProductos(datosBaseProductos);
		} catch (Exception e) {
			e.printStackTrace();
			// Logger logger = Logger.getLogger(CargadorDatos.class.getName());
			// logger.warning("Error al cargar los datos iniciales");
		}

	}

	/**
	 * A partir de los datos base (cadenas de texto),
	 * obtiene los datos de las tiendas,
	 * crea los objetos y los guarda en el objeto recorrido.
	 */
	private void cargarDatosTiendas(List<String[]> datosBase) {
		for (String[] datoBaseTienda : datosBase) {
			String codigo = datoBaseTienda[0];
			String nombre = datoBaseTienda[1];
			Tienda tienda = new Tienda(codigo, nombre);
			this.recorrido.addTienda(tienda);
		}
	}

	/**
	 * A partir de los datos base (cadenas de texto),
	 * obtiene los datos de los productos,
	 * crea los objetos y los guarda en el objeto recorrido.
	 */
	private void cargarDatosProductos(List<String[]> datosBase) {
		// FALTA COMPLETAR EL CÓDIGO
		for (String[] datoBaseProducto : datosBase) {
			String codigo = datoBaseProducto[0];
			String nombre = datoBaseProducto[1];
			double valorUnitario = Double.parseDouble(datoBaseProducto[2]);
			Producto producto = new Producto(codigo, nombre, valorUnitario);
			this.recorrido.addProducto(producto);
		}
	}
}