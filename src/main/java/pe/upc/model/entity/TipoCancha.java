package pe.upc.model.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_cancha")

public class TipoCancha {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int CTipoCancha;
	private String NTipoCancha;
	private float SPrecioHora;
	@ManyToOne
	@JoinColumn(name="CKit", nullable=false)
	private Kit kit;
	
	public int getCTipoCancha() {
		return CTipoCancha;
	}
	public void setCTipoCancha(int cTipoCancha) {
		CTipoCancha = cTipoCancha;
	}
	public String getNTipoCancha() {
		return NTipoCancha;
	}
	public void setNTipoCancha(String nTipoCancha) {
		NTipoCancha = nTipoCancha;
	}
	public float getSPrecioHora() {
		return SPrecioHora;
	}
	public void setSPrecioHora(float sPrecioHora) {
		SPrecioHora = sPrecioHora;
	}
	public Kit getKit() {
		return kit;
	}
	public void setKit(Kit kit) {
		this.kit = kit;
	}
	
	
}
