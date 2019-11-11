package pe.edu.upn.ProyectoWebFinal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import pe.edu.upn.ProyectoWebFinal.model.entity.DetalleVenta;
import pe.edu.upn.ProyectoWebFinal.model.entity.MetodoEntrega;
import pe.edu.upn.ProyectoWebFinal.model.entity.Modelo;
import pe.edu.upn.ProyectoWebFinal.model.entity.Producto;
import pe.edu.upn.ProyectoWebFinal.model.entity.TipoPago;
import pe.edu.upn.ProyectoWebFinal.service.DetalleVentaService;
import pe.edu.upn.ProyectoWebFinal.service.MetodoEntregaService;
import pe.edu.upn.ProyectoWebFinal.service.TipoPagoService;
import pe.edu.upn.ProyectoWebFinal.model.entity.TipoUsuario;
import pe.edu.upn.ProyectoWebFinal.model.entity.Usuario;
import pe.edu.upn.ProyectoWebFinal.model.entity.CarritoDeCompras;
import pe.edu.upn.ProyectoWebFinal.service.ProductoService;
import pe.edu.upn.ProyectoWebFinal.service.TipoUsuarioService;
import pe.edu.upn.ProyectoWebFinal.service.UsuarioService;
import pe.edu.upn.ProyectoWebFinal.service.CarritoDeComprasService;



@Controller
@RequestMapping("/usuario")
@SessionAttributes({"usuario","carrito"})
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	 private PasswordEncoder passwordEncoder;
	
	@Autowired
	private TipoUsuarioService tipoUsuarioService;
	
	@Autowired
	private CarritoDeComprasService carritoDeComprasService;
	
	@Autowired
	private ProductoService productoService;
	@Autowired
	private TipoPagoService tipoPagoService;
	
	@Autowired
	private MetodoEntregaService metodoEntregaService;
	
	@Autowired
	private DetalleVentaService detalleVentaService;
	
	@GetMapping
	public String inicio(Model model) {
		try {
			List<Usuario>usuarios=usuarioService.findAll();
			
			model.addAttribute("usuarios", usuarios);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/usuario/lista";
	}
	
	@GetMapping("/nuevo")
	public String register(Model model) {
		Usuario usuario=new Usuario();
		model.addAttribute("usuario", usuario);
		
		try {
			
			List<TipoUsuario>tipoUsuarios=tipoUsuarioService.findAll();
			model.addAttribute("tipoUsuarios", tipoUsuarios);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return "/usuario/nuevo";
	}
	@PostMapping("/save")
	public String save(@ModelAttribute("usuario") Usuario usuario, Model model, SessionStatus status) {
		try {
			Optional<Usuario>optional=usuarioService.findByUsername(usuario.getUsername());
			if(optional.isPresent()) {
				model.addAttribute("dangerNuevo", "Error en el username "+usuario.getUsername()+" </strong> ya existe");
				return "/usuario/register";
			}
			else {
				usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
				usuario.addAuthority("ROLE_CLIENTE");
				usuarioService.save(usuario);
				status.setComplete();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/login";
		
	}
	@GetMapping("/{id}/nuevocarrito")
	public String nuevoCarrito(@PathVariable("id") long id,Model model) {
		
		CarritoDeCompras carrito = new CarritoDeCompras();
		try {
			Optional<Usuario> usuario = usuarioService.findById(id);
			if(usuario.isPresent()) {
				List<Producto>productos=productoService.findAll();
				List<TipoPago>tipoPagos=tipoPagoService.findAll();
				List<MetodoEntrega>metodoEntregas=metodoEntregaService.findAll();
				
				carrito.setUsuario(usuario.get());
				model.addAttribute("carrito", carrito);
				model.addAttribute("productos", productos);
				model.addAttribute("tipoPagos", tipoPagos);
				model.addAttribute("metodoEntregas", metodoEntregas);
				
				/*
				for(Producto p:productos)
					if(p.getId().equals(carrito.getProducto().getId()))
						p.restarExistencia(carrito.getProducto().getStock());
				productoService.save(p);
				
				
				for(Producto p:productos) {
					if(p.getId().equals(carrito.getProducto().getId())) 
						
						 p.restarExistencia(carrito);
					p.setStock(carrito.getCantidad());
				productoService.save(p);
				}
				
				*/
				
				
			} else {
				return "redirect:/";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "/usuario/nuevocarrito";
	}
	@PostMapping("/savecarrito")
	public String saveCarritoDeCompras(@ModelAttribute("carrito") CarritoDeCompras carrito, 
			Model model, SessionStatus status) {
		
		try {
			carritoDeComprasService.save(carrito);
			
			status.setComplete();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/detalle/{id}")
	public String detalle(@PathVariable("id")long id,Model model,SessionStatus status) {
		/*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		model.addAttribute("currentPrincipalName", currentPrincipalName);*/
		float T=0;
		DetalleVenta detalleventa=new DetalleVenta();
		String estado="no entregado";
		try {
			Optional<Usuario>usuario=usuarioService.findById(id);
		/*Optional<Usuario> usuario = usuarioService.findByUsername(currentPrincipalName);*/
			if(usuario.isPresent()) {
				
				List<CarritoDeCompras>carrito=carritoDeComprasService.findAll();
				for (CarritoDeCompras v:carrito) 
					if(v.getUsuario().equals(usuario.get())) 
						T+=v.getTotal();
						detalleventa.setUsuario(usuario.get());
						detalleventa.setEstado(estado);
						detalleventa.setNombre(usuario.get().getNombres());
						
				        detalleventa.setTotal(T);
						model.addAttribute("detalleventa", detalleventa);
						detalleVentaService.save(detalleventa);
						status.setComplete();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/detalleMostrar/{id}")
	public String DetalleMostrar(@PathVariable("id") long id, Model model) {
		
		
		
		try {
			
			Optional<Usuario>optional=usuarioService.findById(id);
			if(optional.isPresent()) {
				List<DetalleVenta>detalleventa=detalleVentaService.findAll();
				
				for(DetalleVenta d:detalleventa)
				if(d.getUsuario().equals(optional.get()))
					model.addAttribute("detalleventa", detalleventa);
				model.addAttribute("usuario", optional.get());
					
				
				
			} else {
				return "redirect:/";
			}
		} catch (Exception e) {

		}	
		
		return "/usuario/mostrar";
	}
	
	
	
	@GetMapping("/info/{id}")
	public String info(@PathVariable("id") long id, Model model) {
		
		float T=0;
		try {
			
			Optional<Usuario>optional=usuarioService.findById(id);
			if(optional.isPresent()) {
				List<CarritoDeCompras>total=carritoDeComprasService.findAll();
				
				for (CarritoDeCompras v:total)
					if(v.getUsuario().equals(optional.get()))
					
					T+=v.getTotal();
				
				model.addAttribute("usuario", optional.get());
				model.addAttribute("total", T);
			} else {
				return "redirect:/";
			}
		} catch (Exception e) {

		}	
		
		return "/usuario/info";
	}
	@GetMapping("/del/{id}")
	public String eliminar(@PathVariable("id")long id, Model model) {
		try {
			Optional<Usuario>usuario=usuarioService.findById(id);
			if(usuario.isPresent()) {
				usuarioService.deleteById(id);
			}
		} catch (Exception e) {
			model.addAttribute("dangerDel", "ERROR - Violación contra el principio de Integridad referencia");
			 try {
				List<Usuario>usuarios=usuarioService.findAll();
				model.addAttribute("usuarios",usuarios);
			} catch (Exception e2) {
				// TODO: handle exception
			}
			 return "/lista";
		}
		return "redirect:/usuario";
	}
	
	@GetMapping("/informacion/{username}")
	public String informacionUsuario(@PathVariable("username")String username, Model model) {
		try {
			Optional<Usuario>usuario=usuarioService.findByUsername(username);
			if(usuario.isPresent()) {
				model.addAttribute("usuario", usuario.get());
				
			}
		} catch (Exception e) {
			
		}
		return "/usuario/informacionventa";
	}
	
}
