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
@Table(name = "distrito")
public class Distrito {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int CDistrito;
	private String NDistrito;
	public int getCDistrito() {
		return CDistrito;
	}
	public void setCDistrito(int cDistrito) {
		CDistrito = cDistrito;
	}
	public String getNDistrito() {
		return NDistrito;
	}
	public void setNDistrito(String nDistrito) {
		NDistrito = nDistrito;
	}
	

	
	
	
}
