package pe.edu.upn.ProyectoWebFinal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import pe.edu.upn.ProyectoWebFinal.model.entity.CarritoDeCompras;
import pe.edu.upn.ProyectoWebFinal.service.CarritoDeComprasService;

/*
@Controller
@RequestMapping("/venta")
@SessionAttributes("venta")
*/
@Controller
@RequestMapping("/carrito")
@SessionAttributes("carrito")
public class CarritoDeComprasController {

	@Autowired
	private CarritoDeComprasService carritoDeComprasService;
	
	@GetMapping("/del/{id}")
	public String eliminar(@PathVariable("id")int id, Model model) {
		try {
			Optional<CarritoDeCompras>carrito=carritoDeComprasService.findById(id);
			if(carrito.isPresent()) {
				carritoDeComprasService.deleteById(id);
			}
		} catch (Exception e) {
			 model.addAttribute("dangerDel","ERROR");
			 try {
				List<CarritoDeCompras>carritoDeCompras=carritoDeComprasService.findAll();
				model.addAttribute("carritoDeCompras", carritoDeCompras);
				/*model.addAttribute("ventas", ventas);*/
			} catch (Exception e2) {
				// TODO: handle exception
			}
			 return "/usuario";
		}
		return "redirect:/";
	}
}
