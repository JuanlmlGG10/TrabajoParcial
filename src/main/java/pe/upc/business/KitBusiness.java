package pe.upc.business;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import pe.upc.model.entity.Kit;
import pe.upc.model.repository.KitRepository;

@Named
public class KitBusiness implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Inject
	private KitRepository kitRepository;

	@Transactional
	public int insert(Kit kit) throws Exception {
		return kitRepository.insert(kit);
	}

	
	@Transactional
	public int update(Kit kit) throws Exception{
		return kitRepository.update(kit);
	}
	
	
	public List<Kit> getAll() throws Exception {
		return kitRepository.BuscarTodo();
	}
	
	
	public List<Kit> getByName(String name) throws Exception{
		return kitRepository.BuscarPorNombre(name);
	}
}
