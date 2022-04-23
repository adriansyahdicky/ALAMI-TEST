package com.id.cooperative.validator;

import com.id.cooperative.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class ContactCannotExistsValidator implements
        ConstraintValidator<ContactCannotExists, String> {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public void initialize(ContactCannotExists constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return contactRepository.existsById(s);
    }
}
