package com.mvc.models;

import java.sql.Date;

import jakarta.persistence.*;


//Entity permite mapear la clase
@Entity
@Table(name= "mensajes")
public class MensajesModel {
	
//	Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private long id;
	
	@ManyToOne
	@JoinColumn(name= "id_remitente",referencedColumnName = "id")
	private ClientesModel id_remitente;
	
	@ManyToOne
	@JoinColumn(name= "id_destinatario",referencedColumnName = "id")
	private ClientesModel id_destinatario;
	
	private String remitente;
	private String destinatario;
	private String mensaje;
	private Date fecha;
	
//	GETTERS & SETTERS
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public ClientesModel getId_remitente() {
		return id_remitente;
	}
	public void setId_remitente(ClientesModel id_remitente) {
		this.id_remitente = id_remitente;
	}
	public ClientesModel getId_destinatario() {
		return id_destinatario;
	}
	public void setId_destinatario(ClientesModel id_destinatario) {
		this.id_destinatario = id_destinatario;
	}
	public String getRemitente() {
		return remitente;
	}
	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
