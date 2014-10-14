// This program:
//   1. opens an input text file that the user must create beforehand
//   2. creates an output text file
//   3. forks a new child process
// Thereafter, the parent and child are two separate processes that execute
// the same code, namely each executes the code right after the fork() call.

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

FILE * fdrd;
FILE * fdwt;

int c;

void rdwrt();
    int main(int argc, char *argv[]) {
	
	// Check whether the user has provided the proper arguments when invoking
	// the executable version of this program:
	//
	//   arg[1] must be a string that is the relative path name (from the directory in
	//   which this program is located) to a text file containing arbitrary text
	//   that the user has created before running this program.
	//
	//   arg[2] must be a string that is the relative path name (from the directory in
	//   which this program is located) to an empty text file that the user has
	//   created before running this program.
	//
	// Example: if your executable program is named "quickBrownFox", your input file is
	// named "inFile" (and is located in the same directory as "quickBrownFox"), and
	// your output file is named "outFile" (and is located in the same directory as
	// "quickBrownFox") then invoking your program at runtime should be done on
	// the command line as:
	//   quickBrownFox inFile outFile
	// or, in some instances, as
	//   ./quickBrownFox inFile outFile
	// This example assumes that you are invoking the program "quickBrownFox" while in
	// the directory where files quickBrownFox, inFile and outFile are located.
	if (argc != 3) {
		printf("Correct Format: <program name> <file to read from> <file to write to>\r\n");
		exit(1);
	}
	// Check whether the input file name given as arg[1] can be located and/or opened
	// for reading.
	if ( (fdrd = fopen(argv[1], "r")) == NULL) {
		printf("Your file cannot be opened for reading; check your privileges!");
		exit(1);
	}
	// Check whether output file name given as arg[2] can be located and/or opened
	// for writing
	if ( (fdwt = fopen(argv[2], "w")) == NULL) {
		printf("Your file cannot be opened for writing; check your privileges!");
		exit(1);
	}
	
	// Create a child process that can be executed concurrently with the parent process.
	fork();
	
	// From this point on, both the parent and the child processes have the same program
	// code (machine language code for this entire program, not just what is below).
	// However, from this juncture on, both the parent and the child processes execute the
	// code below as they are scheduled to run.
	rdwrt();
	return 1;
}
	// When running, the process continuously reads a character from the input file and
	// writes that character to the output file, until the end-of-file character is read.

    void rdwrt() {
	do {
	c = fgetc(fdrd);
	fputc(c, fdwt);
	} while (c != EOF);
}