package pl.pioro.shipmentregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import pl.pioro.shipmentregister.entity.Recipient;
import pl.pioro.shipmentregister.repository.RecipientRepository;

import javax.transaction.Transactional;

@RestController
@Transactional
@CrossOrigin
@RequestMapping(path = "/admin-all", produces = "application/json")
public class AdminRecipientController {

    @Autowired
    private RecipientRepository recipientRepository;

    @GetMapping("/recipients")
    public Iterable<Recipient> findAllRecipients(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "size", required = false) String size) {
        if(page != null && size != null) {
            PageRequest pageRequest = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
            return recipientRepository.findAll(pageRequest);
        } else {
            return recipientRepository.findAll();
        }
    }
}
