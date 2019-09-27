package pe.upc.model.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.upc.model.entity.Distrito;
import pe.upc.model.entity.Sede;

public class SedeRepository implements Serializable{

	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName="pwPU")
	private EntityManager em;
	public int insert(Sede sede) throws Exception {
		em.persist(sede);
		return sede.getCSede();
	}
	public int update(Sede sede) throws Exception {
		em.merge(sede);
		return sede.getCSede();
	}
	public void delete(Sede sede) throws Exception {
		em.remove(em.contains(sede) ? sede: em.merge(sede));
	}
	public List<Sede> BuscarTodo() throws Exception{
		List<Sede> sedes=new ArrayList<>();
		
		TypedQuery<Sede> query=em.createQuery("FROM Sede s"
				,Sede.class);
		sedes=query.getResultList();
		
		return sedes;
	}
	public Optional<Sede> BuscarPorID(int id) throws Exception{
		Sede sedeencontrada;
		
		TypedQuery<Sede> query=em.createQuery("FROM Sede s WHERE c.CSede=?1"
				,Sede.class);
		query.setParameter(1, id);
		sedeencontrada=query.getSingleResult();
		
		return Optional.of(sedeencontrada);
	}
	public List<Sede> BuscarPorNombre(String name) throws Exception{
		List<Sede> sedes =new ArrayList<>();
		
		TypedQuery<Sede> query=em.createQuery("FROM Sede s WHERE s.NSede LIKE ?1"
				,Sede.class);
		query.setParameter(1, "%"+name+"%");
		sedes=query.getResultList();
		
		return sedes;
	}
	public List<Sede> BuscarPorDistrito(Distrito distrito) throws Exception{
		List<Sede> sedes =new ArrayList<>();
		
		TypedQuery<Sede> query=em.createQuery("Select s "+"FROM Sede s JOIN s.distrito c "+"WHERE c.CDistrito=?1"
				,Sede.class);
		query.setParameter(1, distrito.getCDistrito());
		sedes=query.getResultList();
		
		return sedes;
	}
}