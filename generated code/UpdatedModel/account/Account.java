package account;

import java.util.*;

/**
 * 
 */
public interface Account {

    /**
     * @param amount 
     * @return
     */
    public void deposit(Money amount);

    /**
     * @return
     */
    public Money getBalance();

}