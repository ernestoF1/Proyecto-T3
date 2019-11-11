package pe.edu.upn.ProyectoWebFinal.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="boletas")
public class DetalleVenta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	

	@Column(name="nombre")
	private String nombre;
	
	@Column(name="fechaYHora",length=30)
	 private String fechaYHora;
	
	@Column(name="total")
	private Float total;
	
	@Column(name="estado")
	private String estado;
	
	@ManyToOne
    @JoinColumn
    private Usuario usuario;

	public DetalleVenta() {
		 this.fechaYHora = Utiles.obtenerFechaYHoraActual();
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


	
	
	public String getFechaYHora() {
		return fechaYHora;
	}

	public void setFechaYHora(String fechaYHora) {
		this.fechaYHora = fechaYHora;
	}

	public Float getTotal() {
		return total;
	}


	public void setTotal(Float total) {
		this.total = total;
	}





	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	 
	
	
	
	
	
	
	 
	 
	
}
