import java.util.*;
import java.io.*;


class ProgramFrame {
    int start;
    int stop;
    int PC;
    
    public ProgramFrame(int myStart, int myStop, int myPC) {
    	start=myStart;
    	stop=myStop;
    	PC=myPC;
    }

    // returns a String describing the content of the object
    public String toString() {
    	return "ProgramFrame: start = " + start + " stop = " + stop + " PC = " + PC;
    }
}


class MergeSortNonRecursive {

    static Stack<ProgramFrame> callStack;

    // this implement the merge algorithm seen in class. Feel free to call it.
    public static void merge(int A[], int start, int mid, int stop) 
    {
	int index1=start;
	int index2=mid+1;
	int tmp[]=new int[A.length];
	int indexTmp=start;
	
	while (indexTmp<=stop) {
	    if (index1<=mid && (index2>stop || A[index1]<=A[index2])) {
		tmp[indexTmp]=A[index1];
		index1++;
	    }
	    else {
		if (index2<=stop && (index1>mid || A[index2]<=A[index1])) {
		    tmp[indexTmp]=A[index2];
		    index2++;
		}
	    }
	    indexTmp++;
	}
	for (int i=start;i<=stop;i++) A[i]=tmp[i];
    }
    
    // Our non-recursive version of the mergeSort algorithm, using a stack to mimic recursion  
    static void mergeSort(int A[]) {
    	int start=0;
    	int mid;
    	int stop=A.length-1;

    	// the stack we will use
        callStack = new Stack<ProgramFrame>();
        // the initial program frame
        ProgramFrame current = new ProgramFrame(start,stop,1); 
        // put that frame on the stack
        callStack.push(current);
        // As long as our recursion stack is not empty...
        
        while (!callStack.empty()) {
	        // for debugging purposes
	        System.out.println(callStack);

	        // our base cases
	    	if (callStack.peek().PC == 1) {
	    	   	if (callStack.peek().start == callStack.peek().stop) {
	    	   		// we are done with that frame
	    	   		callStack.pop(); 
		    	    // if there is nothing left on the stack, we are done
	    	   		if (callStack.empty()) break;
	    	   		// move PC up by one
	    	   		callStack.peek().PC++;
	    	   	}
	    	//not in the base case
	    	else callStack.peek().PC++;
	       	continue;
	        }

    	    // This corresponds to the recursive call on mergeSort for the first half of the array
    	    if (callStack.peek().PC == 2) {
    	    	// midpoint of current (top) frame
    	    	mid=(callStack.peek().start + callStack.peek().stop)/2;
    	    	// create a new program frame, corresponding to the recursive call
    	    	current = new ProgramFrame(callStack.peek().start,mid,1);
    	    	callStack.push(current);
    	    	continue;
    	    }
    	    
    	    // This corresponds to the recursive call on mergeSort for the second half of the array
    	    if (callStack.peek().PC == 3) {
    	    	// midpoint of current (top) frame
    	    	mid=(callStack.peek().start + callStack.peek().stop)/2;
    	    	// create a new program frame, corresponding to the recursive call
    	    	current = new ProgramFrame(mid+1,callStack.peek().stop,1);
    	    	callStack.push(current);
    	    	continue;
    	    }
    		
    	    // This merges both sorted halves of the array
    	    if (callStack.peek().PC == 4) {
    	    	if(!callStack.empty()){
    	    		// midpoint of current (top) frame
    	    		mid=(callStack.peek().start + callStack.peek().stop)/2;
    	    		// merge both sorted halves of the array
    	    		merge(A,callStack.peek().start,mid,callStack.peek().stop);
    	    		// we are done with that frame
    	    		callStack.pop();
    	    		// if there is nothing left on the stack, we are done
    	    		if(callStack.empty()) break;
    	    		// move PC up by one
    	    		callStack.peek().PC++;
    	    		continue;
    	    	}
    	    	else break;
    	    }
        }
    }
    
    
    public static void main (String args[]) throws Exception {
	
	// just for testing purposes
	int myArray[] = {4,6,8,1,3,2,9,5,7,6,4,2,1,3,9,8,7,5};
	mergeSort(myArray);
	System.out.println("Sorted array is:\n");
	for (int i=0;i<myArray.length;i++) {
	    System.out.print(myArray[i]+" ");
	}
	System.out.println();
    }
}

	