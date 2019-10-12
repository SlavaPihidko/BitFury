package tests;

import model.AvPrice;
import org.testng.annotations.Test;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import static org.testng.Assert.assertEquals;

public class Test1 extends TestBase {

    @Test
    public void test1() throws IOException, ParseException {

        List<AvPrice> listFromBitcoinAverage = am.getApiTest1Helper().getListLast30DaysFromBitcoinAverage();
        List<AvPrice> listFromBlockchain = am.getApiTest1Helper().getListLast30DaysFromBlockchain();


// Added parameter "check".
// If "check" true - it means that satisfy follow condition y1 <= yFromList1  <= y2.
// Where "y1" - is lower bound,
//       "y2" - is upper bound of "y" value from Blockchain API.
//       yFromList1 - is value from Bitcoinaverage API.

        for (int i=0; i <= listFromBitcoinAverage.size()-1; i++) {
           double yFromList1 =  listFromBitcoinAverage.get(i).getAverage();
           double yFromList2 = listFromBlockchain.get(i).getAverage();
              double y1 = yFromList2 - (yFromList2/1000); // lower bound
              double y2 = yFromList2 + (yFromList2/1000); // upper bound
              System.out.println("y1 " + y1);
              System.out.println("yFromList1 : " + yFromList1);
              System.out.println("y2 " + y2);
              System.out.println("=======");
            if (y1 <= yFromList1 & yFromList1 <= y2 )
            {
                listFromBitcoinAverage.get(i).withCheck(true);
                listFromBlockchain.get(i).withCheck(true);
            } else {
                listFromBitcoinAverage.get(i).withCheck(false);
                listFromBlockchain.get(i).withCheck(true);
              }
        }

        System.out.println("after if block :");
        System.out.println("listFromBitcoinaverage : " + listFromBitcoinAverage);
        System.out.println("listFromBlockchain :" + listFromBlockchain);
// compare objects by the following parameters: ts, check
        assertEquals(listFromBitcoinAverage, listFromBlockchain);

    }
}
