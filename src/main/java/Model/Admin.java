package Model;

public class Admin {
    private final String username;
    private final String password;
    private final String contact;
    private  String email;
    private final String gender;
    private final String maritalStatus;
    private final String citizenshipId;

    public Admin(String username, String password, String contact, String email, String gender, String maritalStatus, String citizenshipId) {
        this.username = username;
        this.password = password;
        this.contact = contact;
        this.email = email;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.citizenshipId = citizenshipId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getContact() {
        return contact;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public String getCitizenshipId() {
        return citizenshipId;
    }
}
