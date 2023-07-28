# IPify API Consumer - Scala Command Line Tool

This Scala command-line tool consumes the API provided by IPify (https://api.ipify.org/?format=json) and extracts the IP address part from the response. It utilizes SBT for building and managing dependencies and ensures good test coverage without the need to connect to the actual API.

## Prerequisites

Before running the command-line tool, make sure you have the following installed on your system:

1. Scala (https://www.scala-lang.org/)
2. SBT (https://www.scala-sbt.org/)

## Installation and Usage

Follow these steps to build and use the IPify API Consumer tool:


1.Build the project using SBT:
   ```bash
   sbt compile
   ```

2.Run the command-line tool:
   ```bash
   sbt run
   ```
3.To get executable file
```bash
   sbt
   assembly
   ```

The tool will fetch the IP address from the IPify API and display it on the command line.

## Test Coverage

The project is designed to have comprehensive test coverage to ensure its correctness and robustness. The tests are written using ScalaTest (or any other testing library of your choice) and do not make actual API calls to IPify. Instead, we utilize mock responses to simulate API behavior.

To run the tests, use the following command:

```bash
sbt test
```