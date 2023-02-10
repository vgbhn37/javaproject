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

public class MainPage {

	MainFrame mainframe;
	
	// 메인프레임에서 작동
	public MainPage(MainFrame mainframe) throws IOException, ParseException {
		this.mainframe = mainframe;
		
		// 공공데이터 받기
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
		} else { System.out.println("error");
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
		
		// 받은 데이터를 Data에 저장하기
		Data mdata = new Data();
		for (Object one : arr) {
			JSONObject ob = (JSONObject) one;
			mdata.setPlace((String) ob.get("PLACE"));
			mdata.setTitle((String) ob.get("TITLE"));
		}
		mainaction(mdata);
	}

	public void mainaction(Data data) {
		mainframe.total = data.place.size();
		mainframe.maxpage = mainframe.total/10 + 1;
		int page = mainframe.page;
		StringBuilder textbd = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			if (i + (page - 1) * 10 > mainframe.total - 1) {
				break;
			}
			textbd.append(i + 1 + (page - 1) * 10 + ". " + data.place.get(i + (page - 1) * 10) + "\n\t- "
					+ data.title.get(i + (page - 1) * 10) + "\n\n");
		}
		String text = textbd.toString();
		mainframe.attraction.setText(text);
	}

}
