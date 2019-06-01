package com.yychatclient.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import com.yychatclient.view.ClientLogin;
import com.yychatclient.view.FriendChat1;
import com.yychatclient.view.FriendList;
import com.yychat.model.Message;

public class ClientReceiverThread extends Thread{
       private Socket s;	   
       public ClientReceiverThread(Socket s){
    	   this.s=s;
       }
       public void run(){
    	   ObjectInputStream ois;
    	   while(true){
   			try{
   				ois =new ObjectInputStream(s.getInputStream());
   			    Message mess= (Message)ois.readObject();			    
   			    String showMessage=mess.getSender()+"��"+mess.getReceiver()+"˵"+mess.getContent();
   			    System.out.println(showMessage);
   			    //jta.append(showMessage+"\r\n");
   			   if(mess.getMessageType().equals(Message.message_Common)){
   				FriendChat1 friendChat1=(FriendChat1)FriendList.hmFriendChat1.get(mess.getReceiver()+"to"+mess.getSender());
      			 friendChat1.appendJta(showMessage);   			   			   			   			
   			   }  			 	   			   			   			
 
   			 if(mess.getMessageType().equals(Message.message_RequestOnlineFriend)){
   				 System.out.println("���ߺ���"+mess.getContent());
   				 FriendList friendlist=(FriendList)ClientLogin.hmFriendlist.get(mess.getReceiver());
   				 System.out.println("friendlistΪ��"+friendlist);
   				 friendlist.setEnableFriendIcon(mess.getContent());  				 
   			 }
   			 //�������ߺ���ͼ��
   			 if(mess.getMessageType().equals(Message.message_Newpy)){
   				 System.out.println("���û����ߣ��û�����"+mess.getContent());
   				 FriendList friendList=(FriendList)ClientLogin.hmFriendlist.get(mess.getReceiver());
   				 System.out.println("FriendList������:"+mess.getReceiver());
   				 friendList.setEnableFriendIcon(mess.getContent());
   			 }
   			 
   			}catch (IOException | ClassNotFoundException e){
   				e.printStackTrace();
   			}
       }
     }
}
