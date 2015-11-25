/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deploy;

import entity.Currdesc;
import entity.Currvalues;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Adam
 */
public class tester {
   
    public static void main(String[] args) {
        BankXmlReader xrd = new BankXmlReader();
        String[][] sth=xrd.getResults();
        xrd.showResults(sth);
       // persistDanishBank(em, sth);
        
    }
    private static void persistDanishBank (EntityManager em, String[][] result)
  {
    Currdesc currdesc;
    Currvalues currvalue;
    BankXmlReader bxr = new BankXmlReader();
    String[][] resultSet = bxr.getResults();
    
    try
    {
        for (int i=0;i<resultSet.length+1;i++)
        {
            currdesc = new Currdesc(resultSet[i][bxr.indexOfcode()],resultSet[i][bxr.indexOfdesc()]);
            currvalue= new Currvalues(resultSet[i][bxr.indexOfdate()], Float.parseFloat(resultSet[i][bxr.indexOfvalue()]), currdesc);
            em.getTransaction().begin();
            
            em.persist(currdesc);
            em.persist(currvalue);
            em.getTransaction().commit();
            
            
        }
    }
    catch (Exception e)
    {
        //      Currdesc currdesc = new Currdesc("PLN", "Polska waluta");
//      Currvalues currvalue = new Currvalues("24-05-1995", (56.34), currdesc);
        e.printStackTrace();
    }
  }
}
