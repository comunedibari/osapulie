package it.audit.reflection.test;

import eng.tz.la.annotation.AuditClass;
import eng.tz.la.annotation.AuditField;

@AuditClass
public class Utente {
	
	@AuditField
	private String nome;
	@AuditField
	private String cognome;
	@AuditField
	private String cap;

	public Utente() {
		// TODO Auto-generated constructor stub
	}
	
	public Utente(String nome, String cognome, String cap) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.cap = cap;
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

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

}
