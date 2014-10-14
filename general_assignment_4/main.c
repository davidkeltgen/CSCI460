/* 
 * File:   main.c
 * Author: David Keltgen
 *
 * Created on September 22, 2014, 3:08 PM
 */

#include "main.h"
#include <pthread.h>
/*
 * 
 */
int main(void) {
    start_threads();
    
    shared = 0;
    
    /*Upon program termiation (^c) attempt to join the threads*/
    init_signal_handler();
    sigprocmask(SIG_BLOCK, &mask, &oldmask);
    while(ts_alive){
        sigsuspend(&oldmask);   // wait here until the program is killed
    }
    sigprocmask(SIG_UNBLOCK, &mask, &oldmask);
    
    /*SIGINT caught, ending program*/
    join_threads();
    
    return 0;
}

/*signal all threads to exit*/
void quit_signal(int sig){
    
}

/*this method takes a function pointer and starts it as a new thread*/
void start_threads(){
    
    pthread_attr_init(&attrs);
    pthread_attr_setdetachstate(&attrs, PTHREAD_CREATE_JOINABLE);
    pthread_create(&threads[thread1_thread], &attrs, (void * (*)(void*))Thread1, NULL);
    pthread_create(&threads[thread2_thread], &attrs, (void * (*)(void*))Thread2, NULL);

}

void join_threads(){
    void * returns;
    
    int i;
    for(i = 0; i < NUM_THREADS; i++){
        pthread_join(threads[i], &returns);
    }
}


void init_signal_handler(){
    sigfillset(&oldmask);       //save the old mask
    sigemptyset(&mask);         //create a blank new mask
    sigaddset(&mask, SIGINT);   //add SIGINT (^C) to mask
    quit_action.sa_handler = quit_signal;
    quit_action.sa_mask = oldmask;
    quit_action.sa_flags = 0;
    
    sigaction(SIGINT, &quit_action, NULL);
}

void Thread1()
{ 
   printf("Thread 1 started \n");
   int i = 0;
  
   while(1)
   {
   usleep(500000);
   printf("Thread 1 counter: %d shared: %d\n", i, shared);
   i++;
   shared++;
   }
}

void Thread2()
{
 printf("Thread 2 started \n");
 int j = 0;
 
 while(1)
 {
 sleep(1);
 printf("Thread 2 counter: %d shared %d\n", j, shared);
 j++;
 shared++;
 }
}