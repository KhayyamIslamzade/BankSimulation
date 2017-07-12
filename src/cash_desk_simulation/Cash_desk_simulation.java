/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cash_desk_simulation;

import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;


class Customer
{
    private int id;
    
    Customer(int id)
    {
       this.id=id;
    }

  
    public int getID()
    {
        return id;
    }
}



class Uretici implements Runnable
{
    Bank cash_desk=new Bank();
    Customer idver;
   private int people_count;
    public Uretici(int kisi_sayi)
    {
        this.people_count=kisi_sayi;
    }

private int numerator=0;
@Override
public void run() {
   
    try {
  Thread.sleep(900);
        for (int i = 0; i <people_count; i++) {
        System.out.print("\b");
            numerator++;
          
        idver= new Customer(numerator);
        cash_desk.people_queue.add((idver.getID()));
        System.out.println("Number of people in the queue: "+cash_desk.people_queue.size()+"  "+cash_desk.people_queue.toString());
       
        Thread.sleep(30);
        }
        
    } catch (InterruptedException ex) {
        Logger.getLogger(Uretici.class.getName()).log(Level.SEVERE, null, ex);
    }
}

}

 class Bank
{
    public static Queue people_queue = new PriorityQueue();

}

class Tuketici implements Runnable
{
    Bank banka;
    public String id_process_cashdesk;
    private String cashdesk_id;
    public Tuketici(String cashdesk_id)
    {
        this.cashdesk_id=cashdesk_id;
    }
    @Override
public void run() {
    
        try {
            Thread.sleep(1000);
           do
            {
           
             id_process_cashdesk=banka.people_queue.peek().toString();
            banka.people_queue.poll();
           
             System.out.println(cashdesk_id+"cash desk: "+id_process_cashdesk+" -people ID");
             
            Thread.sleep(100);
             } while(banka.people_queue.size()>0);
        } catch (InterruptedException ex) {
            Logger.getLogger(Tuketici.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}
}
public class Cash_desk_simulation {

    public static void main(String[] args) {
            Runnable generator= new Uretici(50);//size of people on cash desk
            Runnable consumer_1= new Tuketici("1."); 
            Runnable consumer_2= new Tuketici("2."); 
            
            
            Thread Generator=new Thread(generator);
            Thread Consumer_1=new Thread(consumer_1);
            Thread Consumer_2=new Thread(consumer_2);


            Generator.start();
            Consumer_1.start();
            Consumer_2.start();
    }
    
}
