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
	
		//Sol Panel ba�lang��

		leftPanel = new JPanel();
		leftPanel.setBackground(Color.WHITE);
		leftPanel.setLayout(new GridLayout(5, 1));
		
	//DVD ekleme paneli ba�lang��	
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

	//DVD ekleme paneli biti�	
		
	//�ark�c�n�n dvdlerini listele paneli ba�lang��	
		dvdListPanel = new JPanel();
		dvdListPanel.setBackground(Color.LIGHT_GRAY);
		dvdListPanel.setLayout(new BorderLayout());
		
		label1 = new JLabel("Dvdlerini Listelemek istedi�iniz Sanatc�n�n Ad�n� ve SoyAd�n� Girin L�tfen!");
		dvdListPanel.add(label1, BorderLayout.NORTH);
		
		listTextArea = new JTextArea();
		listTextArea.setLineWrap(true);
		
		listTextArea.setBackground(Color.LIGHT_GRAY);
		dvdListPanel.add(listTextArea, BorderLayout.CENTER);
		
		listButton1 = new JButton("�ark�c�n�n DVD lerini Listele");
		listButton1.addActionListener(this);
		dvdListPanel.add(listButton1, BorderLayout.SOUTH);
		
		leftPanel.add(dvdListPanel);
	//�ark�c�n�n dvdlerini listele paneli biti�	
		
	//Dvd silme paneli ba�lang��	
		dvdDeletePanel = new JPanel();
		dvdDeletePanel.setBackground(Color.LIGHT_GRAY);
		dvdDeletePanel.setLayout(new BorderLayout());
		
		label2 = new JLabel("Silmek �stedi�ini �ark�c�n�n Ad�n� ve SoyAd�n� Giriniz");
		dvdDeletePanel.add(label2, BorderLayout.NORTH);
		
		deleteTextArea = new JTextArea();
		deleteTextArea.setBackground(Color.LIGHT_GRAY);
		dvdDeletePanel.add(deleteTextArea, BorderLayout.CENTER);
		
		
		listButton2 = new JButton("DVD' yi S�l");
		listButton2.addActionListener(this);
		dvdDeletePanel.add(listButton2,BorderLayout.SOUTH);
		
		leftPanel.add(dvdDeletePanel);

	//Dvd silme paneli biti�		
		
	//Button paneli ba�lang��	
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(4, 1));
		buttonPanel.setBackground(Color.LIGHT_GRAY);
		
		label3 = new JLabel("S�ralama Se�enekleri");
		buttonPanel.add(label3);
		
		sirala1 = new JButton("A' dan Z' ye G�re Listele");
		sirala1.addActionListener(this);
		buttonPanel.add(sirala1);
		
		sirala2 = new JButton("Z' den A' ya G�re Listele");
		sirala2.addActionListener(this);
		buttonPanel.add(sirala2);
		
		sirala3 = new JButton("2000' den �ncekileri Listele");
		sirala3.addActionListener(this);
		buttonPanel.add(sirala3);
		
		leftPanel.add(buttonPanel);
	//Button paneli biti�
		
	//��k�� Paneli ba�lang��	
		exitPanel = new JPanel();
		exitPanel.setBackground(Color.LIGHT_GRAY);
		exitPanel.setLayout(new GridLayout(1, 1));
		
		exitSystem = new JButton("�IKI�");
		exitSystem.addActionListener(this);
		exitPanel.add(exitSystem);
		
		leftPanel.add(exitPanel);
	//��k�� Paneli biti�
		
		
		add(leftPanel);
		
		//Sol Panel biti�
		
		//Sa� panel Ba�lang��
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
		
		//Sa� panel biti�
		}	
	
	public void actionPerformed(ActionEvent e){
		
		String actionCommand = e.getActionCommand();
		
		Node obj1 = new Node();
		obj1.readFile();
		
		if(actionCommand.equals("DVD Ekle")){			
			obj1.division(addTextArea.getText());
			obj1.writeToFile();
			
			
		}else if(actionCommand.equals("A' dan Z' ye G�re Listele")){
			
			DVD position=obj1.head;
			String alItem="";
			while(position != null){
				alItem +=position.toString(position) + "\n\n";
				position=position.next ;
			}
			listArea.setText(alItem);
			
		}else if(actionCommand.equals("Z' den A' ya G�re Listele")){
			
			DVD position=obj1.tail;
			String alItem="";
			while(position != null){
				alItem +=position.toString(position) + "\n\n";
				position=position.previous ;
			}
			listArea.setText(alItem);
			
		}else if(actionCommand.equals("2000' den �ncekileri Listele")){
			
			listArea.setText(obj1.twoThousand());
			
		}else if(actionCommand.equals("�IKI�")){
			obj1.writeToFile();
			System.exit(0);
		}else if(actionCommand.equals("�ark�c�n�n DVD lerini Listele")){
			listArea.setText(obj1.singer(listTextArea.getText()));
			
		}else if(actionCommand.equals("DVD yi S�l")){
			obj1.deleteNodeHeader(deleteTextArea.getText());
			obj1.writeToFile();
		}
	}
	
	
	public static void main(String[] args) {
		Gui gui1 = new Gui();
		gui1.setVisible(true);
	}

}
