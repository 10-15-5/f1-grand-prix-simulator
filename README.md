# 🏎️ F1 Grand Prix Simulator

A Java-based Formula 1 racing simulator developed as part of a college Object-Oriented Programming assignment.

The application will allow users to select a driver, choose tyre compounds and compete in fictional Grand Prix races. Race outcomes will be influenced by driver skill, car performance and random race events.

## 🎯 Project Objectives

The aim of this project is to develop a simple but engaging Formula 1 simulator while demonstrating key Java programming concepts, including:

- Object-Oriented Programming
- Encapsulation and inheritance
- Interfaces and polymorphism
- Constructors and method overloading
- Exception handling
- Enums and collections
- Records and immutable objects
- Lambda expressions and method references
- Sealed classes and interfaces
- Modern Java language features

## 🏁 Planned Features

- Fictional Formula 1 teams and drivers.
- Driver selection.
- Three tyre compounds: Soft, Medium and Hard.
- Five-lap Grand Prix races.
- Randomised lap times and race positions.
- Overtaking and race incidents.
- Final race classifications.
- Championship points and standings.

## 📁 Project Structure

```text
f1-grand-prix-simulator/
│
├── src/
│   └── com/
│       └── f1sim/
│           ├── Main.java
│           ├── models/
│           │   ├── Driver.java
│           │   ├── Team.java
│           │   └── Car.java
│           │
│           ├── enums/
│           │   └── TyreCompound.java
│           │
│           ├── interfaces/
│           ├── events/
│           └── simulation/
│
├── .gitignore
└── README.md
```

## 🚀 Running the Application

Ensure you have Java installed and available in your terminal.

Clone the repository:

```bash
git clone <repository-url>
```

Navigate into the project directory:

```bash
cd f1-grand-prix-simulator
```

Compile the application:

```bash
javac -d out src/com/f1sim/Main.java src/com/f1sim/models/*.java src/com/f1sim/enums/*.java
```

Run the application:

```bash
java -cp out com.f1sim.Main
```

## 📌 Status

The project is currently under development.

Completed:
- Initial project structure.
- Driver class with encapsulation and validation.
- Tyre compound enum.
- Team class with driver management.
- Car class with performance attributes.
- Basic testing through the main application.

Further development will focus on implementing the race simulation, race events and championship system.

---
