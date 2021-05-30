package it.prova.datacsvmigrator.model;

import java.util.Date;

public class CSVTransientObj {

	private Long id;
	private String codiceFiscale;
	private String nome;
	private String cognome;
	private Date dataNascita;
	private Integer numeroSinistri;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public Integer getNumeroSinistri() {
		return numeroSinistri;
	}

	public void setNumeroSinistri(Integer numeroSinistri) {
		this.numeroSinistri = numeroSinistri;
	}

	public CSVTransientObj(Long id, String codiceFiscale, String nome, String cognome, Date dataNascita,
			Integer numeroSinistri) {
		super();
		this.id = id;
		this.codiceFiscale = codiceFiscale;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.numeroSinistri = numeroSinistri;
	}

	public CSVTransientObj() {
		super();
	}

}
