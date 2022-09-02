package panaderia.vista;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import panaderia.logica.ControlRecorrido;

/**
 * Realiza la interacción con el usuario
 * para poder iniciar el programa y procesar
 * las órdenes de pedido de un recorrido de ventas.
 * 
 * @version 1.0
 */
public class ProgramaPancita {
	private ControlRecorrido control;

	public ProgramaPancita() {
		this.control = new ControlRecorrido();
	}

	/**
	 * Permite cargar los datos iniciales necesarios
	 * para hacer el recorrido.
	 */
	public void iniciar() {
		this.control.cargarDatosIniciales();
	}

	/**
	 * Es el ciclo de control general del programa,
	 * para saber si hay más ordenes o termina.
	 */
	public void hacerRecorrido() {
		Scanner consola = new Scanner(System.in);
		String respuesta = "S";
		while (respuesta.equals("S")) {
			System.out.println("¿Desea crear orden de pedido (S/N)?");
			respuesta = consola.nextLine();

			if (respuesta.equals("S")) {
				this.procesarUnaOrden();
			}
		}
		System.out.println("Fin del programa. Muchas gracias.");
		consola.close();
	}

	/**
	 * Coordina el proceso para poder crear una orden
	 * de pedido, mostrarla y pedir la aceptación
	 * del usuario.
	 */
	private void procesarUnaOrden() {
		Scanner consola = new Scanner(System.in);

		// COMPLETAR:

		// PRIMERO PEDIR EL CÓDIGO DE LA TIENDA
		// Y VERIFICAR SI EXISTE.
		System.out.println("Ingrese el código de la tienda: ");
		String codigoTienda = "";
		codigoTienda = consola.next();

		if (!this.control.existeTienda(codigoTienda)) {
			System.out.println("Tienda no encontrada");
			consola.close();
			return;
		}

		// SI EXISTE:
		// PEDIR LA RUTA CON LOS PRODUCTOS DEL PEDIDO
		// Y SOLICITAR AL CONTROL CREAR LA ORDEN.
		System.out.println("Ingrese la ruta con los productos del pedido: ");
		String ruta = "";
		ruta = consola.next();

		// File archivoPedido = new File(ruta);

		// if(!archivoPedido.exists()) {
		// System.out.println("Problemas con la lectura del archivo");
		// consola.close();
		// return;
		// }

		this.control.crearOrden(ruta, codigoTienda);

		// LUEGO: PEDIR AL CONTROL LOS DATOS DE LA TIENDA
		// Y LOS DETALLES ORDENADOS, PARA MOSTRAR AL USUARIO.
		System.out.println(this.control.obtenerDatosTienda(codigoTienda));
		List<String> detallesOrdenados = this.control.obtenerDetallesOrdenados();
		for (String detalle : detallesOrdenados) {
			System.out.println(detalle);
		}

		// PREGUNTAR SI DESEA ACEPTAR. SI ACEPTA:
		// INFORMAR AL CONTROL PARA QUE GUARDE LA ORDEN.
		String aceptaOrden = "S";
		while (aceptaOrden.equals("S")) {
			System.out.println("¿Acepta la orden? (S/N)");
			aceptaOrden = consola.next();
			if (aceptaOrden.equals("N")) {
				consola.close();
				return;
			}
			aceptaOrden = "N";
			control.guardarOrden();

		}

	}
}
