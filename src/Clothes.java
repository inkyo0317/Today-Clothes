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
 * @author inkyo < GUI�� �̿��� �� ��õ �˰���>
 * "��º� ������" ǥ�� �����Ͽ� ���� ���, ������ ���� �з�
 * 	ū �гξȿ� GridLayout�� �̿��� North, Center, South�� �� ������ �����,
 * 	(����, ����, ���, ��õ ���)�� 4���� �г��� 1,2,1���� ������ �� ������ �־���.  
 *  3���� �г��� üũ�ڽ� ���·� �����Ͽ� �ߺ����� ���� ������ �䱸�ߴ�.
 *  ����ڴ� ������ ���� ������ Ȱ���� üũ�ڽ��� �����ϰ� ��� ��ư�� ������ �ڵ����� Today's clothes�� ��õ�޴´�.
 *  
 *  �����ڷ� : "���� �� ����?! ��º� ������" - FLIR ���� */

public class Clothes extends JFrame implements ActionListener {
	private JButton result; // ��� ��ư
	
	private JRadioButton man;   // ����
	private JRadioButton woman; // ����
	
	private JCheckBox weather_sunny;      // ����, �帲
	private JCheckBox weather_rainy;      // ��, ��
	private JCheckBox weather_yellowdust; // Ȳ��
	
	private JRadioButton degree_28;    // 28�� �̻�
	private JRadioButton degree_23_27; // 23��~27��
	private JRadioButton degree_20_22; // 20��~22��
	private JRadioButton degree_17_19; // 17��~19��
	private JRadioButton degree_12_16; // 12��~16��
	private JRadioButton degree_9_11;  // 9��~11��
	private JRadioButton degree_5_8;   // 5��~8��
	private JRadioButton degree_5;     // 5�� �̸�
	
	private JLabel recommendation;	// ��õ ����� ��Ÿ���� label
	
	public Clothes() {
		init();
		setDisplay();
		showFrame();
	}
	
	private void init() {
		// ����, ����, ��� ���� ����
		man = new JRadioButton("����");
		woman = new JRadioButton("����");

		weather_sunny = new JCheckBox("����, �帲");
		weather_rainy = new JCheckBox("��, ��");
		weather_yellowdust = new JCheckBox("Ȳ��");
		
		degree_28 = new JRadioButton("28�� �̻�"); 
		degree_23_27 = new JRadioButton("23��~27��");  
		degree_20_22 = new JRadioButton("20��~22��"); 
		degree_17_19 = new JRadioButton("17��~19��"); 
		degree_12_16 = new JRadioButton("12��~16��"); 
		degree_9_11 = new JRadioButton("9��~11��"); 
		degree_5_8 = new JRadioButton("5��~8��"); 
		degree_5 = new JRadioButton("5�� �̸�"); 
		
		result = new JButton("���");
		
		recommendation = new JLabel("");
		
		// gender �׷쿡 ���� ���� �ֱ�
		ButtonGroup gender = new ButtonGroup();
		gender.add(man);
		gender.add(woman);
		
		// weather �׷쿡 ���� ���� �ֱ�
		ButtonGroup weather = new ButtonGroup();
		weather.add(weather_sunny);
		weather.add(weather_rainy);
		weather.add(weather_yellowdust);
		
		// degree �׷쿡 ��� ���� �ֱ�
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
		// ���� ������ ���� �г�
		JPanel panel_gender = new JPanel(new GridLayout(1,0));
		panel_gender.add(man);
		panel_gender.add(woman);
		panel_gender.setBorder(new TitledBorder("����"));
		
		// ������ ���� �г�
		JPanel panel_weather = new JPanel(new GridLayout(1,0));
		panel_weather.add(weather_sunny);
		panel_weather.add(weather_rainy);
		panel_weather.add(weather_yellowdust);
		panel_weather.setBorder(new TitledBorder("����"));
		
		// ������ ������ ���� �г�
		JPanel panel_north = new JPanel(new BorderLayout());
		panel_north.add(panel_gender, BorderLayout.NORTH);
		panel_north.add(panel_weather, BorderLayout.CENTER);
		
		// ����� ���� �г�
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
		panel_center.setBorder(new TitledBorder("���"));
		
		// ��õ ��� ���� �г�
		JPanel panel_south = new JPanel(new GridLayout(1,0));
		panel_south.add(recommendation);
		panel_south.setBorder(new TitledBorder("��õ"));
		
		// ���
		add(panel_north, BorderLayout.NORTH);
		add(panel_center, BorderLayout.CENTER);
		add(panel_south, BorderLayout.SOUTH);
	}

	private void showFrame() {
		setTitle("�� ��õ �ý���");
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	@Override // ���
	public void actionPerformed(ActionEvent e) {
		
		JButton result = (JButton) e.getSource();
		
		// ����,������ ��,���� �� 4���� HashMap ����
		// Single Key -> Multiple Values to the HashMap		
				
	    HashMap<String, List<String>> man_up_map = new HashMap<String, List<String>>();
		HashMap<String, List<String>> man_down_map = new HashMap<String, List<String>>();
		HashMap<String, List<String>> woman_up_map = new HashMap<String, List<String>>();
		HashMap<String, List<String>> woman_down_map = new HashMap<String, List<String>>();
				
		// �µ��� ���� ���� ���� ����Ʈ ����
		
		List<String> man_up_28 = new ArrayList<String>();
		man_up_28.add("�μҸ�");
		man_up_28.add("����");
				
		List<String> man_up_23_27= new ArrayList<String>();
		man_up_23_27.add("����");
				
		List<String> man_up_20_22 = new ArrayList<String>();
		man_up_20_22.add("�����");
		man_up_20_22.add("����");
				
		List<String> man_up_17_19 = new ArrayList<String>();
		man_up_17_19.add("������");
		man_up_17_19.add("�����");
				
		List<String> man_up_12_16 = new ArrayList<String>();
		man_up_12_16.add("�����");
		man_up_12_16.add("����");
				
		List<String> man_up_9_11 = new ArrayList<String>();
		man_up_9_11.add("��Ʈ");
		man_up_9_11.add("����");	
				
		List<String> man_up_5_8 = new ArrayList<String>();
		man_up_5_8.add("��Ʈ");
		man_up_5_8.add("��������");	
		man_up_5_8.add("��Ʈ��");
		man_up_5_8.add("��Ʈ");
						
		List<String> man_up_5 = new ArrayList<String>();
		man_up_5.add("�е�");
		man_up_5.add("�β�����Ʈ");	
		man_up_5.add("�񵵸�");	
				
		man_up_map.put("28�� �̻�", man_up_28);       // man_up_28 ����Ʈ�� "28�� �̻�" Key�� �߰�
		man_up_map.put("23��~27��", man_up_23_27);   // man_up_23_27 ����Ʈ�� "23��~27��" Key�� �߰�
		man_up_map.put("20��~22��", man_up_20_22);   // man_up_20_22 ����Ʈ�� "20��~22��" Key�� �߰�
		man_up_map.put("17��~19��", man_up_17_19);   // man_up_17_19 ����Ʈ�� "17��~19��" Key�� �߰�
		man_up_map.put("12��~16��", man_up_12_16);   // man_up_12_16 ����Ʈ�� "9��~11��" Key�� �߰�
		man_up_map.put("9��~11��", man_up_9_11);     // man_up_9_11 ����Ʈ�� "5��~8��" Key�� �߰�
		man_up_map.put("5��~8��", man_up_5_8);       // man_up_5_8 ����Ʈ�� "5��~8��" Key�� �߰�
		man_up_map.put("5�� �̸�", man_up_5);         // man_up_5 ����Ʈ�� "5�� �̸�" Key�� �߰�

		// �µ��� ���� ���� ���� ����Ʈ ����
				
		List<String> man_down_28 = new ArrayList<String>();
		man_down_28.add("�ݹ���");
				
		List<String> man_down_23_27 = new ArrayList<String>();
		man_down_23_27.add("�ݹ���");
				
		List<String> man_down_20_22 = new ArrayList<String>();
		man_down_20_22.add("û����");
				
		List<String> man_down_17_19 = new ArrayList<String>();
		man_down_17_19.add("û����");
				
		List<String> man_down_12_16 = new ArrayList<String>();
		man_down_12_16.add("û����");
				
		List<String> man_down_9_11 = new ArrayList<String>();
		man_down_9_11.add("û����");	
		
		List<String> man_down_5_8 = new ArrayList<String>();
		man_down_5_8.add("û����");
						
		List<String> man_down_5 = new ArrayList<String>();
		man_down_5.add("������");	
				
		man_down_map.put("28�� �̻�", man_down_28);      // man_down_28 ����Ʈ�� "28�� �̻�" Key�� �߰�
		man_down_map.put("23��~27��", man_down_23_27);  // man_down_23_27 ����Ʈ�� "23��~27��" Key�� �߰�
		man_down_map.put("20��~22��", man_down_20_22);  // man_down_20_22 ����Ʈ�� "20��~22��" Key�� �߰�
		man_down_map.put("17��~19��", man_down_17_19);  // man_down_17_19 ����Ʈ�� "17��~19��" Key�� �߰�
		man_down_map.put("12��~16��", man_down_12_16);  // man_down_12_16 ����Ʈ�� "12��~16��" Key�� �߰�
		man_down_map.put("9��~11��", man_down_9_11);    // man_down_9_11 ����Ʈ�� "9��~11��" Key�� �߰�
		man_down_map.put("5��~8��", man_down_5_8);      // man_down_5_8 ����Ʈ�� "5��~8��" Key�� �߰�
		man_down_map.put("5�� �̸�", man_down_5);        // man_down_5 ����Ʈ�� "5�� �̸�" Key�� �߰�
				
				
		// �µ��� ���� ���� ���� ����Ʈ ����
			
		List<String> woman_up_28 = new ArrayList<String>();
		woman_up_28.add("�μҸ�");
		woman_up_28.add("����");
		woman_up_28.add("���ǽ�");
				
		List<String> woman_up_23_27 = new ArrayList<String>();
		woman_up_23_27.add("����");
				
		List<String> woman_up_20_22 = new ArrayList<String>();
		woman_up_20_22.add("�����");
		woman_up_20_22.add("����");
				
		List<String> woman_up_17_19 = new ArrayList<String>();
		woman_up_17_19.add("������");
		woman_up_17_19.add("�����");

		List<String> woman_up_12_16 = new ArrayList<String>();
		woman_up_12_16.add("�����");
		woman_up_12_16.add("����");

		List<String> woman_up_9_11 = new ArrayList<String>();
		woman_up_9_11.add("��Ʈ");
		woman_up_9_11.add("����");	

		List<String> woman_up_5_8 = new ArrayList<String>();
		woman_up_5_8.add("��Ʈ");
		woman_up_5_8.add("��������");	
		woman_up_5_8.add("��Ʈ��");
		woman_up_5_8.add("��Ʈ");

		List<String> woman_up_5 = new ArrayList<String>();
		woman_up_5.add("�е�");
		woman_up_5.add("�β�����Ʈ");	
		woman_up_5.add("�񵵸�");	

		woman_up_map.put("28�� �̻�", woman_up_28);       // woman_up_28 ����Ʈ�� "28�� �̻�" Key�� �߰�
		woman_up_map.put("23��~27��", woman_up_23_27);   // woman_up_23_27 ����Ʈ�� "23��~27��" Key�� �߰�
		woman_up_map.put("20��~22��", woman_up_20_22);   // woman_up_20_22 ����Ʈ�� "20��~22��" Key�� �߰�
		woman_up_map.put("17��~19��", woman_up_17_19);   // woman_up_17_19 ����Ʈ�� "17��~19��" Key�� �߰�
		woman_up_map.put("12��~16��", woman_up_12_16);   // woman_up_12_16 ����Ʈ�� "9��~11��" Key�� �߰�
		woman_up_map.put("9��~11��", woman_up_9_11);     // woman_up_9_11 ����Ʈ�� "5��~8��" Key�� �߰�
		woman_up_map.put("5��~8��", woman_up_5_8);       // woman_up_5_8 ����Ʈ�� "5��~8��" Key�� �߰�
		woman_up_map.put("5�� �̸�", woman_up_5);         // woman_up_5 ����Ʈ�� "5�� �̸�" Key�� �߰�

		// �µ��� ���� ���� ���� ����Ʈ ���� 

		List<String> woman_down_28 = new ArrayList<String>();
		woman_down_28.add("�ݹ���");

		List<String> woman_down_23_27 = new ArrayList<String>();
		woman_down_23_27.add("�ݹ���");

		List<String> woman_down_20_22 = new ArrayList<String>();
		woman_down_20_22.add("û����");

		List<String> woman_down_17_19 = new ArrayList<String>();
		woman_down_17_19.add("û����");

		List<String> woman_down_12_16 = new ArrayList<String>();
		woman_down_12_16.add("û����");

		List<String> woman_down_9_11 = new ArrayList<String>();
		woman_down_9_11.add("û����");	

		List<String> woman_down_5_8 = new ArrayList<String>();
		woman_down_5_8.add("û����");
		woman_down_5_8.add("���뽺");

		List<String> woman_down_5 = new ArrayList<String>();
		woman_down_5.add("������");	
		woman_down_5.add("���뽺");

		woman_down_map.put("28�� �̻�", woman_down_28);      // woman_down_28 ����Ʈ�� "28�� �̻�" Key�� �߰�
		woman_down_map.put("23��~27��", woman_down_23_27);  // woman_down_23_27 ����Ʈ�� "23��~27��" Key�� �߰�
		woman_down_map.put("20��~22��", woman_down_20_22);  // woman_down_20_22 ����Ʈ�� "20��~22��" Key�� �߰�
		woman_down_map.put("17��~19��", woman_down_17_19);  // woman_down_17_19 ����Ʈ�� "17��~19��" Key�� �߰�
		woman_down_map.put("12��~16��", woman_down_12_16);  // woman_down_12_16 ����Ʈ�� "12��~16��" Key�� �߰�
		woman_down_map.put("9��~11��", woman_down_9_11);    // woman_down_9_11 ����Ʈ�� "9��~11��" Key�� �߰�
		woman_down_map.put("5��~8��", woman_down_5_8);      // woman_down_5_8 ����Ʈ�� "5��~8��" Key�� �߰�
		woman_down_map.put("5�� �̸�", woman_down_5);        // woman_down_5 ����Ʈ�� "5�� �̸�" Key�� �߰�
		
		String umbrella = "��� �غ�!";
		String mask = "����ũ �ʼ�!";

		if (man.isSelected()) {
			if (weather_rainy.isSelected()) {
				if (degree_28.isSelected()) {
					recommendation.setText("���� :" + man_up_map.get("28�� �̻�") + " �߿� �ϳ���, ���� : " + man_down_map.get("28�� �̻�") + ",	" + umbrella);
				}
				else if (degree_23_27.isSelected()) {
					recommendation.setText("���� :" + man_up_map.get("23��~27��") + " �߿� �ϳ���, ���� : " + man_down_map.get("23��~27��") + ",	" + umbrella);
				}
				else if (degree_20_22.isSelected()) {
					recommendation.setText("���� :" + man_up_map.get("20��~22��") + " �߿� �ϳ���, ���� : " + man_down_map.get("20��~22��") + ",	" + umbrella);
				}
				else if (degree_17_19.isSelected()) {
					recommendation.setText("���� :" + man_up_map.get("17��~19��") + " �߿� �ϳ���, ���� : " + man_down_map.get("17��~19��") + ",	" + umbrella);
				}
				else if (degree_12_16.isSelected()) {
					recommendation.setText("���� :" + man_up_map.get("12��~16��") + " �߿� �ϳ���, ���� : " + man_down_map.get("12��~16��") + ",	" + umbrella);
				}
				else if (degree_9_11.isSelected()) {
					recommendation.setText("���� :" + man_up_map.get("9��~11��") + " �߿� �ϳ���, ���� : " + man_down_map.get("9��~11��") + ",	" + umbrella);
				}
				else if (degree_5_8.isSelected()) {
					recommendation.setText("���� :" + man_up_map.get("5��~8��") + " �߿� �ϳ���, ���� : " + man_down_map.get("5��~8��") + ",		" + umbrella);
				}
				else if (degree_5.isSelected()) {
					recommendation.setText("���� :" + man_up_map.get("5�� �̸�") + " �߿� �ϳ���, ���� : " + man_down_map.get("5�� �̸�") + ",		" + umbrella);
				}

			}
			else if (weather_yellowdust.isSelected()) {
				if (degree_28.isSelected()) {
					recommendation.setText("���� :" + man_up_map.get("28�� �̻�") + " �߿� �ϳ���, ���� : " + man_down_map.get("28�� �̻�") + ",	" + mask);
				}
				else if (degree_23_27.isSelected()) {
					recommendation.setText("���� :" + man_up_map.get("23��~27��") + " �߿� �ϳ���, ���� : " + man_down_map.get("23��~27��") + ",	" + mask);
				}
				else if (degree_20_22.isSelected()) {
					recommendation.setText("���� :" + man_up_map.get("20��~22��") + " �߿� �ϳ���, ���� : " + man_down_map.get("20��~22��") + ",	" + mask);
				}
				else if (degree_17_19.isSelected()) {
					recommendation.setText("���� :" + man_up_map.get("17��~19��") + " �߿� �ϳ���, ���� : " + man_down_map.get("17��~19��") + ",	" + mask);
				}
				else if (degree_12_16.isSelected()) {
					recommendation.setText("���� :" + man_up_map.get("12��~16��") + " �߿� �ϳ���, ���� : " + man_down_map.get("12��~16��") + ",	" + mask);
				}
				else if (degree_9_11.isSelected()) {
					recommendation.setText("���� :" + man_up_map.get("9��~11��") + " �߿� �ϳ���, ���� : " + man_down_map.get("9��~11��") + ",	" + mask);
				}
				else if (degree_5_8.isSelected()) {
					recommendation.setText("���� :" + man_up_map.get("5��~8��") + " �߿� �ϳ���, ���� : " + man_down_map.get("5��~8��") + ",	" + mask);
				}
				else if (degree_5.isSelected()) {
					recommendation.setText("���� :" + man_up_map.get("5�� �̸�") + " �߿� �ϳ���, ���� : " + man_down_map.get("5�� �̸�") + ",	" + mask);
			    }
			}
			
			
		}
		else if (woman.isSelected()) {
			if (weather_rainy.isSelected()) {
				if (degree_28.isSelected()) {
					recommendation.setText("���� :" + woman_up_map.get("28�� �̻�") + " �߿� �ϳ���, ���� : " + woman_down_map.get("28�� �̻�") + ",	 " + umbrella);
				}
				else if (degree_23_27.isSelected()) {
					recommendation.setText("���� :" + woman_up_map.get("23��~27��") + " �߿� �ϳ���, ���� : " + woman_down_map.get("23��~27��") + ",	  " + umbrella);
				}
				else if (degree_20_22.isSelected()) {
					recommendation.setText("���� :" + woman_up_map.get("20��~22��") + " �߿� �ϳ���, ���� : " + woman_down_map.get("20��~22��") + ",   " + umbrella);
				}
				else if (degree_17_19.isSelected()) {
					recommendation.setText("���� :" + woman_up_map.get("17��~19��") + " �߿� �ϳ���, ���� : " + woman_down_map.get("17��~19��") + ",	   " + umbrella);
				}
				else if (degree_12_16.isSelected()) {
					recommendation.setText("���� :" + woman_up_map.get("12��~16��") + " �߿� �ϳ���, ���� : " + woman_down_map.get("12��~16��") + ",	   " + umbrella);
				}
				else if (degree_9_11.isSelected()) {
					recommendation.setText("���� :" + woman_up_map.get("9��~11��") + " �߿� �ϳ���, ���� : " + woman_down_map.get("9��~11��") + ",	" + umbrella);
				}
				else if (degree_5_8.isSelected()) {
					recommendation.setText("���� :" + woman_up_map.get("5��~8��") + " �߿� �ϳ���, ���� : " + woman_down_map.get("5��~8��") + ",	   " + umbrella);
				}
				else if (degree_5.isSelected()) {
					recommendation.setText("���� :" + woman_up_map.get("5�� �̸�") + " �߿� �ϳ���, ���� : " + woman_down_map.get("5�� �̸�") + ",	" + umbrella);
				}
			}
			else if (weather_yellowdust.isSelected()) {
				if (degree_28.isSelected()) {
					recommendation.setText("���� :" + woman_up_map.get("28�� �̻�") + " �߿� �ϳ���, ���� : " + woman_down_map.get("28�� �̻�") + ",	  " + mask);
				}
				else if (degree_23_27.isSelected()) {
					recommendation.setText("���� :" + woman_up_map.get("23��~27��") + " �߿� �ϳ���, ���� : " + woman_down_map.get("23��~27��") + ",   " + mask);
				}
				else if (degree_20_22.isSelected()) {
					recommendation.setText("���� :" + woman_up_map.get("20��~22��") + " �߿� �ϳ���, ���� : " + woman_down_map.get("20��~22��") + ",	   " + mask);
				}
				else if (degree_17_19.isSelected()) {
					recommendation.setText("���� :" + woman_up_map.get("17��~19��") + " �߿� �ϳ���, ���� : " + woman_down_map.get("17��~19��") + ",	   " + mask);
				}
				else if (degree_12_16.isSelected()) {
					recommendation.setText("���� :" + woman_up_map.get("12��~16��") + " �߿� �ϳ���, ���� : " + woman_down_map.get("12��~16��") + ",	   " + mask);
				}
				else if (degree_9_11.isSelected()) {
					recommendation.setText("���� :" + woman_up_map.get("9��~11��") + " �߿� �ϳ���, ���� : " + woman_down_map.get("9��~11��") + ",	" + mask);
				}
				else if (degree_5_8.isSelected()) {
					recommendation.setText ("���� :" + woman_up_map.get("5��~8��") + " �߿� �ϳ���, ���� : " + woman_down_map.get("5��~8��") + ",    " + mask);
				}
				else if (degree_5.isSelected()) {
					recommendation.setText ("���� :" + woman_up_map.get("5�� �̸�") + " �߿� �ϳ���, ���� : " + woman_down_map.get("5�� �̸�") + ",	" + mask);
				}
			}
		}			
	}
	
	public static void main(String[] args) {
		new Clothes();
	}
}
