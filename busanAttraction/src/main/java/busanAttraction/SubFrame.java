package busanAttraction;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.json.simple.parser.ParseException;

public class SubFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	MainFrame mainframe;
	// 컴포넌트
	JPanel panel1, panel2;
	JLabel place, title, address, addr1, cntct_tel, home, homepage_url, trfc_info, usage_day_week_and_time, hldy_info,
			usage_amount, middle_size_rm1, main_img_normal;
	JButton back, homepage, pic;

	public SubFrame(String t, MainFrame mainframe) {

		super(t);
		this.mainframe = mainframe;

		// 패널, 라벨
		panel1 = new JPanel();
		place = new JLabel("이름");
		title = new JLabel("간략소개");
		address = new JLabel("▼ 주소 (아래 주소를 클릭하면 네이버 지도로 이동) ▼");
		addr1 = new JLabel("주소");
		cntct_tel = new JLabel("전화번호");
		home = new JLabel("▼ 홈페이지 (아래 주소를 클릭하면 홈페이지로 이동) ▼");
		homepage_url = new JLabel("홈페이지 주소");
		trfc_info = new JLabel("교통정보");
		usage_day_week_and_time = new JLabel("이용가능시간");
		hldy_info = new JLabel("휴일");
		usage_amount = new JLabel("이용료");
		middle_size_rm1 = new JLabel("장애인편의시설");
		main_img_normal = new JLabel("사진");

		try {
			new SubPage(mainframe, this);
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		// 상세정보 표기
		panel1.setLayout(new GridLayout(13, 1));
		panel1.add(place);
		panel1.add(title);
		panel1.add(address);
		panel1.add(addr1);
		panel1.add(cntct_tel);
		panel1.add(home);
		panel1.add(homepage_url);
		panel1.add(trfc_info);
		panel1.add(usage_day_week_and_time);
		panel1.add(hldy_info);
		panel1.add(usage_amount);
		panel1.add(middle_size_rm1);
		pic = new JButton("사진보기");
		panel1.add(pic);

		// 닫기 버튼
		panel2 = new JPanel();
		back = new JButton("닫기");
		panel2.add(back);

		// 레이아웃 설정
		setLayout(new BorderLayout());
		add(BorderLayout.CENTER, panel1);
		add(BorderLayout.SOUTH, panel2);
		setSize(1400, 800);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		// 주소 패널을 클릭했을 때
		addr1.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
				setCursor(handCursor);
			}
			public void mouseClicked(MouseEvent e) {
				String defaultMap = "https://map.naver.com/v5/search/";
				String thisAddress = null;
				try {
					thisAddress = URLEncoder.encode(addr1.getText(),"UTF-8");
				} catch (UnsupportedEncodingException e2) {
					e2.printStackTrace();
				}
				String mapUrl = defaultMap + thisAddress;
				try {
					Desktop.getDesktop().browse(new URI(mapUrl));
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
			public void mouseExited(MouseEvent e) {
				Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
				setCursor(normalCursor);
			}
			
		});

		// 홈페이지 패널을 클릭했을 때
		homepage_url.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
				setCursor(handCursor);
			}

			public void mouseClicked(MouseEvent e) {
				try {
					if (homepage_url.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "홈페이지가 없습니다.", "홈페이지 없음", JOptionPane.ERROR_MESSAGE);
						return;
					}
					Desktop.getDesktop().browse(new URI(homepage_url.getText()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					JOptionPane.showMessageDialog(null, "홈페이지에 접속할 수 없습니다.", "홈페이지 접속 불가", JOptionPane.ERROR_MESSAGE);
					
				}
			}

			public void mouseExited(MouseEvent e) {
				Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
				setCursor(normalCursor);
			}

		});

		// 사진보기 버튼을 클릭했을 때
		pic.addActionListener(e -> {
			try {
				Desktop.getDesktop().browse(new URI(main_img_normal.getText()));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		// 닫기 버튼을 클릭했을 때

		back.addActionListener(e -> {
			dispose();
		});

	}

}
