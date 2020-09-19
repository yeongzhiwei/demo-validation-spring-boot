package com.yeongzhiwei.demovalidation;

import javax.validation.Valid;

import com.yeongzhiwei.demovalidation.domain.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidationController {

    @Autowired
    private ObjectValidator validator;

    @Autowired
    private ValidationService validationService;

    @PostMapping("/valid")
    public ResponseEntity<String> valid(@RequestBody @Valid final Customer customer) {
        // @Valid will throw org.springframework.web.bind.MethodArgumentNotValidException if customer is not valid
        String response = customer.toString();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/validate")
    public ResponseEntity<String> validate(@RequestBody final Customer customer) {
        String error = validator.validate(customer);
        String response = error.isEmpty() ? customer.toString() : error;
        HttpStatus httpStatus = error.isEmpty() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(response, httpStatus);
    }

    @PostMapping("/validated")
    public ResponseEntity<String> validated(@RequestBody final Customer customer) {
        // Downstream ValidationService will throw javax.validation.ConstraintViolationException if customer is not valid
        validationService.validate(customer);
        String response = customer.toString();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}