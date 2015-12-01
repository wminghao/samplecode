public class ReEntrantLock {
    private int count = 0;
    private Thread lockedByThread = null;
    private int NOBODY = -1;

    //no other threads can call lock() function at the same time.
    //when a different thread calls lock() and the thread is already locked, block here.
    //when the same thread calls the lock() twice do nothing
    public synchronized void lock() throws InterruptedException{

        while(count > 0 && Thread.currentThread() != lockedByThread){
            this.wait(); //temporarily release the “synchronized" lock to the function.
        }
        lockedByThread = Thread.currentThread();
        count++;
    }

    //no other threads can call unlock() function at the same time.
    //when a different thread calls unlock(), do nothing
    //when the same thread calls the unlock() twice, will notify whoever is blocked.
    public synchronized void unlock(){
        if ( !count || lockedByThread != Thread.currentThread()) {
            return;
        }
        count--;

        if(count == 0){
	    lockedByThread = NOBODY;
            this.notify();
        }
    }
}