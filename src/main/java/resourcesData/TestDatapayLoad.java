package resourcesData;

import pojo.Add_Place;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDatapayLoad {



    public Add_Place AddPlace_Payload(String Address, String Language, String Name)
    {

        Add_Place Place= new Add_Place();
        Place.setAccuracy(80);
        Place.setAddress(Address);
        Place.setLanguage(Language);
        Place.setPhone_number(797669696);
        Place.setName(Name);
        Place.setWebsite("http://google.com");
        Location L= new Location();
        L.setLat(-394939);
        L.setLng(4546.536356);
        Place.setLocation(L);
        List<String> D= new ArrayList<>();
        D.add("shoe");
        D.add("Ader");
        Place.setTypes(D);

        return Place;
    }
}
