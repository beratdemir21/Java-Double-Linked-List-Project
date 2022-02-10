package dvd;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class Gui extends JFrame implements ActionListener{

	
	public static final int WIDTH = 900;
	public static final int HEIGHT = 700;
	

	private JPanel txtAreaPanel;
	private JPanel addPanel;
	private JPanel dvdListPanel;
	private JPanel dvdDeletePanel;
	private JPanel buttonPanel;
	private JPanel exitPanel;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JLabel label;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JTextArea addTextArea; 
	private JTextArea  listTextArea ;
	private JTextArea  deleteTextArea;
	private JTextArea listArea ;
	private JButton addButton;
	private JButton listButton1;
	private JButton listButton2;
	private JButton sirala1;
	private JButton sirala2;
	private JButton sirala3;
	private JButton exitSystem;
	private JScrollPane scroll;
	
	public Gui(){
		super("Grafical User Interface");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(1, 2));
	
		//Sol Panel baþlangýç

		leftPanel = new JPanel();
		leftPanel.setBackground(Color.WHITE);
		leftPanel.setLayout(new GridLayout(5, 1));
		
	//DVD ekleme paneli baþlangýþ	
		addPanel = new JPanel();
		addPanel.setLayout(new BorderLayout());
		addPanel.setBackground(Color.LIGHT_GRAY);
		
		
		label= new JLabel("DVD Bilgilerini giriniz.");
		addPanel.add(label, BorderLayout.NORTH);
		
		addTextArea= new JTextArea();
		addTextArea.setBackground(Color.LIGHT_GRAY);
		addPanel.add(addTextArea, BorderLayout.CENTER);
	
		addButton = new JButton("DVD Ekle");
		addButton.addActionListener(this);
		addPanel.add(addButton, BorderLayout.SOUTH);
		
		leftPanel.add(addPanel);

	//DVD ekleme paneli bitiþ	
		
	//Þarkýcýnýn dvdlerini listele paneli baþlangýç	
		dvdListPanel = new JPanel();
		dvdListPanel.setBackground(Color.LIGHT_GRAY);
		dvdListPanel.setLayout(new BorderLayout());
		
		label1 = new JLabel("Dvdlerini Listelemek istediðiniz Sanatcýnýn Adýný ve SoyAdýný Girin Lütfen!");
		dvdListPanel.add(label1, BorderLayout.NORTH);
		
		listTextArea = new JTextArea();
		listTextArea.setLineWrap(true);
		
		listTextArea.setBackground(Color.LIGHT_GRAY);
		dvdListPanel.add(listTextArea, BorderLayout.CENTER);
		
		listButton1 = new JButton("Þarkýcýnýn DVD lerini Listele");
		listButton1.addActionListener(this);
		dvdListPanel.add(listButton1, BorderLayout.SOUTH);
		
		leftPanel.add(dvdListPanel);
	//Þarkýcýnýn dvdlerini listele paneli bitiþ	
		
	//Dvd silme paneli baþlangýç	
		dvdDeletePanel = new JPanel();
		dvdDeletePanel.setBackground(Color.LIGHT_GRAY);
		dvdDeletePanel.setLayout(new BorderLayout());
		
		label2 = new JLabel("Silmek Ýstediðini Þarkýcýnýn Adýný ve SoyAdýný Giriniz");
		dvdDeletePanel.add(label2, BorderLayout.NORTH);
		
		deleteTextArea = new JTextArea();
		deleteTextArea.setBackground(Color.LIGHT_GRAY);
		dvdDeletePanel.add(deleteTextArea, BorderLayout.CENTER);
		
		
		listButton2 = new JButton("DVD' yi SÝl");
		listButton2.addActionListener(this);
		dvdDeletePanel.add(listButton2,BorderLayout.SOUTH);
		
		leftPanel.add(dvdDeletePanel);

	//Dvd silme paneli bitiþ		
		
	//Button paneli baþlangýç	
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(4, 1));
		buttonPanel.setBackground(Color.LIGHT_GRAY);
		
		label3 = new JLabel("Sýralama Seçenekleri");
		buttonPanel.add(label3);
		
		sirala1 = new JButton("A' dan Z' ye Göre Listele");
		sirala1.addActionListener(this);
		buttonPanel.add(sirala1);
		
		sirala2 = new JButton("Z' den A' ya Göre Listele");
		sirala2.addActionListener(this);
		buttonPanel.add(sirala2);
		
		sirala3 = new JButton("2000' den Öncekileri Listele");
		sirala3.addActionListener(this);
		buttonPanel.add(sirala3);
		
		leftPanel.add(buttonPanel);
	//Button paneli bitiþ
		
	//Çýkýþ Paneli baþlangýç	
		exitPanel = new JPanel();
		exitPanel.setBackground(Color.LIGHT_GRAY);
		exitPanel.setLayout(new GridLayout(1, 1));
		
		exitSystem = new JButton("ÇIKIÞ");
		exitSystem.addActionListener(this);
		exitPanel.add(exitSystem);
		
		leftPanel.add(exitPanel);
	//Çýkýþ Paneli bitiþ
		
		
		add(leftPanel);
		
		//Sol Panel bitiþ
		
		//Sað panel Baþlangýç
		rightPanel = new JPanel();
		rightPanel.setBackground(Color.LIGHT_GRAY);
		rightPanel.setLayout(new GridLayout(1, 1));
		rightPanel.getBorder();
		
		listArea = new JTextArea(80,90);
		listArea.setLineWrap(true);
		listArea.setEditable(false);
		listArea.setBackground(Color.WHITE);
		
		scroll = new JScrollPane(listArea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		rightPanel.add(scroll);		
		add(rightPanel);
		
		//Sað panel bitiþ
		}	
	
	public void actionPerformed(ActionEvent e){
		
		String actionCommand = e.getActionCommand();
		
		Node obj1 = new Node();
		obj1.readFile();
		
		if(actionCommand.equals("DVD Ekle")){			
			obj1.division(addTextArea.getText());
			obj1.writeToFile();
			
			
		}else if(actionCommand.equals("A' dan Z' ye Göre Listele")){
			
			DVD position=obj1.head;
			String alItem="";
			while(position != null){
				alItem +=position.toString(position) + "\n\n";
				position=position.next ;
			}
			listArea.setText(alItem);
			
		}else if(actionCommand.equals("Z' den A' ya Göre Listele")){
			
			DVD position=obj1.tail;
			String alItem="";
			while(position != null){
				alItem +=position.toString(position) + "\n\n";
				position=position.previous ;
			}
			listArea.setText(alItem);
			
		}else if(actionCommand.equals("2000' den Öncekileri Listele")){
			
			listArea.setText(obj1.twoThousand());
			
		}else if(actionCommand.equals("ÇIKIÞ")){
			obj1.writeToFile();
			System.exit(0);
		}else if(actionCommand.equals("Þarkýcýnýn DVD lerini Listele")){
			listArea.setText(obj1.singer(listTextArea.getText()));
			
		}else if(actionCommand.equals("DVD yi SÝl")){
			obj1.deleteNodeHeader(deleteTextArea.getText());
			obj1.writeToFile();
		}
	}
	
	
	public static void main(String[] args) {
		Gui gui1 = new Gui();
		gui1.setVisible(true);
	}

}
