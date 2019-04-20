package ru.job4j.bank;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class BankTransfersTest {

    @Test
    public void whenAddUser() {
        BankTransfers transfers = new BankTransfers();
        transfers.addUser(new User("Filipp", "123"));
        transfers.addUser(new User("Foma", "456"));
        assertThat(Set.of(transfers.getUserByPassport("123"), transfers.getUserByPassport("456")),
                is(Set.of(new User("Filipp", "123"), new User("Foma", "456"))));
    }

    @Test
    public void whenDeleteUser() {
        BankTransfers transfers = new BankTransfers();
        transfers.addUser(new User("Filipp", "123"));
        transfers.addUser(new User("Foma", "456"));
        transfers.deleteUser(new User("Filipp", "123"));
        Set<User> result = new HashSet<>();
        if (transfers.getUserByPassport("123") != null) {
            result.add(transfers.getUserByPassport("123"));
        }
        result.add(transfers.getUserByPassport("456"));
        assertThat(result, is(Set.of(new User("Foma", "456"))));
    }

    @Test
    public void whenAddAccountToUser() {
        BankTransfers transfers = new BankTransfers();
        transfers.addUser(new User("Filipp", "123"));
        transfers.addUser(new User("Foma", "456"));
        transfers.addAccountToUser("123", new Account(777, "account1"));
        transfers.addAccountToUser("123", new Account(111, "account2"));
        assertThat(transfers.getUserAccounts("123"),
                is(List.of(new Account(777, "account1"), new Account(111, "account2"))));
    }

    @Test
    public void whenGetUserAccounts() {
        BankTransfers transfers = new BankTransfers();
        transfers.addUser(new User("Filipp", "123"));
        transfers.addUser(new User("Foma", "456"));
        transfers.addAccountToUser("456", new Account(777, "account1"));
        transfers.addAccountToUser("456", new Account(111, "account2"));
        assertThat(transfers.getUserAccounts("456"),
                is(List.of(new Account(777, "account1"), new Account(111, "account2"))));
    }

    @Test
    public void whenDeleteAccountFromUser() {
        BankTransfers transfers = new BankTransfers();
        transfers.addUser(new User("Filipp", "123"));
        transfers.addUser(new User("Foma", "456"));
        transfers.addAccountToUser("456", new Account(777, "account1"));
        transfers.addAccountToUser("456", new Account(111, "account2"));
        transfers.deleteAccountFromUser("456", new Account(111, "account2"));
        assertThat(transfers.getUserAccounts("456"), is(List.of(new Account(777, "account1"))));
    }

    @Test
    public void whenTransferMoneyThenTrue() {
        BankTransfers transfers = new BankTransfers();
        transfers.addUser(new User("Filipp", "123"));
        transfers.addUser(new User("Foma", "456"));
        transfers.addAccountToUser("456", new Account(777, "account1"));
        transfers.addAccountToUser("123", new Account(888, "account2"));
        transfers.transferMoney("456", "account1", "123", "account2", 222);
        assertThat(List.of(transfers.getAccountByPassportAndRequisite("123",
                "account2"), transfers.getAccountByPassportAndRequisite("456", "account1")),
                is(List.of(new Account(1110, "account2"), new Account(555, "account1"))));
    }

    @Test
    public void whenTransferMoneyThenFalse() {
        BankTransfers transfers = new BankTransfers();
        transfers.addUser(new User("Filipp", "123"));
        transfers.addUser(new User("Foma", "456"));
        transfers.addAccountToUser("456", new Account(777, "account1"));
        transfers.addAccountToUser("123", new Account(111, "account2"));
        transfers.transferMoney("123", "account2", "456", "account1", 1000);
        assertThat(List.of(transfers.getAccountByPassportAndRequisite("123",
                "account2"), transfers.getAccountByPassportAndRequisite("456", "account1")),
                is(List.of(new Account(111, "account2"), new Account(777, "account1"))));
    }
}
