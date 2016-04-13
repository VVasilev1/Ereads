package bg.ereads.classes;

public class Book {
	@Override
	public String toString() {
		return "Book [name=" + name + ", autor=" + autor + ", genre=" + genre + "]";
	}

	private String name;
	private String autor;
	private String image;
	private int sumOfVotes;
	private String description;
	private String genre;
	private String linkToBuy;
	private int numberOfVotes;

	public double getRating() {
		double rating;
		if (this.getNumberOfVotes() == 0 && this.getSumOfVotes() == 0) {
			rating = 1;
		} else {
			rating = ((double) this.getSumOfVotes()) / this.getNumberOfVotes();
		}
		return rating;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getSumOfVotes() {
		return sumOfVotes;
	}

	public void setSumOfVotes(int sumOfVotes) {
		this.sumOfVotes = sumOfVotes;
	}

	public int getNumberOfVotes() {
		return numberOfVotes;
	}

	public void setNumberOfVotes(int numberOfVotes) {
		this.numberOfVotes = numberOfVotes;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getLinkToBuy() {
		return linkToBuy;
	}

	public void setLinkToBuy(String linkToBuy) {
		this.linkToBuy = linkToBuy;
	}

	public Book(String name, String autor, String image, String description, String genre, String linkToBuy) {
		super();
		this.name = name;
		this.autor = autor;
		this.image = image;
		this.description = description;
		this.genre = genre;
		this.linkToBuy = linkToBuy;
	}

	public Book(String name, String autor, String description, String genre, String linkToBuy) {
		super();
		this.name = name;
		this.autor = autor;
		this.description = description;
		this.genre = genre;
		this.linkToBuy = linkToBuy;
	}

	public Book() {

	}

}
