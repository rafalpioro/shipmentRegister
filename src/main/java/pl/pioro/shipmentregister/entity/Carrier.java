package pl.pioro.shipmentregister.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "carriers")
@ApiModel(description = "Carriers used by the company")
public class Carrier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 150)
    @Column(unique = true)
    @ApiModelProperty(notes = "name must be unique, min 2 characters")
    private String name;

    @Column(name = "is_active")
    @ColumnDefault("1")
    private Boolean isActive = true;

    @ManyToOne
    @JoinColumn(name = "carrier_type_id")
    @NotNull
    private CarrierType carrierType;

    @OneToMany(mappedBy = "carrier", orphanRemoval = true)
    private List<Shipment> shipments;

    public Carrier() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CarrierType getCarrierType() {
        return carrierType;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public void setCarrierType(CarrierType carrierType) {
        this.carrierType = carrierType;
    }


}
