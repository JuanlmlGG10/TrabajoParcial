package pe.upc.model.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cancha")
public class Cancha {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int CCancha;
	private String NCancha;
	@ManyToOne
	@JoinColumn(name="CSede", nullable=false)
	private Sede sede;
	@ManyToOne
	@JoinColumn(name="CTipoCancha", nullable=false)
	private TipoCancha tipocancha;
	
	public int getCCancha() {
		return CCancha;
	}
	public void setCCancha(int cCancha) {
		CCancha = cCancha;
	}
	public String getNCancha() {
		return NCancha;
	}
	public void setNCancha(String nCancha) {
		NCancha = nCancha;
	}
	public Sede getSede() {
		return sede;
	}
	public void setSede(Sede sede) {
		this.sede = sede;
	}
	public TipoCancha getTipocancha() {
		return tipocancha;
	}
	public void setTipocancha(TipoCancha tipocancha2) {
		this.tipocancha = tipocancha2;
	}
	
	
	
}
