package base;

public class User {
	private int userId;
	private String userName;
	private String userEmail;
	
	public User (int userId, String userName, String userEmail){
		this.userId = userId;
		this.userEmail = userEmail;
		this.userName = userName;
		
		System.out.println("User [userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail + "]");
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	@Override
	public String toString(){
		return "User " + userId + " " + userName + " " + userEmail;
	}
	
	@Override
	public boolean equals(Object o){
		if (o == null){
			return false;
		}
		
		if (!o.getClass().getName().equals(getClass().getName())){
			return false;
		}
		
		User user = (User) o;
		if (user.userId != userId) {
			return false;
		}
		
		if (!user.userEmail.equals(userEmail)){
			return false;
		}
		
		if (!user.userName.equals(userName)){
			return false;
		}
		
		return true;
	}
	
	@Override
	public int hashCode(){
		return (userId + userName.hashCode() + userEmail.hashCode())*17;
	}
}
