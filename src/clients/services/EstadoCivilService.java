package clients.services;

import java.io.IOException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import clients.model.ResponseObject;
import clients.model.impl.EstadosCiviles;

public class EstadoCivilService extends ServicioOKC {

	String getVgValue() {
		return "Vg_cod_estado_civil";
	}

	public ResponseObject query() {
		return query(null);
	}

	@Override
	ResponseObject bindJSONObject(String jsonString) {
		EstadosCiviles estadosCiviles = null;
		try {
			estadosCiviles = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
					.readValue(jsonString, EstadosCiviles.class);
		} catch (IOException e) {
		}
		return estadosCiviles;
	}
}
