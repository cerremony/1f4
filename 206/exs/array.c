#include <stdio.h>
#include <stdlib.h>

int main(){
	char array[10]={'a','b','c','\0'};
	printf("%s", array);
	array[3]='d';
	printf("%s", array);
	return 0;
}
