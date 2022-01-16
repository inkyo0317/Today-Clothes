import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

/**
 * @author inkyo < GUI를 이용한 옷 추천 알고리즘>
 * "기온별 옷차림" 표를 참고하여 옷을 기온, 성별에 따라 분류
 * 	큰 패널안에 GridLayout을 이용해 North, Center, South의 세 구간을 만들고,
 * 	(성별, 날씨, 기온, 추천 결과)의 4개의 패널을 1,2,1개로 나누어 각 구간에 넣었다.  
 *  3개의 패널을 체크박스 형태로 제작하여 중복없이 단일 선택을 요구했다.
 *  사용자는 오늘의 날씨 정보를 활용해 체크박스를 선택하고 결과 버튼을 누르면 자동으로 Today's clothes를 추천받는다.
 *  
 *  참고자료 : "오늘 뭐 입지?! 기온별 옷차림" - FLIR 제작 */

public class Clothes extends JFrame implements ActionListener {
	private JButton result; // 결과 버튼
	
	private JRadioButton man;   // 남자
	private JRadioButton woman; // 여자
	
	private JCheckBox weather_sunny;      // 맑음, 흐림
	private JCheckBox weather_rainy;      // 비, 눈
	private JCheckBox weather_yellowdust; // 황사
	
	private JRadioButton degree_28;    // 28도 이상
	private JRadioButton degree_23_27; // 23도~27도
	private JRadioButton degree_20_22; // 20도~22도
	private JRadioButton degree_17_19; // 17도~19도
	private JRadioButton degree_12_16; // 12도~16도
	private JRadioButton degree_9_11;  // 9도~11도
	private JRadioButton degree_5_8;   // 5도~8도
	private JRadioButton degree_5;     // 5도 미만
	
	private JLabel recommendation;	// 추천 결과를 나타내는 label
	
	public Clothes() {
		init();
		setDisplay();
		showFrame();
	}
	
	private void init() {
		// 성별, 날씨, 기온 변수 선언
		man = new JRadioButton("남자");
		woman = new JRadioButton("여자");

		weather_sunny = new JCheckBox("맑음, 흐림");
		weather_rainy = new JCheckBox("비, 눈");
		weather_yellowdust = new JCheckBox("황사");
		
		degree_28 = new JRadioButton("28도 이상"); 
		degree_23_27 = new JRadioButton("23도~27도");  
		degree_20_22 = new JRadioButton("20도~22도"); 
		degree_17_19 = new JRadioButton("17도~19도"); 
		degree_12_16 = new JRadioButton("12도~16도"); 
		degree_9_11 = new JRadioButton("9도~11도"); 
		degree_5_8 = new JRadioButton("5도~8도"); 
		degree_5 = new JRadioButton("5도 미만"); 
		
		result = new JButton("결과");
		
		recommendation = new JLabel("");
		
		// gender 그룹에 성별 변수 넣기
		ButtonGroup gender = new ButtonGroup();
		gender.add(man);
		gender.add(woman);
		
		// weather 그룹에 날씨 변수 넣기
		ButtonGroup weather = new ButtonGroup();
		weather.add(weather_sunny);
		weather.add(weather_rainy);
		weather.add(weather_yellowdust);
		
		// degree 그룹에 기온 변수 넣기
		ButtonGroup degree = new ButtonGroup();
		degree.add(degree_28);
		degree.add(degree_23_27);
		degree.add(degree_20_22);
		degree.add(degree_17_19);
		degree.add(degree_12_16);
		degree.add(degree_9_11);
		degree.add(degree_5_8);
		degree.add(degree_5);
	}
	
	private void setDisplay() {
		// 성별 종류를 담은 패널
		JPanel panel_gender = new JPanel(new GridLayout(1,0));
		panel_gender.add(man);
		panel_gender.add(woman);
		panel_gender.setBorder(new TitledBorder("성별"));
		
		// 날씨를 담은 패널
		JPanel panel_weather = new JPanel(new GridLayout(1,0));
		panel_weather.add(weather_sunny);
		panel_weather.add(weather_rainy);
		panel_weather.add(weather_yellowdust);
		panel_weather.setBorder(new TitledBorder("날씨"));
		
		// 성별과 날씨를 담은 패널
		JPanel panel_north = new JPanel(new BorderLayout());
		panel_north.add(panel_gender, BorderLayout.NORTH);
		panel_north.add(panel_weather, BorderLayout.CENTER);
		
		// 기온을 담은 패널
		JPanel panel_center = new JPanel();
		panel_center.add(degree_28);
		panel_center.add(degree_23_27);
		panel_center.add(degree_20_22);
		panel_center.add(degree_17_19);
		panel_center.add(degree_12_16);
		panel_center.add(degree_9_11);
		panel_center.add(degree_5_8);
		panel_center.add(degree_5);
		panel_center.add(result);
		result.addActionListener(this);
		panel_center.setBorder(new TitledBorder("기온"));
		
		// 추천 결과 담은 패널
		JPanel panel_south = new JPanel(new GridLayout(1,0));
		panel_south.add(recommendation);
		panel_south.setBorder(new TitledBorder("추천"));
		
		// 결과
		add(panel_north, BorderLayout.NORTH);
		add(panel_center, BorderLayout.CENTER);
		add(panel_south, BorderLayout.SOUTH);
	}

	private void showFrame() {
		setTitle("옷 추천 시스템");
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	@Override // 상속
	public void actionPerformed(ActionEvent e) {
		
		JButton result = (JButton) e.getSource();
		
		// 남자,여자의 상,하의 총 4개의 HashMap 생성
		// Single Key -> Multiple Values to the HashMap		
				
	    HashMap<String, List<String>> man_up_map = new HashMap<String, List<String>>();
		HashMap<String, List<String>> man_down_map = new HashMap<String, List<String>>();
		HashMap<String, List<String>> woman_up_map = new HashMap<String, List<String>>();
		HashMap<String, List<String>> woman_down_map = new HashMap<String, List<String>>();
				
		// 온도에 따른 남자 상의 리스트 생성
		
		List<String> man_up_28 = new ArrayList<String>();
		man_up_28.add("민소매");
		man_up_28.add("반팔");
				
		List<String> man_up_23_27= new ArrayList<String>();
		man_up_23_27.add("반팔");
				
		List<String> man_up_20_22 = new ArrayList<String>();
		man_up_20_22.add("가디건");
		man_up_20_22.add("긴팔");
				
		List<String> man_up_17_19 = new ArrayList<String>();
		man_up_17_19.add("맨투맨");
		man_up_17_19.add("가디건");
				
		List<String> man_up_12_16 = new ArrayList<String>();
		man_up_12_16.add("가디건");
		man_up_12_16.add("자켓");
				
		List<String> man_up_9_11 = new ArrayList<String>();
		man_up_9_11.add("니트");
		man_up_9_11.add("자켓");	
				
		List<String> man_up_5_8 = new ArrayList<String>();
		man_up_5_8.add("코트");
		man_up_5_8.add("가죽자켓");	
		man_up_5_8.add("히트텍");
		man_up_5_8.add("니트");
						
		List<String> man_up_5 = new ArrayList<String>();
		man_up_5.add("패딩");
		man_up_5.add("두꺼운코트");	
		man_up_5.add("목도리");	
				
		man_up_map.put("28도 이상", man_up_28);       // man_up_28 리스트를 "28도 이상" Key에 추가
		man_up_map.put("23도~27도", man_up_23_27);   // man_up_23_27 리스트를 "23도~27도" Key에 추가
		man_up_map.put("20도~22도", man_up_20_22);   // man_up_20_22 리스트를 "20도~22도" Key에 추가
		man_up_map.put("17도~19도", man_up_17_19);   // man_up_17_19 리스트를 "17도~19도" Key에 추가
		man_up_map.put("12도~16도", man_up_12_16);   // man_up_12_16 리스트를 "9도~11도" Key에 추가
		man_up_map.put("9도~11도", man_up_9_11);     // man_up_9_11 리스트를 "5도~8도" Key에 추가
		man_up_map.put("5도~8도", man_up_5_8);       // man_up_5_8 리스트를 "5도~8도" Key에 추가
		man_up_map.put("5도 미만", man_up_5);         // man_up_5 리스트를 "5도 미만" Key에 추가

		// 온도에 따른 남자 하의 리스트 생성
				
		List<String> man_down_28 = new ArrayList<String>();
		man_down_28.add("반바지");
				
		List<String> man_down_23_27 = new ArrayList<String>();
		man_down_23_27.add("반바지");
				
		List<String> man_down_20_22 = new ArrayList<String>();
		man_down_20_22.add("청바지");
				
		List<String> man_down_17_19 = new ArrayList<String>();
		man_down_17_19.add("청바지");
				
		List<String> man_down_12_16 = new ArrayList<String>();
		man_down_12_16.add("청바지");
				
		List<String> man_down_9_11 = new ArrayList<String>();
		man_down_9_11.add("청바지");	
		
		List<String> man_down_5_8 = new ArrayList<String>();
		man_down_5_8.add("청바지");
						
		List<String> man_down_5 = new ArrayList<String>();
		man_down_5.add("기모바지");	
				
		man_down_map.put("28도 이상", man_down_28);      // man_down_28 리스트를 "28도 이상" Key에 추가
		man_down_map.put("23도~27도", man_down_23_27);  // man_down_23_27 리스트를 "23도~27도" Key에 추가
		man_down_map.put("20도~22도", man_down_20_22);  // man_down_20_22 리스트를 "20도~22도" Key에 추가
		man_down_map.put("17도~19도", man_down_17_19);  // man_down_17_19 리스트를 "17도~19도" Key에 추가
		man_down_map.put("12도~16도", man_down_12_16);  // man_down_12_16 리스트를 "12도~16도" Key에 추가
		man_down_map.put("9도~11도", man_down_9_11);    // man_down_9_11 리스트를 "9도~11도" Key에 추가
		man_down_map.put("5도~8도", man_down_5_8);      // man_down_5_8 리스트를 "5도~8도" Key에 추가
		man_down_map.put("5도 미만", man_down_5);        // man_down_5 리스트를 "5도 미만" Key에 추가
				
				
		// 온도에 따른 여자 상의 리스트 생성
			
		List<String> woman_up_28 = new ArrayList<String>();
		woman_up_28.add("민소매");
		woman_up_28.add("반팔");
		woman_up_28.add("원피스");
				
		List<String> woman_up_23_27 = new ArrayList<String>();
		woman_up_23_27.add("반팔");
				
		List<String> woman_up_20_22 = new ArrayList<String>();
		woman_up_20_22.add("가디건");
		woman_up_20_22.add("긴팔");
				
		List<String> woman_up_17_19 = new ArrayList<String>();
		woman_up_17_19.add("맨투맨");
		woman_up_17_19.add("가디건");

		List<String> woman_up_12_16 = new ArrayList<String>();
		woman_up_12_16.add("가디건");
		woman_up_12_16.add("자켓");

		List<String> woman_up_9_11 = new ArrayList<String>();
		woman_up_9_11.add("니트");
		woman_up_9_11.add("자켓");	

		List<String> woman_up_5_8 = new ArrayList<String>();
		woman_up_5_8.add("코트");
		woman_up_5_8.add("가죽자켓");	
		woman_up_5_8.add("히트텍");
		woman_up_5_8.add("니트");

		List<String> woman_up_5 = new ArrayList<String>();
		woman_up_5.add("패딩");
		woman_up_5.add("두꺼운코트");	
		woman_up_5.add("목도리");	

		woman_up_map.put("28도 이상", woman_up_28);       // woman_up_28 리스트를 "28도 이상" Key에 추가
		woman_up_map.put("23도~27도", woman_up_23_27);   // woman_up_23_27 리스트를 "23도~27도" Key에 추가
		woman_up_map.put("20도~22도", woman_up_20_22);   // woman_up_20_22 리스트를 "20도~22도" Key에 추가
		woman_up_map.put("17도~19도", woman_up_17_19);   // woman_up_17_19 리스트를 "17도~19도" Key에 추가
		woman_up_map.put("12도~16도", woman_up_12_16);   // woman_up_12_16 리스트를 "9도~11도" Key에 추가
		woman_up_map.put("9도~11도", woman_up_9_11);     // woman_up_9_11 리스트를 "5도~8도" Key에 추가
		woman_up_map.put("5도~8도", woman_up_5_8);       // woman_up_5_8 리스트를 "5도~8도" Key에 추가
		woman_up_map.put("5도 미만", woman_up_5);         // woman_up_5 리스트를 "5도 미만" Key에 추가

		// 온도에 따른 여자 하의 리스트 생성 

		List<String> woman_down_28 = new ArrayList<String>();
		woman_down_28.add("반바지");

		List<String> woman_down_23_27 = new ArrayList<String>();
		woman_down_23_27.add("반바지");

		List<String> woman_down_20_22 = new ArrayList<String>();
		woman_down_20_22.add("청바지");

		List<String> woman_down_17_19 = new ArrayList<String>();
		woman_down_17_19.add("청바지");

		List<String> woman_down_12_16 = new ArrayList<String>();
		woman_down_12_16.add("청바지");

		List<String> woman_down_9_11 = new ArrayList<String>();
		woman_down_9_11.add("청바지");	

		List<String> woman_down_5_8 = new ArrayList<String>();
		woman_down_5_8.add("청바지");
		woman_down_5_8.add("레깅스");

		List<String> woman_down_5 = new ArrayList<String>();
		woman_down_5.add("기모바지");	
		woman_down_5.add("레깅스");

		woman_down_map.put("28도 이상", woman_down_28);      // woman_down_28 리스트를 "28도 이상" Key에 추가
		woman_down_map.put("23도~27도", woman_down_23_27);  // woman_down_23_27 리스트를 "23도~27도" Key에 추가
		woman_down_map.put("20도~22도", woman_down_20_22);  // woman_down_20_22 리스트를 "20도~22도" Key에 추가
		woman_down_map.put("17도~19도", woman_down_17_19);  // woman_down_17_19 리스트를 "17도~19도" Key에 추가
		woman_down_map.put("12도~16도", woman_down_12_16);  // woman_down_12_16 리스트를 "12도~16도" Key에 추가
		woman_down_map.put("9도~11도", woman_down_9_11);    // woman_down_9_11 리스트를 "9도~11도" Key에 추가
		woman_down_map.put("5도~8도", woman_down_5_8);      // woman_down_5_8 리스트를 "5도~8도" Key에 추가
		woman_down_map.put("5도 미만", woman_down_5);        // woman_down_5 리스트를 "5도 미만" Key에 추가
		
		String umbrella = "우산 준비!";
		String mask = "마스크 필수!";

		if (man.isSelected()) {
			if (weather_rainy.isSelected()) {
				if (degree_28.isSelected()) {
					recommendation.setText("상의 :" + man_up_map.get("28도 이상") + " 중에 하나를, 하의 : " + man_down_map.get("28도 이상") + ",	" + umbrella);
				}
				else if (degree_23_27.isSelected()) {
					recommendation.setText("상의 :" + man_up_map.get("23도~27도") + " 중에 하나를, 하의 : " + man_down_map.get("23도~27도") + ",	" + umbrella);
				}
				else if (degree_20_22.isSelected()) {
					recommendation.setText("상의 :" + man_up_map.get("20도~22도") + " 중에 하나를, 하의 : " + man_down_map.get("20도~22도") + ",	" + umbrella);
				}
				else if (degree_17_19.isSelected()) {
					recommendation.setText("상의 :" + man_up_map.get("17도~19도") + " 중에 하나를, 하의 : " + man_down_map.get("17도~19도") + ",	" + umbrella);
				}
				else if (degree_12_16.isSelected()) {
					recommendation.setText("상의 :" + man_up_map.get("12도~16도") + " 중에 하나를, 하의 : " + man_down_map.get("12도~16도") + ",	" + umbrella);
				}
				else if (degree_9_11.isSelected()) {
					recommendation.setText("상의 :" + man_up_map.get("9도~11도") + " 중에 하나를, 하의 : " + man_down_map.get("9도~11도") + ",	" + umbrella);
				}
				else if (degree_5_8.isSelected()) {
					recommendation.setText("상의 :" + man_up_map.get("5도~8도") + " 중에 하나를, 하의 : " + man_down_map.get("5도~8도") + ",		" + umbrella);
				}
				else if (degree_5.isSelected()) {
					recommendation.setText("상의 :" + man_up_map.get("5도 미만") + " 중에 하나를, 하의 : " + man_down_map.get("5도 미만") + ",		" + umbrella);
				}

			}
			else if (weather_yellowdust.isSelected()) {
				if (degree_28.isSelected()) {
					recommendation.setText("상의 :" + man_up_map.get("28도 이상") + " 중에 하나를, 하의 : " + man_down_map.get("28도 이상") + ",	" + mask);
				}
				else if (degree_23_27.isSelected()) {
					recommendation.setText("상의 :" + man_up_map.get("23도~27도") + " 중에 하나를, 하의 : " + man_down_map.get("23도~27도") + ",	" + mask);
				}
				else if (degree_20_22.isSelected()) {
					recommendation.setText("상의 :" + man_up_map.get("20도~22도") + " 중에 하나를, 하의 : " + man_down_map.get("20도~22도") + ",	" + mask);
				}
				else if (degree_17_19.isSelected()) {
					recommendation.setText("상의 :" + man_up_map.get("17도~19도") + " 중에 하나를, 하의 : " + man_down_map.get("17도~19도") + ",	" + mask);
				}
				else if (degree_12_16.isSelected()) {
					recommendation.setText("상의 :" + man_up_map.get("12도~16도") + " 중에 하나를, 하의 : " + man_down_map.get("12도~16도") + ",	" + mask);
				}
				else if (degree_9_11.isSelected()) {
					recommendation.setText("상의 :" + man_up_map.get("9도~11도") + " 중에 하나를, 하의 : " + man_down_map.get("9도~11도") + ",	" + mask);
				}
				else if (degree_5_8.isSelected()) {
					recommendation.setText("상의 :" + man_up_map.get("5도~8도") + " 중에 하나를, 하의 : " + man_down_map.get("5도~8도") + ",	" + mask);
				}
				else if (degree_5.isSelected()) {
					recommendation.setText("상의 :" + man_up_map.get("5도 미만") + " 중에 하나를, 하의 : " + man_down_map.get("5도 미만") + ",	" + mask);
			    }
			}
			
			
		}
		else if (woman.isSelected()) {
			if (weather_rainy.isSelected()) {
				if (degree_28.isSelected()) {
					recommendation.setText("상의 :" + woman_up_map.get("28도 이상") + " 중에 하나를, 하의 : " + woman_down_map.get("28도 이상") + ",	 " + umbrella);
				}
				else if (degree_23_27.isSelected()) {
					recommendation.setText("상의 :" + woman_up_map.get("23도~27도") + " 중에 하나를, 하의 : " + woman_down_map.get("23도~27도") + ",	  " + umbrella);
				}
				else if (degree_20_22.isSelected()) {
					recommendation.setText("상의 :" + woman_up_map.get("20도~22도") + " 중에 하나를, 하의 : " + woman_down_map.get("20도~22도") + ",   " + umbrella);
				}
				else if (degree_17_19.isSelected()) {
					recommendation.setText("상의 :" + woman_up_map.get("17도~19도") + " 중에 하나를, 하의 : " + woman_down_map.get("17도~19도") + ",	   " + umbrella);
				}
				else if (degree_12_16.isSelected()) {
					recommendation.setText("상의 :" + woman_up_map.get("12도~16도") + " 중에 하나를, 하의 : " + woman_down_map.get("12도~16도") + ",	   " + umbrella);
				}
				else if (degree_9_11.isSelected()) {
					recommendation.setText("상의 :" + woman_up_map.get("9도~11도") + " 중에 하나를, 하의 : " + woman_down_map.get("9도~11도") + ",	" + umbrella);
				}
				else if (degree_5_8.isSelected()) {
					recommendation.setText("상의 :" + woman_up_map.get("5도~8도") + " 중에 하나를, 하의 : " + woman_down_map.get("5도~8도") + ",	   " + umbrella);
				}
				else if (degree_5.isSelected()) {
					recommendation.setText("상의 :" + woman_up_map.get("5도 미만") + " 중에 하나를, 하의 : " + woman_down_map.get("5도 미만") + ",	" + umbrella);
				}
			}
			else if (weather_yellowdust.isSelected()) {
				if (degree_28.isSelected()) {
					recommendation.setText("상의 :" + woman_up_map.get("28도 이상") + " 중에 하나를, 하의 : " + woman_down_map.get("28도 이상") + ",	  " + mask);
				}
				else if (degree_23_27.isSelected()) {
					recommendation.setText("상의 :" + woman_up_map.get("23도~27도") + " 중에 하나를, 하의 : " + woman_down_map.get("23도~27도") + ",   " + mask);
				}
				else if (degree_20_22.isSelected()) {
					recommendation.setText("상의 :" + woman_up_map.get("20도~22도") + " 중에 하나를, 하의 : " + woman_down_map.get("20도~22도") + ",	   " + mask);
				}
				else if (degree_17_19.isSelected()) {
					recommendation.setText("상의 :" + woman_up_map.get("17도~19도") + " 중에 하나를, 하의 : " + woman_down_map.get("17도~19도") + ",	   " + mask);
				}
				else if (degree_12_16.isSelected()) {
					recommendation.setText("상의 :" + woman_up_map.get("12도~16도") + " 중에 하나를, 하의 : " + woman_down_map.get("12도~16도") + ",	   " + mask);
				}
				else if (degree_9_11.isSelected()) {
					recommendation.setText("상의 :" + woman_up_map.get("9도~11도") + " 중에 하나를, 하의 : " + woman_down_map.get("9도~11도") + ",	" + mask);
				}
				else if (degree_5_8.isSelected()) {
					recommendation.setText ("상의 :" + woman_up_map.get("5도~8도") + " 중에 하나를, 하의 : " + woman_down_map.get("5도~8도") + ",    " + mask);
				}
				else if (degree_5.isSelected()) {
					recommendation.setText ("상의 :" + woman_up_map.get("5도 미만") + " 중에 하나를, 하의 : " + woman_down_map.get("5도 미만") + ",	" + mask);
				}
			}
		}			
	}
	
	public static void main(String[] args) {
		new Clothes();
	}
}
