package com.example.demo.service;

public interface ISecurityService {
    String findLoggedInUserName();
    void autoLogin(String userName, String password);
}
