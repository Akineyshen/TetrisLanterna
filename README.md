# Tetris Lanterna
![Language](https://img.shields.io/badge/Language-Java-brightgreen?style=for-the-badge&logo=mingww64&logoColor=F0931C&color=F0931C&labelColor=FCFCFC)
![Library](https://img.shields.io/badge/Library-Lantern-brightgreen?style=for-the-badge&logo=librarything&logoColor=F0931C&color=F0931C&labelColor=FCFCFC)
![Size](https://img.shields.io/github/repo-size/Akineyshen/TetrisLanterna?label=Size&style=for-the-badge&color=F0931C&labelColor=FCFCFC)
![Last Commit](https://img.shields.io/github/last-commit/Akineyshen/TetrisLanterna?label=Last%20Commit&style=for-the-badge&color=F0931C&labelColor=FCFCFC)

## Features
- **Keyboard Controls**: Move and rotate Tetris blocks using arrow keys.
- **Game Over Handling**: Detects when the game ends and allows restarting.
- **Score Tracking**: Keeps track of the player's score and cleared lines.
- **Level Progression**: Increases difficulty as the player clears more lines.
- **Text-Based Rendering**: Uses the Lanterna library for terminal-based graphics.
- **MVC Architecture**: Clean separation of concerns with Model-View-Controller design.
- **Dynamic Piece Generation**: Randomly generates Tetris pieces for gameplay.
- **Line Clearing**: Automatically removes full lines and updates the score.

## Unit testing
- **ControllerTest**: Verifies input handling (e.g., arrow keys, game restart) and ensures the view is updated correctly.
- **ModelTest**: Tests for game logic, including piece movement, line clearing, scoring, and game state.
- **ViewTest**: Ensures the display is updated correctly based on the model's state, including rendering pieces and game over messages.
- **TetrisTest**: Verifies the successful initialization of all core Tetris game components (Screen, Model, View, and Controller).

<img src="https://i.imgur.com/B5bAgtr.png" alt="Test coverage">

## Requirements
- **Java**: JDK 21 or newer.
- **Maven**: For dependency management and building the project.
- **Lanterna**: Terminal-based rendering library.

## Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/Akineyshen/TetrisLanterna.git
   ```
2. Navigate to the project directory:
   ```bash
    cd TetrisLanterna
    ```
3. Build the project using Maven:
    ```bash
    mvn clean install
    ```
4. Navigate to the target Folder:
    ```bash
   Open the target folder.
   ```
5. Run the game:
    ```bash
   Right-click on the file Tetris-Lanterna.jar.
   Select Run from the context menu.
    ```

## Project Structure
```bash
TetrisLanterna/
├── src/                     # Source code
│   ├── main/
│   │   ├── java/
│   │   │   ├── controller/  # Controller logic
│   │   │   ├── model/       # Game logic and data
│   │   │   ├── view/        # Rendering logic
│   │   │   └── Tetris.java  # Main entry point
│   │   └── resources/       # Game resources
│   └── test/                # Unit tests
├── .gitignore               # Ignored files
├── pom.xml                  # Maven configuration
└── README.md                # Project description
```

## Screenshots

<img src="https://i.imgur.com/VGh9XDu.png" alt="Tetris">

<img src="https://i.imgur.com/vyhFmev.png" alt="Game Over">