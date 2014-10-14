/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general_assignment_7_pt1_qs_ss_threaded;

/**
 *
 * @author david
 */
public class ThreadDemo extends Thread {

    public Thread t;
    private String threadName;
    private ThreadedQuickSort QS;
    private int first;
    private int last;
    private int[] table;

    ThreadDemo(String name, int[] in_table, int in_first, int in_last) {
        threadName = name;
        table = in_table;
        first = in_first;
        last = in_last;
        System.out.println("Creating " + threadName);

    }

    public void run() {
        ThreadedQuickSort QS = new ThreadedQuickSort(table, 0, table.length - 1);
        QS.quickSort(table, first, last);

        System.out.println("Thread " + threadName + " exiting.");
    }

    public void start() {
        System.out.println("Starting " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
