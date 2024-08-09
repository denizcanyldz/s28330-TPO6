package org.example.s28330tpo6.service;

import net.datafaker.Faker;
import org.example.s28330tpo6.dto.PersonDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class FakeDataService {
    private final Faker defaultFaker = new Faker(new Locale("en-US"));

    public PersonDTO generateFakePersonDTO() {
        LocalDate dateOfBirth = defaultFaker.date().birthdayLocalDate();

        return new PersonDTO(
                defaultFaker.name().firstName(),
                defaultFaker.name().lastName(),
                dateOfBirth,
                generateOptionalFields(true, true, true, true, true,true,true,true,true, defaultFaker)
        );
    }

    public List<PersonDTO> generateFakePersons(int count, Locale locale, boolean includeAddress, boolean includeUniversity, boolean includeCountryOrigin, boolean includeJobTitle, boolean includePhoneNumber,boolean includeJobExperience, boolean includeHobbies, boolean includeSocialSecurityNumber, boolean includeWebsite) {
        Faker faker = new Faker(locale);
        List<PersonDTO> persons = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            LocalDate dateOfBirth = faker.date().birthdayLocalDate();
            persons.add(new PersonDTO(
                    faker.name().firstName(),
                    faker.name().lastName(),
                    dateOfBirth,
                    generateOptionalFields(includeAddress, includeUniversity, includeCountryOrigin, includeJobTitle, includePhoneNumber,includeJobExperience,includeHobbies,includeSocialSecurityNumber,includeWebsite, faker)
            ));
        }

        return persons;
    }

    private String[] generateOptionalFields(boolean includeAddress, boolean includeUniversity, boolean includeCountryOrigin, boolean includeJobTitle, boolean includePhoneNumber,boolean includeJobExperience, boolean includeHobbies, boolean includeSocialSecurityNumber, boolean includeWebsite, Faker faker) {
        List<String> fields = new ArrayList<>();

        if (includeAddress) {
            fields.add(faker.address().fullAddress());
        }
        if (includeUniversity) {
            fields.add(faker.university().name());
        }
        if (includeCountryOrigin) {
            fields.add(faker.country().name());
        }
        if (includeJobTitle) {
            fields.add(faker.job().title());
        }
        if (includePhoneNumber) {
            fields.add(faker.phoneNumber().phoneNumber());
        }
        if (includeJobExperience) {
            fields.add(faker.job().seniority());
        }
        if (includeSocialSecurityNumber) {
            fields.add(faker.idNumber().ssnValid());
        }
        if (includeWebsite) {
            fields.add(faker.internet().url());
        }
        if (includeHobbies) {
            fields.add(faker.lorem().sentence(3));
        }
        return fields.toArray(new String[0]);
    }

}
