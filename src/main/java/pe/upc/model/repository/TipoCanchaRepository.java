package pe.upc.model.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import pe.upc.model.entity.TipoCancha;

public class TipoCanchaRepository implements Serializable{

	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName="pwPU")
	private EntityManager em;
	public int insert(TipoCancha tipocancha) throws Exception {
		em.persist(tipocancha);
		return tipocancha.getCTipoCancha();
	}
	public int update(TipoCancha tipocancha) throws Exception {
		em.merge(tipocancha);
		return tipocancha.getCTipoCancha();
	}
	public void delete(TipoCancha tipocancha) throws Exception {
		em.remove(tipocancha);
	}
	public List<TipoCancha> BuscarTodo() throws Exception{
		List<TipoCancha> tipocanchas=new ArrayList<>();
		
		TypedQuery<TipoCancha> query=em.createQuery("FROM TipoCancha tp"
				,TipoCancha.class);
		tipocanchas=query.getResultList();
		
		return tipocanchas;
	}
	public Optional<TipoCancha> BuscarPorID(int id) throws Exception{
		TipoCancha tipocanchaencontrada;
		
		TypedQuery<TipoCancha> query=em.createQuery("FROM TipoCancha tp WHERE c.CTipoCancha=?1"
				,TipoCancha.class);
		query.setParameter(1, id);
		tipocanchaencontrada=query.getSingleResult();
		
		return Optional.of(tipocanchaencontrada);
	}
	public List<TipoCancha> BuscarPorNombre(String name) throws Exception{
		List<TipoCancha> tipocanchas =new ArrayList<>();
		
		TypedQuery<TipoCancha> query=em.createQuery("FROM TipoCancha tp WHERE tp.NTipoCanchaLIKE ?1"
				,TipoCancha.class);
		query.setParameter(1, "%"+name+"%");
		tipocanchas=query.getResultList();
		
		return tipocanchas;
	}
}