package busanAttraction;

import java.util.ArrayList;

public class Data {

	// 데이터 배열 생성
	ArrayList<String> place = new ArrayList<String>();
	ArrayList<String> title = new ArrayList<String>();
	ArrayList<String> addr1 = new ArrayList<String>();
	ArrayList<String> cntct_tel = new ArrayList<String>();
	ArrayList<String> homepage_url = new ArrayList<String>();
	ArrayList<String> trfc_info = new ArrayList<String>();
	ArrayList<String> usage_day_week_and_time = new ArrayList<String>();
	ArrayList<String> hldy_info = new ArrayList<String>();
	ArrayList<String> usage_amount = new ArrayList<String>();
	ArrayList<String> middle_size_rm1 = new ArrayList<String>();
	ArrayList<String> main_img_normal = new ArrayList<String>();


	
	// String을 입력 받아 Array에 추가

	public void setPlace(String string) {
		place.add(string);
	}

	public void setTitle(String string) {
		title.add(string);
	}

	public void setAddr1(String string) {
		addr1.add(string);
	}

	public void setCntct_tel(String string) {
		cntct_tel.add(string);
	}

	public void setHomepage_url(String string) {
		homepage_url.add(string);
	}

	public void setTrfc_info(String string) {
		trfc_info.add(string);
	}

	public void setUsage_day_week_and_time(String string) {
		usage_day_week_and_time.add(string);
	}

	public void setHldy_info(String string) {
		hldy_info.add(string);
	}

	public void setUsage_amount(String string) {
		usage_amount.add(string);
	}

	public void setMiddle_size_rm1(String string) {
		middle_size_rm1.add(string);
	}

	public void setMain_img_normal(String string) {
		main_img_normal.add(string);
	}

}
