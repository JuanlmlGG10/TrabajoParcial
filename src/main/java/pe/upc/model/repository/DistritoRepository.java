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

public class DistritoRepository implements Serializable{

	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName="pwPU")
	private EntityManager em;
	
	public int insert(Distrito distrito) throws Exception {
		em.persist(distrito);
		return distrito.getCDistrito();
	}
	public int update(Distrito distrito) throws Exception {
		em.merge(distrito);
		return distrito.getCDistrito();
	}
	public void delete(Distrito distrito) throws Exception {
		em.remove(distrito);
	}
	public List<Distrito> BuscarTodo() throws Exception{
		List<Distrito> distritos=new ArrayList<>();
		
		TypedQuery<Distrito> query=em.createQuery("FROM Distrito d"
				,Distrito.class);
		distritos=query.getResultList();
		
		return distritos;
	}
	public Optional<Distrito> BuscarPorID(int id) throws Exception{
		Distrito distritoencontrado;
		
		TypedQuery<Distrito> query=em.createQuery("FROM Distrito d WHERE d.CDistrito=?1"
				,Distrito.class);
		query.setParameter(1, id);
		distritoencontrado=query.getSingleResult();
		
		return Optional.of(distritoencontrado);
	}
	public List<Distrito> BuscarPorNombre(String name) throws Exception{
		List<Distrito> materiales=new ArrayList<>();
		
		TypedQuery<Distrito> query=em.createQuery("FROM Distrito d WHERE d.NDistrito LIKE ?1"
				,Distrito.class);
		query.setParameter(1, "%"+name+"%");
		materiales=query.getResultList();
		
		return materiales;
	}
}