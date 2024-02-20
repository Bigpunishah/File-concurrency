//Vikel Cuningham
package mypackage;

class MessageLoop extends Thread {
    @Override
    public void run() {
        try {
            Thread.sleep(850);                                                
            System.out.println(Thread.currentThread().getName() + ": 1              A great day to swim"); 
            Thread.sleep(850);

            System.out.println(Thread.currentThread().getName() + ": 2              The trees are beautiful"); 
            Thread.sleep(850);

            System.out.println(Thread.currentThread().getName() + ": 3              All is not evil."); 
            Thread.sleep(850);

            System.out.println(
                    Thread.currentThread().getName() + ": 4              A wise man once said nothing at all."); 
            Thread.sleep(850);
        } catch (InterruptedException e) {                                          
        }
    }
}

public class Concurrency {
    public static void main(String[] args) throws InterruptedException { 
        for (int maxWaitTime = 1; maxWaitTime <= 4; maxWaitTime++) {
            MessageLoop messageThread = new MessageLoop();                
            System.out.println("maxWaitTime: " + maxWaitTime + " second(s).");
            System.out.println(Thread.currentThread().getName() + " Loop starting!");
            messageThread.start();                                          

            int waitTime = maxWaitTime * 2;                     
            int exitLoop = 8;                                   
            int counter = 0;                                    

            while (messageThread.isAlive()) {                   
                if (waitTime == counter) {                      
                    messageThread.interrupt();                  
                    System.out.println(Thread.currentThread().getName() + ": Message Loop Interrupted");
                    break;                                      
                }
                counter++;                                      
                if (counter == exitLoop) {                      
                    System.out.println("Done!");
                    return;
                }
                System.out.println(Thread.currentThread().getName() + ": Continuing to wait...");
                Thread.sleep(500);                       

            }

        }
    }
}







