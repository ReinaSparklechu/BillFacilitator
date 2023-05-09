package com.electricityBill.service;

import com.electricityBill.exception.InvalidCredentialsException;
import com.electricityBill.model.Credentials;
import com.electricityBill.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AdminLogInService {

    public static Credentials login(String username, String password) throws InvalidCredentialsException {
        Map<String,String> adminAccounts = new HashMap<>();
        try{
            Scanner scanner = new Scanner(new File("Admin_Credentials.txt"));
            while (scanner.hasNextLine()){
                String[] credentialData = scanner.nextLine().split(":");
                adminAccounts.put(credentialData[0],credentialData[1]);
            }

            if(!adminAccounts.containsKey(username)|| (!password.equals(adminAccounts.get(username)))){
                throw new InvalidCredentialsException();
            }else{
                return new Credentials(username,password, Credentials.CredentialType.ADMIN);
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
