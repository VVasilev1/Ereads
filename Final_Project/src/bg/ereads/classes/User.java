package bg.ereads.classes;

public class User {
	private String firstName;
	private String lastName;
	private String eMail;
	private String profilePicture;
	private String password;

	public User(String firstName, String lastName, String eMail, String profilePicture, String password) {
		this.eMail = eMail;
		this.lastName = lastName;
		this.firstName = firstName;
		this.password = password;
		if (profilePicture == null) {
			this.profilePicture = "default.jpg";
		} else {
			this.profilePicture = profilePicture;
		}
	}

	public User() {

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
}
