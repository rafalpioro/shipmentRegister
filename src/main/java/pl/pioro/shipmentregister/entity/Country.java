package pl.pioro.shipmentregister.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 150)
    @Column(unique = true)
    private String name;

    @NotBlank
    @Size(min = 2, max = 4)
    @Column(unique = true)
    private String code;

    @OneToMany(mappedBy = "country", orphanRemoval = true)
    private List<Branch> branches;

    @OneToMany(mappedBy = "country", orphanRemoval = true)
    private List<Client> clients;

    @OneToMany(mappedBy = "country", orphanRemoval = true)
    private List<Recipient> recipients;

    public Country() {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
