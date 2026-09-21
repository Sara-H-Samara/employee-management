package com.sara.backend;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    private final List<Employee> employees = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    // GET all — Polymorphism: كل واحد بيرجع نوعه وراتبه المحسوب بطريقته
    @GetMapping
    public List<Map<String, Object>> getAllEmployees() {
        return employees.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // GET average salary (Stream API)
    @GetMapping("/average-salary")
    public double getAverageSalary() {
        return employees.stream()
                .mapToDouble(Employee::calculateSalary)
                .average()
                .orElse(0);
    }

    // POST — إضافة موظف جديد، النوع بيتحدد من الـ request
    @PostMapping
    public Map<String, Object> addEmployee(@RequestBody Map<String, Object> body) {
        String type = (String) body.get("type");
        String name = (String) body.get("name");
        double baseSalary = Double.parseDouble(body.get("baseSalary").toString());
        int id = idCounter.getAndIncrement();

        Employee employee;
        if (type.equalsIgnoreCase("Developer")) {
            double bonus = Double.parseDouble(body.get("extra").toString());
            employee = new Developer(id, name, baseSalary, bonus);
        } else if (type.equalsIgnoreCase("Manager")) {
            double teamBonus = Double.parseDouble(body.get("extra").toString());
            employee = new Manager(id, name, baseSalary, teamBonus);
        } else {
            throw new RuntimeException("Unknown employee type: " + type);
        }

        employees.add(employee);
        return toResponse(employee);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable int id) {
        employees.removeIf(e -> e.getId() == id);
    }

    // Helper: بيحول أي Employee (مهما كان نوعه) لشكل موحد نرجعه للفرونت
    private Map<String, Object> toResponse(Employee e) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", e.getId());
        map.put("name", e.getName());
        map.put("type", e.getType());
        map.put("baseSalary", e.getBaseSalary());
        map.put("calculatedSalary", e.calculateSalary());   // Polymorphism هون بالظبط
        return map;
    }
}