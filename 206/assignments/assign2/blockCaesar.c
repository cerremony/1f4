//blockCaesar.c
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int main(void){

	char sentence[101];
	char c;
	int key1, key2, key3, i, k;

	printf("Please enter a sentence. Keep it less than 100 characters: ");
	scanf(" %100[^\n]s", sentence);	

	printf("Please enter three (positive or negative) integers: ");
	scanf("%d %d %d", &key1, &key2, &key3);

	/*ciphering of the entering sentence takes place in this loop*/
	for (i=0; i<100; i++){
		c = sentence[i];
		if(i%3 == 0) {k = key1;}
		else if (i%3 == 1) {k = key2;}
		else {k = key3;}

		if (c >= 65 && c <= 90){
			sentence[i] = (c + k - 65) % 26 + 65;}
		else if (c >= 97 && c <= 122){
			sentence[i] = (c + k - 97) % 26 + 97;}
		else {
			sentence[i] = c;}
		}

	printf("%s\n", sentence);
	return 0;
}
