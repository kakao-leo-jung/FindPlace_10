package com.hanium.findplace.findplace_10.naver_api;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class SearchLocation extends AsyncTask<Void, Void, String> {

    //member Variables
    private final String clientId = "uNwZ0jwOscgSGh264TFc";//애플리케이션 클라이언트 아이디값";;
    private final String clientSecret = "EfU0rUlk6z";//애플리케이션 클라이언트 시크릿값";
    private List<SetMyLocation> retLocationList;
    private String queryString;

    //constructor
    public SearchLocation(){
        retLocationList = new ArrayList<>();
    }
    public SearchLocation(String queryString){
        this();
        this.queryString = queryString;
    }


    @Override
    public String doInBackground(Void... String) {
        //추후 수정해야함. 백그라운드 다른쓰레드에서 메인UI 조정할라하면 에러가 뜰수가 있다.
        getSearchLocationList(queryString);
        
        return "";
    }

    @Override
    protected void onProgressUpdate(Void... String){

    }

    @Override
    protected void onPostExecute(String s){
        super.onPostExecute(s);

    }


    //Methods
    public List<SetMyLocation> getSearchLocationList(String inputText){
        String resultJson = null;
        //Get AddressInfo by JSON type
        try{
            String query = URLEncoder.encode(inputText, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/local?query="+query;

            Log.d("mylog", "Connetcon to REstAPI 진입시작!!!!!!!!!!!!!!!!!!!!!!!");
            //connection to restAPI
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            Log.d("mylog", "Connetcon to REstAPI 진입완료!!!!!!!!!!!!!!!!!!!!!!!");

            //Reade Query
            Log.d("myLog", "getResponseCode 에서 에러가발생했나? 밑에가 없으면 여기서 에서뜬거임");
            int responseCode = con.getResponseCode();
            Log.d("myLog", "getResponseCode 에서 에러가발생했나? 엥 성공적으로 에러없이 getResponseCode 함");
            BufferedReader br;
            if(responseCode == 200){    //정상호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                Log.d("myLog", "------------------------------------------정상호출");
            }else{                      //에러발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                Log.d("myLog", "------------------------------------------에러발생");
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while((inputLine = br.readLine()) != null){
                response.append(inputLine);
            }
            br.close();
            //result
            resultJson = response.toString().replaceAll(" ", "");
            Log.d("myLog", "입력위치 : "+resultJson);

        }catch (Exception e){
            System.out.println(e);
        }finally {

        }
        Log.d("myLog", "만약 위에 2개의 로그가 안뜬다면 catch문으로 진입한거임");
        Log.d("myLog", "resultJson : "+resultJson);

        //Parse String to JsonObject
        try{
            //JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) new JSONObject(resultJson);
            JSONArray retItem = (JSONArray) jsonObject.get("items");

            //retAddress 클래스에 주소정보 입력함.
            for(int i = 0; i < retItem.length(); i++){
                SetMyLocation tmpLocation = new SetMyLocation();
                JSONObject tmpObject = (JSONObject) retItem.get(i);
                tmpLocation.setTitle(String.valueOf(tmpObject.get("title")));
                tmpLocation.setAddress(String.valueOf(tmpObject.get("address")));
                tmpLocation.setMapx(Integer.parseInt(String.valueOf(tmpObject.get("mapx"))));
                tmpLocation.setMapy(Integer.parseInt(String.valueOf(tmpObject.get("mapy"))));
                retLocationList.add(tmpLocation);
            }
            Log.d("myLog", "getAddressInfo: "+retLocationList.get(0).getAddress());

        }catch(Exception e){
            e.printStackTrace();
        }
        return retLocationList;
    }

    public List<SetMyLocation> getRetLocationList(){
        return retLocationList;
    }

}