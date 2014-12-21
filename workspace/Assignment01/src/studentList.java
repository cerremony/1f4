//STUDENT1_NAME: Lara Goxhaj
//STUDENT1_ID: 260574574
//LG: From here on out, comments written by me will be preceded with "LG:"

import java.io.*;   
import java.util.*;

class studentList {
	int studentID[];
	int numberOfStudents;
	String courseName;

	// A constructor that reads a studentList from the given fileName and assigns it the given courseName
	public studentList(String fileName, String course) {
		String line;
		int tempID[]=new int[4000000]; // this will only work if the number of students is less than 4000000.
		numberOfStudents=0;
		courseName=course;
		BufferedReader myFile;
		try {
			myFile = new BufferedReader(new FileReader( fileName ) );

			while ( (line=myFile.readLine())!=null ) {
				tempID[numberOfStudents]=Integer.parseInt(line);
				numberOfStudents++;
			}
			studentID=new int[numberOfStudents];
			for (int i=0;i<numberOfStudents;i++) {
				studentID[i]=tempID[i];
			}
		} catch (Exception e) {System.out.println("Can't find file "+fileName);}
	}

	// A constructor that generates a random student list of the given size and assigns it the given courseName
	public studentList(int size, String course) {
		int IDrange=2*size;
		studentID=new int[size];
		boolean[] usedID=new boolean[IDrange];
		for (int i=0;i<IDrange;i++) usedID[i]=false;
		for (int i=0;i<size;i++) {
			int t;
			do {
				t=(int)(Math.random()*IDrange);
			} while (usedID[t]);
			usedID[t]=true;
			studentID[i]=t;
		}
		courseName=course;
		numberOfStudents=size;
	}

	//Returns the number of students present in both lists L1 and L2
	public static int intersectionSizeNestedLoops(studentList L1, studentList L2) {
		int both = 0;						//LG: number of shared elements between L1 & L2
		int num1 = L1.studentID.length;		//LG: length of studentID[] array for studentList L1
		int num2 = L2.studentID.length;		//LG: length of studentID[] array for studentList L2
		for (int i = 0; i < num1; i++)
			for (int j = 0; j < num2; j++)
				if (L1.studentID[i] == L2.studentID[j])	//LG: L*.studentID is array of given studentList L*
					both++;
		return both;
	}

	/* This algorithm takes as input a sorted array of integers called mySortedArray, the number of elements it contains,
and the student ID number to look for
It returns true if the array contains an element equal to ID, and false otherwise.*/
	public static boolean myBinarySearch(int mySortedArray[], int numberOfStudents, int ID) {
		int left = 0;						//LG: first index of array
		int right= numberOfStudents;		//LG: array size (right-1 is last array index)
		int mid;
		while (right > left+1) {			 //LG: array is repeatedly bisected in loop until becomes 2 elements
			mid = left + (right - left) / 2; //LG: though (first + last), which is normally used to find mid (and equivalent
			if (mySortedArray[mid] > ID)     //to (first+(last-first)), will never exceed the largest value for ints (2^30-1)
				right = mid;                 //in this situation, this is better form when accounting for such extreme cases
			else
				left = mid;
		}
		if (mySortedArray[left] == ID)
			return true;
		return false;
	}

	public static int intersectionSizeBinarySearch(studentList L1, studentList L2) {
		int both = 0;						//LG: number of shared elements between L1 & L2
		int num1 = L1.studentID.length;		//LG: length of studentID[] array for studentList L1
		int num2 = L2.studentID.length;		//LG: length of studentID[] array for studentList L2
		Arrays.sort(L1.studentID);			//LG: arrays  must be sorted for algorithm to work
		Arrays.sort(L2.studentID);
		for (int i=0; i < num1; i++){
			if (myBinarySearch(L2.studentID, num2, L1.studentID[i])) //LG: L*.studentID is array of given studentList L*
				both++;
		}
		return both;
	}

	public static int intersectionSizeSortAndParallelPointers(studentList L1, studentList L2) {
		int both = 0;						//LG: number of shared elements between L1 & L2
		int num1 = L1.studentID.length;		//LG: length of studentID[] array for studentList L1
		int num2 = L2.studentID.length;		//LG: length of studentID[] array for studentList L2
		int p1 = 0; 						//LG: index pointer for list 1
		int p2 = 0; 						//LG: index pointer for list 2
		Arrays.sort(L1.studentID);			//LG: arrays  must be sorted for algorithm to work
		Arrays.sort(L2.studentID);
		while( p1 < num1 && p2 < num2 ){	
			if( L1.studentID[p1] == L2.studentID[p2]){
				both++;
				p1++;
				p2++;
			}
			else if( L1.studentID[p1] < L2.studentID[p2] )
				p1++;
			else
				p2++;
		}
		return both;
	}

	public static int intersectionSizeMergeAndSort(studentList L1, studentList L2) {
		int both = 0;						//LG: number of shared elements between L1 & L2
		int num1 = L1.studentID.length;		//LG: length of studentID[] array for studentList L1
		int num2 = L2.studentID.length;		//LG: length of studentID[] array for studentList L2
		int[] A = new int[num1+num2];		//LG: new array to house elements of arrays of both studentLists
		for( int i=0; i<num1; i++){			//LG: this and next loop copy elements from each loop into new array
			A[i] = L1.studentID[i];
		}
		for( int i=0; i<num2; i++){
			A[i+num1] = L2.studentID[i];
		}
		Arrays.sort(A);
		int p = 0;							//LG: index of A whose element is to be compared with next element
		while( p < num1 + num2 - 1 ){
			if( A[p] == A[p+1] ){
				both++;
				p += 2;
			}
			else
				p++;
		}
		return both;   
	}

	/* The main method */
	/* Write code here to test your methods, and evaluate the running time of each. 2014 */
	/* This method will not be marked */
	public static void main(String args[]) throws Exception {

		studentList firstList;
		studentList secondList;
		// This is how to read lists from files. Useful for debugging.

		firstList=new studentList("COMP250.txt", "COMP250 - Introduction to Computer Science");
		secondList=new studentList("MATH240.txt", "MATH240 - Discrete Mathematics");
		// get the time before starting the intersections
		long startTime = System.nanoTime();
		// repeat the process a certain number of times, to make more accurate average measurements.
		//for (int rep=0;rep<1000;rep++) {
			// This is how to generate lists of random IDs.
			// For firstList, we generate 16000 IDs
			// For secondList, we generate 16000 IDs

			firstList=new studentList(1024000, "COMP250 - Introduction to Computer Science");
			secondList=new studentList(32000, "MATH240 - Discrete Mathematics");

			// run the intersection method
			int intersection=studentList.intersectionSizeMergeAndSort(firstList,secondList);
			System.out.println("The intersection size is: "+intersection);
		//}
		// get the time after the intersection
		long endTime = System.nanoTime();
		System.out.println("Running time: "+ (endTime-startTime) + " nanoseconds");
	}
}