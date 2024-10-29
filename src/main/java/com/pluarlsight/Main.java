package com.pluarlsight;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Asset> assetsNStuff = new ArrayList<>();
        House vacationHome = new House("Coastal Home", "01-01-3000", 1_000_000);
        assetsNStuff.add(vacationHome);
        House dinkyShack = new House("Shack", "10-28-2024", 100_000);
        assetsNStuff.add(dinkyShack);
        Vehicle car = new Vehicle("My car", "01-01-2020", 10_000);
        assetsNStuff.add(car);
        for (Asset asset : assetsNStuff) {
            if (asset instanceof Vehicle) {
                System.out.println(asset.toString());
            }
            if (asset instanceof House) {
                System.out.println(asset.toString());
            }
        }
    }
}

class Asset {
    protected String description;
    protected String dateAcquired;
    protected double originalCost;

    public Asset(String description, String dateAcquired, double originalCost) {
        this.description = description;
        this.dateAcquired = dateAcquired;
        this.originalCost = originalCost;
    }

    public String getDescription() {
        return description;
    }

    public String getDateAcquired() {
        return dateAcquired;
    }

    public void setOriginalCost(double originalCost) {
        this.originalCost = originalCost;
    }

    public double getOriginalCost() {
        return originalCost;
    }

    public double getValue() {
        return originalCost;
    }
}

class House extends Asset {
    private String address;
    private int condition;
    private int squareFoot;
    private int lotSize;

    public House(String description, String dateAcquired, double originalCost) {
        super(description, dateAcquired, originalCost);
    }

    public String getAddress() {
        return address;
    }

    public int getCondition() {
        return condition;
    }

    public int getSquareFoot() {
        return squareFoot;
    }

    public int getLotSize() {
        return lotSize;
    }

    @Override
    public double getValue() {
        switch (condition) {
            case 1:
                squareFoot = 180;
                break;
            case 2:
                squareFoot = 130;
                break;
            case 3:
                squareFoot = 90;
                break;
            case 4:
                squareFoot = 80;
                break;
            default:
                System.out.println("Default is n/a");
                break;
        }
        return super.getValue() * squareFoot;
    }

    //Only applies to House
    public String toString() {
        return String.format("%s, %s, %.2f", description, dateAcquired, originalCost);
    }
}

class Vehicle extends Asset {
    private String makeModel;
    private int year;
    private int odometer;

    public Vehicle(String description, String dateAcquired, double originalCost) {
        super(description, dateAcquired, originalCost);
    }

    public String getMakeModel() {
        return makeModel;
    }

    public int getYear() {
        return year;
    }

    public int getOdometer() {
        return odometer;
    }

    @Override
    public double getValue() {
        if (year > 0 && year <= 3) {
            setOriginalCost(originalCost * .97);
        } else if (year > 4 && year <= 6) {
            setOriginalCost(originalCost * .94);
        } else if (year > 7 && year <= 10) {
            setOriginalCost(originalCost * .92);
        } else if (year > 10) {
            setOriginalCost(1_000);
        } else if (odometer > 100_000 && !makeModel.equalsIgnoreCase("Honda") &&
                !makeModel.equalsIgnoreCase("Toyota")) {
            setOriginalCost(originalCost * .75);
        } else {
            System.out.println("shrug emoticon");
        }
        return super.getValue();
    }

    //Only applies to Vehicle
    public String toString() {
        return String.format("%-5s, %-1s, %5.2f", description, dateAcquired, originalCost);
    }
}
