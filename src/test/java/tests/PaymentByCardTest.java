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

public class PaymentByCardTest {
    OfferPage offerPage = new OfferPage();
    PaymentPage paymentPage = new PaymentPage();

    @BeforeAll
    static void addReport() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUp() {
        open(System.getProperty("sut.url"));
        offerPage.openPaymentByCard();
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
    public void shouldSuccessPayByValidApprovedCard() {
        val cardInfo = getCardInfoWithAllValidDateApprovedCard();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitSuccessNotification();
        val expectedStatus = "APPROVED";
        val actualStatus = SQLData.getStatusLastPaymentTransaction();
        assertEquals(expectedStatus, actualStatus);
        val paymentId = SQLData.getPaymentId();
        assertNotNull(paymentId);
        val transactionId = SQLData.getTransactionId();
        assertNotNull(transactionId);
    }

    @Test
    public void shouldErrorPayByValidDeclinedCard() {
        val cardInfo = getCardInfoWithAllValidDateDeclinedCard();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitErrorNotification();
        val expectedStatus = "DECLINED";
        val actualStatus = SQLData.getStatusLastPaymentTransaction();
        assertEquals(expectedStatus, actualStatus);
        val paymentId = SQLData.getPaymentId();
        assertNotNull(paymentId);
        val transactionId = SQLData.getTransactionId();
        assertNull(transactionId);
    }

    // Invalid data
    // Card number
    @Test
    public void shouldWrongFormatNotificationIfPayBy1ZeroCardNumber() {
        val cardInfo = getCardInfoWith1ZeroCardNumber();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayBy16ZeroCardNumber() {
        val cardInfo = getCardInfoWith16ZeroCardNumber();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayByIncompleteCardNumber() {
        val cardInfo = getCardInfoWithIncompleteCardNumber();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayByEngLetterCardNumber() {
        val cardInfo = getCardInfoWithEngLetterCardNumber();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayByRusLetterCardNumber() {
        val cardInfo = getCardInfoWithRusLetterCardNumber();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayBySymbolCardNumber() {
        val cardInfo = getCardInfoWithSymbolCardNumber();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldFieldRequiredNotificationIfPayByEmptyCardNumber() {
        val cardInfo = getCardInfoWithEmptyCardNumber();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitFieldRequiredNotification();
    }

    // Card month
    @Test
    public void shouldWrongFormatNotificationIfPayBy1ZeroCardMonth() {
        val cardInfo = getCardInfoWith1ZeroCardMonth();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayBy2ZeroCardMonth() {
        val cardInfo = getCardInfoWith2ZeroCardMonth();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldWrongExpirationDateNotificationIfPayByOverageCardMonth() {
        val cardInfo = getCardInfoWithOverageCardMonth();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongExpirationDateNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayByIncompleteCardMonth() {
        val cardInfo = getCardInfoWithIncompleteCardMonth();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayByEngLetterCardMonth() {
        val cardInfo = getCardInfoWithEngLetterCardMonth();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayByRusLetterCardMonth() {
        val cardInfo = getCardInfoWithRusLetterCardMonth();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayBySymbolCardMonth() {
        val cardInfo = getCardInfoWithSymbolCardMonth();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldFieldRequiredNotificationIfPayByEmptyCardMonth() {
        val cardInfo = getCardInfoWithEmptyCardMonth();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitFieldRequiredNotification();
    }

    // Card year
    @Test
    public void shouldWrongFormatNotificationIfPayBy1ZeroCardYear() {
        val cardInfo = getCardInfoWith1ZeroCardYear();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldCardExpiredNotificationIfPayBy2ZeroCardYear() {
        val cardInfo = getCardInfoWith2ZeroCardYear();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitCardExpiredNotification();
    }

    @Test
    public void shouldWrongExpirationDateNotificationIfPayByOverageCardYear() {
        val cardInfo = getCardInfoWithOverageCardYear();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongExpirationDateNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayByIncompleteCardYear() {
        val cardInfo = getCardInfoWithIncompleteCardYear();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayByEngLetterCardYear() {
        val cardInfo = getCardInfoWithEngLetterCardYear();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayByRusLetterCardYear() {
        val cardInfo = getCardInfoWithRusLetterCardYear();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayBySymbolCardYear() {
        val cardInfo = getCardInfoWithSymbolCardYear();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldFieldRequiredNotificationIfPayByEmptyCardYear() {
        val cardInfo = getCardInfoWithEmptyCardYear();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitFieldRequiredNotification();
    }

    // Card holder
    @Test
    public void shouldWrongFormatNotificationIfPayByOverageCardHolder() {
        val cardInfo = getCardInfoWithOverageCardHolder();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayByIncompleteCardHolder() {
        val cardInfo = getCardInfoWithIncompleteCardHolder();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayByRusCardHolder() {
        val cardInfo = getCardInfoWithRusCardHolder();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayByNumeralCardHolder() {
        val cardInfo = getCardInfoWithNumeralCardHolder();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayBySymbolCardHolder() {
        val cardInfo = getCardInfoWithSymbolCardHolder();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldFieldRequiredNotificationIfPayByEmptyCardHolder() {
        val cardInfo = getCardInfoWithEmptyCardHolder();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitFieldRequiredNotification();
    }

    // Card cvv
    @Test
    public void shouldWrongFormatNotificationIfPayBy3ZeroCardCvv() {
        val cardInfo = getCardInfoWith3ZeroCardCvv();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayByIncompleteCardCvv() {
        val cardInfo = getCardInfoWithIncompleteCardCvv();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayByEngLetterCardCvv() {
        val cardInfo = getCardInfoWithEngLetterCardCvv();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayByRusLetterCardCvv() {
        val cardInfo = getCardInfoWithRusLetterCardCvv();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayBySymbolCardCvv() {
        val cardInfo = getCardInfoWithSymbolCardCvv();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitWrongFormatNotification();
    }

    @Test
    public void shouldFieldRequiredNotificationIfPayByEmptyCardCvv() {
        val cardInfo = getCardInfoWithEmptyCardCvv();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.waitFieldRequiredNotification();
    }
}