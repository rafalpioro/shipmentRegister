package pl.pioro.shipmentregister.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 150)
    @Column(unique = true)
    private String name;

    @NotNull
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

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Recipient> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<Recipient> recipients) {
        this.recipients = recipients;
    }
}
