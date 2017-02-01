import java.awt.*;

class Solution {
    private Point wordStart;
    private Point wordEnd;

    Solution(Point wordStart, Point wordEnd) {
        this.wordStart = wordStart;
        this.wordEnd = wordEnd;
    }

    Point getWordStart() {
        return wordStart;
    }

    Point getWordEnd() {
        return wordEnd;
    }
}
