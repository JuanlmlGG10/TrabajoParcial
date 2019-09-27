package pe.upc.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "sede")
public class Sede {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
private int CSede;
private String NSede;
private String TDireccion;
@ManyToOne
@JoinColumn(name="CDistrito",nullable=false)
private Distrito distrito;


public int getCSede() {
	return CSede;
}
public void setCSede(int cSede) {
	CSede = cSede;
}
public String getNSede() {
	return NSede;
}
public void setNSede(String nSede) {
	NSede = nSede;
}
public String getTDireccion() {
	return TDireccion;
}
public void setTDireccion(String tDireccion) {
	TDireccion = tDireccion;
}
public Distrito getDistrito() {
	return distrito;
}
public void setDistrito(Distrito distrito) {
	this.distrito = distrito;
}

}
