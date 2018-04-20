package clients.services;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.xml.sax.InputSource;

import clients.model.ResponseObject;
import clients.model.impl.Criteria;

public abstract class ServicioOKC {

	abstract String getVgValue();

	abstract ResponseObject bindJSONObject(String jsonString);

	public ResponseObject query(Criteria criteria) {
		return runRequest(criteria == null ? null : criteria.getParameters());
	}

	private ResponseObject runRequest(Map<String, String> parameters) {

		StringBuilder builder = new StringBuilder();
		builder.append("https://novatest.redmutual.com.ar:10443/fw/getXML.aspx?app_cod_sistema=nv_mutuales");
		builder.append("&accion=getxml_json");
		builder.append("&vg=");
		builder.append(getVgValue());

		if (null != parameters && !parameters.isEmpty()) {
			builder.append("&params=");
			StringBuilder paramsBuilder = new StringBuilder();
			paramsBuilder.append("<Criteria> params ");
			for (Map.Entry<String, String> entry : parameters.entrySet()) {
				paramsBuilder.append(entry.getKey());
				paramsBuilder.append("=");
				paramsBuilder.append(entry.getValue());
			}
			paramsBuilder.append("</Criteria>");
			builder.append(encodeURLParams(paramsBuilder.toString()));
		}
		// hacemos la llamada a OKC con esa URL y nos devuelve esto (por ejemplo)
		String response = "<rs_xml_json> <fields> <![CDATA["
				+ "({0: {name:'tipo_docu', datatype : 'i2'},1: {name:'nro_docu', datatype : 'int'},2: {name:'sexo', datatype : 'string'},3: {name:'tipo_docu_desc', datatype : 'string'},4: {name:'apellido', datatype : 'string'},5: {name:'nombres', datatype : 'string'},6: {name:'fe_naci', datatype : 'dateTime'},7: {name:'nro_entidad', datatype : 'int'},8: {name:'cuit', datatype : 'string'},9: {name:'estado_civil', datatype : 'string'},10: {name:'e_civil', datatype : 'string'},11: {name:'numero', datatype : 'string'},12: {name:'piso', datatype : 'string'},13: {name:'depto', datatype : 'string'},14: {name:'resto', datatype : 'string'},15: {name:'descVivienda', datatype : 'string'},16: {name:'a_cargo', datatype : 'i2'},17: {name:'telefono', datatype : 'string'},18: {name:'car_tel', datatype : 'string'},19: {name:'postal', datatype : 'int'},20: {name:'postal_real', datatype : 'int'},21: {name:'postal_real_subcod', datatype : 'int'},22: {name:'localidad', datatype : 'string'},23: {name:'tipo_docu_c', datatype : 'i2'},24: {name:'nro_docu_c', datatype : 'int'},25: {name:'nombre_c', datatype : 'string'},26: {name:'sexo_c', datatype : 'string'},27: {name:'tipo_docu_desc_c', datatype : 'string'},28: {name:'email', datatype : 'string'},29: {name:'cpa', datatype : 'string'},30: {name:'apellido_materno', datatype : 'string'},31: {name:'nro_nacion', datatype : 'i2'},32: {name:'nacionalidad', datatype : 'string'}, length : 33})"
				+ "]]>" + "</fields>" + "<data recordcount=\"1\">" + "<![CDATA["
				+ "({'tipo_docu': '3','nro_docu': '1234567','sexo': 'M','tipo_docu_desc': 'DNI','apellido': 'ZARZA','nombres': 'ALDO','nro_entidad': '264884','cuit': '20012345675','estado_civil': 'S','e_civil': 'Soltero','numero': 'Belgranooooo','piso': '8','depto': 'A','resto': 'interno','descVivienda': 'Propia','a_cargo': '0','telefono': '4559630','car_tel': '0342','postal': '6813','postal_real': '5501','postal_real_subcod': '0','localidad': '5501 - VILLA DEL PARQUE (MENDOZA)','tipo_docu_c': '-1','nro_docu_c': '-1','email': 'jozan@redmutualzzz.com.ar','cpa': 'M5501APB','apellido_materno': 'PEREZ','nro_nacion': '1','nacionalidad': 'ARGENTINA'})"
				+ "]]>" + "</data>" + "<params>" + "<![CDATA["
				+ "{timeout:0 , PageSize:0 , AbsolutePage:'0' , orden_original:'' , recordcount:1 , PageCount:1 , cacheControl:'none' , orden:'' , cache:false}"
				+ "]]>" + "</params>" + "</rs_xml_json>";

		String jsonString = extractJsonFromXMLResponse(response);
		return bindJSONObject(jsonString.substring(1, jsonString.length() - 1));
	}

	private String extractJsonFromXMLResponse(String response) {
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();

		InputSource source = new InputSource(new StringReader(response));
		String jsonString = null;
		try {
			jsonString = xpath.evaluate("/rs_xml_json/data", source);
		} catch (XPathExpressionException e) {
		}
		return jsonString;
	}

	private String encodeURLParams(String params) {
		try {
			return URLEncoder.encode(params, "UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}

}
