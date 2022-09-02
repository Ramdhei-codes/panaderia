package panaderia.logica;

import java.util.List;
import java.util.logging.Logger;

import panaderia.datos.DatosPruebaOrdenes;
import panaderia.datos.DatosPruebaProductos;
import panaderia.datos.EscritorArchivoOrdenes;
import panaderia.datos.IFuenteDatos;
import panaderia.datos.LectorArchivos;
import panaderia.entidades.base.Producto;
import panaderia.entidades.base.Recorrido;
import panaderia.entidades.base.Tienda;
import panaderia.entidades.pedido.OrdenPedido;

/**
 * Lógica del programa de un recorrido de un vendedor,
 * para crear las órdenes de pedido de las tiendas.
 * 
 * @version 1.0
 */
public class ControlRecorrido {
	private Recorrido recorrido;
	private OrdenPedido ordenEnProceso;

	public ControlRecorrido() {
		this.recorrido = new Recorrido();
		this.ordenEnProceso = null;
	}

	public void cargarDatosIniciales() {
		CargadorDatos cargador = new CargadorDatos(recorrido);
		cargador.cargarDatosIniciales();
	}

	// COMPLETAR LOS MÉTODOS QUE FALTAN

	public boolean existeTienda(String codigoTienda) {
		if (this.recorrido.buscarTienda(codigoTienda) == null) {
			return false;
		}
		return true;
	}

	public void crearOrden(String nombreArchivoProductos, String codigoTienda) {
		IFuenteDatos datosPruebaOrdenes = new LectorArchivos("ordenes/"+nombreArchivoProductos);
		Tienda tiendaOrden = this.recorrido.buscarTienda(codigoTienda);

		this.ordenEnProceso = new OrdenPedido(tiendaOrden);

		try {
			List<String[]> datosOrdenes = datosPruebaOrdenes.obtenerDatosBase();

			for (String[] datoOrden : datosOrdenes) {
				crearDetalle(this.ordenEnProceso, datoOrden);	
			}

		} catch (Exception e) {
			Logger logger = Logger.getLogger(ControlRecorrido.class.getName());
			logger.warning("Error al obtener los datos de las órdenes");
		}

	}


	private void crearDetalle(OrdenPedido orden, String[] datosBaseDetalle) {
		Producto productoOrden = this.recorrido.buscarProducto(datosBaseDetalle[0]);
		if(productoOrden == null) {
			return;
		}
		orden.addDetalle(productoOrden, Integer.parseInt(datosBaseDetalle[1]));
	}

	public String obtenerDatosTienda(String codigoTienda) {
		Tienda tiendaConsultada = this.recorrido.buscarTienda(codigoTienda);

		return tiendaConsultada.toString();
	}

	public List<String> obtenerDetallesOrdenados() {
		return this.ordenEnProceso.getDetallesOrdenados();
	}

	/**
	 * Envía la orden para que su información
	 * se guarde en un archivo, y luego
	 * deja el atributo en null para que se
	 * pueda procesar una nueva orden.
	 */
	public void guardarOrden() {
		EscritorArchivoOrdenes escritor = new EscritorArchivoOrdenes();
		escritor.escribirOrden(ordenEnProceso);
		ordenEnProceso = null;
	}
}
