package pl.pioro.shipmentregister.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "projects")
@ApiModel(description = "Projects in the company")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 20)
    @ApiModelProperty(notes = "min 2 characters")
    @Column(unique = true)
    private String number;

    @NotBlank
    @Size(min = 2, max = 150)
    @ApiModelProperty(notes = "min 2 characters")
    private String name;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @NotNull
    private Client client;

    @ManyToOne
    @JoinColumn(name = "project_status_id")
    @NotNull
    private ProjectStatus projectStatus;

    @NotNull
    private LocalDate startDate;

    private LocalDate endDate;

    @Column(name = "is_active")
    @ColumnDefault("1")
    private Boolean isActive = true;

    @OneToMany(mappedBy = "project", orphanRemoval = true)
    private List<Shipment> shipments;

    public Project() {
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

}
