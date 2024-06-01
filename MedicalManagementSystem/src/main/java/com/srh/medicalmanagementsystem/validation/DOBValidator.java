package com.srh.medicalmanagementsystem.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DOBValidator implements ConstraintValidator<PastOrPresentDate, Date> {

    @Override
    public boolean isValid(Date dob, ConstraintValidatorContext context){

        LocalDate localDob=dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return !(localDob.isAfter(LocalDate.now()));
    }


}
