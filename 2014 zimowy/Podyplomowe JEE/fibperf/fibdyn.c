#include <stdio.h>
#include <stdlib.h>

typedef struct {
	long value;
	long count;
} FibCount;

static FibCount* fibcount(long n)
{
	if (n == 0 || n == 1) {
		FibCount* result = (FibCount*) malloc(sizeof (FibCount));
		result->value = n;
		result->count = 1;
		return result;
	}
	FibCount* c1 = fibcount(n-1);
	FibCount* c2 = fibcount(n-2);
	FibCount* result = (FibCount*) malloc(sizeof (FibCount));
	result->value = c1->value + c2->value;
	result->count = c1->count + c2->count + 1;

	free(c1);
	free(c2);

	return result;
}

static void test(long n)
{
	FibCount* c = fibcount(n);
	printf ("%ld, %ld\n", c->value, c->count);
	free(c);
}

int main(void)
{
	test(37);
	test(38);
	test(39);
}
