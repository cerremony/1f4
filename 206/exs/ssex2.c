//ssex2.c

int main (void){
	const int x = 5;
	char c;
	float y;
	double z;
	
	c = 'a';
	y = z = 10.2;
	y = y + 1; y = x + z;
	
	printf("x=%d c=%c y=%f z=%f\n",x,c,y,z);
}
