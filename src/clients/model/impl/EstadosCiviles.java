package clients.model.impl;

import java.util.ArrayList;
import java.util.List;

import clients.model.ResponseObject;

public class EstadosCiviles implements ResponseObject{

	private List<String> nombres = new ArrayList<String>();

	public List<String> getNombres() {
		return nombres;
	}

	public void setNombres(List<String> nombres) {
		this.nombres = nombres;
	}

}
