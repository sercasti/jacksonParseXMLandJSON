import clients.model.impl.Criteria;
import clients.model.impl.Persona;
import clients.services.EstadoCivilService;
import clients.services.PersonaService;

public class SampleClientApp {

	public static void main(String[] args) {
		PersonaService personaService = new PersonaService();
		Criteria criteria = new Criteria();
		criteria.addParameter("cuit", "2720012345675");
		Persona persona = (Persona) personaService.query(criteria);
		System.out.println(persona.getApellido());
		
		EstadoCivilService estadoCivilService = new EstadoCivilService();
		estadoCivilService.query();
	}

}
