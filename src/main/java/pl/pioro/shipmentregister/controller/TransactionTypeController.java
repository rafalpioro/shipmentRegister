package pl.pioro.shipmentregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.pioro.shipmentregister.entity.ProjectStatus;
import pl.pioro.shipmentregister.entity.TransactionType;
import pl.pioro.shipmentregister.exception.SourceNotFoundException;
import pl.pioro.shipmentregister.repository.TransactionTypeRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;

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
    @ResponseStatus(value = HttpStatus.CREATED)
    public TransactionType create(@Valid @RequestBody TransactionType transactionType){
        return transactionTypeRepository.save(transactionType);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") int id){
        if(transactionTypeRepository.findById(id) == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        transactionTypeRepository.deleteById(id);
    }

    @GetMapping(path = "/{id}")
    public TransactionType findById(@PathVariable("id") int id){
        TransactionType transactionType = transactionTypeRepository.findById(id);
        if(transactionType == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        return transactionType;
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public TransactionType updateTransactionType(@PathVariable("id") int id, @Valid @RequestBody TransactionType transactionType) {
        TransactionType transactionTypeUpdated = transactionTypeRepository.findById(id);
        if(transactionTypeUpdated == null) throw new SourceNotFoundException("Source do not found: id= "+ id);
        transactionTypeUpdated.setName(transactionType.getName());

        return this.transactionTypeRepository.save(transactionTypeUpdated);
    }
}
