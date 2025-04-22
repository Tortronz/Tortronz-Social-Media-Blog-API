package Service;

import java.util.List;

import DAO.AccountDAO;
import Model.Account;
import Model.Message;

/**
 * This is a Service class that acts between the endpoints (controller) and the
 * database (DAO) of the "Account" Java class.
 */
public class AccountService {
    public AccountDAO accountDAO;
    
    // CONSTRUCTORS //
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

    // CREATE OPERATIONS //
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

    // READ OPERATIONS //
    /**
     * This authorizes/logs in an account.
     * 
     * @param account   The account credientials attempting to log in
     * 
     * @return  Account if it was successfully logged in, or "null" if it
     *          wasn't successfully logged in
     */
    public Account loginAccount(Account account) {
        return this.accountDAO.getAccount(account);
    }
}
