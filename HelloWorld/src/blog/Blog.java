package blog;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

import base.Post;
import base.User;

public class Blog implements Serializable {
	private User user;
	private ArrayList<Post> allPosts;

	public Blog(User user) {
		this.user = user;
		allPosts = new ArrayList<Post>();
	}

	public ArrayList<Post> getPosts(){
		return allPosts;
	}
	
	public void save(String filepath) {
		try {
			FileOutputStream fileOutStream = new FileOutputStream(filepath);
			ObjectOutputStream objectOutStream = new ObjectOutputStream(fileOutStream);
			objectOutStream.writeObject(this);
		} catch (FileNotFoundException e) {
			System.out.println("Wait! There is something wrong. I cannot find the file..");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void load(String filepath) {
		try {
			FileInputStream fileInStream = new FileInputStream(filepath);
			ObjectInputStream objectInStream = new ObjectInputStream(fileInStream);
			Object object = objectInStream.readObject();
			Blog blog = (Blog) object;
			user = blog.user;
			allPosts = blog.allPosts;
		} catch (FileNotFoundException e) {
			System.out.println("Wait! There is something wrong. I cannot find the file..");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void post(Post post) {
		allPosts.add(post);
		System.out.println("A new Post: ");
		System.out.println(post);
	}

	public void list() {
		System.out.println("Current posts:");
		for (int i = 0; i < allPosts.size(); i++) {
			Post post = allPosts.get(i);
			System.out.println("Post[" + (i + 1) + "]: " + post);
		}
	}

	public void delete(int index) {
		if (index < 1 || index > allPosts.size()) {
			System.out.println("Illegal deletion");
			return;
		}

		allPosts.remove(index - 1);
	}

	public String toString() {
		return "Blog of User: " + user.getUserName();
	}

	public int hashCode() {
		return (allPosts.hashCode() + user.hashCode()) * 17;
	}

	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (!o.getClass().equals(getClass())) {
			return false;
		}

		Blog newBlog = (Blog) o;
		if (!newBlog.user.equals(user)) {
			return false;
		}
		if (!newBlog.allPosts.equals(allPosts)) {
			return false;
		}
		return true;

	}

	public void search(int month, String someone) {
		Calendar cal = Calendar.getInstance();
		month--;
		for (Post p : allPosts) {
			cal.setTime(p.getDate());
			int postMonth = cal.get(Calendar.MONTH);

			if (month != postMonth) {
				continue;
			}

			if (p.getContent().contains(someone)) {
				System.out.println(p);
			}
		}
	}

	public void setPosts(ArrayList<Post> posts) {
		allPosts = posts;
	}
}
