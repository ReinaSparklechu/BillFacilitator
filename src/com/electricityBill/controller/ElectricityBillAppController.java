package com.electricityBill.controller;

import com.electricityBill.dao.BillDao;
import com.electricityBill.dao.CustomerDao;
import com.electricityBill.exception.InvalidCredentialsException;
import com.electricityBill.model.Address;
import com.electricityBill.model.Bill;
import com.electricityBill.model.Credentials;
import com.electricityBill.model.Customer;
import com.electricityBill.service.AdminLogInService;
import com.electricityBill.service.BillService;
import com.electricityBill.service.CustomerLogInService;
import com.electricityBill.view.AdminView;
import com.electricityBill.view.CustomerView;
import com.electricityBill.view.DefaultView;
import com.electricityBill.view.View;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

public class ElectricityBillAppController {
    View currentView = new DefaultView();
    boolean end = false;
    private CustomerDao customerDao;
    private BillDao billDao;

    public void run(){
        while(!end){
            switch (currentView.getViewName()){
                case ADMIN:
                    adminPageHandler();
                    break;
                case DEFAULT:
                    defaultPageHandler();
                    break;
                case CUSTOMER:
                    customerPageHandler();
                    break;

                default:
                    System.out.println("Something went very wrong");
                    end = true;
                    break;
            }
        }

    }
    public ElectricityBillAppController(CustomerDao customerDao, BillDao billDao){
        this.customerDao = customerDao;
        this.billDao = billDao;
    }

    private void addCustomer(){
        boolean flatNoEntered = false;
        currentView.displayMessage("Adding customer");
        String customerName = currentView.getInput("Please enter customer's name");
        String city = currentView.getInput("Please enter customer's city");
        String buildingName = currentView.getInput("Please enter the building's name");
        String streetName = currentView.getInput("Please enter the street's name");
        while(!flatNoEntered){
            try{
                int flatNo = Integer.parseInt(currentView.getInput("Please enter the flat's no"));
                flatNoEntered = true;
                Customer customer = new Customer(0, customerName, new Address(buildingName, flatNo, streetName,city));
                customerDao.addCustomer(customer);
                currentView.displayMessage("Customer created");
                currentView.displayItem(customer);
            }catch (NumberFormatException e){
                currentView.displayMessage("Please enter an actual number");
            }
        }


    }
    private void displayAllCustomers(){
        currentView.displayItem(customerDao.getAllCustomers());
    }
    private void generateBill(){
        LocalDate date;
        int customerId=-1;
        int unitsConsumed = 0;
        while(true){
            try{
                date = LocalDate.parse(currentView.getInput("Enter date of bill in the format yyyy-mm-dd"));
                break;
            }catch (DateTimeParseException e){
                currentView.displayMessage("Please enter the date in the correct format");
            }
        }
        do{
            try{
                customerId = Integer.parseInt(currentView.getInput("Enter customer's ID"));
            }catch (NumberFormatException e){
                currentView.displayMessage("Please enter a number");
            }
            if (!customerDao.existById(customerId)){
                currentView.displayMessage("Please provided an existing customer ID");
            }
        }while (!customerDao.existById(customerId));
        while(true){
            try{
                unitsConsumed = Integer.parseInt(currentView.getInput("Enter number of units that customer consumed"));
                break;
            }catch (NumberFormatException e){
                currentView.displayMessage("Please enter a number");
            }
        }
        Bill bill = BillService.generateBill(customerId, date, unitsConsumed);
        billDao.addBill(bill);

    }
    private void displayAllBills(){
        currentView.displayItem(billDao.getAll());
    }
    private void adminDisplayBillsOfAMonth(){
        int month = Integer.parseInt(currentView.getInput("Enter the numerical value for the month of bills you would like to see"));
        currentView.displayItem(billDao
                .getAll()
                .stream()
                .filter(bill -> bill.getDateOfBill().getMonthValue() == month)
                .collect(Collectors.toList())
        );
    }
    private void configureRate(){
        currentView.displayMessage("1. Under 100 units consumed");
        currentView.displayMessage("2. 100 to 199 units consumed");
        currentView.displayMessage("3. Above 200 units consumed");
        BigDecimal decimal = null;
        int rateIndex = 0;
        while(rateIndex<=0 || rateIndex >3){
            try{
                rateIndex = Integer.parseInt(currentView.getInput("Enter the index of the rate you would like changed"));
            }catch (NumberFormatException e){
                currentView.displayMessage("Please enter a valid number");
            }
            if(rateIndex<=0 || rateIndex >3){
                currentView.displayMessage("Please enter a selection from the menu displayed above");
            }
        }
        while(true){
            try{
                decimal = new BigDecimal(currentView.getInput("Enter the rate you would like it to be changed to"));
            }catch (NumberFormatException e){
                currentView.displayMessage("Please enter a valid decimal number");
            }
            break;
        }
        BillService.configureRate(rateIndex,decimal);
    }
    private void logout(){
        currentView.displayMessage("Logging out");
        currentView = new DefaultView();

    }

    private void customerDisplayBillsOfAMonth(){
        int year =0;
        int month = 0;
        while((year<2000 || year >2100) && (month <1 || month>12)){
            try{
                String yearMonth = currentView.getInput("Please enter the month and year for the bill you would like to see in the format yyyy-mm");
                String[] data = yearMonth.split("-");
                year = Integer.parseInt(data[0]);
                month = Integer.parseInt(data[1]);

            }catch(NumberFormatException e){
                currentView.displayMessage("Please enter the data in the correct format \"yyyy-mm\"");
            }
            if((year<2000 || year >2100) && (month <1 || month>12)){
                currentView.displayMessage("Please enter a valid year and month");
            }
        }
        int custId = (int) currentView.getCredentials().getUsername();
        int finalMonth = month;
        int finalYear = year;
        List<Bill> bills = billDao.getBillsByCustID(custId).stream()
                .filter(
                        bill1 -> (bill1.getDateOfBill().getMonthValue()== finalMonth && bill1.getDateOfBill().getYear()== finalYear)).collect(Collectors.toList());
        if(bills.size() ==0){
            currentView.displayMessage("No bills were found for you for that year and month");
        }else if(bills.size() == 1){
            currentView.displayItem(bills.get(0));
        }else {
            currentView.displayItem(bills);
        }

    }
    private void displayAllCustomerBills(){
        int custId = (int) currentView.getCredentials().getUsername();
        currentView.displayItem(billDao.getBillsByCustID(custId));
    }
    private void adminLogin(){
        String adminId = currentView.getInput("Please enter your admin username");
        String adminPassword = currentView.getInput("Please enter your admin password");
        try{
            Credentials credentials = AdminLogInService.login(adminId, adminPassword);
            currentView = new AdminView(credentials);
        }catch (InvalidCredentialsException e){
            currentView.displayMessage("Invalid credentials entered");
        }

    }
    private void customerLogin(){
        try{
            int custId = Integer.parseInt(currentView.getInput("Please enter your customer id"));
            CustomerLogInService.setCustomerDao(customerDao);
            Credentials credentials = CustomerLogInService.login(custId);
            currentView = new CustomerView(credentials);
        }catch(NumberFormatException e){
            currentView.displayMessage("Please enter a valid id. It is a number");
        }catch(InvalidCredentialsException e){
            currentView.displayMessage("Your customer id was not found");
        }

    }
    private void adminPageHandler(){
        currentView.displayMenu();
        String input;
        try{
            input = currentView.getInput("Please enter your selection");
            int option = Integer.parseInt(input);
            switch(option){
                case 1:
                    addCustomer();
                    break;
                case 2:
                    displayAllCustomers();
                    break;
                case 3:
                    generateBill();
                    break;
                case 4:
                    adminDisplayBillsOfAMonth();
                    break;
                case 5:
                    displayAllBills();
                    break;
                case 6:
                    configureRate();
                    break;
                case 7:
                    logout();
                default:
                    currentView.displayMessage("Please enter a numerical option as displayed in the menu");


            }
        }catch (NumberFormatException e){
            currentView.displayMessage("Please enter a valid number");
        }
    }
    private void customerPageHandler(){
        currentView.displayMenu();
        String input;
        try{
            input = currentView.getInput("Please enter your selection");
            int option = Integer.parseInt(input);
            switch(option){
                case 1:
                    customerDisplayBillsOfAMonth();
                    break;
                case 2:
                    displayAllCustomerBills();
                    break;
                case 3:
                    logout();
                    break;
                default:
                    currentView.displayMessage("Please enter a numerical option as displayed in the menu");


            }
        }catch (NumberFormatException e){
            currentView.displayMessage("Please enter a valid number");
        }
    }

    private void defaultPageHandler(){
        currentView.displayMenu();
        String input;
        try{
            input = currentView.getInput("Please enter your selection");
            int option = Integer.parseInt(input);
            switch(option){
                case 1:
                    adminLogin();
                    break;
                case 2:
                    customerLogin();
                    break;
                case 3:
                    currentView.displayMessage("Stopping program");
                    end = true;
                    break;
                default:
                    currentView.displayMessage("Please enter a numerical option as displayed in the menu");
                    break;


            }
        }catch (NumberFormatException e){
            currentView.displayMessage("Please enter a valid number");
        }

    }


}
