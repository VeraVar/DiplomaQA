package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

public class OfferPage {
    private final SelenideElement buyButton = $$("button").find((exactText("Купить")));
    private final SelenideElement creditButton = $$("button").find((exactText("Купить в кредит")));
    private final SelenideElement paymentByCard = $$("h3").find((exactText("Оплата по карте")));
    private final SelenideElement paymentByCredit = $$("h3").find((exactText("Кредит по данным карты")));

    public void openPaymentByCard() {
        buyButton.click();
        paymentByCard.shouldBe(visible);
    }

    public void openPaymentByCredit() {
        creditButton.click();
        paymentByCredit.shouldBe(visible);
    }
}