package entity;
//地址类
public class Address {
    private int address_id;//地址编号
    private int user_id;//用户账号
    private String user_name;//收货人姓名
    private String user_phone;//收货人电话
    private String user_address;//收货人地址

    public Address(){

    }

    public Address(int user_id, String user_name,  String user_phone, String user_address) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_phone = user_phone;
        this.user_address = user_address;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    @Override
    public String toString() {
        return "Address{" +
                "address_id=" + address_id +
                ", user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_phone='" + user_phone + '\'' +
                ", user_address='" + user_address + '\'' +
                '}';
    }
}
