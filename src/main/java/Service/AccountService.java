package Service;

import DAO.AccountDAO;
import Model.Account;

/**
 * This is a Service class that acts between the web/database layer
 * (controller) of the "account" table and the persistence layer (DAO) of the
 * "Account" Java class.
 */
public class AccountService {
    public AccountDAO accountDAO;
    
    // CREATE OPERATIONS //
    /**
     * No-args constructor for AccountService which creates an AccountDAO.
     */
    public AccountService(){
        accountDAO = new AccountDAO();
    }

    /**
     * Constructor for a AccountService when a AccountDAO is provided.
     * Used for mocking AccountDAO in the test cases.
     * 
     * @param accountDAO
     */
    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    /**
     * This registers a new account into the "account" database table.
     * 
     * @param account   The new account to be registered
     * 
     * @return  Account if it was successfully persisted, or "null" if it
     *          wasn't successfully persisted
     */
    public Account registerAccount(Account account) {
        return this.accountDAO.insertAccount(account);
    }
}
