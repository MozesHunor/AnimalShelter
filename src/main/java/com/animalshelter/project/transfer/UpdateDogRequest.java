package com.animalshelter.project.transfer;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class UpdateDogRequest {

    @NotNull
    private String name;
    @NotNull
    private String breed;
    @NotNull
    private int weight;
    @NotNull
    @Min(0)
    private int age;
    @NotNull
    private Date sinceInShelter;
    @NotNull
    private boolean needsMedicalAttention;
    @NotNull
    private boolean wasAdopted;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getSinceInShelter() {
        return sinceInShelter;
    }

    public void setSinceInShelter(Date sinceInShelter) {
        this.sinceInShelter = sinceInShelter;
    }

    public boolean isNeedsMedicalAttention() {
        return needsMedicalAttention;
    }

    public void setNeedsMedicalAttention(boolean needsMedicalAttention) {
        this.needsMedicalAttention = needsMedicalAttention;
    }

    public boolean isWasAdopted() {
        return wasAdopted;
    }

    public void setWasAdopted(boolean wasAdopted) {
        this.wasAdopted = wasAdopted;
    }

    @Override
    public String toString() {
        return "CreateDogRequest{" +
                "name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", weight=" + weight +
                ", age=" + age +
                ", sinceInShelter=" + sinceInShelter +
                ", needsMedicalAttention=" + needsMedicalAttention +
                ", wasAdopted=" + wasAdopted +
                '}';
    }
}
