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
import pe.upc.model.entity.Distrito;
import pe.upc.model.entity.Sede;
import pe.upc.util.Message;

@Named
@SessionScoped
public class DistritoController implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject
	private DistritoBusiness distritoBusiness;

	private Distrito distrito;
	private List<Distrito> distritos;
	private Distrito distritoSelect;

	private String filterName;

	@PostConstruct
	public void init() {
		distrito = new Distrito();
		distritos = new ArrayList<Distrito>();

		getAllDistritos();
	}

	public void getAllDistritos() {
		try {
			distritos= distritoBusiness.getAll();
		} catch (Exception e) {
			Message.messageError("Error Carga de Distritos:" + e.getMessage());
		}
	}

	public String newDistritos() {

			resetForm();
		return "insert.xhtml";
	}

	public String listDistritos() {
		return "list.xhtml";
	}

	public String saveDistrito() {
		String view = "";
		try {

			if (Long.valueOf(distrito.getCDistrito())!= null) {
				distritoBusiness.update(distrito);
				Message.messageInfo("Registro actualizado exitosamente");
			} else {
				distritoBusiness.insert(distrito);
				Message.messageInfo("Registro guardado exitosamente");

			}
			this.getAllDistritos();
			resetForm();
			view = "list";
		} catch (Exception e) {
			Message.messageError("Error Distrito:" + e.getStackTrace());
		}

		return view;
	}

	public String editDistrito() {
		String view = "";
		try {
			if (this.distritoSelect != null) {
				this.distrito= distritoSelect;

				view = "/distrito/update";
			} else {
				Message.messageInfo("Debe seleccionar un distrito");
			}
		} catch (Exception e) {
			Message.messageError("Error Distrito:" + e.getMessage());
		}

		return view;
	}

	public void searchDistritoByName() {
		try {

			distritos= distritoBusiness.getByName(this.filterName.trim());
			resetForm();
			if (distritos.isEmpty()) {
				Message.messageInfo("No se encontraron distritos");

			}

		} catch (Exception e) {
			Message.messageError("Error Distrito Search :" + e.getMessage());
		}
	}

	public void selectDistrito(SelectEvent e) {
		this.distritoSelect = (Distrito) e.getObject();
	}

	public void resetForm() {
		this.filterName = "";
		this.distrito = new Distrito();
	}

	public Distrito getDistrito() {
		return distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito= distrito;
	}

	public List<Distrito> getDistritos() {
		return distritos;
	}

	public void setDistritos(List<Distrito> distritos) {
		this.distritos= distritos;
	}

	public Distrito getDistritoSelect() {
		return distritoSelect;
	}

	public void setDistritoSelect(Distrito distritoSelect) {
		this.distritoSelect = distritoSelect;
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
}