# Flappy Mario

**Flappy Mario** is a simple 2D arcade-style game based on the popular Flappy Bird game, but with a twist! Instead of the bird, you play as Mario as he navigates through pipes. The goal of the game is to avoid colliding with pipes while trying to achieve the highest score possible.

---

## Features

- **Mario as the main character**: Instead of a bird, you control Mario as he flies through the pipes.
- **Endless gameplay**: The pipes continuously appear, and the difficulty increases over time as they move faster.
- **Pipe generation**: Randomly generated pipes create new challenges every time.
- **Gravity and Jumping**: Mario falls due to gravity, and you can make him jump by pressing the spacebar.
- **Score tracking**: Your score is based on how many pipes Mario successfully passes. Each pipe that Mario passes adds 0.5 to the score.
- **Game Over and Restart**: If Mario collides with a pipe or falls to the ground, the game ends. You can restart by pressing space.

---

## Game Controls

- **Spacebar**: Makes Mario jump.
    - Pressing it while the game is paused starts the game.
    - After a game over, pressing space restarts the game.

---

## How to Play

1. **Run the game**.
2. **Press the spacebar** to start playing.
3. Try to navigate Mario through the pipes without hitting them.
4. The game will end if Mario collides with a pipe or falls to the bottom.
5. Your score will be displayed at the top left corner.

---

## Game Flow

- **Game Start**: Press the spacebar to start the game. The message "Press SPACE to Start" will be shown until you start.
- **Pipes**: Pipes are continuously placed in the game world, moving from right to left.
- **Collision Detection**: If Mario collides with a pipe, the game ends.
- **Score Calculation**: Every time Mario passes a pipe, the score increases by 0.5 points.

---

## Technologies

- **Java**: The game is built using Java Swing for the GUI.
- **Image Assets**: Custom images for Mario, pipes, and background.

---

## Setup Instructions

1. Clone or download the repository.
2. Make sure you have **Java 8** or higher installed on your system.
3. Open the project in your preferred IDE (e.g., IntelliJ IDEA, Eclipse).
4. Run the **App.java** class to start the game.

---

## Screenshots

![Flappy Mario Game Screenshot 1](https://github.com/user-attachments/assets/5ae1e307-6f53-4dca-a029-ccc37fd11d6c)
![Flappy Mario Game Screenshot 2](https://github.com/user-attachments/assets/8c3bcbdd-fe2b-474e-9345-07ef22578e43)
![Flappy Mario Game Screenshot 3](https://github.com/user-attachments/assets/81a978c3-aefc-4057-a5bb-3d2c25760c54)

