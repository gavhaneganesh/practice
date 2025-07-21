package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APITest {

    @Test
    public void testValidateBPI() {
        String url = "https://api.coindesk.com/v1/bpi/currentprice.json";

        Response response = RestAssured.get(url);
        Assert.assertEquals(response.getStatusCode(), 200);

        String usd = response.jsonPath().getString("bpi.USD.code");
        String gbp = response.jsonPath().getString("bpi.GBP.code");
        String eur = response.jsonPath().getString("bpi.EUR.code");

        Assert.assertEquals(usd, "USD");
        Assert.assertEquals(gbp, "GBP");
        Assert.assertEquals(eur, "EUR");

        String gbpDescription = response.jsonPath().getString("bpi.GBP.description");
        Assert.assertEquals(gbpDescription, "British Pound Sterling");
    }
}
