package pe.upc.business;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import pe.upc.model.entity.Material;
import pe.upc.model.repository.MaterialRepository;

@Named
public class MaterialBusiness implements Serializable {
private static final long serialVersionUID = 1L;
	
	@Inject
	private MaterialRepository materialRepository;

	@Transactional
	public int insert(Material material) throws Exception {
		return materialRepository.insert(material);
	}

	
	@Transactional
	public int update(Material material) throws Exception{
		return materialRepository.update(material);
	}
	
	
	public List<Material> getAll() throws Exception {
		return materialRepository.BuscarTodo();
	}
	
	
	public List<Material> getByName(String name) throws Exception{
		return materialRepository.BuscarPorNombre(name);
	}
}
