package pl.pioro.shipmentregister.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "branches")
@ApiModel(description = "All branches of the company")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 150)
    @Column(unique = true)
    @ApiModelProperty(notes = "min 2 characters")
    private String name;

    @NotBlank
    @Size(min = 2, max = 150)
    private String city;

    @NotBlank
    @Size(min = 2, max = 10)
    @Column(name = "zip_code")
    private String zipCode;

    @NotBlank
    private String address;

    @ManyToOne
    @JoinColumn(name = "country_id")
    @NotNull
    private Country country;

    @OneToMany(mappedBy = "branch", orphanRemoval = true)
    private List<Shipment> shipments;

    public Branch() {
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
