package com.yychatclient.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.*;

public class FriendList extends JFrame implements ActionListener,MouseListener{
	public static HashMap hmFriendChat1=new HashMap<String,FriendChat1>();//键值对
           CardLayout cardLayout;
           
           JPanel myFriendPanel;
           JButton myFriendJButton;
           
           JScrollPane myFriendJScrollPane;
           JPanel myFriendListJPanel;
           static final int FRIENDCOUNT=51;
           JLabel[] myFriendJLabel=new JLabel[51];
           
           JScrollPane myStrangerJScrollPane;
           JPanel myStrangerListJPanel;
           static final int A=21;
           JLabel[] myStrangerJLabel=new JLabel[51];
           
           JPanel myStrangerBlackListJPanel;
           JButton myStrangerJButton;
           JButton blackListJButton;
           
           JPanel myStrangerPanel;
            
           //JPanel myblack;
           
           JPanel myFriendStrangerPanel;
           JButton myFriendJButton1;
           JButton myStrangerJButton1;
           
           JButton blackListJButton1;
           
           String userName;
	     public FriendList(String userName,String friendString) {
	    	 this.userName=userName;
		myFriendPanel=new JPanel(new BorderLayout());//边界布局
		
		myFriendJButton=new JButton("我的好友");
		myFriendPanel.add(myFriendJButton,"North");
		
		String[] friendName=friendString.split(" ");
		int count=friendName.length; 
		myFriendListJPanel=new JPanel(new GridLayout(count,1));
		
		for(int i=0;i<count;i++)
		{			
			myFriendJLabel[i]=new JLabel(friendName[i]+"",new ImageIcon("images/yy4.gif"),JLabel.LEFT);
//			myFriendJLabel[i].setEnabled(false);
			myFriendJLabel[i].addMouseListener(this);
		    myFriendListJPanel.add(myFriendJLabel[i]);
		}
//		for(int i=1;i<FRIENDCOUNT;i++)
//		{			
//			myFriendJLabel[i]=new JLabel(i+"",new ImageIcon("images/yy4.gif"),JLabel.LEFT);
//			myFriendJLabel[i].setEnabled(false);
//			myFriendJLabel[i].addMouseListener(this);
//		    myFriendListJPanel.add(myFriendJLabel[i]);
//		}
//		myFriendJLabel[Integer.parseInt(userName)].setEnabled(true);
		myFriendJScrollPane=new JScrollPane(myFriendListJPanel);
		myFriendPanel.add(myFriendJScrollPane);
		
		myStrangerBlackListJPanel=new JPanel(new GridLayout(2,1));
		myStrangerJButton=new JButton("我的陌生人");
		
		myStrangerJButton.addActionListener(this);
		
		blackListJButton=new JButton("黑名单");
		myStrangerBlackListJPanel.add(myStrangerJButton);
		myStrangerBlackListJPanel.add(blackListJButton);
		myFriendPanel.add(myStrangerBlackListJPanel,"South");
		
		myStrangerPanel=new JPanel(new BorderLayout());
		
		myStrangerListJPanel=new JPanel(new GridLayout(A-1,1));
		for(int i=1;i<A;i++)
		{
			myStrangerJLabel[i]=new JLabel(i+"",new ImageIcon("images/yy4.gif"),JLabel.LEFT);
			myStrangerJLabel[i].addMouseListener(this);		    
			myStrangerListJPanel.add(myStrangerJLabel[i]);
		}
		
		myStrangerJScrollPane=new JScrollPane(myStrangerListJPanel);
		myStrangerPanel.add(myStrangerJScrollPane);
		
		myFriendStrangerPanel=new JPanel(new GridLayout(2,1));
		myFriendJButton1=new JButton("我的好友");
		myFriendJButton1.addActionListener(this);
		myStrangerJButton1=new JButton("我的陌生人");
		myFriendStrangerPanel.add(myFriendJButton1);
		myFriendStrangerPanel.add(myStrangerJButton1);
		myStrangerPanel.add(myFriendStrangerPanel,"North");
		
		blackListJButton1=new JButton("黑名单");
		myStrangerPanel.add(blackListJButton1,"South");
		
		cardLayout=new CardLayout();
		this.setLayout(cardLayout);
		this.add(myFriendPanel,"1");
		this.add(myStrangerPanel,"2");
		//this.add(myblack,"3");
		
		this.setSize(150,500);
		this.setTitle(userName+"的好友列表");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		if(arg0.getSource()==myStrangerJButton)
		{
			cardLayout.show(this.getContentPane(),"2");
		}
		if(arg0.getSource()==myFriendJButton1){
			cardLayout.show(this.getContentPane(),"1");
		}
	}

	public static void main(String[] args) 
    {
   //FriendList FriendList=new FriendList();
}

	public void setEnableFriendIcon(String friendString) {
		String[] friendName=friendString.split(" ");
		int count=friendName.length;
		for(int i=0;i<count;i++){
			myFriendJLabel[Integer.parseInt(friendName[i])].setEnabled(true);//激活在线好友列表
		}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
	if(arg0.getClickCount()==2){
		JLabel jlbl=(JLabel)arg0.getSource();
		String receiver=jlbl.getText();
		//new Thread(new FriendChat1(this.userName,receiver)).start();//创建线程
		FriendChat1 friendChat1=(FriendChat1)hmFriendChat1.get(userName+"to"+receiver);
		if(friendChat1==null) {
			friendChat1=new FriendChat1(this.userName,receiver);
			hmFriendChat1.put(userName+"to"+receiver,friendChat1);//保存对象到HashMap
		}else{
			friendChat1.setVisible(true);
		}
		
	}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JLabel jLabel=(JLabel)e.getSource();
		jLabel.setForeground(Color.red);
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JLabel jLabel=(JLabel)e.getSource();
		jLabel.setForeground(Color.black);
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
