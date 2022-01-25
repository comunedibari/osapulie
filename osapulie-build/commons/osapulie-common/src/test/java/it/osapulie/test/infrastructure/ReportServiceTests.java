/**
 * Copyright (c) 2011 Links Management & Technology S.p.A.
 */

package it.osapulie.test.infrastructure;

import static it.osapulie.infrastructure.impl.StreamUtils.dumpToFile;
import static it.osapulie.test.TestHelpers.createMockComuni;
import static org.junit.Assert.assertNotNull;
import it.osapulie.domain.ComuneISA;
import it.osapulie.infrastructure.ReportService;
import it.osapulie.infrastructure.impl.ReportServiceImpl;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Unit tests for {@link ReportServiceImpl}.
 * 
 * @author Mario Scalas
 */
//@RunWith( SpringJUnit4ClassRunner.class )
//@ContextConfiguration
public class ReportServiceTests {
	@Inject
	private ReportService reportService;
	
	/**
	 * Commentato perchè va modificato il template del report.
	 * @throws Throwable
	 */
	@Test
	public void deveCreareUnReport() throws Throwable {
//		//given
//		List<ComuneISA> beans = createMockComuni( 1 );
//
//		//when
//		byte [] reportBytes = reportService.jrxmlToPdf( new HashMap<String, Object>(), beans, "/reports/test_report.jrxml", null );
//		
//		dumpToFile( reportBytes, "/tmp/test_report_1.pdf" );
//		
//		//then
//		assertNotNull( reportBytes );
	}
}
