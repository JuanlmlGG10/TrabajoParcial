package pe.upc.business;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import pe.upc.model.entity.Persona;
import pe.upc.model.repository.PersonaRepository;

@Named
public class PersonaBusiness implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private PersonaRepository personaRepository;

	@Transactional
	public int insert(Persona persona) throws Exception {
		return personaRepository.insert(persona);
	}

	
	@Transactional
	public int update(Persona persona) throws Exception{
		return personaRepository.update(persona);
	}

	@Transactional
	public void delete(Persona persona) throws Exception{
		personaRepository.delete(persona);
	}
	
	public List<Persona> getAll() throws Exception {
		return personaRepository.BuscarTodo();
	}
	
	
	public List<Persona> getByName(String name) throws Exception{
		return personaRepository.BuscarPorNombre(name);
	}
	public Optional<Persona> BuscarPorId(int id) throws Exception{
		return personaRepository.BuscarPorID(id);
	}
}
