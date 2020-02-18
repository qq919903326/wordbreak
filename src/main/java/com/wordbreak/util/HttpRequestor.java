package com.wordbreak.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 * @author hwh
 *
 */
public class HttpRequestor {
	private static CloseableHttpClient httpClient = HttpClients.createDefault();

	public static String charSet = "utf-8";

	private HttpRequestor() {
	}

	public static byte[] doGet(String url) throws Exception {
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = httpClient.execute(httpGet);
		try {
			return EntityUtils.toByteArray(response.getEntity());
		} finally {
			response.close();
		}
	}
	public static String doPost(String url, Map<String, Object> parameterMap) throws Exception {
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		Iterator<String> keys = parameterMap.keySet().iterator();
		while (keys.hasNext()) {
			String key = keys.next();
			nvps.add(new BasicNameValuePair(key, (String) parameterMap.get(key)));
		}
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, charSet));
		CloseableHttpResponse response = httpClient.execute(httpPost);
		try {
			return EntityUtils.toString(response.getEntity(), charSet);
		} finally {
			response.close();
		}
	}
	public static String doPost(String url, String json) throws Exception {
	// 将JSON进行UTF-8编码,以便传输中文
      HttpPost httpPost = new HttpPost(url);
      httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
      StringEntity se = new StringEntity(json,charSet);
      se.setContentType("text/json");
      httpPost.setEntity(se);
      CloseableHttpResponse response = httpClient.execute(httpPost);
      try {
          return EntityUtils.toString(response.getEntity(), charSet);
      } finally {
          response.close();
      }
  }
	public static List doGetBlock(String url) {
		try {
			HttpRequest request = HttpRequest.get(url);
			HttpResponse response = request.send();
			//System.out.println(response.bodyText());
			return JSON.parseObject(response.bodyText(), List.class);
		} catch (Exception e) {
			return null;
		}
	}
}