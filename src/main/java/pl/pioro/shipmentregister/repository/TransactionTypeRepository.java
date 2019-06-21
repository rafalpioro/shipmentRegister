package pl.pioro.shipmentregister.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pioro.shipmentregister.entity.TransactionType;

public interface TransactionTypeRepository extends JpaRepository<TransactionType, Integer> {
    TransactionType findById(int id);
}
