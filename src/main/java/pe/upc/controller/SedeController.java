package pe.upc.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import pe.upc.business.DistritoBusiness;
import pe.upc.business.SedeBusiness;
import pe.upc.business.TipoCanchaBusiness;
import pe.upc.model.entity.Distrito;
import pe.upc.model.entity.Sede;
import pe.upc.model.entity.TipoCancha;
import pe.upc.util.Message;

@Named
@SessionScoped

public class SedeController implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject
	private SedeBusiness sedeBusiness;
	@Inject
	private DistritoBusiness distritoBusiness;

	private Sede sede;
	private List<Sede> sedes;
	private Sede sedeSelect;
	
	private Distrito distrito;
	private List<Distrito> distritos;

	private String filterName;
	private Distrito distritobuscar;

	@PostConstruct
	public void init() {
		sede = new Sede();
		sedes = new ArrayList<Sede>();

		distrito= new Distrito();
		distritos= new ArrayList<Distrito>();
		getAllSedes();
	}

	public void getAllSedes() {
		try {
			sedes = sedeBusiness.getAll();
		} catch (Exception e) {
			Message.messageError("Error Carga de Sedes :" + e.getMessage());
		}
	}

	public String newSedes() {

		try {
			this.distritos = distritoBusiness.getAll();
			resetForm();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "insert.xhtml";
	}

	public String listSedes() {
		return "list.xhtml";
	}

	public String saveSede() {
		String view = "";
		try {

			if (Long.valueOf(sede.getCSede())!= null) {
				sede.setDistrito(distrito);
				sedeBusiness.update(sede);
				Message.messageInfo("Registro actualizado exitosamente");
			} else {
				sede.setDistrito(distrito);
				sedeBusiness.insert(sede);
				Message.messageInfo("Registro guardado exitosamente");

			}
			this.getAllSedes();
			resetForm();
			view = "list";
		} catch (Exception e) {
			Message.messageError("Error Sede:" + e.getStackTrace());
		}

		return view;
	}

	public String editSede() {
		String view = "";
		try {
			if (this.sedeSelect != null) {
				this.sede = sedeSelect;

				view = "/sede/update";
			} else {
				Message.messageInfo("Debe seleccionar una sede");
			}
		} catch (Exception e) {
			Message.messageError("Error Sede:" + e.getMessage());
		}

		return view;
	}
	public String deleteSede() {
		String view = "";
		try {
			if (this.sedeSelect != null) {
				this.sede = sedeSelect;

				sedeBusiness.delete(sede);
			} else {
				Message.messageInfo("Debe seleccionar una sede");
			}
		} catch (Exception e) {
			Message.messageError("Error Sede :" + e.getMessage());
		}
		this.getAllSedes();
		resetForm();
		view = "list";
		return view;
	}

	public void searchSedeByName() {
		try {

			sedes= sedeBusiness.getByName(this.filterName.trim());
			resetForm();
			if (sedes.isEmpty()) {
				Message.messageInfo("No se encontraron sedes");

			}

		} catch (Exception e) {
			Message.messageError("Error Sede Search :" + e.getMessage());
		}
	}
	public void searchSedeByDistrito() {
		try {

			sedes= sedeBusiness.getByDistrito(distritobuscar);
			resetForm();
			if (sedes.isEmpty()) {
				Message.messageInfo("No se encontraron sedes");

			}

		} catch (Exception e) {
			Message.messageError("Error Sede Search :" + e.getMessage());
		}
	}
	public void selectSede(SelectEvent e) {
		this.sedeSelect = (Sede) e.getObject();
	}

	public void resetForm() {
		this.filterName = "";
		this.sede = new Sede();
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

	public List<Sede> getSedes() {
		return sedes;
	}

	public void setPersona(List<Sede> sedes) {
		this.sedes = sedes;
	}

	public Sede getSedeSelect() {
		return sedeSelect;
	}

	public void setSedeSelect(Sede sedeSelect) {
		this.sedeSelect = sedeSelect;
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
		this.distritos= distritos;
	}

	public Distrito getDistritobuscar() {
		return distritobuscar;
	}

	public void setDistritobuscar(Distrito distritobuscar) {
		this.distritobuscar = distritobuscar;
	}
	
}