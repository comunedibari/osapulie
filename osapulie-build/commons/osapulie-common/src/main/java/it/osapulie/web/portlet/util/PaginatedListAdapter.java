/**
 * Copyright (c) 2011 Links Management & Technology S.p.A.
 */

package it.osapulie.web.portlet.util;

import java.io.Serializable;
import java.util.List;

import org.displaytag.pagination.PaginatedList;
import org.displaytag.properties.SortOrderEnum;
import org.springframework.data.domain.Page;

/**
 * DisplayTag adapter for Spring {@link Page} interface.
 * 
 * @author Mario Scalas
 */
public class PaginatedListAdapter<T> implements PaginatedList, Serializable {
	private static final long serialVersionUID = -413186659927314687L;

	private final Page<T> page;
	
	/**
	 * Costruisce un nuovo adapter per questa pagina.
	 * @param page
	 */
	public PaginatedListAdapter( Page<T> page ) {
		this.page = page;
	}

	/* (non-Javadoc)
	 * @see org.displaytag.pagination.PaginatedList#getList()
	 */
	@SuppressWarnings( "rawtypes" )
	@Override
	public List getList() {
		return page.getContent();
	}

	/* (non-Javadoc)
	 * @see org.displaytag.pagination.PaginatedList#getPageNumber()
	 */
	@Override
	public int getPageNumber() {
		return page.getNumber() + 1;
	}

	/* (non-Javadoc)
	 * @see org.displaytag.pagination.PaginatedList#getObjectsPerPage()
	 */
	@Override
	public int getObjectsPerPage() {
		return page.getSize();
	}

	/* (non-Javadoc)
	 * @see org.displaytag.pagination.PaginatedList#getFullListSize()
	 */
	@Override
	public int getFullListSize() {
		return (int) page.getTotalElements();
	}

	/* (non-Javadoc)
	 * @see org.displaytag.pagination.PaginatedList#getSortCriterion()
	 */
	@Override
	public String getSortCriterion() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.displaytag.pagination.PaginatedList#getSortDirection()
	 */
	@Override
	public SortOrderEnum getSortDirection() {
		// XXX Do something cool with page.getSort().getOrderFor( property ) !!
		return SortOrderEnum.ASCENDING;
	}

	/* (non-Javadoc)
	 * @see org.displaytag.pagination.PaginatedList#getSearchId()
	 */
	@Override
	public String getSearchId() {
		return null;
	}

}
