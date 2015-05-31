package hello;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *  Montior class which will monitor the status of web server.
 */
public class Monitor {

    /**
     * A thread that will do a status check by calling head http method on web server.
     */
    private static class StatusChecker implements Runnable {

        private URL url;
        private int timeout;

        public StatusChecker(final URL url, final int timeout) {
            this.url = url;
            this.timeout = timeout;
        }

        @Override
        public void run() {
            try {
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("HEAD");
                connection.setConnectTimeout(timeout);
                connection.setReadTimeout(timeout);
                //we could associate IO with this connection
                connection.connect();
            } catch (IOException ioException) {
                System.out.println("Error while opening socket with server" + ioException);
            }
        }
    }

    /**
     * An initiator of health check service which will run after particular time.
     * @param webaddress
     * @param frequency
     * @param timeout
     */
    public static void checkAvailability(URL webaddress, int frequency, int timeout) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleWithFixedDelay(new StatusChecker(webaddress, timeout), 0, frequency, TimeUnit.SECONDS);//assuming after every x seconds
    }

    public static void main(String[] args) throws MalformedURLException {
        checkAvailability(new URL("http://www.google.com"), 60, 2000);
    }
}
