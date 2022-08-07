package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id =dashboard] ");
    private ElementsCollection cards = $$("body .list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р. ";

    private SelenideElement transferToFirstButton = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']")
            .find("[data-test-id='action-deposit']");
    private SelenideElement transferToSecondButton = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']")
            .find("[data-test-id='action-deposit']");

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public void transferToFirstButton() {
        transferToFirstButton.click();
    }

    public void transferToSecondButton() {
        transferToSecondButton.click();
    }

    public int getTransferToFirstButton() {
        val text = cards.first().text();
        return extractBalance(text);
    }

    public int getTransferToSecondButton() {
        val text = cards.first().text();
        return extractBalance(text);
    }


    public int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}
