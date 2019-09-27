package pe.upc.model.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import pe.upc.model.entity.Reserva;
import pe.upc.model.entity.TipoCancha;

public class ReservaRepository implements Serializable{

	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName="pwPU")
	private EntityManager em;
	public int insert(Reserva reserva) throws Exception {
		float costo = reserva.getCancha().getTipocancha().getSPrecioHora();
		int horas = reserva.getDHoraFin().getHours() - reserva.getDHoraInicio().getHours();
		reserva.setSMonto(costo * horas);
		em.persist(reserva);
		return reserva.getCReserva();
	}
	public int update(Reserva reserva) throws Exception {
		float costo = reserva.getCancha().getTipocancha().getSPrecioHora();
		int horas = reserva.getDHoraFin().getHours() - reserva.getDHoraInicio().getHours();
		reserva.setSMonto(costo * horas);
		
		em.merge(reserva);
		return reserva.getCReserva();
	}
	public void delete(Reserva reserva) throws Exception {
		em.remove(em.contains(reserva) ? reserva: em.merge(reserva));

	}
	public List<Reserva> BuscarTodo() throws Exception{
		List<Reserva> reservas=new ArrayList<>();
		
		TypedQuery<Reserva> query=em.createQuery("FROM Reserva rr"
				,Reserva.class);
		reservas=query.getResultList();
		
		return reservas;
	}
	
	public Optional<Reserva> BuscarPorID(int id) throws Exception{
		Reserva reservaencontrada;
		
		TypedQuery<Reserva> query=em.createQuery("FROM Reserva rr WHERE rr.CReserva=?1"
				,Reserva.class);
		query.setParameter(1, id);
		reservaencontrada=query.getSingleResult();
		
		return Optional.of(reservaencontrada);
	}
	public List<Reserva> BuscarPorNombre(String name) throws Exception{
		List<Reserva> reservas =new ArrayList<>();
		
		TypedQuery<Reserva> query=em.createQuery("FROM Reserva rr WHERE rr.NReserva LIKE ?1"
				,Reserva.class);
		query.setParameter(1, "%"+name+"%");
		reservas=query.getResultList();
		
		return reservas;
	}
	public Optional<Reserva> BuscarPorCancha(int cancha) throws Exception{
		Reserva reservaencontrada;
		
		TypedQuery<Reserva> query=em.createQuery("FROM Reserva rr WHERE rr.CCancha=?1"
				,Reserva.class);
		query.setParameter(1, cancha);
		reservaencontrada=query.getSingleResult();
		
		return Optional.of(reservaencontrada);
	}
	public List<Reserva> BuscarPorFechaDeRegisto(Date fechainicio, Date fechafin) throws Exception{
		List<Reserva> reservas =new ArrayList<>();
		
		TypedQuery<Reserva> query=em.createQuery("FROM Reserva rr WHERE rr.DFecha between ?1 and ?2"
				,Reserva.class);
		query.setParameter(1, fechainicio);
		query.setParameter(2,fechafin);
		reservas=query.getResultList();
		
		return reservas;
	}
	public List<Reserva> BuscarPorCancha1(int tipocancha) throws Exception{
		List<Reserva> reservas =new ArrayList<>();
		
		TypedQuery<Reserva> query=em.createQuery("select r "+ "FROM Reserva r JOIN r.cancha ca  "+ "where ca.CCancha=1"
				,Reserva.class);
													 
		//query.setParameter(1, tipocancha);
		reservas=query.getResultList();
		
		return reservas;
	}
	public List<Reserva> BuscarPorTipoCancha(TipoCancha tipocancha) throws Exception{
		List<Reserva> reservas =new ArrayList<>();
		
		TypedQuery<Reserva> query=em.createQuery("select r "+ "FROM Reserva r JOIN r.cancha ca JOIN ca.tipocancha tp "+ "where tp.CTipoCancha=?1"
				,Reserva.class);
													 
		query.setParameter(1, tipocancha.getCTipoCancha());
		reservas=query.getResultList();
		
		return reservas;
	}
}