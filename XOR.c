#include <stdio.h>
#include <string.h>

int main(){
    char arr1[20]="Hello World";
    int len = strlen(arr1);
    char arr2[len+1], arr3[len+1];

    for (int i=0;i<=len;i++){
        arr2[i]=arr1[i]^0;
        arr3[i]=arr1[i]^127;
    }
    arr2[len]='\0';
    arr3[len]='\0';
    printf("Plaintext: %s\n",arr1);
    printf("Hello World XOR 0: %s\n",arr2);
    printf("Hello World XOR 127: %s\n",arr3);
return 0;
}