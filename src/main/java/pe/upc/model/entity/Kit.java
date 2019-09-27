package pe.upc.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "kit")
public class Kit {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int CKit;
	private String NKit;
	private String TDescripcion;
	@ManyToMany(fetch=FetchType.LAZY,
			cascade= {
					CascadeType.PERSIST,
					CascadeType.MERGE
					
			},
			mappedBy="kits")
	private Set<Material> materiales=new HashSet<>();
	
	public int getCKit() {
		return CKit;
	}
	public void setCKit(int cKit) {
		CKit = cKit;
	}
	public String getNKit() {
		return NKit;
	}
	public void setNKit(String nKit) {
		NKit = nKit;
	}
	public String getTDescripcion() {
		return TDescripcion;
	}
	public void setTDescripcion(String tDescripcion) {
		TDescripcion = tDescripcion;
	}
	
	
}
