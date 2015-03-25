package com.gilera.ryan.accountsystem.account;

import com.gilera.ryan.accountsystem.utility.StringUtil;
import java.util.Objects;
import java.util.UUID;

/**
 * The Client class that represents an account holder.
 * 
 * @author Ryan Gilera
 */
public final class Client {
    private final UUID id;
    private final String name;

    public Client(String name) {
        this.id = UUID.randomUUID();
        this.name = StringUtil.formatClientName(name);
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }
    
    /**
     * Overrides Object class equals method to implement custom
     * implementation. A Client object is said to be
     * equal if and only if their UUID and name matches
     * to another Client object.
     * 
     * @param anObject An Object object (Ha!)
     * 
     * @return True if it is deemed equal otherwise false 
     */
    @Override
    public boolean equals(Object anObject) {
        if (anObject == null) {
            return false;
        }
        if (this == anObject) {
            return true;
        }
        if (!(anObject instanceof Client)) {
            return false;
        }

        Client aClient = (Client) anObject;
        
        return this.id.equals(aClient.id) &&
                this.name.equals(aClient.name);
    }

    /**
     * Custom hashcode implementation using a 
     * random integer number.
     * 
     * @return integer value as resulting code
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.id);
        hash = 11 * hash + Objects.hashCode(this.name);
        return hash;
    }

    /**
     * String equivalent of this class.
     * 
     * @return String object 
     */
    @Override
    public String toString() {
        return "Client Name: " + getName() 
                + "\nClient ID: " + getId();
    }
}
