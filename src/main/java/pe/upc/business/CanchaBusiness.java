package pe.upc.business;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import pe.upc.model.entity.Cancha;
import pe.upc.model.entity.Sede;
import pe.upc.model.repository.CanchaRepository;

@Named
public class CanchaBusiness implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CanchaRepository canchaRepository;

	@Transactional
	public int insert(Cancha cancha) throws Exception {
		return canchaRepository.insert(cancha);
	}

	
	@Transactional
	public int update(Cancha cancha) throws Exception{
		return canchaRepository.update(cancha);
	}
	
	@Transactional
	public void delete(Cancha cancha) throws Exception{
		canchaRepository.delete(cancha);
	}
	
	public List<Cancha> getAll() throws Exception {
		return canchaRepository.BuscarTodo();
	}
	
	
	public List<Cancha> getByName(String name) throws Exception{
		return canchaRepository.BuscarPorNombre(name);
	}
	public Optional<Cancha> BuscarPorId(int id) throws Exception{
		return canchaRepository.BuscarPorID(id);
	}
	public List<Cancha> BuscarPorTipoCancha(String tipocancha) throws Exception{
		return canchaRepository.BuscarPorTipoCancha(tipocancha);
	}
	public List<Cancha> BuscarPorSede(Sede sede) throws Exception{
		return canchaRepository.BuscarPorSede(sede);
	}
	

}
