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
@Table(name = "clients")
@ApiModel(description = "Clients of the company")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 150)
    @Column(unique = true)
    @ApiModelProperty(notes = "name must be unique")
    private String name;

    @NotBlank
    private String address;

    @NotBlank
    @Size(min = 2, max = 150)
    @ApiModelProperty(notes = "min 2 characters")
    private String city;

    @NotBlank
    @Size(min = 2, max = 10)
    @Column(name = "zip_code")
    @ApiModelProperty(notes = "min 2, max 10 characters")
    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "country_id")
    @NotNull
    private Country country;

    @Column(name = "is_active")
    @ColumnDefault("1")
    private Boolean isActive = true;

    @OneToMany(mappedBy = "client", orphanRemoval = true)
    private List<Project> projects;

    public Client() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

}
