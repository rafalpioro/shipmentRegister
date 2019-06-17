package pl.pioro.shipmentregister.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "carrier_types")
public class CarrierType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 2, max = 150)
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "carrierType", orphanRemoval = true)
    private List<Carrier> carriers;

    public CarrierType() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Carrier> getCarriers() {
        return carriers;
    }

    public void setCarriers(List<Carrier> carriers) {
        this.carriers = carriers;
    }
}
