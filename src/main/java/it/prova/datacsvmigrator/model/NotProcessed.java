package it.prova.datacsvmigrator.model;

public class NotProcessed {

	private Long id;
	private String codiceFiscale;
	private String oldId;

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

	public String getOldId() {
		return oldId;
	}

	public void setOldId(String oldId) {
		this.oldId = oldId;
	}

	public NotProcessed(Long id, String codiceFiscale, String oldId) {
		super();
		this.id = id;
		this.codiceFiscale = codiceFiscale;
		this.oldId = oldId;
	}

	public NotProcessed() {
		super();
	}

}
