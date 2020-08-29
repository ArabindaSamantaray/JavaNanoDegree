package com.udacity.pricing;

import com.udacity.pricing.api.PricingController;
import com.udacity.pricing.domain.price.Price;
import com.udacity.pricing.service.PriceException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PricingServiceApplicationTests {

    @InjectMocks
    PricingController pricingController;



	@Test
	public void contextLoads() {
	}

	@Test
    public void testPrices(){
        Price price = pricingController.get(10l);
        Assert.assertNotNull(price);
    }

    @Test
    public void testException(){
	    try {
            pricingController.get(100l);
        } catch (ResponseStatusException e){
	        Assert.assertEquals(e.getReason(), "Price Not Found");
        }

    }

}
