package br.com.braggion.model;

import br.com.braggion.tipos.Sexo;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pessoa extends PanacheEntityBase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(updatable = false, unique = true)
	public String id;
	
	public String nome;
	
	public Integer idade;
	
	@Enumerated(EnumType.STRING)
	public Sexo sexo;
	
}
