package com.jxau.server;

import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
public class Client extends JFrame implements ActionListener,ItemListener
{
	private JPanel pN,pS,pW,pE,pSize;//pN(North panel),pC(Center panel),pS(South panel),pE(East panel),pW(West  panel);
	private JTextArea in_message;
	private JTextField out_message,secret;
	private JButton putOut;
	private JLabel label1,label2,labelgif;
	private JComboBox ziti,size,color;
	//private JList list;
	private JScrollPane scroll;
	private int zsize;
	private String zti;
	private String username;
	Color ziColor;//������ʾ��ɫ�ı�ʶ
	String zicolor;
	private String messageGetout;
	private boolean siliao;//˽�ı��
	//
	 int  clientID;
	 private Socket connection;
	 private DataInputStream input;
	 private DataOutputStream output;
	 //������
	 private static String winTitle="ˮ����������(Ⱥ����-----)";
	 
	 boolean InorNot=false;   //�ж��Ƿ������Ϸ�����
	public Client(String ss) throws Exception, IOException
	{
		super(winTitle);
		Container con=getContentPane();
		username=ss;
		
		putOut=new JButton("����");
		//��ϵĻ�ӭ���
		Icon bug=new ImageIcon("4.gif");
		//ss+"��ã�ˮ����������Ϊ�����"
		labelgif=new JLabel(bug);
		label1=new JLabel(ss+"��ˮ����������Ϊ�����");
		label1.setForeground(Color.BLUE);
		label1.setFont(new Font("����",Font.BOLD,12));
		//˽�Ŀ�
		secret=new JTextField("���˭˽��---",6);
		secret.addMouseListener(new MouseAdapter(){

			public void mouseClicked(MouseEvent arg0) {
				if(secret.getText().equals("���˭˽��---")){
					secret.setText("");
				}
			}
		});
		label2=new JLabel("       �����С");
		//������Ͽ�
		String names[]=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();;
		ziti=new JComboBox(names);
		ziti.setSelectedIndex(64);
		zti=(String)ziti.getSelectedItem();
		//�ִ�С
		String sizenum[]={"10","13","15","18","20","25","30","50"};
		size=new JComboBox(sizenum);
		size.setSelectedIndex(1);
		zsize=Integer.parseInt(sizenum[size.getSelectedIndex()]);
		//����ɫ
		Color colors1[]={Color.black,Color.blue,Color.cyan,Color.DARK_GRAY,Color.green};
		String colors[]={"��","��","����","���ɫ","��ɫ"};
		color=new JComboBox(colors);
		color.setSelectedIndex(2);
		ziColor=colors1[color.getSelectedIndex()];
		//������Ϣ��ʾ��
		out_message=new JTextField("��Ҫ˵ʲô~~",60);
		out_message.setFont(new Font(zti,Font.BOLD,zsize));
		out_message.setForeground(ziColor);
		out_message.addMouseListener(new MouseAdapter(){

			public void mouseClicked(MouseEvent arg0) {
				if(out_message.getText().equals("��Ҫ˵ʲô~~")){
					out_message.setText("");
				}
			}
		});
		
		
		//��ʾ������Ϣ��
		in_message=new JTextArea(30,15);
		in_message.setFont(new Font(zti,Font.PLAIN,zsize));
		in_message.setForeground(ziColor);		
		in_message.setEditable(false);
		
		//---------------------------------------------------------
		pN=new JPanel(new FlowLayout(FlowLayout.CENTER));
		pS=new JPanel();
		pW=new JPanel();
		pE=new JPanel();
		//---------------������----------------------------------
		//pN�����
		
		pN.add(labelgif);
		
		//in_message��ӵ�scroll��
		scroll=new JScrollPane(in_message);
		
		//pS ��� ���Ϳ򡢷��Ͱ�ť������������С��pS�Ĳ���ΪGRIDLAYOUT
		pS.setLayout(new GridLayout(3,1));
		pSize=new JPanel(new GridLayout(1,4));
		pSize.add(ziti);pSize.add(color);pSize.add(label2);pSize.add(size);
		pS.add(pSize);pS.add(out_message);
		JPanel pSpanel=new JPanel();
		pSpanel.add(secret);pSpanel.add(putOut);pSpanel.add(label1);
		//pSpanel.add(new JLabel("----------------------------------��Ȩ����------------------------------"));
		pS.add(pSpanel);
		
		//�������Ӽ�����
		putOut.addActionListener(this);
		putOut.addActionListener(new secretText());
		out_message.addActionListener(this);
		out_message.addActionListener(new secretText());
		secret.addActionListener(new secretText());
		size.addItemListener(this);
		ziti.addItemListener(this);
		color.addItemListener(this);
		
		//��ӵ�����
		con.add(pN,BorderLayout.NORTH);
		con.add(scroll,BorderLayout.CENTER);
		con.add(pS,BorderLayout.SOUTH);
		con.add(pW,BorderLayout.WEST);
	    con.add(pE,BorderLayout.EAST);
		//������Ϣ�߳�
		Thread thread=new Thread(new Informaintion());
		thread.start();
		
		
		//�������
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(400,100,320,500);
		setVisible(true);
		validate();
		
	}
	private class secretText implements ActionListener
	{

		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			messageGetout=secret.getText();
			if(!messageGetout.equals("���˭˽��---")&&!messageGetout.equals("")&&!messageGetout.equals("0"))
			{				
				char sis[]=messageGetout.toCharArray();
				int havezimu=0;//�ж���û�г�����������ַ���0��ʾû�У�1��ʾ�У�
				for(int x=0;x<sis.length;x++)
				{
					if(sis[x]<48||sis[x]>57)     //0��9��ASCIIֵΪ48--57
					{						
						havezimu=1;
						break;
					}
				}
				if(havezimu==0)
				{
					siliao=true;
					winTitle="ˮ����������(˽����----)";
					setTitle(winTitle);
				}
				else
				{
					siliao=false;
					in_message.append("�Ƿ��ַ�����������������\n");
					secret.setText("���˭˽��---");
					out_message.setText("");
					winTitle="ˮ����������(Ⱥ����----)";
					setTitle(winTitle);
				}
				
			}
			else
			{
				siliao=false;
				secret.setText("���˭˽��---");
				winTitle="ˮ����������(Ⱥ����----)";
				setTitle(winTitle);
			}
						
		}
		
	}
	//������Ϣ
	public void actionPerformed(ActionEvent e) {
		// TODO ���Ͱ�ť
		if((e.getSource()==putOut||e.getSource()==out_message)&&!out_message.getText().equals(""))
		{
			//messageGetout
		if(InorNot)
		 {
			if(siliao)
			{
				try{
					String s="*"+messageGetout+"*"+username+"��"+"#&��|��&#"+out_message.getText();
					output.writeUTF(s);
									
				}catch(Exception e1){}
				
				out_message.setText("");
			 }
			else
			{
				try{
					String s=" "+username+"��"+"#&��|��&#"+out_message.getText();
					output.writeUTF(s);
									
				}catch(Exception e1){}
				
				out_message.setText("");
			}
		}
			
		else
		{
			in_message.append("����������ӳ�ʱ��"+"��"+out_message.getText()+"��"+"û�з��ͳɹ�"+"\n");
		}				
		 }				
	}
	

	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
			String sizenum[]={"10","13","15","18","20","25","30","50"};
			zti=(String)ziti.getSelectedItem();
			Color colors1[]={Color.black,Color.blue,Color.cyan,Color.DARK_GRAY,Color.green};
			ziColor=colors1[color.getSelectedIndex()];
			zsize=Integer.parseInt(sizenum[size.getSelectedIndex()]);
			in_message.setFont(new Font(zti,Font.PLAIN,zsize));
			in_message.setForeground(ziColor);
			out_message.setFont(new Font(zti,Font.PLAIN,zsize));
			out_message.setForeground(ziColor);
		
		
	}
	private class Informaintion implements Runnable
	{

		public void run() {
			 try {
		         
		         // make connection
		         connection = new Socket( "localhost", 50000);
		         // get streams
		         input = new DataInputStream( connection.getInputStream() );
		         output = new DataOutputStream( connection.getOutputStream() );
		         clientID = input.readInt()+1;
		         label1.setText(username+"�����ǵ�"+clientID+"���ÿ�"); 	
		         InorNot=true;
		        
		      }

		      // catch problems setting up connection and streams
		      catch ( IOException ioException ) {
		       //ioException.printStackTrace();  
		         System.out.println("���Ӳ��Ϸ�����");
		         label1.setText(username+"�����������ӳ�ʱ��"); 
		      }
			while(true)//ѭ���ȴ���ȡ������Ϣ
			{				
				try
				{					 			
					String message=new String(input.readUTF());
					StringTokenizer min=new StringTokenizer(message,"#&��|��&#");
					String str1=min.nextToken();
					String str2=min.nextToken();
					in_message.append(str1+str2+"\n");
					
					
				}catch(Exception e){}
			}
			
		}
		
	}

	
	public static void main(String[] args) throws IOException, Exception
	{
		new Client("ˮ��");
	}

}
