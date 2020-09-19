package com.yeongzhiwei.demovalidation;

import javax.validation.Valid;

import com.yeongzhiwei.demovalidation.domain.Customer;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class ValidationService {
    
    public void validate(@Valid Customer customer) {
        // @Validated and @Valid will throw javax.validation.ConstraintViolationException if customer is not valid
    }

}
