package base;

import java.util.Date;

public class Post {
	private Date date;
	private String content;
	
	public Post (Date date, String content){
		this.date = date;
		this.content = content;
	}
	
	public String getContent(){
		return content;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	
	public Date getDate(){
		return date;
	}
	
	public void setDate(Date date){
		this.date = date;
	}
	
	@Override
	public String toString(){
		return date.toString() + "\n" + content;
	}
	
	/**
	 * returns true if the posts have the same date and content
	 */
	@Override
	public boolean equals(Object o){		
		if (o == null){
			return false;
		}
		
		if (!o.getClass().getName().equals(getClass().getName())){
			return false;
		}
		
		Post post = (Post) o;
		if (!date.equals(post.date)){
			return false;
		}
		if (!content.equals(post.content)){
			return false;
		}
		
		return true;
	}
	
	@Override
	public int hashCode(){
		return 17*date.hashCode() + content.hashCode();
	}
	
	/**
	 * returns true if the post contains the keyword
	 * @param keyword
	 * @return
	 */
	public boolean contains (String keyword){
		return content.contains(keyword);
	}
}
