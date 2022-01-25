package it.osapulie.profiloutente.ws.trasformer;

import org.apache.commons.beanutils.BeanUtils;

public class MainTrasformer<D, E> {
	final Class<D> dtoClass;
	final Class<E> entityClass;

    public MainTrasformer(Class<D> dtoClass, Class<E> entityClass) {
        this.dtoClass = dtoClass;
        this.entityClass = entityClass;
    }
	
	public D toDto(E entity) throws Exception{
		if(entity!=null){
			D dto = dtoClass.newInstance();
			BeanUtils.copyProperties(dto, entity);
			return dto;
		}else{
			return null;
		}
	}
	
	public E fromDto(D dto) throws Exception{
		if(dto!=null){
			E entity = entityClass.newInstance();
			BeanUtils.copyProperties(entity, dto);
			return entity;
		}else{
			return null;
		}
	}
}
