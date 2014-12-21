//hash.c
#include <stdlib.h>
#include <stdio.h>

int hash(int key);

char sentence[101];

/*main function*/
int main(){
	int key, hashNumber;
	
	printf("Please enter a sentence. Keep it less than 100 characters: ");
	fgets(sentence,100,stdin);	

	printf("Please enter a key: ");
	scanf("%d", &key);
	
	hashNumber = hash(key);
	printf("%d\n", hashNumber);
	printf("Done.\n");
	
	return 0;
}

/*hash function called from main, does work of summing character values (except null)*/
int hash(int key){
	int sum = 0, i = 0, final=1;
	
	while(sentence[i] != 10 && i < 100){
		sum = sum + sentence[i];
		i++;
	}	

	for (i=0; i < key; i++){
		final=final*sum;
	}

	return final;
}

