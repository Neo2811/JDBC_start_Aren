package am.hitech.jdbc.model;

public class PhoneNumber {
    private int id;
    private int number;
    private int phoneCodeId;
    private int userId;

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    public int getNumber() {
        return number;
    }

    public void setPhoneCodeId(int phoneCodeId) {
        this.phoneCodeId = phoneCodeId;
    }
    public int getPhoneCodeId() {
        return phoneCodeId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getUserId() {
        return userId;
    }
    public PhoneNumber(){

    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "id=" + id +
                ", number=" + number +
                ", phoneCodeId=" + phoneCodeId +
                ", userId=" + userId +
                '}';
    }
}
