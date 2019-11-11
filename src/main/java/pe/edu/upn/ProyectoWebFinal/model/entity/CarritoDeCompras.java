package pe.edu.upn.ProyectoWebFinal.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;




@Entity
@Table(name="carritodecompras")
public class CarritoDeCompras {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="fechaYHora",length=30)
	 private String fechaYHora;
	 
	 public CarritoDeCompras() {
	        this.fechaYHora = Utiles.obtenerFechaYHoraActual();
	    }
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_envio",length=30)
	private Date fecha_envio;
	
	@Column(name="cantidad")
	private Float cantidad;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="producto_id")
	private Producto producto;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tipoPago_id")
	private TipoPago tipoPago;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="metodoEntrega_id")
	private MetodoEntrega metodoentrega;
	
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getFechaYHora() {
		return fechaYHora;
	}

	public void setFechaYHora(String fechaYHora) {
		this.fechaYHora = fechaYHora;
	}

	
	public Date getFecha_envio() {
		return fecha_envio;
	}

	public void setFecha_envio(Date fecha_envio) {
		this.fecha_envio = fecha_envio;
	}

	public Float getCantidad() {
		return cantidad;
	}

	public void setCantidad(Float cantidad) {
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public TipoPago getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(TipoPago tipoPago) {
		this.tipoPago = tipoPago;
	}
	

	public MetodoEntrega getMetodoentrega() {
		return metodoentrega;
	}

	public void setMetodoentrega(MetodoEntrega metodoentrega) {
		this.metodoentrega = metodoentrega;
	}

	
	
	public Float getTotal() {
		return this.producto.getPrecio()*this.cantidad-(this.metodoentrega.getComision()*this.producto.getPrecio()*this.cantidad);
	}
	
}
