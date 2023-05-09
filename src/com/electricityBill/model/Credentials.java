package com.electricityBill.model;

public class Credentials {
    public enum CredentialType{CUSTOMER, ADMIN}
    private Object username;
    private Object password;
    private CredentialType credentialType;
    public Credentials(Object username, Object password, CredentialType credentialType){
        this.username = username;
        this.password = password;
    }
    public Object getUsername(){
        return this.username;
    }


}
