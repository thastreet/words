public class Main {

    public static void main(String[] args) {
        Bot bot = new Bot();
        sleep(2000);
        bot.initialize();
        bot.play(20);
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
