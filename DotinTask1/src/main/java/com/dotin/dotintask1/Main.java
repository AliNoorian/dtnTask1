
package com.dotin.dotintask1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

/**
 *
 * @author Alireza
 */
public class Main {
    
    public static void main(String[] args){
    
        List<Pay> payList = new ArrayList<>();
        List<Account> accountList = new ArrayList<>();
        List<Balance> balanceList = new ArrayList<>();
        List<Transaction> transactionList = new ArrayList<>();
        
        
        Scanner scanner = new Scanner(System.in);
    
        //Get input from user
        String strInput="";
        
        boolean wantContinue=true;
        
         boolean payFilePresent = false;
         boolean accountFilePresent = false;
         boolean balanceFilePresent = false;
         boolean transactionPresent = false;
         boolean payDone = false;
         boolean payDone2 = false;
         boolean notPositiveAmount=true;
                          
         
           
        
        try
        {
            FileInputStream fisAccount = new FileInputStream("accounts.tmp");
            ObjectInputStream oisAccount = new ObjectInputStream(fisAccount);
            accountList = (List<Account>) oisAccount.readObject();
            oisAccount.close();
            
            accountFilePresent = true;
            
        }
        catch(IOException | ClassNotFoundException T)
        {
            accountFilePresent = false;
        }
        
        
        try
        {
            FileInputStream fisPay = new FileInputStream("pays.tmp");
            ObjectInputStream oisPay = new ObjectInputStream(fisPay);
            payList = (List<Pay>) oisPay.readObject();
            oisPay.close();
            payFilePresent = true;
            
        }
        catch(IOException | ClassNotFoundException T)
        {
            payFilePresent = false;
        }
        
        
        try
        {
            FileInputStream fisBalance = new FileInputStream("balances.tmp");
            ObjectInputStream oisBalance = new ObjectInputStream(fisBalance);
            balanceList = (List<Balance>) oisBalance.readObject();
            oisBalance.close();
            balanceFilePresent = true;
            
        }
        catch(IOException | ClassNotFoundException T)
        {
            balanceFilePresent = false;
        }
        
        
        try
        {
            FileInputStream fisTransaction = new FileInputStream("transactions.tmp");
            ObjectInputStream oisTransaction = new ObjectInputStream(fisTransaction);
            transactionList = (List<Transaction>) oisTransaction.readObject();
            oisTransaction.close();
            transactionPresent = true;
            
        }
        catch(IOException | ClassNotFoundException T)
        {
            transactionPresent = false;
        }
        
        
        do
        {
                        
            System.out.println("1: Account");
            System.out.println("2: Pay");
            System.out.println("3: Balance");
            System.out.println("4: Transaction");
            System.out.println("5: Save and Exit");
            System.out.println("6: Exit");
            
            strInput = scanner.nextLine();
            
            
            switch(strInput)
            {
              case "1":
                   do
                  {
                  System.out.println("1: Add account");
                  System.out.println("2: Show account");
                  System.out.println("3: Return to main manu");
                 
                  strInput = scanner.nextLine();
                 
                  switch(strInput)
                  {
                      case "1":

                            if(!accountFilePresent)
                            {
                                System.out.println("You do not have any account file yet or missed.");
                                System.out.println("Do you want to creat new one ?(Y/N) ");
                                strInput = scanner.nextLine().toUpperCase();
                                if(strInput.equals("Y")||strInput.equals("YES"))
                                {
                                     try
                                  {
                                  FileOutputStream fosAccount3 = new FileOutputStream("accounts.tmp");
                                  ObjectOutputStream oosAccount3 = new ObjectOutputStream(fosAccount3); 
                                  oosAccount3.writeObject(accountList);
                                  oosAccount3.close();

                                  FileInputStream fisAccount2 = new FileInputStream("accounts.tmp");
                                  ObjectInputStream oisAccount2 = new ObjectInputStream(fisAccount2);
                                  accountList = (List<Account>) oisAccount2.readObject();
                                  oisAccount2.close();

                                  //Need overwrite or create other files here!

                                  accountFilePresent = true;                        
                                  }
                                  catch(Throwable t)
                                  {
                                  System.out.println("Account file created successfully");
                                  }

                                }
                            }
                            do
                            {
                                Account newAccount = new Account();
                                System.out.println("Enter your new deposit number:");
                                strInput = scanner.nextLine();
                                String inputDepositNumber; 
                                inputDepositNumber = strInput;

                                Optional<Account> first = accountList.stream()
                                            .filter(x -> Objects.equals(inputDepositNumber, x.getDepositNumber()))
                                            .findFirst();


                                if(first.isPresent())
                                    {

                                        System.out.println("\nYour account already present!");

                                    }

                                else
                                    {

                                    newAccount.setDepositNumber(inputDepositNumber);

                                    do{
                                    System.out.println("Enter amount for this new account:");
                                    strInput = scanner.nextLine();
                                    BigDecimal bigDecimal=new BigDecimal(strInput);
                                    if(!(bigDecimal.doubleValue()<0))
                                    {

                                        newAccount.setAmount(bigDecimal);
                                        notPositiveAmount = false;
                                    }
                                    else
                                        System.out.println("Wrong amount, enter positive amount");

                                    }while(notPositiveAmount);
                                     notPositiveAmount = true;
                                    accountList.add(newAccount);
                                    }

                                System.out.println("Do you want to add new account ?(Y/N) ");
                                strInput = scanner.nextLine().toUpperCase();

                                if(strInput.equals("N")||strInput.equals("No"))
                                    wantContinue=false;


                            }while(wantContinue);
                            wantContinue=true;


                          break;
                     
                      case "2":
                            if(accountList.isEmpty())
                          {
                              System.out.println("You do not have any account yet ");
                              
                              
                          }
                          else
                          {
                           
                              System.out.println("depositNumber\tamount\n");
                              accountList.forEach((account) -> {
                              System.out.println(account.toString());
                          });
                              System.out.println("\n");

                          }
                          break;

                      case "3":
                          wantContinue=false;
                          break;
                          
                  }

                  }while(wantContinue);
                  wantContinue=true;
         



                  break;

              case "2":
                  do{
                  System.out.println("1: Do pay");
                  System.out.println("2: Show pay");
                  System.out.println("3: Return to main manu");
                  
                 
                  strInput = scanner.nextLine();
                 
                  switch(strInput)
                  {
                     
                      case "1":
                     
                          System.out.println("Enter your deposit number:");
                          strInput = scanner.nextLine();
                          String inputDepositNumber; 
                          inputDepositNumber = strInput;
                          Optional<Account> first = accountList.stream()
                                  .filter(x -> Objects.equals(inputDepositNumber, x.getDepositNumber()))
                                  .findFirst();
                          //find deposit number that you want to pay
                          if(first.isPresent())
                          {
                              

                                  //pay(deptor)
                                  Pay newPay = new Pay();
                                  newPay.setDeptorOrCreditor("debtor");  
                                  newPay.setDepositNumber(inputDepositNumber);
                                  newPay.setAmount(first.get().getAmount());
                               
                                  //pay(creditor)
                                  Pay newPay2 = new Pay();
                                  System.out.println("Enter your deposit number that you want to pay:");
                                  strInput = scanner.nextLine();
                                  String inputDepositNumber2; 
                                  inputDepositNumber2 = strInput;
                                  Optional<Account> first2 = accountList.stream()
                                            .filter(x -> Objects.equals(inputDepositNumber2, x.getDepositNumber()))
                                  .findFirst();
                          
                                  //find account(creditor) in account 
                                  if(first2.isPresent())
                                  {
                                      newPay2.setDeptorOrCreditor("creditor");
                                      newPay2.setDepositNumber(inputDepositNumber2);
                                       BigDecimal bigDecimal;
                                      
                                      do{
                                         System.out.println("How much do you want to pay:");
                                         strInput = scanner.nextLine();
                                         bigDecimal=new BigDecimal(strInput);
                                        
                                         if(!(bigDecimal.doubleValue()<0))
                                            notPositiveAmount = false;
                                         else
                                            System.out.println("Wrong amount, enter positive amount");

                                        }while(notPositiveAmount);
                                         notPositiveAmount = true;
                          
                                      if(!(accountList.get(accountList.indexOf(first.get())).getAmount().compareTo(bigDecimal)<0))
                                      {
                                          System.out.println("Are you sure to do this cash?(Y/N) ");
                                          strInput = scanner.nextLine().toUpperCase();
                                          if(strInput.equals("Y")||strInput.equals("YES"))
                                          {
                                              
                                              accountList.get(accountList.indexOf(first.get())).setAmount(first.get().getAmount().subtract(bigDecimal));
                                              accountList.get(accountList.indexOf(first2.get())).setAmount(first2.get().getAmount().add(bigDecimal));
                                               
                                              newPay2.setAmount(bigDecimal);
                                              
                                              //save deposit number that you want to pay(deptor) in to the pay list
                                              payList.add(newPay);
                                       
                                              //save deposit number that you want to pay(deptor) yin to the balance list
                                              Balance newBalance = new Balance(first.get().getDepositNumber(),first.get().getAmount());
                                              balanceList.add(newBalance);
                                              
                                              //save deposit number that give pay (creditor) in to the balance list
                                              Balance newBalance2  =  new Balance(first2.get().getDepositNumber(),bigDecimal);
                                              balanceList.add(newBalance2);
                                              
                                              //save transactions in to the transaction list list
                                              Transaction newTransaction  =  new Transaction(first.get().getDepositNumber(), first2.get().getDepositNumber(), bigDecimal);
                                              transactionList.add(newTransaction);
                                           
                                              //save deposit number that give pay (creditor) in to the pay list
                                              payList.add(newPay2);
                                              
                                              System.out.println("You pay done successfully\n");
                                              payDone=true;
                                          }
                                      }
                                      else
                                              System.out.println("You do not have enough money to pay\n");

                                          
                         
                                  }
                          }
                
                          else
                          
                          {
                              System.out.println("You do not have any account in our bank :D ");
                              System.out.println("Maybe you enter wrong deposit number,try it again ");
                          
                          }

             
                          break;
                          
                    case "2":
                            if(payList.isEmpty())
                          {
                              System.out.println("You do not have any pay yet ");
                              System.out.println("Do some pay and try again ");
                              
                          }
                          else
                          {
                           
                              System.out.println("debtor/creditor\tdepositNumber\tamount\n");
                              payList.forEach((pay) -> {
                              System.out.println(pay.toString());
                          });
                              System.out.println("\n");

                          }
                          break;

                          
                    case "3":
                       
                          wantContinue=false;
                          break;
                          
                          
                  }

                  }while(wantContinue);
                  wantContinue=true;
                  break;
              case "3":
                 
                   do
                  {
                  System.out.println("1: Show Balance");
                  System.out.println("2: Return to main manu");
                 
                  strInput = scanner.nextLine();
                 
                  switch(strInput)
                  {
                      case "1":
                          if(balanceList.isEmpty())
                          {
                              System.out.println("You do not have any balance yet ");
                              System.out.println("Do some pay and try again ");
                              
                          }
                          else
                          {
                            System.out.println("depositNumber\tamount\n");
                            balanceList.forEach((balance) -> {
                              System.out.println(balance.toString());
                            });
                              System.out.println("\n");

                          }
                          break;
                     
                      case "2":
                          wantContinue=false;
                          break;
                          
                  }

                  }while(wantContinue);
                  wantContinue=true;
                  break;
                  
              case "4":
                 
                   do
                  {
                  System.out.println("1: Show Transaction");
                  System.out.println("2: Return to main manu");
                 
                  strInput = scanner.nextLine();
                 
                  switch(strInput)
                  {
                      case "1":
                          if(transactionList.isEmpty())
                          {
                              System.out.println("You do not have any transaction yet ");
                              System.out.println("Do some pay and try again ");
                              
                          }
                          else
                          {
                            System.out.println("debtorDepositNumber\tcreditorDepositNumber\tamount\n");
                            transactionList.forEach((transaction) -> {
                              System.out.println(transaction.toString());
                            });
                              System.out.println("\n");                                  
                          }
                          break;
                     
                      case "2":
                          wantContinue=false;
                          break;
                          
                  }

                  }while(wantContinue);
                  wantContinue=true;
                  break;
                        
                  
              case "5":
                  
                 
                  //save file if you dont want to write it again:D
                  try
                    {
                        FileOutputStream fosAccount = new FileOutputStream("accounts.tmp");
                        ObjectOutputStream oosAccount = new ObjectOutputStream(fosAccount); 
                        oosAccount.writeObject(accountList);
                        oosAccount.close();


                    }
                    catch(Throwable t)
                    {
                        System.out.println("Account file can not save, call programmer!!!");

                    }
                  
                  
                 
                  try
                    {
                        FileOutputStream fosPay = new FileOutputStream("pays.tmp");
                        ObjectOutputStream oosPay = new ObjectOutputStream(fosPay); 
                        oosPay.writeObject(payList);
                        oosPay.close();


                    }
                    catch(Throwable t)
                    {
                        System.out.println("Pay file can not save, call programmer!!!");

                    }
                 
                 
                 
                  try
                    {
                        FileOutputStream fosBalance = new FileOutputStream("balances.tmp");
                        ObjectOutputStream oosBalance = new ObjectOutputStream(fosBalance); 
                        oosBalance.writeObject(balanceList);
                        oosBalance.close();


                    }
                    catch(Throwable t)
                    {
                        System.out.println("Balance file can not save, call programmer!!!");

                    }
                 
                 
                 
                  try
                    {
                        FileOutputStream fosTransaction = new FileOutputStream("transactions.tmp");
                        ObjectOutputStream oosTransaction = new ObjectOutputStream(fosTransaction); 
                        oosTransaction.writeObject(transactionList);
                        oosTransaction.close();


                    }
                    catch(Throwable t)
                    {
                        System.out.println("Transaction file can not save, call programmer!!!");

                    }
                  
                  System.out.println("All files saved successfully");
                  wantContinue=false;
                  break;
              
              case "6":
                    wantContinue=false;
                  break;
                    
                   
            
            }
            
        
        
        
        
        }while(wantContinue);
        
        
    
    }
    
}
