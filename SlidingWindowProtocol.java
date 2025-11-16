import java.util.Random;
import java.util.Scanner;

public class SlidingWindowProtocol {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        System.out.print("Enter total number of frames: ");
        int totalFrames = sc.nextInt();

        System.out.print("Enter window size: ");
        int windowSize = sc.nextInt();

        int framesSent = 0;

        while (framesSent < totalFrames) {

            int framesToSend = Math.min(windowSize, totalFrames - framesSent);

            System.out.println("\nSending frames:");
            for (int i = 0; i < framesToSend; i++)
                System.out.println("Sent frame #" + (framesSent + i));

            boolean allAcked = true;

            // Process ACKs
            for (int i = 0; i < framesToSend; i++) {

                boolean ackReceived = rand.nextInt(10) < 9;  // 90% success

                if (ackReceived) {
                    System.out.println("ACK received for frame #" + (framesSent + i));
                } else {
                    System.out.println("ACK lost for frame #" + (framesSent + i));
                    System.out.println("Retransmitting from frame #" + (framesSent + i));
                    framesSent += i;
                    allAcked = false;
                    break;
                }
            }

            if (allAcked) {
                framesSent += framesToSend;
            }
        }

        System.out.println("\nAll frames sent successfully!");
    }
}
