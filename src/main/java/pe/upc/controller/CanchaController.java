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

import pe.upc.business.CanchaBusiness;
import pe.upc.business.SedeBusiness;
import pe.upc.business.TipoCanchaBusiness;
import pe.upc.model.entity.Cancha;
import pe.upc.model.entity.Sede;
import pe.upc.model.entity.TipoCancha;
import pe.upc.util.Message;

@Named
@SessionScoped
public class CanchaController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CanchaBusiness canchaBusiness;

	@Inject
	private TipoCanchaBusiness tipocanchaBusiness;
	
	@Inject
	private SedeBusiness sedeBusiness;
	
	private Cancha cancha;
	private List<Cancha> canchas;
	private Cancha canchaSelect;

	private TipoCancha tipocancha;
	private List<TipoCancha> tipocanchas;
	
	private Sede sede;
	private List<Sede> sedes;

	private String filterName;
	private Sede sedebuscar;

	@PostConstruct
	public void init() {
		cancha = new Cancha();
		canchas= new ArrayList<Cancha>();

		tipocancha= new TipoCancha();
		tipocanchas= new ArrayList<TipoCancha>();
		
		sede= new Sede();
		sedes= new ArrayList<Sede>();

		getAllCanchas();
	}

	public void getAllCanchas() {
		try {
			canchas = canchaBusiness.getAll();
		} catch (Exception e) {
			Message.messageError("Error Carga de Canchas :" + e.getMessage());
		}
	}

	public String newCancha() {

		try {
			this.tipocanchas = tipocanchaBusiness.getAll();
			this.sedes= sedeBusiness.getAll();
			resetForm();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return "insert.xhtml";
	}

	public String listCancha() {
		return "list.xhtml";
	}

	public String saveCancha() {
		String view = "";
		try {

			if (Long.valueOf(cancha.getCCancha() )!= null) {
				cancha.setTipocancha(tipocancha);
				cancha.setSede(sede);
				canchaBusiness.update(cancha);
				Message.messageInfo("Registro actualizado exitosamente");
			} else {
				cancha.setTipocancha(tipocancha);
				cancha.setSede(sede);
				canchaBusiness.insert(cancha);			
				Message.messageInfo("Registro guardado exitosamente");

			}
			this.getAllCanchas();
			resetForm();
			TimeUnit.SECONDS.sleep(3);
			view = "list";
		} catch (Exception e) {
			Message.messageError("Error Cancha :" + e.getStackTrace());
		}

		return view;
	}

	public String editCancha() {
		String view = "";
		try {
			if (this.canchaSelect != null) {
				this.cancha = canchaSelect;

				view = "/cancha/update.xhtml";
			} else {
				Message.messageInfo("Debe seleccionar una cancha");
			}
		} catch (Exception e) {
			Message.messageError("Error Cancha:" + e.getMessage());
		}

		return view;
	}
	public String deleteCancha() {
		String view = "";
		try {
			if (this.canchaSelect != null) {
				this.cancha = canchaSelect;

				canchaBusiness.delete(cancha);
			} else {
				Message.messageInfo("Debe seleccionar una cancha");
			}
		} catch (Exception e) {
			Message.messageError("Error cancha :" + e.getMessage());
		}
		this.getAllCanchas();
		resetForm();
		view = "list";
		return view;
	}
	public void searchCanchaByName() {
		try {

			canchas = canchaBusiness.getByName(this.filterName.trim());
			resetForm();
			if (canchas.isEmpty()) {
				Message.messageInfo("No se encontraron canchas");

			}

		} catch (Exception e) {
			Message.messageError("Error Cancha Search :" + e.getMessage());
		}
	}
	public void searchCanchaBySede() {
		try {

			canchas = canchaBusiness.BuscarPorSede(sedebuscar);
			resetForm();
			if (canchas.isEmpty()) {
				Message.messageInfo("No se encontraron canchas");

			}

		} catch (Exception e) {
			Message.messageError("Error Cancha Search :" + e.getMessage());
		}
	}


	public void selectCancha(SelectEvent e) {
		this.canchaSelect = (Cancha) e.getObject();
	}

	public void resetForm() {
		this.filterName = "";
		this.cancha= new Cancha();
	}

	public Cancha getCancha() {
		return cancha;
	}

	public void setCancha(Cancha cancha) {
		this.cancha = cancha;
	}

	public List<Cancha> getCanchas() {
		return canchas;
	}

	public void setCanchas(List<Cancha> canchas) {
		this.canchas= canchas;
	}

	public Cancha getCanchaSelect() {
		return canchaSelect;
	}

	public void setCanchaSelect(Cancha canchaSelect) {
		this.canchaSelect = canchaSelect;
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

	public TipoCancha getTipoCancha() {
		return tipocancha;
	}

	public void setTipoCancha(TipoCancha tipocancha) {
		this.tipocancha = tipocancha;
	}

	public List<TipoCancha> getTipoCanchas() {
		return tipocanchas;
	}

	public void setTipoCanchas(List<TipoCancha> tipocanchas) {
		this.tipocanchas= tipocanchas;
	}
	public Sede getSede() {
		return sede;
	}

	public TipoCancha getTipocancha() {
		return tipocancha;
	}

	public void setTipocancha(TipoCancha tipocancha) {
		this.tipocancha = tipocancha;
	}

	public List<TipoCancha> getTipocanchas() {
		return tipocanchas;
	}

	public void setTipocanchas(List<TipoCancha> tipocanchas) {
		this.tipocanchas = tipocanchas;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

	public List<Sede> getSedes() {
		return sedes;
	}

	public void setSedes(List<Sede> sedes) {
		this.sedes= sedes;
	}

	public Sede getSedebuscar() {
		return sedebuscar;
	}

	public void setSedebuscar(Sede sedebuscar) {
		this.sedebuscar = sedebuscar;
	}
	

}

