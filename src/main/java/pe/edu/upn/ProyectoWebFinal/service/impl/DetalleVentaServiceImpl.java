package pe.edu.upn.ProyectoWebFinal.service.impl;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upn.ProyectoWebFinal.model.entity.DetalleVenta;
import pe.edu.upn.ProyectoWebFinal.model.repository.DetalleVentaRepository;
import pe.edu.upn.ProyectoWebFinal.service.DetalleVentaService;

@Service
public class DetalleVentaServiceImpl implements DetalleVentaService{

	@Autowired
	private DetalleVentaRepository detalleVentaRepository;
	
	@Transactional(readOnly=true)
	@Override
	public List<DetalleVenta> findAll() throws Exception {
		// TODO Auto-generated method stub
		return detalleVentaRepository.findAll();
	}

	@Transactional(readOnly=true)
	@Override
	public Optional<DetalleVenta> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return detalleVentaRepository.findById(id);
	}

	@Transactional
	@Override
	public DetalleVenta save(DetalleVenta entity) throws Exception {
		// TODO Auto-generated method stub
		return detalleVentaRepository.save(entity);
	}

	@Transactional
	@Override
	public DetalleVenta update(DetalleVenta entity) throws Exception {
		// TODO Auto-generated method stub
		return detalleVentaRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		detalleVentaRepository.deleteById(id);
		
	}

	@Transactional
	@Override
	public void deleteAll() throws Exception {
		detalleVentaRepository.deleteAll();
		
	}

}
