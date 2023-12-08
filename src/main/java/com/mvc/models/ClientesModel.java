package com.mvc.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

import java.util.ArrayList;

import jakarta.persistence.*;


//Entity permite mapear la clase
@Entity
@Table(name= "cliente")
public class ClientesModel {
	
//	atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private long id; 
	
//	Operaciones en cascada. DETACH => Deja a los clientes sin gestor si este es eliminado.
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name= "id_gestor", referencedColumnName = "id")
	private GestoresModel id_gestor;
	private String nombre;
	private String apellido;
	private String email;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id_remitente")
	private ArrayList<MensajesModel> rem_mensajes;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "destinatario")
	private ArrayList<MensajesModel> des_mensajes;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id_remitente")
	private ArrayList<TransferenciasModel> rem_transferencias;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "destinatario")
	private ArrayList<TransferenciasModel> des_transferencias;
	
//	GETETERS & SETTERS
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public GestoresModel getId_gestor() {
		return id_gestor;
	}
	public void setId_gestor(GestoresModel id_gestor) {
		this.id_gestor = id_gestor;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
//	public ArrayList<MensajesModel> getRem_mensajes() {
//		return rem_mensajes;
//	}
//	public void setRem_mensajes(ArrayList<MensajesModel> rem_mensajes) {
//		this.rem_mensajes = rem_mensajes;
//	}
//	public ArrayList<MensajesModel> getDes_mensajes() {
//		return des_mensajes;
//	}
//	public void setDes_mensajes(ArrayList<MensajesModel> des_mensajes) {
//		this.des_mensajes = des_mensajes;
//	}
//	public ArrayList<TransferenciasModel> getRem_transferencias() {
//		return rem_transferencias;
//	}
//	public void setRem_transferencias(ArrayList<TransferenciasModel> rem_transferencias) {
//		this.rem_transferencias = rem_transferencias;
//	}
//	public ArrayList<TransferenciasModel> getDes_transferencias() {
//		return des_transferencias;
//	}
//	public void setDes_transferencias(ArrayList<TransferenciasModel> des_transferencias) {
//		this.des_transferencias = des_transferencias;
//	}
//	


}
