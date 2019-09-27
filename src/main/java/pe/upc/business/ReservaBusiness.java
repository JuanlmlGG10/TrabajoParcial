package pe.upc.business;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import pe.upc.model.entity.Reserva;
import pe.upc.model.entity.Sede;
import pe.upc.model.entity.TipoCancha;
import pe.upc.model.repository.ReservaRepository;

@Named
public class ReservaBusiness implements Serializable{
	private static final long serialVersionUID = 1L;
	@Inject
	private ReservaRepository reservaRepository;

	@Transactional
	public int insert(Reserva reserva) throws Exception {
		return reservaRepository.insert(reserva);
	}

	
	@Transactional
	public int update(Reserva reserva) throws Exception{
		return reservaRepository.update(reserva);
	}	
	@Transactional
	public void delete(Reserva reserva) throws Exception{
		 reservaRepository.delete(reserva);
	}
	
	
	public List<Reserva> getAll() throws Exception {
		return reservaRepository.BuscarTodo();
	}
	
	
	public List<Reserva> getByName(String name) throws Exception{
		return reservaRepository.BuscarPorNombre(name);
	}
	public Optional<Reserva> BuscarPorId(int id) throws Exception{
		return reservaRepository.BuscarPorID(id);
	}
	public Optional<Reserva> BuscarPorCancha(int cancha) throws Exception{
		return reservaRepository.BuscarPorCancha(cancha);
	}
	public List<Reserva> BuscarPorFecha(Date fechainicio,Date fechafin) throws Exception{
		return reservaRepository.BuscarPorFechaDeRegisto(fechainicio,fechafin);
	}
	public List<Reserva> BuscarPorTipoCancha(TipoCancha tipocancha) throws Exception{
		return reservaRepository.BuscarPorTipoCancha(tipocancha);
	}
	
}
