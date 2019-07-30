package pl.pioro.shipmentregister.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
public class PasswordGenerator {

    public String generatePassword() {
        return RandomStringUtils.random(8, true, true);
    }
}
