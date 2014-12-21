// test1.c
#include <stdio.h>

// takes 5 int as input, and output(print) them(from first to last and reversly)

int main(){

	//declaration of var
	int a[5];
	
	//printf
	printf("this is a C program\n");
	printf("Please input 5 numbers separated by space: ");

	//takes input save them in a[]
	scanf("%d %d %d %d %d", &a[0], &a[1], &a[2], &a[3], &a[4]);

	int i, j;
	//print received input
	printf("\nfirst to last: ");
	for(i = 0; i < 5; i ++){
		//print form frist to last
		printf("%d", a[i]);
	}
	printf("\nlast to first: ");
	for(j = 4; j >= 0; j--){
		//print from last to first
		printf("%d ", a[j]);
	}
	
	return 0;
}
