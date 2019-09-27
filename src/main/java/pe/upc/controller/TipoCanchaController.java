package pe.upc.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.ToLongFunction;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import pe.upc.business.KitBusiness;
import pe.upc.business.TipoCanchaBusiness;
import pe.upc.model.entity.Kit;
import pe.upc.model.entity.TipoCancha;
import pe.upc.util.Message;

@Named
@SessionScoped
public class TipoCanchaController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TipoCanchaBusiness tipocanchaBusiness;
	
	@Inject
	private KitBusiness kitBusiness;

	private TipoCancha tipocancha;
	private List<TipoCancha> tipocanchas;
	private TipoCancha tipocanchaSelect;

	private Kit kit;
	private List<Kit> kits;
	
	private String filterName;

	@PostConstruct
	public void init() {
		tipocancha = new TipoCancha();
		tipocanchas = new ArrayList<TipoCancha>();
		
		kit= new Kit();
		kits= new ArrayList<Kit>();
		getAllTipoCanchas();
	}

	public void getAllTipoCanchas() {
		try {
			tipocanchas = tipocanchaBusiness.getAll();
		} catch (Exception e) {
			Message.messageError("Error Carga de Productos :" + e.getMessage());
		}
	}

	public String newTipoCancha() {
		try {
			this.kits= kitBusiness.getAll();
			resetForm();
		} catch (Exception e) {
			// TODO: handle exception
		}
		resetForm();
		return "insert.xhtml";
	}

	public String listTipoCancha() {
		return "list.xhtml";
	}

	public String saveTipoCancha() {
		String view = "";
		try {

			if (Long.valueOf(tipocancha.getCTipoCancha()) !=null  ) {
				tipocancha.setKit(kit);
				tipocanchaBusiness.update(tipocancha);
				Message.messageInfo("Registro actualizado exitosamente");
			} else {
				tipocancha.setKit(kit);
				tipocanchaBusiness.insert(tipocancha);
				Message.messageInfo("Registro guardado exitosamente");

			}
			this.getAllTipoCanchas();
			resetForm();
			view = "list";
		} catch (Exception e) {
			Message.messageError("Error Tipo Cancha :" + e.getStackTrace());
		}

		return view;
	}

	public String editTipoCancha() {
		String view = "";
		try {
			if (this.tipocanchaSelect != null) {
				this.tipocancha = tipocanchaSelect;

				view = "/tipocancha/update";
			} else {
				Message.messageInfo("Debe seleccionar un tipo de cancha");
			}
		} catch (Exception e) {
			Message.messageError("Error Tipo Cancha :" + e.getMessage());
		}

		return view;
	}

	public void searchTipoCanchaByName() {
		try {

			tipocanchas = tipocanchaBusiness.getByName(this.filterName.trim());
			resetForm();
			if (tipocanchas.isEmpty()) {
				Message.messageInfo("No se encontraron tipos de canchas");

			}

		} catch (Exception e) {
			Message.messageError("Error tipo cancha Search :" + e.getMessage());
		}
	}

	public void selectTipoCancha(SelectEvent e) {
		this.tipocanchaSelect = (TipoCancha) e.getObject();
	}

	public void resetForm() {
		this.filterName="";
		this.tipocancha = new TipoCancha();
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
		this.tipocanchas = tipocanchas;
	}

	public TipoCancha getTipoCanchaSelect() {
		return tipocanchaSelect;
	}

	public void setProductSelect(TipoCancha productSelect) {
		this.tipocanchaSelect = productSelect;
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
	public Kit getKit() {
		return kit;
	}

	public void setKit(Kit kit) {
		this.kit = kit;
	}

	public List<Kit> getKits() {
		return kits;
	}

	public void setKits(List<Kit> kits) {
		this.kits= kits;
	}
}
