package com.rbank.bank.service.validator;

import com.rbank.bank.service.dto.CreateAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountValidatorTest {
    AccountValidator accountValidator;

    @BeforeEach
    void setUp() {
        accountValidator = new AccountValidator();
    }

    @Test
    void isValid_withValidAccount() {
        CreateAccount account = new CreateAccount(
                "account_holder_name",
                100, 1L);
        assertDoesNotThrow(() -> accountValidator.isValid(account));

    }

    @Test
    void isValid_withInValidAccountHolderName() {

        CreateAccount account1 = new CreateAccount(null, 100, 1L);

        Throwable resultException = assertThrows(
                IllegalArgumentException.class,
                () -> accountValidator.isValid(account1));
        assertEquals(resultException.getMessage(), "Account holder name cannot be null or empty");


    }

    @Test
    void isValid_withInValidBalance() {
        CreateAccount account = new CreateAccount("valid holder name", -1, 1L);
        Throwable resultException = assertThrows(
                IllegalArgumentException.class,
                () -> accountValidator.isValid(account));
        assertEquals(resultException.getMessage(), "Account balance cannot be less than 0");

    }

    @Test
    void isValid_withInValidUserId() {
        Long invalidUserId = null;
        CreateAccount account = new CreateAccount("valid holder name", 100, invalidUserId);

        Throwable resultException = assertThrows(
                IllegalArgumentException.class,
                () -> accountValidator.isValid(account));
        assertEquals(resultException.getMessage(), "User id cannot be null");
    }
    @Test
    void isValid_withInValidAccountInfo() {
        CreateAccount account = null;
        Throwable resultException = assertThrows(
                IllegalArgumentException.class,
                () -> accountValidator.isValid(account));
        assertEquals(resultException.getMessage(), "Account info cannot be null");
    }

}