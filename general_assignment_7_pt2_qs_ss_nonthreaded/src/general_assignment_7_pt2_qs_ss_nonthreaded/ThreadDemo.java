/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general_assignment_7_pt2_qs_ss_nonthreaded;

/**
 *
 * @author david
 */

/*Make this Modular */
public class ThreadDemo extends Thread {

    QS_T QS;
    QSSS_T QSSS;
    public Thread t;
    private String threadName;
    private int first;
    private int last;
    private int[] table;
    sortType algorithm;

    ThreadDemo(String name, int[] in_table, int in_first, int in_last, sortType in_algorithm) {
        threadName = name;
        table = in_table;
        first = in_first;
        last = in_last;
        algorithm = in_algorithm;
        System.out.println("Creating " + threadName);
    }

    public void run() {

        if (algorithm == QS_T) {
            QS = new QS_T(table, 0, table.length - 1);
            QS.quickSort(table, first, last);
        } else if (algorithm == QSSS_T) {
            QSSS = new QSSS_T(table, 0, table.length - 1);
            QSSS.quickSort(table, first, last);
        }
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
