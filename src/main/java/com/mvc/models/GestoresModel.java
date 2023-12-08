package com.mvc.models;

import java.util.ArrayList;

import jakarta.persistence.*;

//Entity permite mapear la clase
@Entity
@Table(name= "gestores")
public class GestoresModel {

//	atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private long id;
	
	private String nombre;
	private String apellido;
	private String email;
	private int edad;
	private double salario;
	
//	Lista de clientes con id_gestor
//	LAZY consulta la lista en el momento que se necesita
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id_gestor")
	private ArrayList<ClientesModel> clientes;
	
//	GETTERS & SETTERS
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	
	
	
}
