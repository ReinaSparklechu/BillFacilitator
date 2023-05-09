package com.electricityBill.view;

import com.electricityBill.model.Credentials;

import java.util.Scanner;

public abstract class View {
    Scanner scanner = new Scanner(System.in);
    public enum ViewName{DEFAULT, ADMIN, CUSTOMER}
    ViewName viewName;
    public abstract void displayMenu();
    public abstract void displayItem(Object object);

    public String getInput() {
        return scanner.nextLine();
    }


    public String getInput(String prompt) {
        System.out.printf("%s :", prompt);
        return scanner.nextLine();
    }


    public void displayMessage(String message) {
        System.out.println(message);
    }

    public abstract ViewName getViewName();
    public abstract Credentials getCredentials();
}
