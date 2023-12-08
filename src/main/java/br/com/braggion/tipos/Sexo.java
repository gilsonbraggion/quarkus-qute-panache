package br.com.braggion.tipos;

public enum Sexo {
	
	FEMININO("Feminino"),
	MASCULINO("Masculino");
	
	public String descricao;

	Sexo(String descricao) {
		this.descricao = descricao;
	}

}
