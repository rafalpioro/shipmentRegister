package pl.pioro.shipmentregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.pioro.shipmentregister.entity.ProjectStatus;
import pl.pioro.shipmentregister.entity.TransactionType;
import pl.pioro.shipmentregister.repository.TransactionTypeRepository;

import javax.transaction.Transactional;

@RestController
@Transactional
@CrossOrigin
@RequestMapping(path = "admin/transactiontypes", produces = "application/json")
public class TransactionTypeController {

    @Autowired
    private TransactionTypeRepository transactionTypeRepository;

    @GetMapping
    public Iterable<TransactionType> findAll() {
        return transactionTypeRepository.findAll();
    }

    @PostMapping(consumes = "application/json")
    public TransactionType create(@RequestBody TransactionType transactionType){
        return transactionTypeRepository.save(transactionType);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") int id){
        transactionTypeRepository.deleteById(id);
    }

    @GetMapping(path = "/{id}")
    public TransactionType findById(@PathVariable("id") int id){
        return transactionTypeRepository.findById(id);
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public TransactionType updateTransactionType(@PathVariable("id") int id, @RequestBody TransactionType transactionType) {
        TransactionType transactionTypeUpdated = transactionTypeRepository.findById(id);
        transactionTypeUpdated.setName(transactionType.getName());

        return this.transactionTypeRepository.save(transactionTypeUpdated);
    }
}
