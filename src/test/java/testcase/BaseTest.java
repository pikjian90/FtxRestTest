package testcase;

import common.EndPoints;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;

import java.util.HashMap;

public class BaseTest {
    public static HashMap<String,String> hm = new HashMap();

    @BeforeTest
    public void beforeTest(){
        RestAssured.baseURI = EndPoints.endPoint;
    }

    public HashMap<String,String> convertResponseResultToMap(Response res){
        JsonPath jsonPath = res.jsonPath();
        String jsonPathString = jsonPath.get("result").toString()
                .replace("{","")
                .replace("}","")
                .replace("[","")
                .replace("]","");

//        System.out.println(jsonPathString);
        String[] s = jsonPathString.split(",");
        for(int i=0;i<s.length;i++){
            String[] sSplit = s[i].split("=");
            if(sSplit[0].startsWith(" ")){
                sSplit[0] = sSplit[0].substring(1,sSplit[0].length());
            }
            hm.put(sSplit[0],sSplit[1]);
        }

// for (Map.Entry<String, String> set : hm.entrySet()) {
//     System.out.println(set.getKey() + " = " + set.getValue());
// }

        return hm;
    }
}
