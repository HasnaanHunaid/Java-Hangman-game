# Java Swing Hangman Game

A GUI-based implementation of the classic Hangman word-guessing game built using Java Swing. Players guess letters using an on-screen visual keyboard to uncover a hidden word randomly selected from a local dictionary. 

## 🎮 Features

* **Interactive Graphical UI:** Features a cleanly structured desktop layout powered by Java Swing elements (`JFrame`, `JPanel`, `JButton`, and `JLabel`).
* **On-Screen Keyboard Grid:** An intuitive $2 \times 13$ button panel managing inputs. Buttons automatically disable upon selection to prevent redundant or accidental duplicate guesses.
* **Dynamic Health Tracking:** Utilizes highly visible emoji status indicators (❤️ / 💔) to dynamically track a player's remaining 6 lives.
* **Persistent Word Integration:** Reads and caches a list of dictionary assets directly from a plain text file (`nouns.txt`) at runtime.
* **Replay Mechanics:** Integrated `JOptionPane` dialog alerts prompt players upon winning or losing, offering quick, one-click options to clear grids and immediately start a fresh match.

## 🏗️ Architecture Overview

The system abstracts logic, visuals, and control flows across modular files within the `hangman` package:
* **`Run.java`**: The absolute entry point containing the initialization loop to instantiate the GUI context.
* **`MainWindow.java`**: Acts as the principal controller and master `JFrame` pane. Handles layout arrangement, game flow lifecycle (win/loss states), resetting metrics, and parsing text files.
* **`ButtonPanel.java`**: Organizes the alphanumeric buttons grid and processes all action event triggers when input is registered.
* **`HealthPanel.java`**: Manages structural rendering for tracking remaining validation counts and handles updating UI text states.
* **`HangmanGame.java`**: Contains underlying structural components intended for separate terminal parsing hooks and backend array sorting.

## 🛠️ Requirements & Installation

1. Ensure **Java Development Kit (JDK) 8** or higher is properly installed on your local environment.
2. Clone this repository down locally.
