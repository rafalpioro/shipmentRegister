package pl.pioro.shipmentregister.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.pioro.shipmentregister.entity.CarrierType;
import pl.pioro.shipmentregister.repository.CarrierTypeRepository;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static sun.plugin2.util.PojoUtil.toJson;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CarrierTypeController.class, secure = false)
public class CarrierTypeControllerTest {

    @Autowired
    private MockMvc mvc;


    @MockBean
    private CarrierTypeRepository carrierTypeRepository;

    @Test
    public void shouldReturnCarrierTypesById() throws Exception {

        CarrierType carrierType = new CarrierType();
        carrierType.setId(1);
        carrierType.setName("Name");

        Mockito.when(carrierTypeRepository.findById(1)).thenReturn(carrierType);

        mvc.perform(get("/admin/carriertypes/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("{id:1, name:Name}",false));

    }

    @Test
    public void testCreateCarrierType() throws Exception {
        CarrierType carrierType = new CarrierType();
        carrierType.setId(1);
        carrierType.setName("Name");

        Mockito.when(carrierTypeRepository.existsById(carrierType.getId())).thenReturn(false);
        mvc.perform(
                post("/admin/carriertypes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(carrierType)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testDeleteCarrierType() throws Exception {
        CarrierType carrierType = new CarrierType();
        carrierType.setId(1);
        carrierType.setName("Name");
        Mockito.when(carrierTypeRepository.findById(1)).thenReturn(carrierType);

        mvc.perform(
                delete("/admin/carriertypes/{id}", carrierType.getId()))
                .andExpect(status().isOk());

        Mockito.verify(carrierTypeRepository).deleteById(1);
    }

    @Test
    public void testPutCarrierType() throws Exception {
        CarrierType carrierType = new CarrierType();
        carrierType.setId(1);
        carrierType.setName("Name");
        Mockito.when(carrierTypeRepository.findById(1)).thenReturn(carrierType);

        mvc.perform(
                put("/admin/carriertypes/{id}", carrierType.getId())
                        .contentType(MediaType.APPLICATION_JSON).content(toJson(carrierType)))
                .andExpect(status().isOk());

        Mockito.verify(carrierTypeRepository).save(carrierType);
        Mockito.verify(carrierTypeRepository).findById(1);
    }
}