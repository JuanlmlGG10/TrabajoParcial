package pe.upc.business;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import pe.upc.model.entity.TipoCancha;
import pe.upc.model.repository.TipoCanchaRepository;

@Named

public class TipoCanchaBusiness implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Inject
	private TipoCanchaRepository tipocanchaRepository;

	@Transactional
	public int insert(TipoCancha tipocancha) throws Exception {
		return tipocanchaRepository.insert(tipocancha);
	}

	
	@Transactional
	public int update(TipoCancha tipocancha) throws Exception{
		return tipocanchaRepository.update(tipocancha);
	}
	
	
	public List<TipoCancha> getAll() throws Exception {
		return tipocanchaRepository.BuscarTodo();
	}
	
	
	public List<TipoCancha> getByName(String name) throws Exception{
		return tipocanchaRepository.BuscarPorNombre(name);
	}
	public Optional<TipoCancha> BuscarPorId(int id) throws Exception{
		return tipocanchaRepository.BuscarPorID(id);
	}
	
	
}
