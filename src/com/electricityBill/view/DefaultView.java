package com.electricityBill.view;

import com.electricityBill.model.Credentials;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class DefaultView extends View{
    ViewName viewName = ViewName.DEFAULT;

    @Override
    public void displayMenu() {
        StringBuilder builder = new StringBuilder("Main menu\n");
        for(int i =0; i<30; i++){
            builder.append("=");
        }
        System.out.println(builder);
        displayMessage("1. Log in as admin");
        displayMessage("2. Log in as customer");
        displayMessage("3. Exit Program");
    }

    @Override
    public void displayItem(Object object) {
        throw new NotImplementedException();
    }

    @Override
    public ViewName getViewName() {
        return viewName;
    }

    @Override
    public Credentials getCredentials() {
        return null;
    }
}
