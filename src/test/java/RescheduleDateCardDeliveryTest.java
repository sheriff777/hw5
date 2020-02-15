
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;


public class RescheduleDateCardDeliveryTest {

    SelenideElement form = $("form");
    SelenideElement cityChoose = form.$("[data-test-id=city] input");
    SelenideElement cityOK = $(".menu");
    SelenideElement dateChoose = form.$("[data-test-id=date] input");
    SelenideElement nameRandom = form.$("[data-test-id=name] input");
    SelenideElement phoneRandom = form.$("[data-test-id=phone] input");
    SelenideElement agreementCheck = form.$("[data-test-id=agreement]");
    SelenideElement button = $$("button").find(exactText("Запланировать"));
    SelenideElement notificationSuccess = $("[data-test-id='success-notification']");
    SelenideElement rescheduleNotification = $("[data-test-id= 'replan-notification']");
    SelenideElement planButton = $(byText("Перепланировать"));


    private Faker faker;

    @BeforeEach
    void setUpAll() {
        faker = new Faker(new Locale("ru"));
    }

    @BeforeEach
    void openHost() {
        open("http://localhost:9999");
    }

    @Test
    void shouldRescheduleDateCardDelivery() {
        String randomCity = Generator.getAnyCapital();
        UserName userFullName = Generator.randomNameAndSurname();
        String futureDay = Generator.getFutureDate(4);
        String phoneNumber = Generator.randomPhoneNumber();

        cityChoose.setValue(randomCity);
        cityOK.waitUntil(exist, 5000).click();
        dateChoose.doubleClick().sendKeys(Keys.BACK_SPACE);
        dateChoose.setValue(futureDay);
        nameRandom.setValue(String.valueOf(userFullName));
        phoneRandom.setValue(phoneNumber);
        agreementCheck.click();
        button.click();
        notificationSuccess.waitUntil(visible, 15000);
        dateChoose.doubleClick().sendKeys(Keys.BACK_SPACE);
        String newFutureDay = Generator.getFutureDate(7);
        dateChoose.setValue(newFutureDay);
        button.click();
        rescheduleNotification.waitUntil(visible, 15000);
        planButton.click();
        rescheduleNotification.waitUntil(exist, 15000);
    }
}