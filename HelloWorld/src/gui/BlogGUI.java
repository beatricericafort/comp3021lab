package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import base.Post;
import base.User;
import blog.Blog;

public class BlogGUI implements ActionListener {
	private static final String PROMPT = "What's on your mind?";
	private JFrame mainFrame;
	private JTextArea postTextArea;
	private JTextField postContent;
	private JButton refresh;
	private JButton post;
	private JLabel informationLabel;
	private JPanel upperPanel;
	private JPanel buttonPanel;
	private Blog myBlog;
	

	public BlogGUI(){	
		User user = new User(0, "username", "useremail");
		myBlog = new Blog(user);
	}
	
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
	
	class PostListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String content = postTextArea.getText();
			
			if (content == null || content.length() == 0){
				return;
			}
			
			if (content.length() > 140){
				return;
			}
			
			myBlog.post(new Post(new Date(), content));
			myBlog.save(FILENAME);
		}
	}
	
	class RefreshListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			myBlog.load(FILENAME);
			ArrayList<Post> posts = myBlog.getPosts();
			String postString = "";
			for (Post post: posts){
				postString += post.toString() + "\n";
			}
			postContent.setText(postString);
			
		}
	};

	class LengthListener implements KeyListener{
		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
			updateInfoLabel();
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}
	};
	
	private void upperPanel() {
		refresh = new JButton("Refresh");
		refresh.setBackground(Color.BLUE);
		refresh.addActionListener(new RefreshListener());
		
		post = new JButton("Post");
		post.setBackground(Color.GREEN);
		post.addActionListener(new PostListener());
		
		int numLeft = 140 - PROMPT.length();
		informationLabel = new JLabel("You can still input " + numLeft + " Characters");
		
		postTextArea = new JTextArea(PROMPT);
		postTextArea.setBackground(Color.lightGray);
		postTextArea.addKeyListener(new LengthListener());
		
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

	private void updateInfoLabel(){
		int numLeft = postTextArea == null ? 140 : 140 - postTextArea.getText().length();
		informationLabel.setText("You can still input " + numLeft + " Characters");
		
	}
	

	private static final String FILENAME = "complabb.txt";
}
