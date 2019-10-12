package apimanager;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import model.Address;
import model.Txrefs;
import org.apache.http.client.fluent.Request;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ApiTest2Helper extends ApiHelperBase {

    String latest_url;
    String firstTxIdFromLatestUrl;
    String jsonAddress;
    String firstAddressFromList;


    public String getLatestUrl() throws IOException, ParseException {

        String header = "https://api.blockcypher.com/v1/btc/main";
        String json = Request.Get(header)
                .addHeader("Content-Type", "application/json")
                .execute().returnContent().asString();
        //System.out.println( "json*** :"  + json);

        JSONParser jsonParser = new JSONParser();
        JSONObject parsed = (JSONObject) jsonParser.parse(json);
        latest_url = (String) parsed.get("latest_url");

        System.out.println(latest_url);
        return latest_url;
    }

    public String getFirstTxIdFromLatestUrl() throws IOException, ParseException {

        List<String> txIdsList = new ArrayList<>();

        String header = latest_url;
        String json = Request.Get(header)
                .addHeader("Content-Type", "application/json")
                .execute().returnContent().asString();

        //System.out.println("json : " + json);

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(json);

        JSONArray txids = (JSONArray) jsonObject.get("txids");
        //System.out.println("JSONArray txids :" + txids);

        for(int i=0; i<=txids.size()-1; i++) {
            txIdsList.add((String) txids.get(i));
        }

        System.out.println("txIdsList :" + txIdsList);
        firstTxIdFromLatestUrl = txIdsList.get(0);
        return firstTxIdFromLatestUrl;
    }


    public String getAddrsFromFirstTx() throws IOException, ParseException, InterruptedException {

        List<String> listAddrs = new ArrayList<>();
        Thread.sleep(3000);

        String header = "https://api.blockcypher.com/v1/btc/main/txs/" + firstTxIdFromLatestUrl;
        //System.out.println("header :" + header);

        String json = Request.Get(header)
                .addHeader("Content-Type", "application/json")
                .execute().returnContent().asString();

        //System.out.println("json : " + json);

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(json);
        JSONArray addrs = (JSONArray) jsonObject.get("addresses");

        //System.out.println("JSONArray addrs :" +  addrs);

        for (int i=0; i<=addrs.size()-1; i++) {
            listAddrs.add((String) addrs.get(i));
        }

        System.out.println("listAddrs :" + listAddrs);
        firstAddressFromList = listAddrs.get(0);
        return firstAddressFromList;
    }


    public String getJsonAddress() throws IOException, InterruptedException {

        String header = "https://api.blockcypher.com/v1/btc/main/addrs/" + firstAddressFromList;
        Thread.sleep(2000);
        jsonAddress = Request.Get(header)
                .addHeader("Content-Type", "application/json")
                .execute().returnContent().asString();

        System.out.println("jsonAddress in method getJsonAddress() " + jsonAddress);
        return jsonAddress;
    }


    public Address getValuesFromAddressTop()  {

        JsonParser jsonParser = new JsonParser();

        //FileReader file = new FileReader("src/test/resources/jsonHW2.json");
        JsonElement parsed_1 = jsonParser.parse(jsonAddress);

        Address addrs =
                new Gson().fromJson(parsed_1, new TypeToken<Address>(){}.getType());
        //System.out.println("addrs :" + addrs);

        Address objAddressFromTop = new Address()
                .withBalance(addrs.getBalance())
                .withTotal_sent(addrs.getTotal_sent())
                .withTotal_received(addrs.getTotal_received());

        System.out.println("objAddressFromTop :" + objAddressFromTop);
        return objAddressFromTop;
    }

    public Address getValuesFromAddressArray()  {

        //FileReader file = new FileReader("src/test/resources/jsonHW2.json");

        String json_1 = jsonAddress.replace("\"spent\": false","\"spent\": \"false\"");
        String json_2 = json_1.replace("\"spent\": true","\"spent\": \"true\"");

        JsonParser jsonParser = new JsonParser();
        JsonArray parsed_2 =  jsonParser.parse(json_2).getAsJsonObject().getAsJsonArray("txrefs");

        List<Txrefs> txRefs = new Gson().fromJson(parsed_2, new TypeToken<List<Txrefs>>(){}.getType());
        //System.out.println("txrefs :" + txrefs);

        long sumValuesFalse = 0;
        long sumValuesTrue = 0;
//      long sumValuesNull = 0;

        System.out.println("txrefs.size() :" + txRefs.size());

        for(Txrefs i: txRefs) {

            String spent = i.getSpent();

            if(spent == null) {
//                System.out.println("null");
//                sumValuesNull += i.getValue() ;
//                System.out.println(i.getValue());
            } else {

                if (spent.equals("false")) {
//                  System.out.println("false :");
                    sumValuesFalse += i.getValue() ;
//                  System.out.println(i.getValue());
                }
                else  {
//                  System.out.println("true :");
                    sumValuesTrue += i.getValue();
//                  System.out.println(i.getValue());
                }
            }
        }

        Address objAddressFromArray = new Address()
                .withTotal_received(sumValuesFalse + sumValuesTrue)
                .withTotal_sent(sumValuesTrue)
                .withBalance(sumValuesFalse);

        System.out.println("objAddressFromArray :" + objAddressFromArray);
        return objAddressFromArray;
    }

}
