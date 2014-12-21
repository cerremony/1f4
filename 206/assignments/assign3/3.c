#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#define ENGLISH
//#define ALBANIAN

struct node {
	char word[256];
	struct NODE* next;
}*root=NULL;

void addToLinkedList(node* list, char *str);
void printLinkedList(node* list);
void printNodes(node* my_node);

void main() {
	node* longString = NULL;
	char welcome[49], prompt[40], tail[11], string[64];
	#ifdef ALBANIAN
		sprintf(welcome, "Miresevini te programit e ruajtje strings pafund.\n");
		sprintf(prompt, "Ju lutemi te dhena nje fjale te vetme: ");
		sprintf(tail, "***END***");
	#else
		sprintf(welcome, "Welcome to the infinite string storage program.\n");
		sprintf(prompt, "Please input a single word: ");
		sprintf(tail, "***END***");
	#endif
	printf("%s\n%s", welcome, prompt);
	scanf("%s", string);
	while (strcmp(string, tail) != 0){
		addToLinkedList(longString, string);
		memset(string, '0', strlen(string));
		printf("%s", prompt);
		scanf("%s", string);
	}
	printLinkedList(longString);
}

void addToLinkedList(node* list, char *str) {
	node* freeSpot;
	node* newNode;
	
	freeSpot = list;
//	if (list == NULL){

	while(freeSpot->next != NULL) {
		freeSpot = freeSpot->next;
	}
	
	newNode = (node *)malloc(sizeof(node));

	strcpy(newNode->word, str);
	newNode->next = NULL;
	freeSpot->next = newNode;
}

void printLinkedList(node* list) {
	if (list != NULL){
		printNodes(list);
	} else {
		printf("List is empty.");
	}
	printf("\n");
}

void printNodes(node* my_node) {
	printf(" %s ", my_node->word);
	if (my_node->next != NULL){
		printNodes(my_node->next);
	}
}
