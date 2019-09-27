package pe.upc.business;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import pe.upc.model.entity.Distrito;
import pe.upc.model.entity.TipoCancha;
import pe.upc.model.repository.DistritoRepository;
import pe.upc.model.repository.TipoCanchaRepository;

@Named

public class DistritoBusiness implements Serializable {
private static final long serialVersionUID = 1L;
	
	@Inject
	private DistritoRepository distritoRepository;

	@Transactional
	public int insert(Distrito distrito) throws Exception {
		return distritoRepository.insert(distrito);
	}

	
	@Transactional
	public int update(Distrito distrito) throws Exception{
		return distritoRepository.update(distrito);
	}
	
	
	public List<Distrito> getAll() throws Exception {
		return distritoRepository.BuscarTodo();
	}
	
	
	public List<Distrito> getByName(String name) throws Exception{
		return distritoRepository.BuscarPorNombre(name);
	}
	public Optional<Distrito> BuscarPorId(int id) throws Exception{
		return distritoRepository.BuscarPorID(id);
	}
	
	
}
