package data;

import lombok.NoArgsConstructor;
import lombok.Value;

@NoArgsConstructor
public class DataHelper {

    @Value
    public static class CardInfo {
        String cardNumber;
        String cardMonth;
        String cardYear;
        String cardHolder;
        String cardCvv;
    }

    public static CardInfo getCardInfoWithAllValidDateApprovedCard() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "03",
                "22",
                "Card Holder",
                "123");
    }
    public static CardInfo getCardInfoWithAllValidDateDeclinedCard() {
        return new CardInfo(
                "4444 4444 4444 4442",
                "03",
                "22",
                "Card Holder",
                "123");
    }

    public static CardInfo getCardInfoWith1ZeroCardNumber() {
        return new CardInfo(
                "0",
                "03",
                "22",
                "Card Holder",
                "123");
    }
    public static CardInfo getCardInfoWith16ZeroCardNumber() {
        return new CardInfo(
                "0000 0000 0000 0000",
                "03",
                "22",
                "Card Holder",
                "123");
    }
    public static CardInfo getCardInfoWithOverageCardNumber() {
        return new CardInfo(
                "1234 1234 1234 1234 1",
                "03",
                "22",
                "Card Holder",
                "123");
    }
    public static CardInfo getCardInfoWithIncompleteCardNumber() {
        return new CardInfo(
                "1234 1234 1234 123",
                "03",
                "22",
                "Card Holder",
                "123");
    }
    public static CardInfo getCardInfoWithEngLetterCardNumber() {
        return new CardInfo(
                "Zz",
                "03",
                "22",
                "Card Holder",
                "123");
    }
    public static CardInfo getCardInfoWithRusLetterCardNumber() {
        return new CardInfo(
                "Яя",
                "03",
                "22",
                "Card Holder",
                "123");
    }
    public static CardInfo getCardInfoWithSymbolCardNumber() {
        return new CardInfo(
                "~!@#$%^&*()_+",
                "03",
                "22",
                "Card Holder",
                "123");
    }
    public static CardInfo getCardInfoWithEmptyCardNumber() {
        return new CardInfo(
                "",
                "03",
                "22",
                "Card Holder",
                "123");
    }

    public static CardInfo getCardInfoWith1ZeroCardMonth() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "0",
                "22",
                "Card Holder",
                "123");
    }
    public static CardInfo getCardInfoWith2ZeroCardMonth() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "00",
                "22",
                "Card Holder",
                "123");
    }
    public static CardInfo getCardInfoWithOverageCardMonth() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "22",
                "22",
                "Card Holder",
                "123");
    }
    public static CardInfo getCardInfoWithIncompleteCardMonth() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "1",
                "22",
                "Card Holder",
                "123");
    }
    public static CardInfo getCardInfoWithEngLetterCardMonth() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "Zz",
                "22",
                "Card Holder",
                "123");
    }
    public static CardInfo getCardInfoWithRusLetterCardMonth() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "Яя",
                "22",
                "Card Holder",
                "123");
    }
    public static CardInfo getCardInfoWithSymbolCardMonth() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "~!@#$%^&*()_+",
                "22",
                "Card Holder",
                "123");
    }
    public static CardInfo getCardInfoWithEmptyCardMonth() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "",
                "22",
                "Card Holder",
                "123");
    }

    public static CardInfo getCardInfoWith1ZeroCardYear() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "03",
                "0",
                "Card Holder",
                "123");
    }
    public static CardInfo getCardInfoWith2ZeroCardYear() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "03",
                "00",
                "Card Holder",
                "123");
    }
    public static CardInfo getCardInfoWithOverageCardYear() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "03",
                "33",
                "Card Holder",
                "123");
    }
    public static CardInfo getCardInfoWithIncompleteCardYear() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "03",
                "1",
                "Card Holder",
                "123");
    }
    public static CardInfo getCardInfoWithEngLetterCardYear() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "03",
                "Zz",
                "Card Holder",
                "123");
    }
    public static CardInfo getCardInfoWithRusLetterCardYear() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "03",
                "Яя",
                "Card Holder",
                "123");
    }
    public static CardInfo getCardInfoWithSymbolCardYear() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "03",
                "~!@#$%^&*()_+",
                "Card Holder",
                "123");
    }
    public static CardInfo getCardInfoWithEmptyCardYear() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "03",
                "",
                "Card Holder",
                "123");
    }

    public static CardInfo getCardInfoWithOverageCardHolder() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "03",
                "22",
                "Card Holder Name",
                "123");
    }
    public static CardInfo getCardInfoWithIncompleteCardHolder() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "03",
                "22",
                "Cardholder",
                "123");
    }
    public static CardInfo getCardInfoWithRusCardHolder() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "03",
                "22",
                "Владелец Карты",
                "123");
    }
    public static CardInfo getCardInfoWithNumeralCardHolder() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "03",
                "22",
                "00",
                "123");
    }
    public static CardInfo getCardInfoWithSymbolCardHolder() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "03",
                "22",
                "~!@#$%^&*()_+",
                "123");
    }
    public static CardInfo getCardInfoWithEmptyCardHolder() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "03",
                "22",
                "",
                "123");
    }

    public static CardInfo getCardInfoWith3ZeroCardCvv() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "03",
                "22",
                "Card Holder",
                "000");
    }
    public static CardInfo getCardInfoWithOverageCardCvv() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "03",
                "22",
                "Card Holder",
                "1234");
    }
    public static CardInfo getCardInfoWithIncompleteCardCvv() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "03",
                "22",
                "Card Holder",
                "12");
    }
    public static CardInfo getCardInfoWithEngLetterCardCvv() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "03",
                "22",
                "Card Holder",
                "Zz");
    }
    public static CardInfo getCardInfoWithRusLetterCardCvv() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "03",
                "22",
                "Card Holder",
                "Яя");
    }
    public static CardInfo getCardInfoWithSymbolCardCvv() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "03",
                "22",
                "Card Holder",
                "~!@#$%^&*()_+");
    }
    public static CardInfo getCardInfoWithEmptyCardCvv() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "03",
                "22",
                "Card Holder",
                "");
    }
}
