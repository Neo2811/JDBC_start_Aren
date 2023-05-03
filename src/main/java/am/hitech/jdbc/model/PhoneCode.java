package am.hitech.jdbc.model;

public class PhoneCode {
    private int id;
    private int code;
    private int operatorId;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public int getOperatorId() {
        return operatorId;
    }
    public PhoneCode(){

    }

    @Override
    public String
    toString() {
        return "PhoneCode{" +
                "id=" + id +
                ", code=" + code +
                ", operatorId=" + operatorId +
                '}';
    }
}
