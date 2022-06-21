package org.animal.info;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Animal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idanimal;
	private String nome_animal;
	private String tipo_animal;
	private String raca;
	
	
	public int getIdanimal() {
		return idanimal;
	}
	public void setIdanimal(int idanimal) {
		this.idanimal = idanimal;
	}
	public String getNome_animal() {
		return nome_animal;
	}
	public void setNome_animal(String nome_animal) {
		this.nome_animal = nome_animal;
	}
	public String getTipo_animal() {
		return tipo_animal;
	}
	public void setTipo_animal(String tipo_animal) {
		this.tipo_animal = tipo_animal;
	}
	public String getRaça() {
		return raca;
	}
	public void setRaça(String raca) {
		this.raca = raca;
	}
	
}
