package pl.pioro.shipmentregister.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "incoterms")
public class Incoterms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min = 3, max = 3)
    @Column(unique = true)
    @ApiModelProperty(notes = "name must be unique and have 3 characters")
    private String name;

    @OneToMany(mappedBy = "incoterms", orphanRemoval = true)
    private List<Shipment> shipments;

    public Incoterms() {
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
