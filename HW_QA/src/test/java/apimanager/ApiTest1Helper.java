package apimanager;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import model.AvPrice;
import model.AvPriceFromBitcoinaverage;
import model.AvPriceFromblockchain;
import org.apache.http.client.fluent.Request;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ApiTest1Helper extends ApiHelperBase {

    public List<AvPrice> getListLast30DaysFromBitcoinAverage() throws IOException, ParseException {

        List<AvPrice> listFromBitcoinaverage = new ArrayList<>();

//        FileReader jsonFile = new FileReader("src/test/resources/json1.json");

        String header = "https://apiv2.bitcoinaverage.com/indices/global/history/BTCUSD?period=alltime";
        String json = Request.Get(header)
                .addHeader("Content-Type", "application/json")
                .execute().returnContent().asString();


        JsonParser jsonParser = new JsonParser();
        JsonArray parsed = (JsonArray) jsonParser.parse(json);

        List<AvPriceFromBitcoinaverage> avPrice_1 =
                new Gson().fromJson(parsed, new TypeToken<List<AvPriceFromBitcoinaverage>>(){}.getType());
//        System.out.println("avPrice_1 : " + avPrice_1);

       List<AvPriceFromBitcoinaverage> subListLast30Days = avPrice_1.subList(0,30);
       // System.out.println("subListLast30Days : " + subListLast30Days);

        for(AvPriceFromBitcoinaverage i : subListLast30Days) {
            Double average = i.getAverage();
            String time = i.getTime();
           // String time2 = "2019-10-03 00:00:00";

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Timestamp ts = new Timestamp(df.parse(time).getTime());

          //  System.out.println("ts : " + ts);

            AvPrice oneObj = new AvPrice()
                    .withAverage(average)
                    .withTs(ts);

          //  System.out.println("avPriceFromBitcoinAverage : " + avPriceFromBitcoinAverage);

            listFromBitcoinaverage.add(oneObj);
        }

       // System.out.println("listFromBitcoinaverage :" + listFromBitcoinaverage);

        listFromBitcoinaverage.sort(Comparator.comparing(AvPrice::getTs));
        System.out.println("listFromBitcoinaverage :" + listFromBitcoinaverage);

        return listFromBitcoinaverage;
    }

    public List<AvPrice> getListLast30DaysFromBlockchain() throws IOException, ParseException {

        List<AvPrice> listFromBlockchain = new ArrayList<>();

        //FileReader jsonFile = new FileReader("src/test/resources/json2.json");

        String header = "https://api.blockchain.info/charts/market-price?timespan=30days&format=json";
        String json = Request.Get(header)
                .addHeader("Content-Type", "application/json")
                .execute().returnContent().asString();


        JsonParser jsonParser = new JsonParser();
        JsonArray parsed =  jsonParser.parse(json).getAsJsonObject().getAsJsonArray("values");

        List<AvPriceFromblockchain> list =
                new Gson().fromJson(parsed, new TypeToken<List<AvPriceFromblockchain>>(){}.getType());

        for(AvPriceFromblockchain i: list ) {
            long tm = i.getX();
            double average = i.getY();

            String time = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date (tm*1000));

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Timestamp ts = new Timestamp(df.parse(time).getTime());

           // System.out.println("i.getX() :" + i.getX());
           // System.out.println("i.getY() :" + i.getY());

            AvPrice oneObj = new AvPrice()
                           .withAverage(average)
                           .withTs(ts);

            listFromBlockchain.add(oneObj);
        }

        listFromBlockchain.sort(Comparator.comparing(AvPrice::getTs));
        System.out.println("listFromBlockchain :" + listFromBlockchain);
        return  listFromBlockchain;
    }
}
