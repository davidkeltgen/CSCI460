/**************************************************
 * Author: David Keltgen                            *
 * Class: Montana State University: CSCI 460        *
 * File Name: QSSS_NT.java                          *
 * Date:  October 17 2014                           *
 * Description:  ThreadDemo is used to setup the    *
 *               the methods that will be ran in    *
 *               their own threads. It then creates *
 *               those threads                      *
 **************************************************/
package general_assignment_7_pt2;

/*Make this Modular */
public class ThreadDemo extends Thread {

    public QS_T QS;
    public QSSS_T QSSS;
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

        if (algorithm == sortType.QS_T) {
            QS = new QS_T(table, first, last, true);
            QS.quickSort(table, first, last);
        } else if (algorithm == sortType.QSSS_T) {
            QSSS = new QSSS_T(table, first, last, true);
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
