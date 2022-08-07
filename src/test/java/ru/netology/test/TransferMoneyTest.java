package ru.netology.test;

import com.codeborne.selenide.Configuration;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.TransferPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferMoneyTest {

    @BeforeEach
    void setup() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCode(authInfo);
        verificationPage.validVerify(verificationCode);
    }


    @Test
    void shouldTransferMoneySecondToFirstCard() {
        int value = 100;
        String cardNumber = String.valueOf(DataHelper.getSecondNumber());
        val dashboardPage = new DashboardPage();
        var firstCardBalance = dashboardPage.getTransferToFirstButton();
        var secondCardBalance = dashboardPage.getTransferToSecondButton();
        dashboardPage.transferToSecondButton();
        val transferPage = new TransferPage();
        transferPage.makeTransfer(value, cardNumber);
        var firstCardBalanceNew = dashboardPage.getTransferToFirstButton();
        var secondCardBalanceNew = dashboardPage.getTransferToSecondButton();
        Assertions.assertEquals(secondCardBalance - value, secondCardBalanceNew);
        Assertions.assertEquals(firstCardBalance + value, firstCardBalanceNew);
    }
}
