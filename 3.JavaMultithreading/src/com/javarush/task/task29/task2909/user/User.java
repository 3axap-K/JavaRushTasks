package com.javarush.task.task29.task2909.user;

public class User {

    public User(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    private String name;
    private String surname;
    private int age;


    private Work work;

    private boolean man;

    private Address address;

    public String getAddress() {
        return address.getCountry() + " " + address.getCity() + " " + address.getHouse();
    }


        public String getCountry() {
            return this.address.getCountry();
        }

        public void setCountry(String country) {
            this.address.setCountry(country);
        }

        public String getCity() {
            return this.address.getCity();
        }

        public void setCity(String city) {
            this.address.setCity(city);
        }

        public String getHouse() {
            return this.address.getHouse();
        }

        public void setHouse(String house) {
            this.address.setHouse(house);
        }



    public void setAddress(Address address) {
        this.address = address;
    }

    public boolean isMan() {
        return man;
    }

    public void setMan(boolean man) {
        this.man = man;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBoss() {
        return this.getWork().getBoss();
    }

///    public String getAddress() {return country + " " + city + " " + house.house;}

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    public void printInfo(){
        String info = String.format("Имя: %s\nФамилия: %s", this.getName(), this.getSurname());
        System.out.println(info);
    }

    public void printAdditionalInfo() {
        if (this.getAge() < 16)
            System.out.println("Пользователь моложе 16 лет");
        else
            System.out.println("Пользователь старше 16 лет");
    }
}