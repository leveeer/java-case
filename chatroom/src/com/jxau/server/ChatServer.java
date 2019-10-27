package com.jxau.server;

import java.net.*;
import java.util.StringTokenizer;
import java.io.*;

//import javax.swing.*;
//import java.awt.*;
public class ChatServer //extends JFrame
{   private static int clientNum=0;  //��ǰ���ӵ��û���   
    private int maxClients=10;//�������ӵ��û������
   	private ServerSocket ss;
   	//�����û�������Ϣ���߳�����
	private CommunicationThread[]ccs;
   	//��������
	private int leftID[]=new int[maxClients];
    private int IDnum=0;//��ǰ���ߵ��û���
   
   	public ChatServer()
	{  leftID[0]=-1;
	  try {
         ss = new ServerSocket(50000,maxClients);
      }
      // process problems creating ServerSocket
      catch( IOException ioException ) {
         System.out.println("�������𶯲��ɹ���");
         System.exit(0);
        
      }
	  ccs=new CommunicationThread [maxClients];
	  
	  for (int i=0;i<maxClients;i++) //ѭ���ȴ��û�����
      { try {
      	 //�ȴ��û�����,���ӳɹ�ʱ����server��ͨ���̲߳�������
      	 Socket s=ss.accept();
		 ccs[i]=new CommunicationThread(s,i); 
      	 ccs[i].start();
      	 
      	for (int m=0;m<clientNum;m++)    //���͸�����ĸ��������û�
		{   int biaoji=0;
			for(int j=0;j<IDnum;j++)
			{
				if(m==leftID[j])
				{
					biaoji=1;
					
					break;
				}            					
			}
			if(biaoji==0)
			{
				ccs[m].output.writeUTF("ˮ������ʾ��#&��|��&#"+"�û�"+(i+1)+"�����ˣ�"+"��������Ϊ"+String.valueOf(clientNum-IDnum+1));
				
			}
			
		}
      	clientNum++;
      	 
        }
        catch( IOException ioException ) {
        
        } 
      }	  
    }
      
     private  class CommunicationThread extends Thread 
     {
     	private Socket socket;
     	private int clientID;
   	    private DataInputStream input;
        private DataOutputStream output;
        String message;
     	public CommunicationThread(Socket ss,int number)
     	{
     		socket=ss;        //ȡ����Socket 
     		clientID=number;  //ȡ������û���ID
     		
     		try {
     		 //��Socket�õ�����/�����	
             input = new DataInputStream( socket.getInputStream() );  
             output = new DataOutputStream( socket.getOutputStream() );  
            
            }catch( IOException ioException )
            {
             System.out.println("��д����������");
            }
         
        }
        public void run()
        {     
        
        	 try {
        		  output.writeInt(clientID);//���û�����clientID
 				output.writeUTF("ˮ������ʾ��#&��|��&#"+"��������Ϊ"+String.valueOf(clientNum-IDnum));
 			} catch (IOException e) {
 				
 				System.out.println("clientIDû�з���ȥ");
 			}  
 			while (true) {   //ѭ����һ�û�clientID����������Ϣ,���͸����ߵĸ����û� 				
        		try {
        			
        			String message=input.readUTF();  //���û�clientID����������Ϣ
        			 //����˽����Ϣ����˽����Ϣ����ʱ������*��
        			if(message.startsWith("*"))    
        			{
        				StringTokenizer min=new StringTokenizer(message,"*");
        				String si=min.nextToken();        				
        				int str1=Integer.parseInt(si)-1;        				
    					if(str1<clientNum)
    					{
    						message=min.nextToken();
    					    //���͸�����ĸ������ߵ��û�
            			   int biaoji=0;
            				for(int j=0;j<IDnum;j++)
            				{
            					if(str1==leftID[j])//��˽�ĵ������뿪�����У�
            					{
            						biaoji=1;       //��˽�ĵ������뿪�����У��Ͱ�BIAOJI��Ϊ1�����˳����ѭ������
            						ccs[clientID].output.writeUTF("ˮ������ʾ��#&��|��&#"+"�Բ������ҵ��˲����� ");
            						break;
            					}            					
            				}
            				if(biaoji==0)           
        					{            					
            					if(str1!=clientID)
            					{    
            						ccs[str1].output.writeUTF("˽�� "+(clientID+1)+message);
            						//System.out.println(message);
            						ccs[clientID].output.writeUTF("(�㷢��˽����Ϣ) \n"+message);
            					}
            					else if(str1==clientID)
            					{
            						ccs[clientID].output.writeUTF("ˮ������ʾ��#&��|��&#��ô���Լ�˵������ ");
            					}
        					}
    					}
    					else if(str1>=clientNum)
    					{    						
    							ccs[clientID].output.writeUTF("ˮ������ʾ��#&��|��&#�Բ������ҵ��˲�����");    					  						   						
    					}    					     			
    					
        			}
        			//����Ⱥ��Ϣ
        			else
        			{
        				for (int i=0;i<clientNum;i++)    //���͸����ߵĸ����û�
            			{   int biaoji=0;
            				for(int j=0;j<IDnum;j++)
            				{
            					if(i==leftID[j])
            					{
            						biaoji=1;
            						
            						break;
            					}            					
            				}
            				if(biaoji==0)
        					{
        						ccs[i].output.writeUTF(" "+message);
        					}            				
            			}        		
        			}      			
        			
        	    }
               
               catch( IOException ioException ) {
                //ioException.printStackTrace();
            	   //���ͻ����뿪
            	   leftID[IDnum]=clientID;
            	   System.out.println(leftID[IDnum]+"�ͻ�������");
            	   for (int m=0;m<clientNum;m++)    //���͸����ߵĸ����û�
           		{   int biaoji=0;
           			for(int j=0;j<IDnum;j++)
           			{
           				if(m==leftID[j])
           				{
           					biaoji=1;
           					
           					break;
           				}            					
           			}
           			if(biaoji==0)
           			{
           				try {
							ccs[m].output.writeUTF("ˮ������ʾ��#&��|��&#"+"�û�"+(leftID[IDnum]+1)+"������;"+"��������Ϊ"+String.valueOf(clientNum-IDnum-1));
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							System.out.print("������ȥ"+(clientNum-IDnum-1)+";");
							
						}
           			}
           			
           		}
            	   IDnum++;            	  
            	   break;               
               }
               
           }
         try { 
         output.close();
         input.close();
         socket.close();
         }
         catch( EOFException ioException ) {
            System.err.println( "Client terminated connection" );
        }
         catch( IOException ioException ) {
             ioException.printStackTrace();
             System.exit( 1 );
        }	 
        
     }
      
 	}
     

	public static void main(String[] args)
	{
		new ChatServer();
		 
	}
 
	
}