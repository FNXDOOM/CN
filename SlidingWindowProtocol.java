import java.util.Random;
import java.util.Scanner;

public class SlidingWindowProtocol {

    static final int MAX = 50;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        System.out.print("Enter the total number of frames to send: ");
        int totalFrames = sc.nextInt();

        System.out.print("Enter the window size: ");
        int windowSize = sc.nextInt();

        int framesSent = 0;

        while (framesSent < totalFrames) {
            int framesToSend = Math.min(windowSize, totalFrames - framesSent);

            System.out.println("\nSending frames: ");
            for (int i = 0; i < framesToSend; i++) {
                System.out.println("Sent frame #" + (framesSent + i));
            }

            // Simulate ACKs
            boolean allAcked = true;
            for (int i = 0; i < framesToSend; i++) {
                // Simulate ACK loss or success (90% success rate)
                boolean ackReceived = rand.nextInt(10) < 9;

                if (ackReceived) {
                    System.out.println("ACK received for frame #" + (framesSent + i));
                } else {
                    System.out.println("ACK lost for frame #" + (framesSent + i) + " — retransmitting from here...");
                    framesSent += i;  // Go back to this frame
                    allAcked = false;
                    break;
                }
            }

            if (allAcked) {
                framesSent += framesToSend;
            }
        }

        System.out.println("\nAll frames sent and acknowledged successfully.");
    }
}
