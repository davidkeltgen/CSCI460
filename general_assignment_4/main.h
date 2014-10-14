/* 
 * File:   main.h
 * Author: David Keltgen
 *
 * Created on September 22, 2014, 3:08 PM
 */


#include "system.h"
#include <signal.h>

#define NUM_THREADS 2

int shared;

volatile sig_atomic_t ts_alive = 1;     //variable modified by signal handler, setting this to false will end the threads
pthread_attr_t attrs;
struct sigaction quit_action;   //action to be taken when ^C is entered
sigset_t mask, oldmask;


void *hlp_down(void *);
void *hlp_control(void *);
void *hkupSimThread(void *);

void quit_signal(int);  //signal handler
void start_threads();
void join_threads();
void init_signal_handler();
void Thread1();
void Thread2();

enum thread{
    thread1_thread,
    thread2_thread,
};

pthread_t threads[NUM_THREADS];