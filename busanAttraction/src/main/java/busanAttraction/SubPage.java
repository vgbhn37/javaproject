package busanAttraction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class SubPage {
	
	MainFrame mainframe;
	SubFrame subframe;
	int number;

public SubPage(MainFrame mainframe,SubFrame subframe) throws IOException, ParseException {
		
		this.mainframe = mainframe;
		this.subframe = subframe;

		StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/6260000/AttractionService/getAttractionKr");
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "="
				+ "Dxq9ipTQilxdkEVXuXtGur7rzNf6ubNOpMKTd%2FhgYAUi6c618PmDrVWW5DtPAyr515TebsJlwHWIoK3km7g8Vg%3D%3D");
		urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("123", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("resultType", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));// json타입

		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");

		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(sb.toString());
		JSONObject jsonObject2 = (JSONObject) jsonObject.get("getAttractionKr");
		JSONArray arr = (JSONArray) jsonObject2.get("item");

		Data sdata = new Data();
		for (Object one : arr) {
			JSONObject ob = (JSONObject) one;
			sdata.setPlace((String) ob.get("PLACE"));
			sdata.setTitle((String) ob.get("TITLE"));
			sdata.setAddr1((String) ob.get("ADDR1"));
			sdata.setCntct_tel((String) ob.get("CNTCT_TEL"));
			sdata.setHomepage_url((String) ob.get("HOMEPAGE_URL"));
			sdata.setTrfc_info((String) ob.get("TRFC_INFO"));
			sdata.setUsage_day_week_and_time((String) ob.get("USAGE_DAY_WEEK_AND_TIME"));
			sdata.setHldy_info((String) ob.get("HLDY_INFO"));
			sdata.setUsage_amount((String) ob.get("USAGE_AMOUNT"));
			sdata.setMiddle_size_rm1((String) ob.get("MIDDLE_SIZE_RM1"));
			sdata.setMain_img_normal((String) ob.get("MAIN_IMG_NORMAL"));
		}
		subaction(sdata);
	}

	public void subaction(Data data) {

		number = Integer.parseInt(mainframe.number.getText().trim());

		subframe.place.setText("이름 : " + data.place.get(number - 1));
		subframe.title.setText("간략소개 : " + data.title.get(number - 1));
		subframe.addr1.setText(data.addr1.get(number - 1));
		subframe.cntct_tel.setText("전화번호 : " + data.cntct_tel.get(number - 1));
		subframe.homepage_url.setText(data.homepage_url.get(number - 1));
		subframe.trfc_info.setText("교통정보 : " + data.trfc_info.get(number - 1));
		subframe.usage_day_week_and_time.setText("이용가능시간 : " + data.usage_day_week_and_time.get(number - 1));
		subframe.hldy_info.setText("휴일정보 : " + data.hldy_info.get(number - 1));
		subframe.usage_amount.setText("이용료 : " + data.usage_amount.get(number - 1));
		subframe.middle_size_rm1.setText("장애인편의시설 : " + data.middle_size_rm1.get(number - 1));
		subframe.main_img_normal.setText(data.main_img_normal.get(number - 1));

	}

}
