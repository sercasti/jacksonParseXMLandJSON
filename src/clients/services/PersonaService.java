package clients.services;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import clients.model.ResponseObject;
import clients.model.impl.Persona;

public class PersonaService extends ServicioOKC {

	String getVgValue() {
		return "Vg_ent_listar";
	}

	@Override
	ResponseObject bindJSONObject(String jsonString) {
		Persona persona = null;
		try {
			persona = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true)
					.readValue(jsonString, Persona.class);
		} catch (IOException e) {
		}
		return persona;
	}

}
