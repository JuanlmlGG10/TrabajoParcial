package pe.upc.model.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import pe.upc.model.entity.Persona;

public class PersonaRepository implements Serializable{

	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName="pwPU")
	private EntityManager em;
	public int insert(Persona persona) throws Exception {
		em.persist(persona);
		return persona.getCPersona();
	}
	public int update(Persona persona) throws Exception {
		em.merge(persona);
		return persona.getCPersona();
	}
	public void delete(Persona persona) throws Exception {
		em.remove(em.contains(persona) ? persona: em.merge(persona));
	
	}
	public List<Persona> BuscarTodo() throws Exception{
		List<Persona> personas=new ArrayList<>();
		
		TypedQuery<Persona> query=em.createQuery("FROM Persona p"
				,Persona.class);
		personas=query.getResultList();
		
		return personas;
	}
	public Optional<Persona> BuscarPorID(int id) throws Exception{
		Persona personaencontrada;
		
		TypedQuery<Persona> query=em.createQuery("FROM Persona p WHERE c.CPersona=?1"
				,Persona.class);
		query.setParameter(1, id);
		personaencontrada=query.getSingleResult();
		
		return Optional.of(personaencontrada);
	}
	public List<Persona> BuscarPorNombre(String name) throws Exception{
		List<Persona> personas =new ArrayList<>();
		
		TypedQuery<Persona> query=em.createQuery("FROM Persona p WHERE p.NPersona LIKE ?1"
				,Persona.class);
		query.setParameter(1, "%"+name+"%");
		personas=query.getResultList();
		
		return personas;
	}
}