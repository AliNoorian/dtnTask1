/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dotin.dotintask1;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Alireza
 */
public class Transaction implements Serializable {
    
    
    private String debtorDepositNumber;
    private String creditorDepositNumber;
    private BigDecimal amount;
  

    
    
    public Transaction() {
        
    }
    
    public Transaction(String debtorDepositNumber, String creditorDepositNumber, BigDecimal amount) {
        this.debtorDepositNumber = debtorDepositNumber;
        this.creditorDepositNumber = creditorDepositNumber;
        this.amount = amount;
       
    }

    public String getDebtorDepositNumber() {
        return debtorDepositNumber;
    }

    public String getCreditorDepositNumber() {
        return creditorDepositNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

   

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public void setCreditorDepositNumber(String creditorDepositNumber) {
        this.creditorDepositNumber = creditorDepositNumber;
    }
    public void setDebtorDepositNumber(String debtorDepositNumber) {
        this.debtorDepositNumber = debtorDepositNumber;
    }
   

    @Override
    public String toString() {
        return debtorDepositNumber + "\t" + creditorDepositNumber + "\t" + amount ;
    }
    
}
