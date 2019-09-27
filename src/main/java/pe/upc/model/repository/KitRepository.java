package pe.upc.model.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import pe.upc.model.entity.Kit;

public class KitRepository implements Serializable{

	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName="pwPU")
	private EntityManager em;
	public int insert(Kit kit) throws Exception {
		em.persist(kit);
		return kit.getCKit();
	}
	public int update(Kit kit) throws Exception {
		em.merge(kit);
		return kit.getCKit();
	}
	public void delete(Kit kit) throws Exception {
		em.remove(kit);
	}
	public List<Kit> BuscarTodo() throws Exception{
		List<Kit> kits=new ArrayList<>();
		
		TypedQuery<Kit> query=em.createQuery("FROM Kit k"
				,Kit.class);
		kits=query.getResultList();
		
		return kits;
	}
	public Optional<Kit> BuscarPorID(int id) throws Exception{
		Kit kitencontrado;
		
		TypedQuery<Kit> query=em.createQuery("FROM Kit k WHERE c.CKit=?1"
				,Kit.class);
		query.setParameter(1, id);
		kitencontrado=query.getSingleResult();
		
		return Optional.of(kitencontrado);
	}
	public List<Kit> BuscarPorNombre(String name) throws Exception{
		List<Kit> kits=new ArrayList<>();
		
		TypedQuery<Kit> query=em.createQuery("FROM Kit k WHERE c.NKit LIKE ?1"
				,Kit.class);
		query.setParameter(1, "%"+name+"%");
		kits=query.getResultList();
		
		return kits;
	}
}
