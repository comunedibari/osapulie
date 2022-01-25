package it.osapulie.domain;

import javax.persistence.MappedSuperclass;

import org.springframework.data.jpa.domain.AbstractPersistable;


@SuppressWarnings("serial") // Per le classi derivate
@MappedSuperclass
public class Ente extends AbstractPersistable<Long> {

	
}
