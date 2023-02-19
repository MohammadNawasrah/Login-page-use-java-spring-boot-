package com.login.loginPage.service;

import com.login.loginPage.module.UserModule;

import java.util.ArrayList;
import java.util.List;

public class User implements UserRepository<UserModule> {
    private List<UserModule> users;
    private int userIndex = -1;

    public User() {
        this.users = new ArrayList<UserModule>();
    }

    @Override
    public void addNewUser(UserModule user) {
        if (!user.getUsername().equals("mohammad")) {
            this.users.add(user);
            for (int i = 0; i < this.users.size(); i++)
                System.out.println(this.users.get(i).getUsername());
        }
    }

    @Override
    public void deleteUser() {

    }

    @Override
    public UserModule getUser() {
        if (this.userIndex == -1)
            return null;
        else
            return this.users.get(this.userIndex);
    }

    @Override
    public boolean isUser(UserModule user) {
        for (int i = 0; i < this.users.size(); i++) {
            if (user.getUsername().equals(this.users.get(i).getUsername())) {
                if (user.getPassword().equals(this.users.get(i).getPassword())) {
                    this.userIndex = i;
                    return true;
                }
            }
        }
        return false;
    }
}
