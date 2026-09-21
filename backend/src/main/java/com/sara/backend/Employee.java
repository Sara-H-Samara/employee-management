package com.sara.backend;

public abstract class Employee {
    private int id;
    private String name;
    private double baseSalary;

    public Employee(int id, String name, double baseSalary) {
        this.id = id;
        this.name = name;
        this.baseSalary = baseSalary;
    }

    // Abstraction: كل نوع موظف لازم ينفذ هاي الميثود بطريقته الخاصة
    public abstract double calculateSalary();

    // Abstraction: كل نوع لازم يقول شو نوعه
    public abstract String getType();

    // Encapsulation: الحقول private، الوصول إلها بس عن طريق getters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getBaseSalary() { return baseSalary; }
}