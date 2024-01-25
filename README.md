# AirCoach-Optical-Roughness-Metrology-Software


This repository contains the source code and documentation for the AirCoach Optical Roughness Metrology Software. This software is designed to work seamlessly with AirCoach's optical roughness metrology device, providing a user-friendly interface and essential functionalities for analyzing and visualizing measurements.

## Project Structure

The project is organized into several packages to ensure modularity and clarity in code organization:

### 1. `binmethod` Package

This package contains the implementation of three bin rule formulae: Square root choice, Sturge's, and Rice rule. The abstract parent class `BinFormulae` serves as the foundation, while concrete classes represent specific bin rule formulae. The client code, `Unit_test_binmethod.java`, tests the functionality of these classes.

#### Tasks:
- Task 1a: An [UML diagram](path/to/UML_diagram_binmethod.png) illustrating the relationships and members of classes in the `binmethod` package.
- Task 1b: Implementation of classes to ensure that the client code `Unit_test_binmethod.java` functions correctly. [Evidence in report]

### 2. `statutils` Package

This package focuses on providing functionalities related to basic statistical figures and histogram processing. It includes classes for calculating mean, variance, max, min, median, standard deviation, and counting samples in each bin. Additionally, there's support for histogram normalization.

#### Tasks:
- Task 2a: Development of a class hierarchy to calculate basic statistical figures.
- Task 2b: Capability to count the number of samples in each bin.
- Task 2c: Capability to perform histogram normalization.
- Task 2d: A client class for unit testing, similar to `Unit_test_binmethod.java`. [Evidence in report]
- Task 2e: An [UML diagram](path/to/UML_diagram_statutils.png) illustrating the class structure, including input and output arguments.

### 3. `mathutils` Package

This package focuses on data fitting, providing a class for calculating the fitting parameters of a histogram. It utilizes the Levenberg-Marquardt method from the Apache Commons Math library.

#### Tasks:
- Task 3a: Development of a class for data fitting using Apache Commons Math library. [Evidence in report]

### 4. GUI Application

The core of the software is the Graphical User Interface (GUI) application, developed to meet AirCoach's requirements. It incorporates features for streaming from a text file, calculating and displaying statistics, plotting normalized histograms, fitting histograms with a Probability Density Function model, and saving results as a Bitmap png file.

#### Tasks:
- Task 4a: Implementation of a fully operational GUI application. [Evidence in report]
- Task 4b: [Hierarchical order](path/to/GUI_hierarchy.png) of GUI nodes.
- Task 4c: [GUI Event Handling UML diagram](path/to/GUI_event_handling.png) (sequence diagram).

## How to Run the Project

1. Ensure you have NetBeans installed.
2. Open the project in NetBeans.
3. Run the main class of the GUI application.

