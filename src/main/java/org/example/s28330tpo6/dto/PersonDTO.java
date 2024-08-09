package org.example.s28330tpo6.dto;

import java.time.LocalDate;
import java.time.Period;

public class PersonDTO {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String address;
    private String university;
    private String countryOrigin;
    private String jobTitle;
    private String phoneNumber;
    private String jobExperience;
    private String hobbies;
    private String socialSecurityNumber;
    private String website;

    public PersonDTO(String firstName, String lastName, LocalDate dateOfBirth, String... additionalFields) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;

        if (additionalFields != null && additionalFields.length >= 1) {
            this.address = additionalFields[0];
        }
        if (additionalFields != null && additionalFields.length >= 2) {
            this.university = additionalFields[1];
        }
        if (additionalFields != null && additionalFields.length >= 3) {
            this.countryOrigin = additionalFields[2];
        }
        if (additionalFields != null && additionalFields.length >= 4) {
            this.jobTitle = additionalFields[3];
        }
        if (additionalFields != null && additionalFields.length >= 5) {
            this.phoneNumber = additionalFields[4];
        }
        if (additionalFields != null && additionalFields.length >= 6) {
            this.jobExperience = additionalFields[5];
        }
        if (additionalFields != null && additionalFields.length >= 7) {
            this.hobbies = additionalFields[6];
        }
        if (additionalFields != null && additionalFields.length >= 8) {
            this.socialSecurityNumber = additionalFields[7];
        }
        if (additionalFields != null && additionalFields.length >= 9) {
            this.website = additionalFields[8];
        }
    }

    public int getAge(){
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }


    public String getAddress() {
        return address;
    }

    public String getUniversity() {
        return university;
    }

    public String getCountryOrigin() {
        return countryOrigin;
    }

    public String getJobTitle() {
        return jobTitle;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getJobExperience() {
        return jobExperience;
    }

    public String getHobbies() {
        return hobbies;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public String getWebsite() {
        return website;
    }
}
