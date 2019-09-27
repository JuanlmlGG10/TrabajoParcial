package pe.upc.business;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import pe.upc.model.entity.Distrito;
import pe.upc.model.entity.Persona;
import pe.upc.model.entity.Sede;
import pe.upc.model.entity.TipoCancha;
import pe.upc.model.repository.SedeRepository;

@Named

public class SedeBusiness implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Inject
	private SedeRepository sedeRepository;

	@Transactional
	public int insert(Sede sede) throws Exception {
		return sedeRepository.insert(sede);
	}

	
	@Transactional
	public int update(Sede sede) throws Exception{
		return sedeRepository.update(sede);
	}
	
	@Transactional
	public void delete(Sede sede) throws Exception{
		sedeRepository.delete(sede);
	}
	
	public List<Sede> getAll() throws Exception {
		return sedeRepository.BuscarTodo();
	}
	
	
	public List<Sede> getByName(String name) throws Exception{
		return sedeRepository.BuscarPorNombre(name);
	}
	public Optional<Sede> BuscarPorId(int id) throws Exception{
		return sedeRepository.BuscarPorID(id);
	}
	public List<Sede> getByDistrito(Distrito distrito) throws Exception{
		return sedeRepository.BuscarPorDistrito(distrito);
	}
}
