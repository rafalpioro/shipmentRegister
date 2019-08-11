package pl.pioro.shipmentregister.repository;


import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;;
import org.springframework.test.context.junit4.SpringRunner;
import pl.pioro.shipmentregister.entity.CarrierType;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CarrierTypeRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarrierTypeRepository repository;

    @Test
    public void testExample()  {
        CarrierType carrierType = new CarrierType();
        carrierType.setName("Name");

        this.entityManager.persist(carrierType);
        CarrierType found = this.repository.findByName("Name");
        assertThat(found.getName()).isEqualTo("Name");
    }
}