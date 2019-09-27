package pe.upc.model.entity;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.primefaces.PrimeFaces;

import pe.upc.util.Message;
@Entity
@Table(name = "reserva")
public class Reserva {
		@Id
		@GeneratedValue(strategy= GenerationType.IDENTITY)
		private int CReserva;
		private double SMonto;
		private String TComentario;
		private Date DFecha;
		private Date DHoraInicio;
		private Date DHoraFin;
		private Date DFechaRegistro;	
		private String NTipopago;
		
		@ManyToOne
		@JoinColumn(name="CPersona", nullable=false)
		private Persona persona;
		@ManyToOne
		@JoinColumn(name="CCancha", nullable=false)
		private Cancha cancha;
		
		public Cancha getCancha() {
			return cancha;
		}
		public void setCancha(Cancha cancha) {
			this.cancha = cancha;
		}
		public Persona getPersona() {
			return persona;
		}
		public void setPersona(Persona persona) {
			this.persona = persona;
		}
		public int getCReserva() {
			return CReserva;
		}
		public void setCReserva(int cReserva) {
			CReserva = cReserva;
		}
		public double getSMonto() {
			return SMonto;
		}
		public void setSMonto(double sMonto) {
			SMonto = sMonto;
		}
		public String getTComentario() {
			return TComentario;
		}
		public void setTComentario(String tComentario) {
			TComentario = tComentario;
		}
		public Date getDFecha() {
			return DFecha;
		}
		public void setDFecha(Date dFecha) {
			DFecha = dFecha;
		}
		public Date getDHoraInicio() {
			return DHoraInicio;
		}
		public void setDHoraInicio(Date dHoraInicio) {
			DHoraInicio = dHoraInicio;
		}
		public Date getDHoraFin() {
			return DHoraFin;
		}
		public void setDHoraFin(Date dHoraFin) {
			DHoraFin = dHoraFin;
		}
		public Date getDFechaRegistro() {
		
			return DFechaRegistro;
		}
		public void setDFechaRegistro(Date dFechaRegistro) {
			LocalDateTime now = LocalDateTime.now();
			Date afecharegistro= Date.from( now.atZone( ZoneId.systemDefault()).toInstant());
			DFechaRegistro = afecharegistro;
		}
		public String getNTipopago() {
			return NTipopago;
		}
		public void setNTipopago(String nTipopago) {
			NTipopago = nTipopago;
		}
		public void showMessage() {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "What we do in life", "Echoes in eternity.");
	         
	        PrimeFaces.current().dialog().showMessageDynamic(message);
	    }
		
		
}
