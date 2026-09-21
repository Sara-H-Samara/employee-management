package com.sara.backend;

public class Developer extends Employee {
    private double bonus;

    public Developer(int id, String name, double baseSalary, double bonus) {
        super(id, name, baseSalary);   // Inheritance: بينادي constructor الأب
        this.bonus = bonus;
    }

    @Override
    public double calculateSalary() {
        return getBaseSalary() + bonus;
    }

    @Override
    public String getType() {
        return "Developer";
    }

    public double getBonus() { return bonus; }
}