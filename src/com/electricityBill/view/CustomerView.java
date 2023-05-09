package com.electricityBill.view;

import com.electricityBill.model.Bill;
import com.electricityBill.model.Credentials;

import java.util.List;

public class CustomerView extends View {

    private static final ViewName viewName = ViewName.CUSTOMER;

    private Credentials loggedIn;

    public CustomerView(Credentials credentials){
        this.loggedIn = credentials;
    }

    @Override
    public void displayMenu() {
        StringBuilder builder = new StringBuilder("Customer ");
        builder.append(loggedIn.getUsername());
        builder.append("\n");
        for(int i =0; i<30; i++){
            builder.append("=");
        }
        displayMessage(builder.toString());
        displayMessage("1. Display bill of a specific month(yyyy-mm)");
        displayMessage("2. Display all your bills");
        displayMessage("3. Logout");
    }

    @Override
    public void displayItem(Object object) {
        if(object instanceof Bill){
            Bill bill = (Bill) object;
            System.out.println(bill);

        }else if(object instanceof List<?>){
            try{
                List<Bill> bills = (List<Bill>) object;
                System.out.println("Here are your bills");
                StringBuilder header = new StringBuilder();
                for(int j =0; j<20; j++){
                    header.append("=");
                }
                System.out.println(header.toString());
                for(Bill b: bills){
                    System.out.println(b);
                }
            }catch (Exception e){
                System.out.println("Something went wrong with the bill");
            }


        }
    }

    @Override
    public ViewName getViewName() {
        return viewName;
    }

    @Override
    public Credentials getCredentials() {
        return this.loggedIn;
    }
}
