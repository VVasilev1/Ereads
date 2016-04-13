package bg.ereads.classes;

public class Review {
	private String comment;
	private String name;
	private String picture;

	public Review(String comment, String name, String picture) {
		super();
		this.comment = comment;
		this.name = name;
		this.picture = picture;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

}
