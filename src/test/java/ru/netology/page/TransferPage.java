package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement amountInput = $("[data-test-id='amount'] input");
    private SelenideElement fromInput = $("[data-test-id='from'] input");
    private SelenideElement transferButton = $("[data-test-id='action-transfer']");
    private SelenideElement transferHead = $("[data-test-id='action-transfer'] ");


    public void makeTransfer(int value, String cardInfo) {
        amountInput.setValue(Integer.toString(value));
        fromInput.setValue(cardInfo);
        transferButton.click();

    }


}

