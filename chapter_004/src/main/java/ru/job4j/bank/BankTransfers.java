package ru.job4j.bank;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class BankTransfers {
    private Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод добавляет пользователя.
     * @param user пользователь
     */
    public void addUser(User user) {
        this.users.put(user, new ArrayList<>());
    }

    /**
     * Метод удаляет пользователя.
     * @param user пользователь
     */
    public void deleteUser(User user) {
        this.users.remove(user);
    }

    /**
     * Метод добавляет счет пользователю.
     * @param passport паспорт пользователя
     * @param account новый счет
     */
    public void addAccountToUser(String passport, Account account) {
        if (this.getUserByPassport(passport) != null) {
            List<Account> accounts =
                    this.users.putIfAbsent(this.getUserByPassport(passport), new ArrayList<>(Arrays.asList(account)));
            if (accounts != null) {
                accounts.add(account);
                this.users.replace(this.getUserByPassport(passport), accounts);
            }
        }
    }

    /**
     * Метод удаляет счет пользователя.
     * @param passport паспорт пользователя
     * @param account удаляемый счет
     */
    public void deleteAccountFromUser(String passport, Account account) {
        if (!this.users.isEmpty() && this.getUserByPassport(passport) != null) {
            this.users.replace(this.getUserByPassport(passport), this.users.get(this.getUserByPassport(passport))
                    .stream().filter(current -> !current.equals(account)).collect(Collectors.toList()));
        }
    }

    /**
     * Метод возвращает лист со всеми счетами пользователя.
     * @param passport паспорт пользователя
     * @return лист со всеми счетами пользователя
     */
    public List<Account> getUserAccounts(String passport) {
        return this.getUserByPassport(passport) != null
                ? this.users.get(this.getUserByPassport(passport)) : new ArrayList<>();
    }

    /**
     * Метод для перечисления денег с одного счёта на другой счет.
     * @param srcPassport паспорт пользователя, со счета которого перечисляются деньги
     * @param srcRequisite счет, с которого перечисляются деньги
     * @param destPassport паспорт пользователя, на счет которого перечисляются деньги
     * @param destRequisite счет, на который перечисляются деньги
     * @param amount перечисляемая сумма
     * @return true - перевод осуществлен, false - нет
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean result = false;
        Account srcAccount = getAccountByPassportAndRequisite(srcPassport, srcRequisite);
        Account destAccount = getAccountByPassportAndRequisite(destPassport, destRequisite);
        if (srcAccount != null && destAccount != null && srcAccount.getValue() >= amount) {
            srcAccount.setValue(srcAccount.getValue() - amount);
            destAccount.setValue(destAccount.getValue() + amount);
            result = true;
        }
        return result;
    }

    /**
     * Метод возвращает пользователя по паспорту.
     * @param passport паспорт пользователя
     * @return пользователь
     */
    public User getUserByPassport(String passport) {
        return users.keySet().stream().filter(user -> user.getPassport().equals(passport)).findFirst().orElse(null);
    }

    /**
     * Метод возвращает счет по паспорту пользователя и реквизитам счета.
     * @param passport паспорт пользователя
     * @param requisite реквизиты счета
     * @return счет
     */
    public Account getAccountByPassportAndRequisite(String passport, String requisite) {
        return getUserAccounts(passport).stream().filter(account -> account.getRequisites().equals(requisite))
                .findFirst().orElse(null);
    }
}