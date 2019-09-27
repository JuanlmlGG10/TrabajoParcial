package pe.upc.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "persona")
public class Persona {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int CPersona;
	private String NPersona;
	private String NApellido;
	private String NDni;	
	private String NCelular;
	private String TEmail;
	private String NUsuario;
	private String NContraseña;
	private String tipousuario;
	
	@ManyToOne
	@JoinColumn(name="CDistrito",nullable=false)
	private Distrito distrito;
	

	public int getCPersona() {
		return CPersona;
	}
	public void setCPersona(int cPersona) {
		CPersona = cPersona;
	}
	public String getNPersona() {
		return NPersona;
	}
	public void setNPersona(String nPersona) {
		NPersona = nPersona;
	}
	public String getNApellido() {
		return NApellido;
	}
	public void setNApellido(String nApellido) {
		NApellido = nApellido;
	}

	public String getNDni() {
		return NDni;
	}
	public void setNDni(String nDni) {
		NDni = nDni;
	}
	public Distrito getDistrito() {
		return distrito;
	}
	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}
	public String getNCelular() {
		return NCelular;
	}
	public void setNCelular(String nCelular) {
		NCelular = nCelular;
	}
	public String getTEmail() {
		return TEmail;
	}
	public void setTEmail(String tEmail) {
		TEmail = tEmail;
	}
	public String getNUsuario() {
		return NUsuario;
	}
	public void setNUsuario(String nUsuario) {
		NUsuario = nUsuario;
	}
	public String getNContraseña() {
		return NContraseña;
	}
	public void setNContraseña(String nContraseña) {
		NContraseña = nContraseña;
	}
	public String getTipousuario() {
		return tipousuario;
	}
	public void setTipousuario(String tipousuario) {
		this.tipousuario = tipousuario;
	}
	
	
	
}
