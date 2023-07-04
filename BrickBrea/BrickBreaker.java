import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BrickBreaker extends JPanel implements ActionListener {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int PADDLE_WIDTH = 100;
    private static final int PADDLE_HEIGHT = 10;
    private static final int BALL_RADIUS = 10;
    private static final int BRICK_WIDTH = 60;
    private static final int BRICK_HEIGHT = 20;
    private static final int ROWS = 5;
    private static final int COLS = 10;
    private static final int DELAY = 5;

    private Timer timer;
    private boolean playing;
    private int paddleX;
    private int ballX;
    private int ballY;
    private int ballXSpeed;
    private int ballYSpeed;
    private boolean[][] bricks;

    public BrickBreaker() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);

        paddleX = WIDTH / 2 - PADDLE_WIDTH / 2;
        ballX = WIDTH / 2;
        ballY = HEIGHT / 2;
        ballXSpeed = 1;
        ballYSpeed = 1;
        bricks = new boolean[ROWS][COLS];
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                bricks[row][col] = true;
            }
        }

        timer = new Timer(DELAY, this);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    movePaddle(-5);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    movePaddle(5);
                }
            }
        });
        setFocusable(true);
    }

    public void startGame() {
        playing = true;
        timer.start();
    }

    private void movePaddle(int deltaX) {
        int newPaddleX = paddleX + deltaX;
        if (newPaddleX >= 0 && newPaddleX <= WIDTH - PADDLE_WIDTH) {
            paddleX = newPaddleX;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (playing) {
            update();
            checkCollision();
        }
        repaint();
    }

    private void update() {
        ballX += ballXSpeed;
        ballY += ballYSpeed;

        if (ballX < 0 || ballX > WIDTH - BALL_RADIUS) {
            ballXSpeed = -ballXSpeed;
        }

        if (ballY < 0 || ballY > HEIGHT - BALL_RADIUS) {
            ballYSpeed = -ballYSpeed;
        }
    }

    private void checkCollision() {
        if (ballY + BALL_RADIUS >= HEIGHT - PADDLE_HEIGHT && ballX + BALL_RADIUS >= paddleX && ballX <= paddleX + PADDLE_WIDTH) {
            ballYSpeed = -ballYSpeed;
        }

        int brickWidthWithGap = BRICK_WIDTH + 2;
        int brickHeightWithGap = BRICK_HEIGHT + 2;

        int row = (ballY - 2 * BALL_RADIUS) / brickHeightWithGap;
        int col = ballX / brickWidthWithGap;

        if (row >= 0 && row < ROWS && col >= 0 && col < COLS && bricks[row][col]) {
            bricks[row][col] = false;
            ballYSpeed = -ballYSpeed;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.fillRect(paddleX, HEIGHT - PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT);

        g.setColor(Color.RED);
        g.fillOval(ballX, ballY, BALL_RADIUS, BALL_RADIUS);

        g.setColor(Color.GREEN);
        int brickWidthWithGap = BRICK_WIDTH + 2;
        int brickHeightWithGap = BRICK_HEIGHT + 2;
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (bricks[row][col]) {
                    int brickX = col * brickWidthWithGap;
                    int brickY = row * brickHeightWithGap;
                    g.fillRect(brickX + 1, brickY + 1, BRICK_WIDTH, BRICK_HEIGHT);
                }
            }
        }

        if (!playing) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            String gameOverMsg = "Game Over";
            FontMetrics fontMetrics = g.getFontMetrics();
            int gameOverMsgWidth = fontMetrics.stringWidth(gameOverMsg);
            int gameOverMsgHeight = fontMetrics.getHeight();
            int gameOverX = WIDTH / 2 - gameOverMsgWidth / 2;
            int gameOverY = HEIGHT / 2 - gameOverMsgHeight / 2;
            g.drawString(gameOverMsg, gameOverX, gameOverY);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Brick Breaker");
        BrickBreaker game = new BrickBreaker();
        frame.add(game);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        game.startGame();
    }
}
