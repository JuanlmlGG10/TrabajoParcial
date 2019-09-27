package pe.upc.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import pe.upc.business.DistritoBusiness;
import pe.upc.business.PersonaBusiness;
import pe.upc.model.entity.Distrito;
import pe.upc.model.entity.Persona;
import pe.upc.util.Message;

@Named
@SessionScoped
public class PersonaController implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Inject
	private PersonaBusiness personaBusiness;
	
	@Inject
	private DistritoBusiness distritoBusiness;
	
	private Persona persona;
	private List<Persona> personas;
	private Persona personaSelect;

	private Distrito distrito;
	private List<Distrito> distritos;
	
	private String filterName;

	@PostConstruct
	public void init() {
		persona = new Persona();
		personas = new ArrayList<Persona>();

		distrito= new Distrito();
		distritos= new ArrayList<Distrito>();
		getAllPersonas();
	}

	public void getAllPersonas() {
		try {
			personas = personaBusiness.getAll();
		} catch (Exception e) {
			Message.messageError("Error Carga de Personas :" + e.getMessage());
		}
	}

	public String newPersonas() {

		try {
			this.distritos = distritoBusiness.getAll();
			resetForm();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "insert.xhtml";
	}

	public String listPersonas() {
		return "list.xhtml";
	}

	public String savePersona() {
		String view = "";
		try {

			if (Long.valueOf(persona.getCPersona()) != null) {
				persona.setDistrito(distrito);
				personaBusiness.update(persona);
				Message.messageInfo("Registro actualizado exitosamente");
			} else {
				persona.setDistrito(distrito);
				personaBusiness.insert(persona);
				Message.messageInfo("Registro guardado exitosamente");

			}
			this.getAllPersonas();
			resetForm();
			TimeUnit.SECONDS.sleep(3);
			view = "list";
		} catch (Exception e) {
			Message.messageError("Error Persona :" + e.getStackTrace());
		}

		return view;
	}

	public String editPersona() {
		String view = "";
		try {
			if (this.personaSelect != null) {
				this.persona = personaSelect;

				view = "/persona/update.xhtml";
			} else {
				Message.messageInfo("Debe seleccionar una persona");
			}
		} catch (Exception e) {
			Message.messageError("Error Persona :" + e.getMessage());
		}

		return view;
	}
	public String deletePersona() {
		String view = "";
		try {
			if (this.personaSelect != null) {
				this.persona = personaSelect;

				personaBusiness.delete(persona);
			} else {
				Message.messageInfo("Debe seleccionar una persona");
			}
		} catch (Exception e) {
			Message.messageError("Error Persona :" + e.getMessage());
		}
		this.getAllPersonas();
		resetForm();
		view = "list";
		return view;
	}
	
	public void searchPersonaByName() {
		try {

			personas = personaBusiness.getByName(this.filterName.trim());
			resetForm();
			if (personas.isEmpty()) {
				Message.messageInfo("No se encontraron personas");

			}

		} catch (Exception e) {
			Message.messageError("Error Persona Search :" + e.getMessage());
		}
	}

	public void selectPersona(SelectEvent e) {
		this.personaSelect = (Persona) e.getObject();
	}

	public void resetForm() {
		this.filterName = "";
		this.persona = new Persona();
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<Persona> getPersonas() {
		return personas;
	}

	public void setPersona(List<Persona> personas) {
		this.personas = personas;
	}

	public Persona getPersonaSelect() {
		return personaSelect;
	}

	public void setPersonaSelect(Persona personaSelect) {
		this.personaSelect = personaSelect;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}
	public Distrito getDistrito() {
		return distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

	public List<Distrito> getDistritos() {
		return distritos;
	}

	public void setDistritos(List<Distrito> distritos) {
		this.distritos = distritos;
	}
	
}
