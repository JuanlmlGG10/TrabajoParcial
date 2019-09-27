package pe.upc.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "material")
public class Material {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int CMaterial;
	private String NMaterial;
	@ManyToMany(fetch=FetchType.LAZY,
			cascade= {
					CascadeType.PERSIST,
					CascadeType.MERGE
					
			})
	@JoinTable(name="material_kit",
	joinColumns= {@JoinColumn(name="material_cmaterial")},
	inverseJoinColumns = {@JoinColumn(name="kit_ckit")})
	private Set<Kit> kits=new HashSet<>();

	public int getCMaterial() {
		return CMaterial;
	}
	public void setCMaterial(int cMaterial) {
		CMaterial = cMaterial;
	}
	public String getNMaterial() {
		return NMaterial;
	}
	public void setNMaterial(String nMaterial) {
		NMaterial = nMaterial;
	}
	public Set<Kit> getKits() {
		return kits;
	}
	public void setKits(Set<Kit> kits) {
		this.kits = kits;
	}
	
		
}
