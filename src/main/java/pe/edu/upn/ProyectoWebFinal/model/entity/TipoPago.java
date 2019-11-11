package pe.edu.upn.ProyectoWebFinal.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tipopagos")
public class TipoPago {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	private Integer id;
	
	@Column (name = "nombre",length = 25,nullable = false)
	private String nombre;

	@OneToMany(mappedBy="tipoPago",fetch=FetchType.LAZY)
	private List<CarritoDeCompras>listacarrito;
	
	public TipoPago() {
		listacarrito=new ArrayList<>();
	}
	public void addCarrito(CarritoDeCompras carrito) {
		carrito.setTipoPago(this);
		this.listacarrito.add(carrito);
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<CarritoDeCompras> getListacarrito() {
		return listacarrito;
	}
	public void setListacarrito(List<CarritoDeCompras> listacarrito) {
		this.listacarrito = listacarrito;
	}
	
	
	
	
	
}
