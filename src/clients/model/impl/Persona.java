package clients.model.impl;

import clients.model.ResponseObject;

public class Persona implements ResponseObject {
	private String nro_docu;
	private String sexo;
	private String nombres;
	private String apellido;
	private String cuit;

	public String getNro_docu() {
		return nro_docu;
	}

	public void setNro_docu(String nro_docu) {
		this.nro_docu = nro_docu;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

}
