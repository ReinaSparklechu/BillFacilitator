package com.electricityBill.view;

import com.electricityBill.model.Bill;
import com.electricityBill.model.Credentials;
import com.electricityBill.model.Customer;

import java.util.List;
import java.util.Scanner;

public class AdminView extends View{
    private static final ViewName viewName = ViewName.ADMIN;

    private Credentials loggedIn;

    public AdminView(Credentials credentials){
        this.loggedIn = credentials;
    }

    private Scanner scanner = new Scanner(System.in);

    @Override
    public void displayMenu() {

        StringBuilder builder = new StringBuilder("Admin ");
        builder.append(loggedIn.getUsername());
        builder.append("\n");
        for(int i =0; i<30; i++){
            builder.append("=");
        }
        displayMessage(builder.toString());
        displayMessage("1. Add customer");
        displayMessage("2. Display all customers");
        displayMessage("3. Generate bill for a customer");
        displayMessage("4. Display bills of a given month");
        displayMessage("5. Display bills of all customers");
        displayMessage("6. Configure billing rate");
        displayMessage("7. Logout");
    }

    @Override
    public void displayItem(Object object) {
        if(object instanceof List<?>){
            List<?> list = (List<?>) object;
            boolean headerDisplayed = false;
            for(Object o: list){
                if(!headerDisplayed){
                    StringBuilder header = new StringBuilder("");
                    if(o instanceof Bill){
                        header.append("Bills\n");
                    }
                    if(o instanceof Customer){
                        header.append("Customers\n");
                    }
                    for(int j =0; j<20; j++){
                        header.append("=");
                    }
                    System.out.println(header);
                    headerDisplayed=true;
                }
                System.out.println(o);
            }
        }else {
            System.out.println(object);
        }
    }



    public ViewName getViewName() {
        return viewName;
    }

    @Override
    public Credentials getCredentials() {
        return this.loggedIn;
    }
}
