package com.sara.backend;

public class Manager extends Employee {
    private double teamBonus;

    public Manager(int id, String name, double baseSalary, double teamBonus) {
        super(id, name, baseSalary);
        this.teamBonus = teamBonus;
    }

    @Override
    public double calculateSalary() {
        return getBaseSalary() + (teamBonus * 1.5);
    }

    @Override
    public String getType() {
        return "Manager";
    }

    public double getTeamBonus() { return teamBonus; }
}