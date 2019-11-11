package pe.edu.upn.ProyectoWebFinal.init;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pe.edu.upn.ProyectoWebFinal.model.entity.TipoUsuario;
import pe.edu.upn.ProyectoWebFinal.model.entity.Usuario;
import pe.edu.upn.ProyectoWebFinal.model.repository.AuthorityRepository;
import pe.edu.upn.ProyectoWebFinal.model.repository.TipoUsuarioRepository;
import pe.edu.upn.ProyectoWebFinal.model.repository.UsuarioRepository;

@Service
public class InitDB implements CommandLineRunner{

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private AuthorityRepository authorityRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private TipoUsuarioRepository tipoUsuarioRepository;
	@Override
	public void run(String... args) throws Exception {
		/*
		this.usuarioRepository.deleteAll();
		this.authorityRepository.deleteAll();
		
		this.tipoUsuarioRepository.deleteAll();
		
		TipoUsuario tipo1=new TipoUsuario();
		tipo1.setNombre("Usuario Frecuente");
		
		TipoUsuario tipo2=new TipoUsuario();
		tipo2.setNombre("Usuario Nuevo");
		
		List<TipoUsuario>tipos=Arrays.asList(tipo1,tipo2);
        this.tipoUsuarioRepository.saveAll(tipos);
        
	
        Usuario cliente = new Usuario();
		cliente.setUsername("maria");
		cliente.setPassword(passwordEncoder.encode("maria"));
		cliente.setApellidos("Del Pilar");
		cliente.setNombres("Maria Fernanda");
		cliente.setFechaNacimiento("2019/05/10");
		cliente.setLugarNacimiento("Lima");
		cliente.setSexo('F');
		cliente.setTipousuario(tipo2);
		cliente.setEnable(true);
		
		Usuario admin = new Usuario();
		admin.setUsername("ernesto");
		admin.setPassword(passwordEncoder.encode("ernesto"));
		admin.setApellidos("Tarazona Espinoza");
		admin.setNombres("Ernesto Fidel Alejandro");
		admin.setFechaNacimiento("1998/05/25");
		admin.setLugarNacimiento("Lima");
		admin.setSexo('M');
		admin.setTipousuario(tipo1);
		admin.setEnable(true);
		
		cliente.addAuthority("ROLE_CLIENTE");
		admin.addAuthority("ROLE_ADMIN");
        
        List<Usuario> usuarios = Arrays.asList(cliente, admin);        
        this.usuarioRepository.saveAll(usuarios);
        */
	}
}