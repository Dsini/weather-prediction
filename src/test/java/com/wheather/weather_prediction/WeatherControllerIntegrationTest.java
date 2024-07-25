package com.wheather.weather_prediction;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.wheather.weather_prediction.services.WeatherService;


@SpringBootTest
@AutoConfigureMockMvc
public class WeatherControllerIntegrationTest {

	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private WeatherService weatherService;
   
    @Test
    public void getWeatherByCityTest() throws Exception {
    	mockMvc.perform(MockMvcRequestBuilders.get("/weather/{foo}","london").accept(MediaType.ALL)
                .contentType(MediaType.ALL)).andExpect(status().isOk()).andReturn();
    }

    
    
    @Test
    public void getWeatherByCityNoContentTest() throws Exception {
        String city = " ";
        mockMvc.perform(MockMvcRequestBuilders.get("/weather/" + city).accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andReturn();
    }

}
