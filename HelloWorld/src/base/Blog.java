package base;

import java.util.ArrayList;

public class Blog {
	private User user;
	private ArrayList<Post> allPosts;
	
	public Blog (User user){
		this.user = user;
		allPosts = new ArrayList<Post>();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void post (Post post){
		allPosts.add(post);
		System.out.println("A new Post: ");
		System.out.println(post);
	}
	
	public void list(){
		System.out.println("Current posts:");
		for (int i=0; i<allPosts.size(); i++){
			Post post = allPosts.get(i);
			System.out.println("Post[" + (i+1) + "]: " + post);
		}
	}
		
	public void delete(int index){
		if (index < 1 || index > allPosts.size()) {
			System.out.println("Illegal deletion");
			return;
		}
		
		allPosts.remove(index-1);
	}
	
	public String toString(){
		return "Blog of User: " + user.getUserName();
	}
	
	public int hashCode(){
		return (allPosts.hashCode() + user.hashCode())*17;
	}
	
	public boolean equals(Object o){
		if (o==null){
			return false;
		}
		if (!o.getClass().equals(getClass())){
			return false;
		}
		
		Blog newBlog = (Blog) o;
		if (!newBlog.user.equals(user)){
			return false;
		}
		if (!newBlog.allPosts.equals(allPosts)){
			return false;
		}
		return true;
		
	}
}
