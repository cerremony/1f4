#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int main(int arc, char *argv[]){
	FILE *data;
	data = fopen(argv[1], "rt");
	if (data == NULL){
		printf("No data\n");
		return EXIT_FAILURE;
	}
	char name[32], num[8];
	memset(name, '\0', 32);
	memset(num, ' ', 8);
	int sum, size, i;	
	int c = fgetc(data);
	do {
		sum = 0, size = 0, i = 0;
		while (c != ','){ 
			name[i] = c;
			i++;
			printf("%d ", i);
			c = fgetc(data);
	//		printf("%c\n", c);
		}
		printf("%s ", name);
		memset(name, '\0', i);
	//	printf("%s ", name);
//		c = fgetc(data);
		do {
			c = fgetc(data);
			i = 0;
			while (c != ',' && c != '\n' && c != EOF){
				num[i] = c;
				i++;
				c = fgetc(data);
			}
			sum += atoi(num);
			memset(num, ' ', i);
			size++;
		} while (c != '\n' && c != EOF);
		float average = sum/size;
		printf("%.2f", average);
	} while (c != EOF);
	fclose(data);
	return EXIT_SUCCESS;
}
