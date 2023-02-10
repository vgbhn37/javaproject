package busanAttraction;

import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.json.simple.parser.ParseException;

public class MainFrame extends JFrame implements KeyListener {
	private static final long serialVersionUID = 1L;
	// 컴포넌트
	JPanel panel1, panel2, panel3;
	JTextField number;
	TextArea attraction;
	JButton btn, prev, next;
	JLabel totalAtt, list, aLabel;

	// 기본 페이지, 최대 페이지, 데이터의 갯수
	public int page = 1;
	public int maxpage = 0;
	public int total = 0;

	public MainFrame(String title) {
		super(title);

		// 위 쪽에 번호를 입력받는 창 생성
		panel1 = new JPanel();
		aLabel = new JLabel("번호 입력");
		number = new JTextField(50);
		number.setText("원하시는 명소의 번호를 입력해주세요.");
		btn = new JButton("클릭");
		panel1.add(aLabel);
		panel1.add(number);
		panel1.add(btn);

		// 가운데 명소들의 리스트 출력
		panel2 = new JPanel();
		list = new JLabel("목록");
		attraction = new TextArea();
		try {
			new MainPage(this);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		attraction.setEditable(false);
		panel2.add(list);
		panel2.add(attraction);

		// 맨 아래 이전, 다음 버튼, 데이터의 총 갯수 표시
		panel3 = new JPanel();
		totalAtt = new JLabel("총 " +total+ " 곳의 명소 검색");
		prev = new JButton("이전");
		next = new JButton("다음");
		panel3.add(prev);
		panel3.add(totalAtt);
		panel3.add(next);
		
		

		// 버튼을 클릭했을 때
		btn.addActionListener(e -> {
			int inputNumber = 0;
			try {
				inputNumber = Integer.parseInt(number.getText().trim());
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "1~" + total + " 사이의 숫자를 입력해주세요.", "올바른 범위가 아님", JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (inputNumber > 0 && inputNumber <= total) {
				new SubFrame("상세 정보", this);
		
			} else {
				JOptionPane.showMessageDialog(null, "1~" + total + " 사이의 숫자를 입력해주세요.", "올바른 범위가 아님", JOptionPane.ERROR_MESSAGE);
				return;
			}

		});
		prev.addActionListener(e -> {
			if (page - 1 < 1) {
				JOptionPane.showMessageDialog(null, "이전 페이지가 없습니다.", "이전 페이지 없음", JOptionPane.ERROR_MESSAGE);
				return;
			}
			page -= 1;
			try {
				new MainPage(this);
			

			} catch (IOException | ParseException e1) {
				e1.printStackTrace();
			}

		});

		next.addActionListener(e -> {
			if (page + 1 > maxpage) {
				JOptionPane.showMessageDialog(null, "다음 페이지가 없습니다.", "다음 페이지 없음", JOptionPane.ERROR_MESSAGE);
				return;
			}
			page += 1;
			try {
				new MainPage(this);
			
			} catch (IOException | ParseException e1) {
				e1.printStackTrace();
			}

		});

		// 엔터키를 입력했을 때
		number.addKeyListener(this);

		// 레이아웃 설정
		setLayout(new BorderLayout());
		add(BorderLayout.NORTH, panel1);
		add(BorderLayout.WEST, list);
		add(BorderLayout.CENTER, attraction);
		add(BorderLayout.SOUTH, panel3);

		setSize(800, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

	// 엔터 키 입력시 작동

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		int inputKeyNumber = 0;

		if (e.getKeyChar() == '\n') {
			try {
				inputKeyNumber = Integer.parseInt(number.getText().trim());
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "1~" + total + " 사이의 숫자를 입력해주세요.", "올바른 범위가 아님", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (inputKeyNumber > 0 && inputKeyNumber <= total) {
				new SubFrame("상세 정보", this);
				
			} else {
				JOptionPane.showMessageDialog(null, "1~" + total + " 사이의 숫자를 입력해주세요.", "올바른 범위가 아님", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}
