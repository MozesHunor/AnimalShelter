package com.animalshelter.project.transfer;

public class CustomerResponse {

    private long id;
    private String firstName;
    private String lastName;
    private double ssn;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public double getSsn() {
        return ssn;
    }

    public void setSsn(double ssn) {
        this.ssn = ssn;
    }

    @Override
    public String toString() {
        return "CustomerResponse{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", ssn=" + ssn +
                '}';
    }
}
