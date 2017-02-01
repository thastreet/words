import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

class Bot {
    private final int GRID_STEP = 80;
    private final Rectangle listRect = new Rectangle(15, 175, 420, 790);
    private final Rectangle gridRect = new Rectangle(580, 55, 1180, 950);
    private final Point gridStart = new Point(628, 94);
    private Robot robot;
    private ITesseract tesseract;
    private ArrayList<String> wordList;
    private int gridWidth = 0;
    private int gridHeight = 0;
    private Solver solver;

    Bot() {
        try {
            robot = new Robot();
            tesseract = new Tesseract();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    void initialize() {
        final BufferedImage screenCapture = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        final BufferedImage listImage = screenCapture.getSubimage(listRect.x, listRect.y, listRect.width, listRect.height);
        final BufferedImage gridImage = screenCapture.getSubimage(gridRect.x, gridRect.y, gridRect.width, gridRect.height);

        try {
            final String listOCRResult = tesseract.doOCR(listImage);
            final String gridOCRResult = tesseract.doOCR(gridImage);

            wordList = parseWordList(listOCRResult);
            Character[][] grid = parseGrid(gridOCRResult);
            solver = new Solver(grid, gridWidth, gridHeight);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }

    void play(long timeBetweenActionsInMillis) {

        long timeBetweenActions = timeBetweenActionsInMillis < 20 ? 20 : timeBetweenActionsInMillis ;

        for (String word : wordList) {
            Solution solution = solver.solve(word);

            if (solution != null) {
                robot.mouseMove((int) (gridStart.getX() + GRID_STEP * solution.getWordStart().getX()), (int) (gridStart.getY() + GRID_STEP * solution.getWordStart().getY()));
                sleep(timeBetweenActions);
                robot.mousePress(InputEvent.BUTTON1_MASK);
                sleep(timeBetweenActions);
                robot.mouseMove((int) (gridStart.getX() + GRID_STEP * solution.getWordEnd().getX()), (int) (gridStart.getY() + GRID_STEP * solution.getWordEnd().getY()));
                sleep(timeBetweenActions);
                robot.mouseRelease(InputEvent.BUTTON1_MASK);
                sleep(timeBetweenActions);
            }
        }

        System.out.println("Done");
    }

    private ArrayList<String> parseWordList(String OCRResult) {
        return parseLines(OCRResult);
    }

    private Character[][] parseGrid(String OCRResult) {
        final Character[][] grid;
        final ArrayList<String> lines = parseLines(OCRResult);

        gridHeight = lines.size();
        if (lines.size() > 0) {
            gridWidth = lines.get(0).replaceAll(" ", "").length();
            grid = new Character[gridHeight][gridWidth];

            for (int i = 0; i < lines.size(); ++i) {
                final String line = lines.get(i).replaceAll(" ", "");

                for (int j = 0; j < line.length(); ++j) {
                    grid[i][j] = line.charAt(j);
                }
            }

            return grid;
        }

        return new Character[][]{};
    }

    private ArrayList<String> parseLines(String OCRResult) {
        final BufferedReader bufferedReader = new BufferedReader(new StringReader(OCRResult));
        final ArrayList<String> lines = new ArrayList<>();

        try {
            for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                if (!line.isEmpty()) {
                    lines.add(line);
                }
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
