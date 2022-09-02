package panaderia.datos;

import java.util.ArrayList;
import java.util.List;

import panaderia.datos.IFuenteDatos;

public class DatosPruebaOrdenes implements IFuenteDatos{
    @Override
	public List<String[]> obtenerDatosBase() {
		String[] orden1 = {"100","2"};
		String[] orden2 = {"200","1"};
		String[] orden3 = {"400","3"};
		String[] orden4 = {"500","6"};

		List<String[]> ordenes = new ArrayList<>();
		ordenes.add(orden1);
		ordenes.add(orden2);
		ordenes.add(orden3);
		ordenes.add(orden4);
		return ordenes;
	}
}
