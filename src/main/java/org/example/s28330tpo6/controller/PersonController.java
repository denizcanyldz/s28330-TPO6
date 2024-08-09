package org.example.s28330tpo6.controller;

import org.example.s28330tpo6.dto.PersonDTO;
import org.example.s28330tpo6.service.FakeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Controller
public class PersonController {
    @Autowired
    private FakeDataService fakeDataService;

    @GetMapping("/")
    public String showForm(Model model) {
        List<Locale> supportedLanguages = new ArrayList<>(Arrays.asList(
                Locale.forLanguageTag("en-US"), // English - US
                Locale.forLanguageTag("fr-FR"), // French
                Locale.forLanguageTag("de-DE"), // German
                Locale.forLanguageTag("es-ES"), // Spanish
                Locale.forLanguageTag("it-IT"), // Italian
                Locale.forLanguageTag("ja-JP"), // Japanese
                Locale.forLanguageTag("pl-PL"), // Polish
                Locale.forLanguageTag("uk-UA"), // Ukrainian
                Locale.forLanguageTag("he-IL"), // Hebrew
                Locale.forLanguageTag("da-DK")  // Danish
        ));
        model.addAttribute("languages", supportedLanguages);
        return "index";
    }

    @RequestMapping("/generate")
    public String generateData(
            @RequestParam(name = "count", defaultValue = "10") int count,
            @RequestParam(name = "language", defaultValue = "en-US") String language,
            @RequestParam(name = "includeAddress", defaultValue = "false") boolean includeAddress,
            @RequestParam(name = "includeUniversity", defaultValue = "false") boolean includeUniversity,
            @RequestParam(name = "includeCountryOrigin", defaultValue = "false") boolean includeCountryOrigin,
            @RequestParam(name = "includeJobTitle", defaultValue = "false") boolean includeJobTitle,
            @RequestParam(name = "includePhoneNumber", defaultValue = "false") boolean includePhoneNumber,
            @RequestParam(name = "includeJobExperience", defaultValue = "false") boolean includeJobExperience,
            @RequestParam(name = "includeHobbies", defaultValue = "false") boolean includeHobbies,
            @RequestParam(name = "includeSocialSecurityNumber", defaultValue = "false") boolean includeSocialSecurityNumber,
            @RequestParam(name = "includeWebsite", defaultValue = "false") boolean includeWebsite,
            Model model) {
        try {
            if (count <= 0) {
                model.addAttribute("countError", "Count must be greater than zero and must be integer type.");
            }
            Locale locale = Locale.forLanguageTag(language);
            List<PersonDTO> persons = fakeDataService.generateFakePersons(
                    (int)count, locale, includeAddress, includeUniversity, includeCountryOrigin, includeJobTitle, includePhoneNumber, includeJobExperience, includeHobbies, includeSocialSecurityNumber, includeWebsite);
            List<String> displayHeaders = getLocalizedTableHeaders(Locale.forLanguageTag(language), includeAddress, includeUniversity, includeCountryOrigin, includeJobTitle, includePhoneNumber, includeJobExperience, includeHobbies, includeSocialSecurityNumber, includeWebsite);
            model.addAttribute("tableHeaders", displayHeaders);
            model.addAttribute("persons", persons);
            return "index";
        }catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
            return "index";
        }
}


    private List<String> getLocalizedTableHeaders(Locale locale, boolean includeAddress, boolean includeUniversity, boolean includeCountryOrigin, boolean includeJobTitle, boolean includePhoneNumber, boolean includeJobExperience, boolean includeHobbies, boolean includeSocialSecurityNumber, boolean includeWebsite) {
        List<String> displayHeaders = new ArrayList<>();

        switch (locale.getLanguage()) {
            case "fr":
                displayHeaders.addAll(Arrays.asList("Prénom", "Nom", "Date de naissance"));
                break;
            case "de":
                displayHeaders.addAll(Arrays.asList("Vorname", "Nachname", "Geburtsdatum"));
                break;
            case "es":
                displayHeaders.addAll(Arrays.asList("Nombre", "Apellido", "Fecha de nacimiento"));
                break;
            case "it":
                displayHeaders.addAll(Arrays.asList("Nome", "Cognome", "Data di nascita"));
                break;
            case "ja":
                displayHeaders.addAll(Arrays.asList("名", "姓", "生年月日"));
                break;
            case "pl":
                displayHeaders.addAll(Arrays.asList("Imię", "Nazwisko", "Data urodzenia"));
                break;
            case "uk":
                displayHeaders.addAll(Arrays.asList("Ім'я", "Прізвище", "Дата народження"));
                break;
            case "he":
                displayHeaders.addAll(Arrays.asList("שם פרטי", "שם משפחה", "תאריך לידה"));
                break;
            case "da":
                displayHeaders.addAll(Arrays.asList("Fornavn", "Efternavn", "Fødselsdato"));
                break;
            default:
                displayHeaders.addAll(Arrays.asList("First Name", "Last Name", "Date of Birth"));
        }

        if (includeAddress) {
            switch (locale.getLanguage()) {
                case "fr": displayHeaders.add("Adresse"); break;
                case "de": displayHeaders.add("Adresse"); break;
                case "es": displayHeaders.add("Dirección"); break;
                case "it": displayHeaders.add("Indirizzo"); break;
                case "ja": displayHeaders.add("住所"); break;
                case "pl": displayHeaders.add("Adres"); break;
                case "uk": displayHeaders.add("Адреса"); break;
                case "he": displayHeaders.add("כתובת"); break;
                case "da": displayHeaders.add("Adresse"); break;
                default: displayHeaders.add("Address");
            }
        }

        // University
        if (includeUniversity) {
            switch (locale.getLanguage()) {
                case "fr": displayHeaders.add("Université"); break;
                case "de": displayHeaders.add("Universität"); break;
                case "es": displayHeaders.add("Universidad"); break;
                case "it": displayHeaders.add("Università"); break;
                case "ja": displayHeaders.add("大学"); break;
                case "pl": displayHeaders.add("Uniwersytet"); break;
                case "uk": displayHeaders.add("Університет"); break;
                case "he": displayHeaders.add("אוניברסיטה"); break;
                case "da": displayHeaders.add("Universitet"); break;
                default: displayHeaders.add("University");
            }
        }

        //Country of Origin
        if (includeCountryOrigin) {
            switch (locale.getLanguage()) {
                case "fr": displayHeaders.add("Pays d'origine"); break;
                case "de": displayHeaders.add("Herkunftsland"); break;
                case "es": displayHeaders.add("País de origen"); break;
                case "it": displayHeaders.add("Paese di origine"); break;
                case "ja": displayHeaders.add("出身国"); break;
                case "pl": displayHeaders.add("Kraj pochodzenia"); break;
                case "uk": displayHeaders.add("Країна походження"); break;
                case "he": displayHeaders.add("מדינת המוצא"); break;
                case "da": displayHeaders.add("Oprindelsesland"); break;
                default: displayHeaders.add("Country of Origin");
            }
        }

        // Job Title
        if (includeJobTitle) {
            switch (locale.getLanguage()) {
                case "fr": displayHeaders.add("Titre du poste"); break;
                case "de": displayHeaders.add("Berufsbezeichnung"); break;
                case "es": displayHeaders.add("Título de trabajo"); break;
                case "it": displayHeaders.add("Titolo di lavoro"); break;
                case "ja": displayHeaders.add("職種"); break;
                case "pl": displayHeaders.add("Stanowisko"); break;
                case "uk": displayHeaders.add("Посада"); break;
                case "he": displayHeaders.add("כותרת תפקיד"); break;
                case "da": displayHeaders.add("Jobtitel"); break;
                default: displayHeaders.add("Job Title");
            }
        }

        // Phone Number
        if (includePhoneNumber) {
            switch (locale.getLanguage()) {
                case "fr": displayHeaders.add("Numéro de téléphone"); break;
                case "de": displayHeaders.add("Telefonnummer"); break;
                case "es": displayHeaders.add("Número de teléfono"); break;
                case "it": displayHeaders.add("Numero di telefono"); break;
                case "ja": displayHeaders.add("電話番号"); break;
                case "pl": displayHeaders.add("Numer telefonu"); break;
                case "uk": displayHeaders.add("Номер телефону"); break;
                case "he": displayHeaders.add("מספר טלפון"); break;
                case "da": displayHeaders.add("Telefonnummer"); break;
                default: displayHeaders.add("Phone Number");
            }
        }

        // Job Experience
        if (includeJobExperience) {
            switch (locale.getLanguage()) {
                case "fr": displayHeaders.add("Expérience professionnelle"); break;
                case "de": displayHeaders.add("Berufserfahrung"); break;
                case "es": displayHeaders.add("Experiencia laboral"); break;
                case "it": displayHeaders.add("Esperienza lavorativa"); break;
                case "ja": displayHeaders.add("職歴"); break;
                case "pl": displayHeaders.add("Doświadczenie zawodowe"); break;
                case "uk": displayHeaders.add("Досвід роботи"); break;
                case "he": displayHeaders.add("ניסיון תעסוקתי"); break;
                case "da": displayHeaders.add("Erhvervserfaring"); break;
                default: displayHeaders.add("Job Experience");
            }
        }
        // Social Security Number
        if (includeSocialSecurityNumber) {
            switch (locale.getLanguage()) {
                case "fr": displayHeaders.add("Numéro de sécurité sociale"); break;
                case "de": displayHeaders.add("Sozialversicherungsnummer"); break;
                case "es": displayHeaders.add("Número de Seguridad Social"); break;
                case "it": displayHeaders.add("Codice fiscale"); break; // Or another appropriate term in Italian
                case "ja": displayHeaders.add("社会保障番号"); break;
                case "pl": displayHeaders.add("Numer PESEL"); break; // Example for Poland
                case "uk": displayHeaders.add("Номер соціального страхування"); break;
                case "he": displayHeaders.add("מספר ביטוח לאומי"); break;
                case "da": displayHeaders.add("CPR-nummer"); break; // Example for Denmark
                default: displayHeaders.add("Social Security Number");
            }
        }

        // Website
        if (includeWebsite) {
            switch (locale.getLanguage()) {
                case "fr": displayHeaders.add("Site web"); break;
                case "de": displayHeaders.add("Webseite"); break;
                case "es": displayHeaders.add("Sitio web"); break;
                case "it": displayHeaders.add("Sito web"); break;
                case "ja": displayHeaders.add("ウェブサイト"); break;
                case "pl": displayHeaders.add("Strona internetowa"); break;
                case "uk": displayHeaders.add("Веб-сайт"); break;
                case "he": displayHeaders.add("אתר אינטרנט"); break;
                case "da": displayHeaders.add("Internet side"); break;
                default: displayHeaders.add("Website");
            }
        }

        // Hobbies
        if (includeHobbies) {
            switch (locale.getLanguage()) {
                case "fr": displayHeaders.add("Loisirs"); break;
                case "de": displayHeaders.add("Hobbys"); break;
                case "es": displayHeaders.add("Aficiones"); break;
                case "it": displayHeaders.add("Hobby"); break;
                case "ja": displayHeaders.add("趣味"); break;
                case "pl": displayHeaders.add("Hobby"); break;
                case "uk": displayHeaders.add("Хобі"); break;
                case "he": displayHeaders.add("תחביבים"); break;
                case "da": displayHeaders.add("Hobbies"); break;
                default: displayHeaders.add("Hobbies");
            }
        }

        return displayHeaders;
    }



}
