package si.academia.unit21.vaja5.api;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import static org.junit.Assert.*;

public class WeatherApiProxyTest {

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void testGetHistoricalData() throws IOException, InterruptedException {
        Date xDaysAgo = Date.from(Instant.now().minus(Duration.ofDays(5)));
        WeatherApiProxy proxy = new WeatherApiProxy("http://api.weatherapi.com/v1/", "0f72bd72c67844d18e0160302212503");
        WeatherData wdata = proxy.getHistoricalData("Maribor", xDaysAgo);
        assertNotNull(wdata);
    }
}