package mx.drivera.place.examples.microservices.domain;

import com.jsoniter.output.JsonStream;

public class UserBuilder {
    private String userId;
    private String fullName;
    private String address;
    private String email;
    private String phone;
    private String employeeId;

    public UserBuilder() {
    }

    public String getUserId() {
        return userId;
    }

    public UserBuilder setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserBuilder setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public UserBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserBuilder setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public UserBuilder setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    @Override
    public String toString() {
        return JsonStream.serialize(this);
    }

}