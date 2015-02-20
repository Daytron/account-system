/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilera.ryan.accountsystem.account;

import com.gilera.ryan.accountsystem.utility.StringUtil;
import java.util.UUID;

/**
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
    
}
