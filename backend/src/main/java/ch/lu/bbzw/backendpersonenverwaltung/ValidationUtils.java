package ch.lu.bbzw.backendpersonenverwaltung;

import ch.lu.bbzw.backendpersonenverwaltung.dto.in.InAddressDto;
import ch.lu.bbzw.backendpersonenverwaltung.dto.in.InCreatePersonDto;
import ch.lu.bbzw.backendpersonenverwaltung.dto.in.InEditPersonDto;
import ch.lu.bbzw.backendpersonenverwaltung.dto.in.InEditSelfDto;
import ch.lu.bbzw.backendpersonenverwaltung.dto.out.OutValidationAnswerDto;

import java.util.HashSet;
import java.util.Set;


// Brace yourselves for some absolutely TOP NOTCH functional programming
public class ValidationUtils{

    private static OutValidationAnswerDto validateFirstname(String firstname){
        return isNullOrBlank(firstname) ? OutValidationAnswerDto.FIRSTNAME_INVALID : null;
    }

    private static OutValidationAnswerDto validateLastname(String lastname){
        return isNullOrBlank(lastname) ? OutValidationAnswerDto.LASTNAME_INVALID : null;
    }

    private static OutValidationAnswerDto validateEmail(String email){
        return isNullOrBlank(email) || ! email.trim().matches("^(.+)@(.+)$") ? OutValidationAnswerDto.EMAIL_INVALID : null;
    }

    private static OutValidationAnswerDto validateAddressStreet(String street){
        return isNullOrBlank(street) || street.trim().length() < 3 ? OutValidationAnswerDto.ADDRESS_STREET_INVALID : null;
    }

    private static OutValidationAnswerDto validateAddressNumber(String number){
        return isNullOrBlank(number) ? OutValidationAnswerDto.ADDRESS_NUMBER_INVALID : null;
    }

    private static OutValidationAnswerDto validateAddressZipcode(String zipcode){
        return isNullOrBlank(zipcode) || zipcode.trim().length() < 4 ? OutValidationAnswerDto.ADDRESS_ZIPCODE_INVALID : null;
    }

    private static OutValidationAnswerDto validateAddressCity(String city){
        return isNullOrBlank(city) ? OutValidationAnswerDto.ADDRESS_CITY_INVALID : null;
    }

    private static Set<OutValidationAnswerDto> validateAddress(InAddressDto addressDto){
        Set<OutValidationAnswerDto> validationAnswerDtoSet = new HashSet<>();
        validationAnswerDtoSet = addIfNotNull(validationAnswerDtoSet, validateAddressStreet(addressDto.getStreet()));
        validationAnswerDtoSet = addIfNotNull(validationAnswerDtoSet, validateAddressNumber(addressDto.getNumber()));
        validationAnswerDtoSet = addIfNotNull(validationAnswerDtoSet, validateAddressZipcode(addressDto.getZipcode()));
        validationAnswerDtoSet = addIfNotNull(validationAnswerDtoSet, validateAddressCity(addressDto.getCity()));

        return validationAnswerDtoSet;
    }

    private static OutValidationAnswerDto validatePosition(String position){
        return isNullOrBlank(position) ? OutValidationAnswerDto.POSITION_INVALID : null;
    }

    private static OutValidationAnswerDto validatePhonenumber(String phonenumber){
        return isNullOrBlank(phonenumber) || phonenumber.trim().length() < 10 ? OutValidationAnswerDto.PHONENUMBER_INVALID : null;
    }

    private static OutValidationAnswerDto validateIsAdmin(boolean admin){
        // The completionist
        return null;
    }

    private static OutValidationAnswerDto validateUsername(String username){
        return isNullOrBlank(username) || username.trim().length() < 3 ? OutValidationAnswerDto.USERNAME_INVALID : null;
    }

    public static Set<OutValidationAnswerDto> validateEditPersonDto(InEditPersonDto editPersonDto){
        Set<OutValidationAnswerDto> validationAnswerDtoSet = new HashSet<>();
        validationAnswerDtoSet = addIfNotNull(validationAnswerDtoSet, validateFirstname(editPersonDto.getFirstname()));
        validationAnswerDtoSet = addIfNotNull(validationAnswerDtoSet, validateLastname(editPersonDto.getLastname()));
        validationAnswerDtoSet = addIfNotNull(validationAnswerDtoSet, validateEmail(editPersonDto.getEmail()));
        validationAnswerDtoSet.addAll(validateAddress(editPersonDto.getAddress()));
        validationAnswerDtoSet = addIfNotNull(validationAnswerDtoSet, validatePosition(editPersonDto.getPosition()));
        validationAnswerDtoSet = addIfNotNull(validationAnswerDtoSet, validatePhonenumber(editPersonDto.getPhonenumber()));
        validationAnswerDtoSet = addIfNotNull(validationAnswerDtoSet, validateIsAdmin(editPersonDto.isAdmin()));

        return validationAnswerDtoSet;
    }

    public static Set<OutValidationAnswerDto> validateCreatePersonDto(InCreatePersonDto createPersonDto){
        Set<OutValidationAnswerDto> validationAnswerDtoSet = new HashSet<>();
        validationAnswerDtoSet = addIfNotNull(validationAnswerDtoSet, validateFirstname(createPersonDto.getFirstname()));
        validationAnswerDtoSet = addIfNotNull(validationAnswerDtoSet, validateLastname(createPersonDto.getLastname()));
        validationAnswerDtoSet = addIfNotNull(validationAnswerDtoSet, validateEmail(createPersonDto.getEmail()));
        validationAnswerDtoSet.addAll(validateAddress(createPersonDto.getAddress()));
        validationAnswerDtoSet = addIfNotNull(validationAnswerDtoSet, validatePosition(createPersonDto.getPosition()));
        validationAnswerDtoSet = addIfNotNull(validationAnswerDtoSet, validatePhonenumber(createPersonDto.getPhonenumber()));
        validationAnswerDtoSet = addIfNotNull(validationAnswerDtoSet, validateIsAdmin(createPersonDto.isAdmin()));
        validationAnswerDtoSet = addIfNotNull(validationAnswerDtoSet, validateUsername(createPersonDto.getUsername()));

        return validationAnswerDtoSet;
    }

    public static Set<OutValidationAnswerDto> validateEditSelfDtoForUser(InEditSelfDto editSelfDto){
        Set<OutValidationAnswerDto> validationAnswerDtoSet = new HashSet<>();
        validateEditSelfDto(validationAnswerDtoSet, editSelfDto);

        return validationAnswerDtoSet;
    }

    public static Set<OutValidationAnswerDto> validateEditSelfDtoForAdmin(InEditSelfDto editSelfDto){
        Set<OutValidationAnswerDto> validationAnswerDtoSet = new HashSet<>();
        validateEditSelfDto(validationAnswerDtoSet,editSelfDto);
        validationAnswerDtoSet = addIfNotNull(validationAnswerDtoSet, validatePosition(editSelfDto.getPosition()));
        validationAnswerDtoSet = addIfNotNull(validationAnswerDtoSet, validateUsername(editSelfDto.getUsername()));


        return validationAnswerDtoSet;
    }

    private static void validateEditSelfDto(Set<OutValidationAnswerDto> validationAnswerDtoSet, InEditSelfDto editSelfDto){
        validationAnswerDtoSet = addIfNotNull(validationAnswerDtoSet, validateFirstname(editSelfDto.getFirstname()));
        validationAnswerDtoSet = addIfNotNull(validationAnswerDtoSet, validateLastname(editSelfDto.getLastname()));
        validationAnswerDtoSet.addAll(validateAddress(editSelfDto.getAddress()));
        validationAnswerDtoSet = addIfNotNull(validationAnswerDtoSet, validateEmail(editSelfDto.getEmail()));
        validationAnswerDtoSet = addIfNotNull(validationAnswerDtoSet, validatePhonenumber(editSelfDto.getPhonenumber()));
    }


    // Holy shit this is bad
    private static Set<OutValidationAnswerDto> addIfNotNull(Set<OutValidationAnswerDto> validationAnswerDtoSet, OutValidationAnswerDto toAdd){
        if(toAdd != null){
            validationAnswerDtoSet.add(toAdd);
        }

        return validationAnswerDtoSet;
    }

    // Did I just not find the default implementation of this?
    private static boolean isNullOrBlank(String s){
        return s == null || s.trim().isEmpty();
    }

}
