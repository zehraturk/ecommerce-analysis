package com.ecommerce.Service;
import com.ecommerce.Producer.SearchProducer;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class SearchService {
    @Autowired
    SearchProducer searchProducer;

    List<String> cities= Arrays.asList("Ankara", "İstanbul", "Adana", "Zonguldak", "Malatya", "Elazığ", "İzmir", "Trabzon");
    List<String> products=Arrays.asList("Telefon kabı","Elbise","Çanta","Cüzdan","Cep telefonu","Bardak","Tencere","Kulaklık","Mouse","Klavye");

    public void search(String search_term){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        while (true){
            Random random = new Random();
            int city_index = random.nextInt(cities.size());
            int product_index=random.nextInt(products.size());
            long offset = Timestamp.valueOf("2020-07-28 01:00:00").getTime();
            long end = Timestamp.valueOf("2020-07-28 23:59:00").getTime();
            long diff = end - offset + 1;
            Timestamp random_ts = new Timestamp(offset + (long) (Math.random() * diff));

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("search",products.get(product_index));
            jsonObject.put("city",cities.get(city_index));
            jsonObject.put("userID",random.nextInt(2000));
            jsonObject.put("current_ts",random_ts.toString());

            searchProducer.send(jsonObject.toJSONString());
            System.out.println(jsonObject.toJSONString());
        }
    }


    public void searchStream(String search_term){
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Random random = new Random();
            int city_index = random.nextInt(cities.size());

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("search",search_term);
            jsonObject.put("city",cities.get(city_index));
            jsonObject.put("userID",random.nextInt(2000));
            jsonObject.put("current_ts",timestamp.toString());

            searchProducer.send(jsonObject.toJSONString());
            System.out.println(jsonObject.toJSONString());

    }

}
