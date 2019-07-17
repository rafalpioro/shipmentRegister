package pl.pioro.shipmentregister.entity;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "shipments")
@ApiModel(description = "Shipments in the company")
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    @NotNull
    private Recipient recipient;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    @NotNull
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "project_id")
    @NotNull
    private Project project;

    @Column(name = "send_date")
    private LocalDate sendDate;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @ManyToOne
    @JoinColumn(name = "shipment_status_id")
    @NotNull
    private ShipmentStatus shipmentStatus;

    @ManyToOne
    @JoinColumn(name = "carrier_id")
    private Carrier carrier;

    @Column(name = "is_active")
    @ColumnDefault("1")
    private Boolean isActive= true;

    @ColumnDefault("0")
    private Boolean pod;

    @Size(min = 19, max = 19)
    private String mrn;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @ManyToOne
    @JoinColumn(name = "transaction_type_id")
    @NotNull
    private TransactionType transactionType;

    public Shipment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public LocalDate getSendDate() {
        return sendDate;
    }

    public void setSendDate(LocalDate sendDate) {
        this.sendDate = sendDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public ShipmentStatus getShipmentStatus() {
        return shipmentStatus;
    }

    public void setShipmentStatus(ShipmentStatus shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }

    public Carrier getCarrier() {
        return carrier;
    }

    public void setCarrier(Carrier carrier) {
        this.carrier = carrier;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getPod() {
        return pod;
    }

    public void setPod(Boolean pod) {
        this.pod = pod;
    }

    public String getMrn() {
        return mrn;
    }

    public void setMrn(String mrn) {
        this.mrn = mrn;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
