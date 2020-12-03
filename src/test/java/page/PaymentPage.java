package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentPage {
    private final SelenideElement cardNumberField = $$(".input__inner").findBy(text("Номер карты"))
            .$(".input__control");
    private final SelenideElement cardMonthField = $$(".input__inner").findBy(text("Месяц"))
            .$(".input__control");
    private final SelenideElement cardYearField = $$(".input__inner").findBy(text("Год"))
            .$(".input__control");
    private final SelenideElement cardHolderField = $$(".input__inner").findBy(text("Владелец"))
            .$(".input__control");
    private final SelenideElement cardCvvField = $$(".input__inner").findBy(text("CVC/CVV"))
            .$(".input__control");
    private final SelenideElement continueButton = $$("button").findBy(text("Продолжить"));
    private final SelenideElement successNotification = $(byText("Операция одобрена Банком"));
    private final SelenideElement errorNotification = $(byText("Ошибка! Банк отказал в проведении операции"));
    private final SelenideElement wrongFormatNotification = $(byText("Неверный формат"));
    private final SelenideElement fieldRequiredNotification = $(byText("Поле обязательно для заполнения"));
    private final SelenideElement wrongExpirationDateNotification = $(byText("Неверно указан срок действия карты"));
    private final SelenideElement cardExpiredNotification = $(byText("Истёк срок действия карты"));

    public void setCardInfo(DataHelper.CardInfo cardInfo) {
        cardNumberField.setValue(cardInfo.getCardNumber());
        cardMonthField.setValue(cardInfo.getCardMonth());
        cardYearField.setValue(cardInfo.getCardYear());
        cardHolderField.setValue(cardInfo.getCardHolder());
        cardCvvField.setValue(cardInfo.getCardCvv());
        continueButton.click();
    }

    public void getSuccessNotification() {
        successNotification.waitUntil(Condition.visible, 15000);
    }

    public void getErrorNotification() {
        errorNotification.waitUntil(Condition.visible, 15000);
    }

    public void getWrongFormatNotification() {
        wrongFormatNotification.waitUntil(Condition.visible, 15000);
    }

    public void getFieldRequiredNotification() {
        fieldRequiredNotification.waitUntil(Condition.visible, 15000);
    }

    public void getWrongExpirationDateNotification() {
        wrongExpirationDateNotification.waitUntil(Condition.visible, 15000);
    }

    public void getCardExpiredNotification() {
        cardExpiredNotification.waitUntil(Condition.visible, 15000);
    }
}