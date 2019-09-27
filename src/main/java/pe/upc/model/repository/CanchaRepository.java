package pe.upc.model.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import pe.upc.model.entity.Cancha;
import pe.upc.model.entity.Sede;
import pe.upc.model.entity.TipoCancha;

public class CanchaRepository implements Serializable{

	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName="pwPU")
	private EntityManager em;
	public int insert(Cancha cancha) throws Exception {
		em.persist(cancha);
		return cancha.getCCancha();
	}
	public int update(Cancha cancha) throws Exception {
		em.merge(cancha);
		return cancha.getCCancha();
	}
	public void delete(Cancha cancha) throws Exception {
		em.remove(em.contains(cancha) ? cancha: em.merge(cancha));
	}
	public List<Cancha> BuscarTodo() throws Exception{
		List<Cancha> canchas=new ArrayList<>();
		
		TypedQuery<Cancha> query=em.createQuery("FROM Cancha c"
				,Cancha.class);
		canchas=query.getResultList();
		
		return canchas;
	}
	public Optional<Cancha> BuscarPorID(int id) throws Exception{
		Cancha canchaencontrada;
		
		TypedQuery<Cancha> query=em.createQuery("FROM Cancha c WHERE c.CCancha=?1"
				,Cancha.class);
		query.setParameter(1, id);
		canchaencontrada=query.getSingleResult();
		
		return Optional.of(canchaencontrada);
	}
	public List<Cancha> BuscarPorNombre(String name) throws Exception{
		List<Cancha> canchas=new ArrayList<>();
		
		TypedQuery<Cancha> query=em.createQuery("FROM Cancha c WHERE c.NCancha LIKE ?1"
				,Cancha.class);
		query.setParameter(1, "%"+name+"%");
		canchas=query.getResultList();
		
		return canchas;
	}
	public List<Cancha> BuscarPorTipoCancha(String ctipocancha) throws Exception{
		List<Cancha> canchas=new ArrayList<>();
		
		TypedQuery<Cancha> query=em.createQuery("FROM Canchas c join TipoCancha tc on c.tipocancha=tc.ctipocancha WHERE tc.?1=c.CTipoCancha"
				,Cancha.class);
		query.setParameter(1, ctipocancha);
		canchas=query.getResultList();
		
		return canchas;
	}
	public List<Cancha> BuscarPorSede(Sede sede) throws Exception{
		List<Cancha> canchas=new ArrayList<>();
		
		TypedQuery<Cancha> query=em.createQuery("Select c "+"FROM Cancha c Join c.sede s "+"WHERE s.CSede=?1"
				,Cancha.class);
		query.setParameter(1, sede.getCSede());
		canchas=query.getResultList();
		
		return canchas;
	}
}
