package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.SQLData;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.OfferPage;
import page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static data.DataHelper.*;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentPageTest {
    OfferPage offerPage = new OfferPage();
    PaymentPage paymentPage = new PaymentPage();

    @BeforeAll
    static void addReport() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUp() {
        open(System.getProperty("sut.url"));
    }

    @AfterAll
    static void cleanDB() {
        SQLData.cleanDB();
    }

    @AfterAll
    static void removeReport() {
        SelenideLogger.removeListener("allure");
    }

    // Test-cases for payment by card
    // Valid data
    @Test
    public void shouldSuccessPayByValidApprovedCard() {
        offerPage.openPaymentByCard();
        val cardInfo = getCardInfoWithAllValidDateApprovedCard();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getSuccessNotification();
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
        offerPage.openPaymentByCard();
        val cardInfo = getCardInfoWithAllValidDateDeclinedCard();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getErrorNotification();
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
        offerPage.openPaymentByCard();
        val cardInfo = getCardInfoWith1ZeroCardNumber();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayBy16ZeroCardNumber() {
        offerPage.openPaymentByCard();
        val cardInfo = getCardInfoWith16ZeroCardNumber();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayByOverageCardNumber() {
        offerPage.openPaymentByCard();
        val cardInfo = getCardInfoWithOverageCardNumber();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayByIncompleteCardNumber() {
        offerPage.openPaymentByCard();
        val cardInfo = getCardInfoWithIncompleteCardNumber();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayByEngLetterCardNumber() {
        offerPage.openPaymentByCard();
        val cardInfo = getCardInfoWithEngLetterCardNumber();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayByRusLetterCardNumber() {
        offerPage.openPaymentByCard();
        val cardInfo = getCardInfoWithRusLetterCardNumber();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayBySymbolCardNumber() {
        offerPage.openPaymentByCard();
        val cardInfo = getCardInfoWithSymbolCardNumber();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldFieldRequiredNotificationIfPayByEmptyCardNumber() {
        offerPage.openPaymentByCard();
        val cardInfo = getCardInfoWithEmptyCardNumber();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getFieldRequiredNotification();
    }

    // Card month
    @Test
    public void shouldWrongFormatNotificationIfPayBy1ZeroCardMonth() {
        offerPage.openPaymentByCard();
        val cardInfo = getCardInfoWith1ZeroCardMonth();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayBy2ZeroCardMonth() {
        offerPage.openPaymentByCard();
        val cardInfo = getCardInfoWith2ZeroCardMonth();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongExpirationDateNotificationIfPayByOverageCardMonth() {
        offerPage.openPaymentByCard();
        val cardInfo = getCardInfoWithOverageCardMonth();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongExpirationDateNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayByIncompleteCardMonth() {
        offerPage.openPaymentByCard();
        val cardInfo = getCardInfoWithIncompleteCardMonth();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayByEngLetterCardMonth() {
        offerPage.openPaymentByCard();
        val cardInfo = getCardInfoWithEngLetterCardMonth();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayByRusLetterCardMonth() {
        offerPage.openPaymentByCard();
        val cardInfo = getCardInfoWithRusLetterCardMonth();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayBySymbolCardMonth() {
        offerPage.openPaymentByCard();
        val cardInfo = getCardInfoWithSymbolCardMonth();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldFieldRequiredNotificationIfPayByEmptyCardMonth() {
        offerPage.openPaymentByCard();
        val cardInfo = getCardInfoWithEmptyCardMonth();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getFieldRequiredNotification();
    }

    // Card year
    @Test
    public void shouldWrongFormatNotificationIfPayBy1ZeroCardYear() {
        offerPage.openPaymentByCard();
        val cardInfo = getCardInfoWith1ZeroCardYear();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldCardExpiredNotificationIfPayBy2ZeroCardYear() {
        offerPage.openPaymentByCard();
        val cardInfo = getCardInfoWith2ZeroCardYear();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getCardExpiredNotification();
    }

    @Test
    public void shouldWrongExpirationDateNotificationIfPayByOverageCardYear() {
        offerPage.openPaymentByCard();
        val cardInfo = getCardInfoWithOverageCardYear();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongExpirationDateNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayByIncompleteCardYear() {
        offerPage.openPaymentByCard();
        val cardInfo = getCardInfoWithIncompleteCardYear();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayByEngLetterCardYear() {
        offerPage.openPaymentByCard();
        val cardInfo = getCardInfoWithEngLetterCardYear();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayByRusLetterCardYear() {
        offerPage.openPaymentByCard();
        val cardInfo = getCardInfoWithRusLetterCardYear();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayBySymbolCardYear() {
        offerPage.openPaymentByCard();
        val cardInfo = getCardInfoWithSymbolCardYear();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldFieldRequiredNotificationIfPayByEmptyCardYear() {
        offerPage.openPaymentByCard();
        val cardInfo = getCardInfoWithEmptyCardYear();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getFieldRequiredNotification();
    }

    // Card holder
    @Test
    public void shouldWrongFormatNotificationIfPayByOverageCardHolder() {
        offerPage.openPaymentByCard();
        val cardInfo = getCardInfoWithOverageCardHolder();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayByIncompleteCardHolder() {
        offerPage.openPaymentByCard();
        val cardInfo = getCardInfoWithIncompleteCardHolder();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayByRusLetterCardHolder() {
        offerPage.openPaymentByCard();
        val cardInfo = getCardInfoWithRusCardHolder();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayByNumeralCardHolder() {
        offerPage.openPaymentByCard();
        val cardInfo = getCardInfoWithNumeralCardHolder();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayBySymbolCardHolder() {
        offerPage.openPaymentByCard();
        val cardInfo = getCardInfoWithSymbolCardHolder();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldFieldRequiredNotificationIfPayByEmptyCardHolder() {
        offerPage.openPaymentByCard();
        val cardInfo = getCardInfoWithEmptyCardHolder();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getFieldRequiredNotification();
    }

    // Card cvv
    @Test
    public void shouldWrongFormatNotificationIfPayBy3ZeroCardCvv() {
        offerPage.openPaymentByCard();
        val cardInfo = getCardInfoWith3ZeroCardCvv();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayByOverageCardCvv() {
        offerPage.openPaymentByCard();
        val cardInfo = getCardInfoWithOverageCardCvv();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayByIncompleteCardCvv() {
        offerPage.openPaymentByCard();
        val cardInfo = getCardInfoWithIncompleteCardCvv();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayByEngLetterCardCvv() {
        offerPage.openPaymentByCard();
        val cardInfo = getCardInfoWithEngLetterCardCvv();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayByRusLetterCardCvv() {
        offerPage.openPaymentByCard();
        val cardInfo = getCardInfoWithRusLetterCardCvv();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfPayBySymbolCardCvv() {
        offerPage.openPaymentByCard();
        val cardInfo = getCardInfoWithSymbolCardCvv();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldFieldRequiredNotificationIfPayByEmptyCardCvv() {
        offerPage.openPaymentByCard();
        val cardInfo = getCardInfoWithEmptyCardCvv();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getFieldRequiredNotification();
    }

    // Test-cases for payment by credit
    // Valid data
    @Test
    public void shouldSuccessCreditByValidApprovedCard() {
        offerPage.openPaymentByCredit();
        val cardInfo = getCardInfoWithAllValidDateApprovedCard();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getSuccessNotification();
        val expectedStatus = "APPROVED";
        val actualStatus = SQLData.getStatusLastCreditTransaction();
        assertEquals(expectedStatus, actualStatus);
        val creditId = SQLData.getCreditId();
        assertNotNull(creditId);
        val creditRequestId = SQLData.getCreditRequestId();
        assertNotNull(creditRequestId);
    }

    @Test
    public void shouldErrorCreditByValidDeclinedCard() {
        offerPage.openPaymentByCredit();
        val cardInfo = getCardInfoWithAllValidDateDeclinedCard();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getErrorNotification();
        val expectedStatus = "DECLINED";
        val actualStatus = SQLData.getStatusLastCreditTransaction();
        assertEquals(expectedStatus, actualStatus);
        val creditId = SQLData.getCreditId();
        assertNotNull(creditId);
        val creditRequestId = SQLData.getCreditRequestId();
        assertNull(creditRequestId);
    }

    // Invalid data
    // Card number
    @Test
    public void shouldWrongFormatNotificationIfCreditBy1ZeroCardNumber() {
        offerPage.openPaymentByCredit();
        val cardInfo = getCardInfoWith1ZeroCardNumber();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditBy16ZeroCardNumber() {
        offerPage.openPaymentByCredit();
        val cardInfo = getCardInfoWith16ZeroCardNumber();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditByOverageCardNumber() {
        offerPage.openPaymentByCredit();
        val cardInfo = getCardInfoWithOverageCardNumber();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditByIncompleteCardNumber() {
        offerPage.openPaymentByCredit();
        val cardInfo = getCardInfoWithIncompleteCardNumber();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditByEngLetterCardNumber() {
        offerPage.openPaymentByCredit();
        val cardInfo = getCardInfoWithEngLetterCardNumber();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditByRusLetterCardNumber() {
        offerPage.openPaymentByCredit();
        val cardInfo = getCardInfoWithRusLetterCardNumber();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditBySymbolCardNumber() {
        offerPage.openPaymentByCredit();
        val cardInfo = getCardInfoWithSymbolCardNumber();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldFieldRequiredNotificationIfCreditByEmptyCardNumber() {
        offerPage.openPaymentByCredit();
        val cardInfo = getCardInfoWithEmptyCardNumber();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getFieldRequiredNotification();
    }

    // Card month
    @Test
    public void shouldWrongFormatNotificationIfCreditBy1ZeroCardMonth() {
        offerPage.openPaymentByCredit();
        val cardInfo = getCardInfoWith1ZeroCardMonth();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditBy2ZeroCardMonth() {
        offerPage.openPaymentByCredit();
        val cardInfo = getCardInfoWith2ZeroCardMonth();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongExpirationDateNotificationIfCreditByOverageCardMonth() {
        offerPage.openPaymentByCredit();
        val cardInfo = getCardInfoWithOverageCardMonth();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongExpirationDateNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditByIncompleteCardMonth() {
        offerPage.openPaymentByCredit();
        val cardInfo = getCardInfoWithIncompleteCardMonth();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditByEngLetterCardMonth() {
        offerPage.openPaymentByCredit();
        val cardInfo = getCardInfoWithEngLetterCardMonth();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditByRusLetterCardMonth() {
        offerPage.openPaymentByCredit();
        val cardInfo = getCardInfoWithRusLetterCardMonth();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditBySymbolCardMonth() {
        offerPage.openPaymentByCredit();
        val cardInfo = getCardInfoWithSymbolCardMonth();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldFieldRequiredNotificationIfCreditByEmptyCardMonth() {
        offerPage.openPaymentByCredit();
        val cardInfo = getCardInfoWithEmptyCardMonth();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getFieldRequiredNotification();
    }

    // Card year
    @Test
    public void shouldWrongFormatNotificationIfCreditBy1ZeroCardYear() {
        offerPage.openPaymentByCredit();
        val cardInfo = getCardInfoWith1ZeroCardYear();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldCardExpiredNotificationIfCreditBy2ZeroCardYear() {
        offerPage.openPaymentByCredit();
        val cardInfo = getCardInfoWith2ZeroCardYear();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getCardExpiredNotification();
    }

    @Test
    public void shouldWrongExpirationDateNotificationIfCreditByOverageCardYear() {
        offerPage.openPaymentByCredit();
        val cardInfo = getCardInfoWithOverageCardYear();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongExpirationDateNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditByIncompleteCardYear() {
        offerPage.openPaymentByCredit();
        val cardInfo = getCardInfoWithIncompleteCardYear();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditByEngLetterCardYear() {
        offerPage.openPaymentByCredit();
        val cardInfo = getCardInfoWithEngLetterCardYear();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditByRusLetterCardYear() {
        offerPage.openPaymentByCredit();
        val cardInfo = getCardInfoWithRusLetterCardYear();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditBySymbolCardYear() {
        offerPage.openPaymentByCredit();
        val cardInfo = getCardInfoWithSymbolCardYear();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldFieldRequiredNotificationIfCreditByEmptyCardYear() {
        offerPage.openPaymentByCredit();
        val cardInfo = getCardInfoWithEmptyCardYear();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getFieldRequiredNotification();
    }

    // Card holder
    @Test
    public void shouldWrongFormatNotificationIfCreditByOverageCardHolder() {
        offerPage.openPaymentByCredit();
        val cardInfo = getCardInfoWithOverageCardHolder();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditByIncompleteCardHolder() {
        offerPage.openPaymentByCredit();
        val cardInfo = getCardInfoWithIncompleteCardHolder();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditByRusCardHolder() {
        offerPage.openPaymentByCredit();
        val cardInfo = getCardInfoWithRusCardHolder();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditByNumeralCardHolder() {
        offerPage.openPaymentByCredit();
        val cardInfo = getCardInfoWithNumeralCardHolder();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditBySymbolCardHolder() {
        offerPage.openPaymentByCredit();
        val cardInfo = getCardInfoWithSymbolCardHolder();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldFieldRequiredNotificationIfCreditByEmptyCardHolder() {
        offerPage.openPaymentByCredit();
        val cardInfo = getCardInfoWithEmptyCardHolder();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getFieldRequiredNotification();
    }

    // Card cvv
    @Test
    public void shouldWrongFormatNotificationIfCreditBy3ZeroCardCvv() {
        offerPage.openPaymentByCredit();
        val cardInfo = getCardInfoWith3ZeroCardCvv();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditByOverageCardCvv() {
        offerPage.openPaymentByCredit();
        val cardInfo = getCardInfoWithOverageCardCvv();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditByIncompleteCardCvv() {
        offerPage.openPaymentByCredit();
        val cardInfo = getCardInfoWithIncompleteCardCvv();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditByEngLetterCardCvv() {
        offerPage.openPaymentByCredit();
        val cardInfo = getCardInfoWithEngLetterCardCvv();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditByRusLetterCardCvv() {
        offerPage.openPaymentByCredit();
        val cardInfo = getCardInfoWithRusLetterCardCvv();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldWrongFormatNotificationIfCreditBySymbolCardCvv() {
        offerPage.openPaymentByCredit();
        val cardInfo = getCardInfoWithSymbolCardCvv();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getWrongFormatNotification();
    }

    @Test
    public void shouldFieldRequiredNotificationIfCreditByEmptyCardCvv() {
        offerPage.openPaymentByCredit();
        val cardInfo = getCardInfoWithEmptyCardCvv();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.getFieldRequiredNotification();
    }
}
