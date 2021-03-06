package org.jasig.cas.client.validation;

import junit.framework.TestCase;
import org.jasig.cas.client.proxy.CleanUpTimerTask;
import org.jasig.cas.client.proxy.ProxyGrantingTicketStorage;
import org.jasig.cas.client.proxy.ProxyGrantingTicketStorageImpl;
import org.jasig.cas.client.util.MethodFlag;
import org.springframework.mock.web.MockFilterConfig;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Unit test for {@link org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter}
 *
 * @author Brad Cupit (brad [at] lsu {dot} edu)
 */
public class Cas20ProxyReceivingTicketValidationFilterTests extends TestCase {

    private final Timer defaultTimer = new Timer(true);

    private final ProxyGrantingTicketStorage storage = new ProxyGrantingTicketStorageImpl();

    private final CleanUpTimerTask defaultTimerTask = new CleanUpTimerTask(storage);

    public void testStartsThreadAtStartup() throws Exception {
        final MethodFlag scheduleMethodFlag = new MethodFlag();
        final Cas20ProxyReceivingTicketValidationFilter filter = newCas20ProxyReceivingTicketValidationFilter();

        final Timer timer = new Timer(true) {
            public void schedule(TimerTask task, long delay, long period) {
                scheduleMethodFlag.setCalled();
            }
        };

        filter.setMillisBetweenCleanUps(1);
        filter.setProxyGrantingTicketStorage(storage);
        filter.setTimer(timer);
        filter.setTimerTask(defaultTimerTask);

        filter.init();
        assertTrue(scheduleMethodFlag.wasCalled());
    }

    public void testShutsDownTimerThread() throws Exception {
        final MethodFlag cancelMethodFlag = new MethodFlag();
        final Cas20ProxyReceivingTicketValidationFilter filter = newCas20ProxyReceivingTicketValidationFilter();

        final Timer timer = new Timer(true) {
            public void cancel() {
                cancelMethodFlag.setCalled();
                super.cancel();
            }
        };

        filter.setProxyGrantingTicketStorage(storage);
        filter.setMillisBetweenCleanUps(1);
        filter.setTimer(timer);
        filter.setTimerTask(defaultTimerTask);
        filter.init();
        filter.destroy();

        assertTrue(cancelMethodFlag.wasCalled());
    }

public void testCallsCleanAllOnSchedule() throws Exception {
        final MethodFlag timerTaskFlag = new MethodFlag();
        final Cas20ProxyReceivingTicketValidationFilter filter = newCas20ProxyReceivingTicketValidationFilter();

        final TimerTask timerTask = new TimerTask() {
            public void run() {
                timerTaskFlag.setCalled();
            }
        };

        final int millisBetweenCleanUps = 250;
        filter.setProxyGrantingTicketStorage(storage);
        filter.setTimerTask(timerTask);
        filter.setTimer(defaultTimer);
        filter.setMillisBetweenCleanUps(millisBetweenCleanUps);

        filter.init();

        // wait long enough for the clean up to occur
        Thread.sleep(millisBetweenCleanUps * 2);

        assertTrue(timerTaskFlag.wasCalled());
        filter.destroy();
    }

    public void testDelaysFirstCleanAll() throws Exception {
        final MethodFlag timerTaskFlag = new MethodFlag();
        final Cas20ProxyReceivingTicketValidationFilter filter = newCas20ProxyReceivingTicketValidationFilter();

        final TimerTask timerTask = new TimerTask() {
            public void run() {
                timerTaskFlag.setCalled();
            }
        };

        final int millisBetweenCleanUps = 250;
        filter.setProxyGrantingTicketStorage(storage);
        filter.setMillisBetweenCleanUps(millisBetweenCleanUps);
        filter.setTimer(defaultTimer);
        filter.setTimerTask(timerTask);

        filter.init();

        assertFalse(timerTaskFlag.wasCalled());

        // wait long enough for the clean up to occur
        Thread.sleep(millisBetweenCleanUps * 2);

        assertTrue(timerTaskFlag.wasCalled());

        filter.destroy();
    }

    public void testThrowsForNullStorage() throws Exception {
        Cas20ProxyReceivingTicketValidationFilter filter = newCas20ProxyReceivingTicketValidationFilter();
        filter.setProxyGrantingTicketStorage(null);

        try {
            filter.init();
            fail("expected an exception due to null ProxyGrantingTicketStorage");
        } catch (IllegalArgumentException exception) {
            // test passes
        }
    }

    public void testGetTicketValidator() throws Exception {
        Cas20ProxyReceivingTicketValidationFilter filter = newCas20ProxyReceivingTicketValidationFilter();
        filter.setProxyGrantingTicketStorage(storage);
        filter.setMillisBetweenCleanUps(250);
        filter.setTimer(defaultTimer);
        filter.setTimerTask(new TimerTask() {
          public void run() {}
        });
        filter.init();

        // Test case #1
        final MockFilterConfig config1 = new MockFilterConfig();
        config1.addInitParameter("allowedProxyChains", "https://a.example.com");
        config1.addInitParameter("casServerUrlPrefix", "https://cas.jasig.org/");
        assertNotNull(filter.getTicketValidator(config1));

        // Test case #2
        final MockFilterConfig config2 = new MockFilterConfig();
        config2.addInitParameter(
                "allowedProxyChains",
                "https://a.example.com https://b.example.com");
        config2.addInitParameter("casServerUrlPrefix", "https://cas.jasig.org/");
        assertNotNull(filter.getTicketValidator(config2));

        // Test case #3
        final MockFilterConfig config3 = new MockFilterConfig();
        config3.addInitParameter(
                "allowedProxyChains",
                "https://a.example.com https://b.example.com\nhttps://c.example.com");
        config3.addInitParameter("casServerUrlPrefix", "https://cas.jasig.org/");
        assertNotNull(filter.getTicketValidator(config3));
    }
  


    /**
     * construct a working {@link org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter}
     */
    private Cas20ProxyReceivingTicketValidationFilter newCas20ProxyReceivingTicketValidationFilter() {
        final Cas20ProxyReceivingTicketValidationFilter filter = new Cas20ProxyReceivingTicketValidationFilter();
        filter.setServerName("localhost");
        filter.setTicketValidator(new Cas20ProxyTicketValidator(""));

        return filter;
    }
}
