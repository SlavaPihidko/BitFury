package tests;


import model.Address;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import java.io.IOException;
import static org.testng.Assert.assertEquals;

public class Test2 extends TestBase {

    @Test
    public void test2() throws IOException, ParseException, InterruptedException {
        String latestUrl = am.getApiTest2Helper().getLatestUrl();
        String firstTxIdFromLatestUrl = am.getApiTest2Helper().getFirstTxIdFromLatestUrl();
        String address = am.getApiTest2Helper().getAddrsFromFirstTx();
        String jsonAddress = am.getApiTest2Helper().getJsonAddress();
        Address valuesAddressFromTop = am.getApiTest2Helper().getValuesFromAddressTop();
        Address valuesAddressFromArray = am.getApiTest2Helper().getValuesFromAddressArray();

// compare objects by the following parameters: total_received, total_sent, balance
        assertEquals(valuesAddressFromTop, valuesAddressFromArray);
    }
}
