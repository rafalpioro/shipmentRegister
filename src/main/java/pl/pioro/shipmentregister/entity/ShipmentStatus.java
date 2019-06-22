package pl.pioro.shipmentregister.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "shipment_statuses")
@ApiModel(description = "Statuses that a shipment can have")
public class ShipmentStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min = 2, max = 240)
    @Column(unique = true)
    @ApiModelProperty(notes = "name must be unique, min 2 characters")
    private String name;

    @OneToMany(mappedBy = "shipmentStatus", orphanRemoval = true)
    private List<Shipment> shipments;

    public ShipmentStatus() {
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

}
