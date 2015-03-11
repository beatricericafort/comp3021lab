package base;

import java.util.Comparator;

public class PostSortByContentLength implements Comparator<Post>{

	@Override
	public int compare(Post o1, Post o2) {
		Post post1 = (Post) o1;
		Post post2 = (Post) o2;
		
		Integer length1 = post1.getContent().length();
		Integer length2 = post2.getContent().length();
		
		return length1.compareTo(length2);
	}

}
