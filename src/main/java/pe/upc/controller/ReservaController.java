package pe.upc.controller;
import java.io.Serializable;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import pe.upc.business.CanchaBusiness;
import pe.upc.business.PersonaBusiness;
import pe.upc.business.ReservaBusiness;
import pe.upc.model.entity.Cancha;
import pe.upc.model.entity.Persona;
import pe.upc.model.entity.Reserva;
import pe.upc.model.entity.TipoCancha;
import pe.upc.util.Message;

@Named
@SessionScoped
public class ReservaController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ReservaBusiness reservaBusiness;

	@Inject
	private CanchaBusiness canchaBusiness;
	
	@Inject
	private PersonaBusiness personaBusiness;
	
	
	private Reserva reserva;
	private List<Reserva> reservas;
	private Reserva reservaSelect;

	private Cancha cancha;
	private List<Cancha> canchas;
	
	private Persona persona;
	private Persona persona1;
	private List<Persona> personas;

	private String filterName;
	private Date inicio;
	private Date fin;
	private TipoCancha tipocancha;

	@PostConstruct
	public void init() {
		reserva = new Reserva();
		reservas = new ArrayList<Reserva>();
		cancha= new Cancha();
		canchas= new ArrayList<Cancha>();
		persona= new Persona();
		persona1=new Persona();
		personas= new ArrayList<Persona>();

		getAllReservas();
	}

	public void getAllReservas() {
		try {
			reservas = reservaBusiness.getAll();
		} catch (Exception e) {
			Message.messageError("Error Carga de Reservas :" + e.getMessage());
		}
	}

	public String newReserva() {

		try {
			this.canchas = canchaBusiness.getAll();
			this.personas= personaBusiness.getAll();
			resetForm();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return "insert.xhtml";
	}

	public String listReserva() {
		return "list.xhtml";
	}

	public String saveReserva() {
		String view = "";
		try { // oyee lee los mnsaje s:v
			
			
			if (Long.valueOf(reserva.getCReserva() )!= null) { 
				reserva.setCancha(cancha);
				reservaBusiness.update(reserva);
				Message.messageInfo("Registro actualizado exitosamente");
			} else {
				Message.messageError("Insert :" + persona.getCPersona());
				reserva.setPersona(persona1);
				reserva.setCancha(cancha);
				reservaBusiness.insert(reserva);
				Message.messageInfo("Registro guardado exitosamente");

			}
			this.getAllReservas();
			resetForm();
			view = "list";
		} catch (Exception e) {
			Message.messageError("Error Reserva :" + e.getStackTrace());
		}

		return view;
	}

	public String editReserva() {
		String view = "";
		try {
			if (this.reservaSelect != null) {
				this.reserva= reservaSelect;

				view = "/reserva/update";
			} else {
				Message.messageInfo("Debe seleccionar una reserva");
			}
		} catch (Exception e) {
			Message.messageError("Error Reserva:" + e.getMessage());
		}

		return view;
	}
	public String deleteReserva() {
		String view = "";
		try {
			if (this.reservaSelect != null) {
				this.reserva = reservaSelect;

				reservaBusiness.delete(reserva);
			} else {
				Message.messageInfo("Debe seleccionar una reserva");
			}
		} catch (Exception e) {
			Message.messageError("Error reserva:" + e.getMessage());
		}
		this.getAllReservas();
		resetForm();
		view = "list";
		return view;
	}

	public void searchReservaByName() {
		try {

			reservas = reservaBusiness.getByName(this.filterName.trim());
			resetForm();
			if (reservas.isEmpty()) {
				Message.messageInfo("No se encontraron reservas");

			}

		} catch (Exception e) {
			Message.messageError("Error Reservas Search :" + e.getMessage());
		}
	}
	public void searchReservaByDate() {
		try {

			reservas = reservaBusiness.BuscarPorFecha(inicio,fin);
			resetForm();
			if (reservas.isEmpty()) {
				Message.messageInfo("No se encontraron reservas");

			}

		} catch (Exception e) {
			Message.messageError("Error Reservas Search :" + e.getMessage());
		}
	}
	public void searchReservaByTipoCancha() {
		try {

			reservas = reservaBusiness.BuscarPorTipoCancha(tipocancha);
			resetForm();
			if (reservas.isEmpty()) {
				Message.messageInfo("No se encontraron reservas");

			}

		} catch (Exception e) {
			Message.messageError("Error Reservas Search :" + e.getMessage());
		}
	}
	
	
	public void selectReserva(SelectEvent e) {
		this.reservaSelect = (Reserva) e.getObject();
	}

	public void resetForm() {
		this.filterName = "";
		LocalDateTime now = LocalDateTime.now();
		Date afecharegistro= Date.from( now.atZone( ZoneId.systemDefault()).toInstant());
		this.inicio= afecharegistro;
		this.fin=afecharegistro;
		this.tipocancha=null;
		this.reserva= new Reserva();
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public List<Reserva> getReservas () {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas= reservas;
	}

	public Reserva getReservaSelect() {
		return reservaSelect;
	}

	public void setReservaSelect(Reserva reservaSelect) {
		this.reservaSelect = reservaSelect;
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
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas= personas;
	}

	public Persona getPersona1() {
		return persona1;
	}

	public void setPersona1(Persona persona1) {
		this.persona1 = persona1;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	public TipoCancha getTipocancha() {
		return tipocancha;
	}

	public void setTipocancha(TipoCancha tipocancha) {
		this.tipocancha = tipocancha;
	}
	

}

