package com.animalshelter.project.transfer;

public class GetCustomersRequest {

    private String partialFirstName;

    public String getPartialFirstName() {
        return partialFirstName;
    }

    public void setPartialFirstName(String partialFirstName) {
        this.partialFirstName = partialFirstName;
    }

    @Override
    public String toString() {
        return "GetCustomersRequest{" +
                "partialName='" + partialFirstName + '\'' +
                '}';
    }
}
