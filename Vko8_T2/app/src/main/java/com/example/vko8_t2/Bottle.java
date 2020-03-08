package com.example.vko8_t2;

public class Bottle {

    private String name;

    private String manufacturer;

    private double total_energy;

    private double size;

    private float prize;

    public Bottle(){
        name = "Pepsi Max";
        manufacturer = "Pepsi";
        total_energy = 0.3;
        size = 0.5;
        prize = (float) 1.80;
    }

    public Bottle(String name_in, double size_in, float prize_in){
        name = name_in;
        size = size_in;
        prize = prize_in;
    }

    public String getName() {
        return name;
    }
    //public String getManufacturer(){}

    //public double getEnergy(){}

    //public double getSize() {}
    public float getPrize() {
        return prize;
    }

    public double getSize() {
        return size;
    }

}
