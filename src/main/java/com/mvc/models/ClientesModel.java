package com.mvc.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;


import java.util.Set;

import jakarta.persistence.*;


//Entity permite mapear la clase
@Entity
@Table(name= "cliente")
public class ClientesModel {
	
public ClientesModel() {}
	
	public ClientesModel(Long id, String nombre, String apellido, String email, GestoresModel id_gestor) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.id_gestor = id_gestor;
	}
	
	@OneToMany(mappedBy="id_remitente", cascade = CascadeType.REMOVE)
	private Set<MensajesModel> rem_mensajes;
	@OneToMany(mappedBy="id_destinatario", cascade = CascadeType.REMOVE)
	private Set<MensajesModel> des_mensajes;
	@OneToMany(mappedBy="id_remitente", cascade = CascadeType.REMOVE)
	private Set<TransferenciasModel> rem_transferencias;
	@OneToMany(mappedBy="id_destinatario", cascade = CascadeType.REMOVE)
	private Set<TransferenciasModel> des_transferencias;
	
//	atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private long id; 

	@ManyToOne
	@JoinColumn(name= "id_gestor", referencedColumnName = "id")
	private GestoresModel id_gestor;
	private String nombre;
	private String apellido;
	private String email;
	
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
	public GestoresModel getGestor() {
		return this.id_gestor;
	}
	
	public void setGestor(GestoresModel gestor) {
		this.id_gestor = gestor;
	}



}
