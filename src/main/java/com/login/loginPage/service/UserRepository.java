package com.login.loginPage.service;

import com.login.loginPage.module.UserModule;

public interface UserRepository<T> {

     void addNewUser(UserModule newUser);
     void deleteUser();
     T getUser();
     boolean isUser(UserModule userLogin);



}
