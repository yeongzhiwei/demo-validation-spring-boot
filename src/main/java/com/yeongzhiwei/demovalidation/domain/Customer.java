package com.yeongzhiwei.demovalidation.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.yeongzhiwei.demovalidation.pattern.DateTimeString;
import com.yeongzhiwei.demovalidation.pattern.NoSpecialCharacters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @NotEmpty
    @NoSpecialCharacters
    public String name;

    @Pattern(regexp = "^[\\d]+$")
    public String age;

    @DateTimeString
    public String joined;

    @DateTimeString(format = "yyyyMMdd")
    public String dateOfBirth;

}   