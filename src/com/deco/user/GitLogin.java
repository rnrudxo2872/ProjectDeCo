package com.deco.user;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import javax.net.ssl.HttpsURLConnection;

public class GitLogin {
    /**
     * Post 방식
     * Http
     * @throws Exception
     */
	private String CLIENT_CODE = "3623794773adf0eda9c5";
	private String SECRET_CODE = "781cceeaff2e2a2668fa639c22f0e49a5fde7da0";
	
    public String getCLIENT_CODE() {
		return CLIENT_CODE;
	}

	public String getSECRET_CODE() {
		return SECRET_CODE;
	}

	public String sendPost(String finURL) throws Exception {

        String url = finURL;
        //String urlParameters = "?Param1=aaaa"+"&Param2=bbbb";
        
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        
        //set reuqest header
        con.setRequestMethod("POST");
//      con.setRequestProperty("User-Agent", "Mozilla/5.0");
//      con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.setRequestProperty("Accept", "application/json");
        con.setConnectTimeout(10000);       //컨텍션타임아웃 10초
        con.setReadTimeout(5000);           //컨텐츠조회 타임아웃 5초
        
        // Send post request
        con.setDoOutput(true);              //항상 갱신된내용을 가져옴.
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        //wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        //System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        Charset charset = Charset.forName("UTF-8");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(),charset));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        
        System.out.println("반환"+response.toString());
        return response.toString();
    }
	
	public String sendGet(String apiURL, String access_token) throws Exception {

        String url = apiURL;
        //String urlParameters = "?Param1=aaaa"+"&Param2=bbbb";
        
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        
        //set reuqest header
        con.setRequestMethod("GET");
        con.setRequestProperty("method", "GET");
        con.setRequestProperty("Authorization", "token"+" "+access_token);
        con.setConnectTimeout(10000);       //컨텍션타임아웃 10초
        con.setReadTimeout(5000);           //컨텐츠조회 타임아웃 5초
        
        // Send post request
        con.setDoOutput(true);              //항상 갱신된내용을 가져옴.
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        //wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        //System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        Charset charset = Charset.forName("UTF-8");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(),charset));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        
        System.out.println("반환"+response.toString());
        return response.toString();
    }
}
