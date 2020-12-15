package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.SQLData;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import pages.OfferPage;
import pages.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static data.DataHelper.*;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentByCreditTest {
    OfferPage offerPage = new OfferPage();
    PaymentPage paymentPage = new PaymentPage();

    @BeforeAll
    static void addReport() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUp() {
        open(System.getProperty("sut.url"));
        offerPage.openPaymentByCredit();
    }

    @AfterEach
    void cleanDB() {
        SQLData.cleanDB();
    }

    @AfterAll
    static void removeReport() {
        SelenideLogger.removeListener("allure");
    }

    // Valid data
    @Test
    public void shouldSuccessCreditByValidApprovedCard() {
        val cardInfo = getCardInfoWithAllValidDateApprovedCard();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitSuccessNotification();
        val expectedStatus = "APPROVED";
        val actualStatus = SQLData.getStatusLastCreditTransaction();
        assertEquals(expectedStatus, actualStatus);
        val creditId = SQLData.getCreditId();
        assertNotNull(creditId);
        val bankId = SQLData.getBankId();
        assertNotNull(bankId);
    }

    @Test
    public void shouldErrorCreditByValidDeclinedCard() {
        val cardInfo = getCardInfoWithAllValidDateDeclinedCard();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitErrorNotification();
        val expectedStatus = "DECLINED";
        val actualStatus = SQLData.getStatusLastCreditTransaction();
        assertEquals(expectedStatus, actualStatus);
        val creditId = SQLData.getCreditId();
        assertNull(creditId);
        val bankId = SQLData.getBankId();
        assertNotNull(bankId);
    }

    // Invalid data
    // Card number
    @Test
    public void shouldWrongFormatNotificationIfCreditBy1ZeroCardNumber() {
        val cardInfo = getCardInfoWith1ZeroCardNumber();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditBy16ZeroCardNumber() {
        val cardInfo = getCardInfoWith16ZeroCardNumber();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditByIncompleteCardNumber() {
        val cardInfo = getCardInfoWithIncompleteCardNumber();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditByEngLetterCardNumber() {
        val cardInfo = getCardInfoWithEngLetterCardNumber();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditByRusLetterCardNumber() {
        val cardInfo = getCardInfoWithRusLetterCardNumber();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditBySymbolCardNumber() {
        val cardInfo = getCardInfoWithSymbolCardNumber();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldFieldRequiredNotificationIfCreditByEmptyCardNumber() {
        val cardInfo = getCardInfoWithEmptyCardNumber();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitFieldRequiredNotification();
    }

    // Card month
    @Test
    public void shouldWrongFormatNotificationIfCreditBy1ZeroCardMonth() {
        val cardInfo = getCardInfoWith1ZeroCardMonth();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditBy2ZeroCardMonth() {
        val cardInfo = getCardInfoWith2ZeroCardMonth();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldWrongExpirationDateNotificationIfCreditByOverageCardMonth() {
        val cardInfo = getCardInfoWithOverageCardMonth();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongExpirationDateNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditByIncompleteCardMonth() {
        val cardInfo = getCardInfoWithIncompleteCardMonth();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditByEngLetterCardMonth() {
        val cardInfo = getCardInfoWithEngLetterCardMonth();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditByRusLetterCardMonth() {
        val cardInfo = getCardInfoWithRusLetterCardMonth();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditBySymbolCardMonth() {
        val cardInfo = getCardInfoWithSymbolCardMonth();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldFieldRequiredNotificationIfCreditByEmptyCardMonth() {
        val cardInfo = getCardInfoWithEmptyCardMonth();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitFieldRequiredNotification();
    }

    // Card year
    @Test
    public void shouldWrongFormatNotificationIfCreditBy1ZeroCardYear() {
        val cardInfo = getCardInfoWith1ZeroCardYear();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldCardExpiredNotificationIfCreditBy2ZeroCardYear() {
        val cardInfo = getCardInfoWith2ZeroCardYear();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitCardExpiredNotification();
    }

    @Test
    public void shouldWrongExpirationDateNotificationIfCreditByOverageCardYear() {
        val cardInfo = getCardInfoWithOverageCardYear();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongExpirationDateNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditByIncompleteCardYear() {
        val cardInfo = getCardInfoWithIncompleteCardYear();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditByEngLetterCardYear() {
        val cardInfo = getCardInfoWithEngLetterCardYear();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditByRusLetterCardYear() {
        val cardInfo = getCardInfoWithRusLetterCardYear();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditBySymbolCardYear() {
        val cardInfo = getCardInfoWithSymbolCardYear();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldFieldRequiredNotificationIfCreditByEmptyCardYear() {
        val cardInfo = getCardInfoWithEmptyCardYear();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitFieldRequiredNotification();
    }

    // Card holder
    @Test
    public void shouldWrongFormatNotificationIfCreditByOverageCardHolder() {
        val cardInfo = getCardInfoWithOverageCardHolder();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditByIncompleteCardHolder() {
        val cardInfo = getCardInfoWithIncompleteCardHolder();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditByRusCardHolder() {
        val cardInfo = getCardInfoWithRusCardHolder();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditByNumeralCardHolder() {
        val cardInfo = getCardInfoWithNumeralCardHolder();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditBySymbolCardHolder() {
        val cardInfo = getCardInfoWithSymbolCardHolder();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldFieldRequiredNotificationIfCreditByEmptyCardHolder() {
        val cardInfo = getCardInfoWithEmptyCardHolder();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitFieldRequiredNotification();
    }

    // Card cvv
    @Test
    public void shouldWrongFormatNotificationIfCreditBy3ZeroCardCvv() {
        val cardInfo = getCardInfoWith3ZeroCardCvv();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditByIncompleteCardCvv() {
        val cardInfo = getCardInfoWithIncompleteCardCvv();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditByEngLetterCardCvv() {
        val cardInfo = getCardInfoWithEngLetterCardCvv();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditByRusLetterCardCvv() {
        val cardInfo = getCardInfoWithRusLetterCardCvv();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditBySymbolCardCvv() {
        val cardInfo = getCardInfoWithSymbolCardCvv();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldFieldRequiredNotificationIfCreditByEmptyCardCvv() {
        val cardInfo = getCardInfoWithEmptyCardCvv();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitFieldRequiredNotification();
    }
}