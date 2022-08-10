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
    void shouldTransferMoneyFirstToSecondCard() {

        int value = 100;
        String cardNumber = DataHelper.getFirstNumber().getCardNumber();
        val dashboardPage = new DashboardPage();
        var firstCardBalance = dashboardPage.getFirstCardBalance();
        var secondCardBalance = dashboardPage.getSecondCardBalance();
        dashboardPage.transferToSecondButton();
        val transferPage = new TransferPage();
        transferPage.makeTransfer(value, cardNumber);
        var firstCardBalanceNew = dashboardPage.getFirstCardBalance();
        var secondCardBalanceNew = dashboardPage.getSecondCardBalance();
        Assertions.assertEquals(secondCardBalance + value, secondCardBalanceNew);
        Assertions.assertEquals(firstCardBalance - value, firstCardBalanceNew);
    }

    @Test
    void shouldTransferMoneySecondToFirstCard() {

        int value = 500;
        String cardNumber = DataHelper.getSecondNumber().getCardNumber();
        val dashboardPage = new DashboardPage();
        var firstCardBalance = dashboardPage.getFirstCardBalance();
        var secondCardBalance = dashboardPage.getSecondCardBalance();
        dashboardPage.transferToFirstButton();
        val transferPage = new TransferPage();
        transferPage.makeTransfer(value, cardNumber);
        var firstCardBalanceNew = dashboardPage.getFirstCardBalance();
        var secondCardBalanceNew = dashboardPage.getSecondCardBalance();
        Assertions.assertEquals(secondCardBalance - value, secondCardBalanceNew);
        Assertions.assertEquals(firstCardBalance + value, firstCardBalanceNew);
    }
    @Test
    void shouldTransferMoneySecondToFirstCardOverLimit() {

        int value = 15_000;
        String cardNumber = DataHelper.getSecondNumber().getCardNumber();
        val dashboardPage = new DashboardPage();
        var firstCardBalance = dashboardPage.getFirstCardBalance();
        var secondCardBalance = dashboardPage.getSecondCardBalance();
        dashboardPage.transferToFirstButton();
        val transferPage = new TransferPage();
        transferPage.makeTransfer(value, cardNumber);
        var firstCardBalanceNew = dashboardPage.getFirstCardBalance();
        var secondCardBalanceNew = dashboardPage.getSecondCardBalance();
        Assertions.assertEquals(secondCardBalance - value, secondCardBalanceNew);
        Assertions.assertEquals(firstCardBalance + value, firstCardBalanceNew);
    }
}
