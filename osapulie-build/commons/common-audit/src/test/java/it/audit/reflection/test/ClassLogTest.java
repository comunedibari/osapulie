package it.audit.reflection.test;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import eng.tz.la.annotation.AuditClass;
import eng.tz.la.annotation.AuditConfig;
import eng.tz.la.annotation.AuditField;
import eng.tz.la.annotation.AuditMethod;
import eng.tz.la.annotation.AuditRequestParam;
 

@AuditConfig
@AuditClass
public class ClassLogTest {

	 @AuditField
	private String nome;
	 @AuditField
	private String cognome;
	
	 @AuditField
	private Double eta;
	
	 @AuditField
	private Date data; 
	
	 @AuditField
	 private ClassLogTestField classLogTestField;
	 
		@AuditField
		public BeanUno beanUno;
		
		@AuditField
		public BeanDue beanDue;
	 
	public ClassLogTest() {
		classLogTestField=new ClassLogTestField();	
		beanDue=new BeanDue();
		beanUno=new BeanUno();
	}

	 
	public String testParam(@AuditRequestParam(name="nome") String nome){
 
	return "Hello "+nome;
	}
	
 
 
	@AuditMethod(key="userTest")
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
	
	@AuditMethod(method={"getName","getPippo"})
	public ClassLogTestField getTestLog(){
		return new ClassLogTestField();
	}
	 
	@AuditMethod(type=List.class)
	public List<Utente>getUtenti(){
		List<Utente> u= new ArrayList<Utente>();
		u.add(new Utente("Gionanni","Lamberti","80078"));
		u.add(new Utente("Fulvio","Trullo","80078"));
		u.add(new Utente("Nicola","Verde","80078"));
		u.add(new Utente("Giulio","Tormolo","80078"));
		u.add(new Utente("Giuseppe","De Luca","80078"));
		return u;
	}
	
	@AuditMethod
	public Utente[] getUtentiArray(){
		List<Utente> u= new ArrayList<Utente>();
		u.add(new Utente("Gionannidfgdf","Lamberti","8007855"));
		u.add(new Utente("Fulviofgdfg","Trullo","8007855"));
		u.add(new Utente("Nicolafdgf","Verde","8007855"));
		u.add(new Utente("Giuliogfdfggf","Tormolo","8007855"));
		u.add(new Utente("Giuseppefdggf","De Luca","8007855"));
		return u.toArray(new Utente[]{});
	}
	
 
	
}
