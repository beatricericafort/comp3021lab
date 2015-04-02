package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class BlogGUI implements ActionListener {
	private JFrame mainFrame;
	private JTextArea postTextArea;
	private JTextField postContent;
	private JButton refresh;
	private JButton post;
	private JLabel informationLabel;
	private JPanel upperPanel;
	private JPanel buttonPanel;
	

	public void setWindow(){
		mainFrame = new JFrame("Blog");
		mainFrame.setSize(500, 500);
		mainFrame.setLayout(new GridLayout(0, 1));
		
	}
	
	public static void main(String[] args){
		BlogGUI bloggui = new BlogGUI();
		bloggui.setWindow();
		bloggui.addWidgets();
		bloggui.setListeners();

	}

	private void setListeners() {
		post.addActionListener(this);
		refresh.addActionListener(this);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == post){
			postContent.setText("You clicked POST!");
		} else if (e.getSource() == refresh){
			postContent.setText("You clicked REFRESH!");
		}
		
	}

	private void addWidgets() {
		upperPanel();
		
		
		postContent = new JTextField("Here are my posts!");
		mainFrame.add(upperPanel);
		mainFrame.add(postContent);
		
		mainFrame.setVisible(true);
	}

	private void upperPanel() {
		refresh = new JButton("Refresh");
		refresh.setBackground(Color.BLUE);
		post = new JButton("Post");
		post.setBackground(Color.GREEN);
		
		informationLabel = new JLabel("You can still input 140 Characters");
		
		postTextArea = new JTextArea("What's on your mind?");
		postTextArea.setBackground(Color.lightGray);
		
		buttonPanel = new JPanel(new GridLayout(1, 0));
		buttonPanel.add(refresh);
		buttonPanel.add(post);
		

		
		upperPanel = new JPanel();
		upperPanel.setLayout(new BorderLayout());
		upperPanel.setBorder(BorderFactory.createLineBorder(Color.gray));
		
		upperPanel.add(informationLabel, BorderLayout.NORTH);
		upperPanel.add(postTextArea, BorderLayout.CENTER);
		upperPanel.add(buttonPanel, BorderLayout.SOUTH);
	}

}
