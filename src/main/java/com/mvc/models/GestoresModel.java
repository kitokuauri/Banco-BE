package com.mvc.models;


import jakarta.persistence.*;

//Entity permite mapear la clase
@Entity
@Table(name= "gestor")
public class GestoresModel {
	
	public GestoresModel (Long id, String nombre, String apellido, String email, int edad, double salario) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.edad = edad;
		this.salario = salario;
	}

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
