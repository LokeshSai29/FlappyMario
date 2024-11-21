import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class FlappyMario extends JPanel implements ActionListener, KeyListener {
    int boardWidth = 360;
    int boardHeight = 640;

    // Images
    Image backgroundImg;
    Image birdImg;
    Image topPipeImg;
    Image bottomPipeImg;

    // Bird properties
    int birdX = boardWidth / 8;
    int birdY = boardWidth / 2;
    int birdWidth = 48;
    int birdHeight = 40;

    class Bird {
        int x = birdX;
        int y = birdY;
        int width = birdWidth;
        int height = birdHeight;

        Bird(Image img) {}
    }

    // Pipe properties
    int pipeX = boardWidth;
    int pipeY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;

    class Pipe {
        int x = pipeX;
        int y = pipeY;
        int width = pipeWidth;
        int height = pipeHeight;
        Image img;
        boolean passed = false;

        Pipe(Image img) {
            this.img = img;
        }
    }

    // Game logic
    Bird bird;
    int velocityX = -4; // Pipe speed
    int velocityY = 0;  // Bird vertical speed
    int gravity = 1;

    ArrayList<Pipe> pipes;
    @SuppressWarnings("unused")
    Random random = new Random();

    Timer gameLoop;
    Timer placePipeTimer;
    boolean gameOver = false;
    boolean gameStarted = false; // New flag for game start
    double score = 0;

    FlappyMario() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setFocusable(true);
        addKeyListener(this);

        // Load images
        backgroundImg = new ImageIcon(getClass().getResource("./flappymariobg.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("./flappymario.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();

        // Bird
        bird = new Bird(birdImg);
        pipes = new ArrayList<>();

        // Timer to place pipes
        placePipeTimer = new Timer(1500, e -> placePipes());
        placePipeTimer.start();

        // Main game loop
        gameLoop = new Timer(1000 / 60, this); // 60 FPS
        gameLoop.start();
    }

    void placePipes() {
        int randomPipeY = (int) (pipeY - pipeHeight / 4 - Math.random() * (pipeHeight / 2));
        int openingSpace = boardHeight / 4;

        Pipe topPipe = new Pipe(topPipeImg);
        topPipe.y = randomPipeY;
        pipes.add(topPipe);

        Pipe bottomPipe = new Pipe(bottomPipeImg);
        bottomPipe.y = topPipe.y + pipeHeight + openingSpace;
        pipes.add(bottomPipe);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        // Background
        g.drawImage(backgroundImg, 0, 0, this.boardWidth, this.boardHeight, null);

        if (!gameStarted) {
            g.setColor(Color.red);
            g.setFont(new Font("Arial", Font.ITALIC, 36));
            g.drawString("FLAPPY MARIO", boardWidth / 2 - 120, boardHeight / 2 - 250);
            // Display start message
            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("Press SPACE to Start", boardWidth / 2 - 125, boardHeight / 2);
            return; // Exit drawing further components until the game starts
        }

        // Bird
        g.drawImage(birdImg, bird.x, bird.y, bird.width, bird.height, null);

        // Pipes
        for (Pipe pipe : pipes) {
            g.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height, null);
        }

        // Score
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 32));
        if (gameOver) {
            g.drawString("Game Over: " + String.valueOf((int) score), 10, 35);
        } else {
            g.drawString(String.valueOf((int) score), 10, 35);
        }
    }

    public void move() {
        if (!gameStarted) return;

        // Bird
        velocityY += gravity;
        bird.y += velocityY;
        bird.y = Math.max(bird.y, 0);

        // Pipes
        for (Pipe pipe : pipes) {
            pipe.x += velocityX;

            if (!pipe.passed && bird.x > pipe.x + pipe.width) {
                score += 0.5;
                pipe.passed = true;
            }

            if (collision(bird, pipe)) {
                gameOver = true;
            }
        }

        if (bird.y > boardHeight) {
            gameOver = true;
        }
    }

    boolean collision(Bird a, Pipe b) {
        return a.x < b.x + b.width &&
               a.x + a.width > b.x &&
               a.y < b.y + b.height &&
               a.y + a.height > b.y;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameStarted) {
            repaint();
            return;
        }

        move();
        repaint();

        if (gameOver) {
            placePipeTimer.stop();
            gameLoop.stop();
        }
    }

    @Override
public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
        if (!gameStarted) {
            gameStarted = true;
        } else if (gameOver) {
            resetGame(); // Reset the game to its initial state
        } else {
            velocityY = -9; // Make the bird jump
        }
    }
}

// Method to reset the game to its initial state
private void resetGame() {
    // Reset game variables
    bird.y = birdY;
    velocityY = 0;
    pipes.clear();
    gameOver = false;
    score = 0;
    gameStarted = false; // Return to initial screen

    // Restart timers
    placePipeTimer.start();
    gameLoop.start();

    repaint(); // Redraw the initial screen
}


    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
