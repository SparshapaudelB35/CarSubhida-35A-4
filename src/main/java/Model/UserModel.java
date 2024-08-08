package Model;
public class UserModel {
    private final String id;
    private final String name;
    private final String contact;
    private final String address;
    private final String license;
    private final String gender;

    public UserModel(String id, String name,  String contact, String address,String license, String gender) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.license = license;
        this.gender = gender;
    }

    public String getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getContact() {
        return contact;
    }

    public String getLicense() {
        return license;
    }

    public String getGender() {
        return gender;
    }
}