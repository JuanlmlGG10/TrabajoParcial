package pe.upc.model.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import pe.upc.model.entity.Material;

public class MaterialRepository implements Serializable{

	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName="pwPU")
	private EntityManager em;
	public int insert(Material material) throws Exception {
		em.persist(material);
		return material.getCMaterial();
	}
	public int update(Material material) throws Exception {
		em.merge(material);
		return material.getCMaterial();
	}
	public void delete(Material material) throws Exception {
		em.remove(material);
	}
	public List<Material> BuscarTodo() throws Exception{
		List<Material> materiales=new ArrayList<>();
		
		TypedQuery<Material> query=em.createQuery("FROM Material m"
				,Material.class);
		materiales=query.getResultList();
		
		return materiales;
	}
	public Optional<Material> BuscarPorID(int id) throws Exception{
		Material materialencontrado;
		
		TypedQuery<Material> query=em.createQuery("FROM Material m WHERE c.CMaterial=?1"
				,Material.class);
		query.setParameter(1, id);
		materialencontrado=query.getSingleResult();
		
		return Optional.of(materialencontrado);
	}
	public List<Material> BuscarPorNombre(String name) throws Exception{
		List<Material> materiales=new ArrayList<>();
		
		TypedQuery<Material> query=em.createQuery("FROM Material m WHERE m.NMaterial LIKE ?1"
				,Material.class);
		query.setParameter(1, "%"+name+"%");
		materiales=query.getResultList();
		
		return materiales;
	}
}