package pl.pioro.shipmentregister.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.pioro.shipmentregister.entity.Country;
import pl.pioro.shipmentregister.repository.CountryRepository;

import java.util.Arrays;
import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static sun.plugin2.util.PojoUtil.toJson;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CountryController.class, secure = false)
public class CountryControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private CountryRepository countryRepository;

    Country country = new Country("Poland", "PL");
    List<Country> countries = Arrays.asList(country);

    @Test
    public void testGetCountryByName() throws Exception {

        Mockito.when(countryRepository.findByName(Mockito.anyString())).thenReturn(country);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/admin/countries/name")
                .param("name", "Poland")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();


        String expected = "{id:null,name:Poland,code:PL}";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void testGetAllCountries() throws Exception {

        Mockito.when(countryRepository.findAll()).thenReturn(countries);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/admin/countries")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();


        String expected = "{id:null,name:Poland,code:PL}";
        List<String> exp = Arrays.asList(expected);

        JSONAssert.assertEquals(exp.toString(), result.getResponse().getContentAsString(), false);
    }

    @Test
    public void testAddNewCountryCountries() throws Exception {

        Country country = new Country("Poland", "PL");

//        Mockito.when(countryRepository.save(country)).thenReturn(mockCountry);


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/admin/countries")
                .accept(MediaType.APPLICATION_JSON)
                .content(toJson(country))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().isCreated()).andReturn();

    }

}