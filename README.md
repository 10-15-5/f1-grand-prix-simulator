# 🏎️ F1 Grand Prix Simulator

A Java-based Formula 1 racing simulator developed as part of a college
Object-Oriented Programming assignment.

The application allows users to select a driver, choose tyre compounds
and compete in fictional Grand Prix races. Race outcomes are influenced
by driver skill, car performance, tyre selection and random race events.

## 🎯 Project Objectives

The aim of this project is to develop a simple but engaging Formula 1
simulator while demonstrating key Java programming concepts, including:

-   Object-oriented programming, encapsulation and inheritance
-   Constructors, constructor chaining and method overloading
-   Interfaces, default/static/private interface methods and
    polymorphism
-   Exception handling, enums and collections
-   Arrays, records and immutable objects
-   Defensive copying and call-by-value
-   Lambda expressions, Predicate and method references
-   Sealed classes, pattern matching and switch expressions
-   Modern Java language features, including a Java 27 preview feature

## 🏁 Features

-   Five fictional Formula 1 teams, each with two drivers.
-   Driver selection.
-   Three tyre compounds: Soft, Medium and Hard.
-   Up to three five-lap Grand Prix races.
-   Randomised lap times influenced by driver and car attributes.
-   Race events, including overtaking, safety cars, mechanical failures
    and fastest laps.
-   Live lap-time classifications.
-   Final race classifications.
-   Championship points and overall standings.
-   A final message showing the selected driver's championship result.

## 📁 Project Structure

``` text
f1-grand-prix-simulator/
├── src/
│   └── com/
│       └── f1sim/
│           ├── Main.java
│           │
│           ├── models/
│           │   ├── Car.java
│           │   ├── Driver.java
│           │   └── Team.java
│           │
│           ├── enums/
│           │   └── TyreCompound.java
│           │
│           ├── interfaces/
│           │   ├── Raceable.java
│           │   └── Scorable.java
│           │
│           ├── events/
│           │   ├── FastestLapEvent.java
│           │   ├── MechanicalFailureEvent.java
│           │   ├── OvertakingEvent.java
│           │   ├── RaceEvent.java
│           │   └── SafetyCarEvent.java
│           │
│           └── simulation/
│               ├── Championship.java
│               ├── DriverStanding.java
│               ├── LapResult.java
│               ├── LapTimeClassifier.java
│               ├── Race.java
│               ├── RaceCar.java
│               ├── RaceReport.java
│               ├── RaceResult.java
│               ├── RaceSetupException.java
│               ├── RaceSimulator.java
│               └── RaceTime.java
│
├── .gitignore
└── README.md
```

## ☕ Requirements

-   JDK 27
-   A terminal or command-line interface

The project uses a Java 27 preview language feature (primitive types in
patterns, instanceof and switch). Preview features must be enabled
both when compiling and when running the application.

## 🚀 Running the Application

Clone the repository:

``` bash
git clone <repository-url>
```

Navigate into the project directory:

``` bash
cd f1-grand-prix-simulator
```

Make sure Java 27 is installed:

``` bash
java --version
javac --version
```

Compile all Java source files with preview features enabled:

``` bash
javac --enable-preview --release 27 -d out $(find src -name "*.java")
```

Run the application with preview features enabled:

``` bash
java --enable-preview -cp out com.f1sim.Main
```

**Important:** `--enable-preview` is required for both commands. The
`--release 27` option ensures the code is compiled against Java 27
language and API rules.

## 📌 Project Status

The main simulator functionality has been implemented, including:

-   Project structure and package organisation.
-   Driver, team and car models with encapsulation and validation.
-   Tyre compound enum.
-   Race simulation and lap-time calculation.
-   Race events and event-based lap-time adjustments.
-   Race results and championship scoring.
-   Driver standings and final championship position.
-   Immutable race-time value type and defensive copying.
-   Java 27 preview-feature demonstration using primitive type patterns.