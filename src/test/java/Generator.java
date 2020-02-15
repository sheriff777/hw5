import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class Generator {

    public static String randomPhoneNumber() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.regexify("+7[0-9]{10}");
    }

    public static UserName randomNameAndSurname() {
        Faker faker = new Faker(new Locale("ru"));
        return new UserName(
                faker.name().firstName().replaceAll("Ё", "Е").replaceAll("ё", "е"),
                faker.name().lastName().replaceAll("Ё", "Е").replaceAll("ё", "е")
        );
    }

    public static String getFutureDate(int plusDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        LocalDate currentDate = LocalDate.now();
        LocalDate controlDate = currentDate.plusDays(plusDate);
        String formattedControlDate = controlDate.format(formatter);
        return formattedControlDate;
    }

    public static String getAnyCapital() {
        String[] cities = {"Горно-Алтайск", "Якутск", "Ульяновск", "Калининград", "Тюмень", "Тула", "Пермь", "Нальчик", "Махачкала", "Пенза"};
        Random random = new Random();
        int index = random.nextInt(cities.length);
        return (cities[index]);
    }
}