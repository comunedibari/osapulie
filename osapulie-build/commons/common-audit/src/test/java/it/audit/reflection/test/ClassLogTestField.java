package it.audit.reflection.test;

import java.util.Date;

import eng.tz.la.annotation.AuditClass;
import eng.tz.la.annotation.AuditField;
import eng.tz.la.annotation.AuditMethod;

 

@AuditClass
public class ClassLogTestField {

	 @AuditField
	private String nome="vxcvvxccv";
	 @AuditField
	private String cognome="nnnnnndddddd";
	
	 @AuditField
	private Double eta= 24.5; 
	
	 @AuditField
	private Date data=new Date();; 
	
	public ClassLogTestField() {
		 
	}

	@AuditMethod
	public String testParam(){
 
	return "Hello belllo";
	}
	
 
 
	@AuditMethod(key="user")
	public String getDenominazione(){
	 return nome+" "+cognome;
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
	
	public void setData(Date data) {
		this.data = data;
	}
	public void setEta(Double eta) {
		this.eta = eta;
	}
	
	public String getName(){
		 
	return "Test Get Name";
	}
	public String getPippo(){
		 
	return "Test Get Pippo";
	}
	
}
