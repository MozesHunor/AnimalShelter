package com.animalshelter.project.transfer;

public class GetDogsRequest {

    private String partialName;
    private String breed;

    public String getPartialName() {
        return partialName;
    }

    public void setPartialName(String partialName) {
        this.partialName = partialName;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Override
    public String toString() {
        return "GetDogsRequest{" +
                "partialName='" + partialName + '\'' +
                ", breed='" + breed + '\'' +
                '}';
    }
}
