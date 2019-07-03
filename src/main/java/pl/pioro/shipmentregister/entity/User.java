package pl.pioro.shipmentregister.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.ColumnDefault;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
@ApiModel(description = "Users of the application")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 60)
    @ApiModelProperty(notes = "min 2 characters")
    private String name;

    @NotBlank
    @Size(min = 4, max = 360)
    @JsonIgnore
    private String password;

    @NotBlank
    @Email
    @Column(unique = true)
    @ApiModelProperty(notes = "email must be unique and valid")
    private String email;

    @Column(name = "is_active")
    @ColumnDefault("1")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @NotNull
    private Role role;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Shipment> shipments;


    public User() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
