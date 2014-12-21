#include <stdio.h>

int main(int argc, char *argv[]) { // 0=a.out, 1=file1, 2=file2 
	char c;
	int vowels = 0;
	FILE *in, *out;
	in = fopen(argv[1], "rt");
	out = fopen(argv[2], "wt");
	c = fgetc(in);
	while (!feof(in)) {
		if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'y') vowels++;
		fputc(c, out);
		c = fgetc(in);
	}
	fclose(in);
	fclose(out);
	printf("Vowels = %d\n", vowels);
}
