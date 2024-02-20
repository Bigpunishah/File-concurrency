//Vikel Cuningham
package mypackage;

class MessageLoop extends Thread {
    @Override
    public void run() {
        try {
            Thread.sleep(850);                                                  // this sleep is required!! Code will not run properly without it
            System.out.println(Thread.currentThread().getName() + ": 1              A great day to swim"); //quote 1
            Thread.sleep(850);

            System.out.println(Thread.currentThread().getName() + ": 2              The trees are beautiful"); // quote 2
            Thread.sleep(850);

            System.out.println(Thread.currentThread().getName() + ": 3              All is not evil."); // quote 3
            Thread.sleep(850);

            System.out.println(
                    Thread.currentThread().getName() + ": 4              A wise man once said nothing at all."); // quote 4
            Thread.sleep(850);
        } catch (InterruptedException e) {                                          // try catch
        }
    }
}

public class Concurrency {
    public static void main(String[] args) throws InterruptedException { // throw exception so i do not need the try catch
        for (int maxWaitTime = 1; maxWaitTime <= 4; maxWaitTime++) {
            MessageLoop messageThread = new MessageLoop();                  // thread becomes new state
            System.out.println("maxWaitTime: " + maxWaitTime + " second(s).");
            System.out.println(Thread.currentThread().getName() + " Loop starting!");
            messageThread.start();                                          //thread is not running

            int waitTime = maxWaitTime * 2;                     // this is for iteration of the half seconds
            int exitLoop = 8;                                   // 8 half seconds until the thread terminates
            int counter = 0;                                    //counter for the half seconds

            while (messageThread.isAlive()) {                   //while this thread is alive we are going to perform these actions
                if (waitTime == counter) {                      // when the counter reaches the right amount of half seconds >>
                    messageThread.interrupt();                    // thread interrupts
                    System.out.println(Thread.currentThread().getName() + ": Message Loop Interrupted");
                    break;                                      // breaking the loop because we do not need anything else
                }
                counter++;                                      // if the counter is not equal we add 1 to counter
                if (counter == exitLoop) {                      // if 8 half seconds have been reached we terminate
                    System.out.println("Done!");
                    return;
                }
                System.out.println(Thread.currentThread().getName() + ": Continuing to wait...");
                Thread.sleep(500);                       // this is the half second that iterated each pass through

            }

        }
    }
}

// Program start
// Main thread starts by creating a new thread & notifying the new thread has
// started
// For loop limits the amount of time the external thread has to execute.
// After loop interrupts due to time expiration, a new Thread is created each
// iteration time with the name displayed.
// When thread reaches the max time in loop, it allows all messages to be
// displayed.