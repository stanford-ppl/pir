#include <stdint.h>
#include <malloc.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

static uint64_t       state      = 0x4d595df4d0f33173;		// Or something seed-dependent
static uint64_t const multiplier = 6364136223846793005u;
static uint64_t const increment  = 1442695040888963407u;	// Or an arbitrary odd constant

static uint32_t rotr32(uint32_t x, unsigned r)
{
	return x >> r | x << (-r & 31);
}

uint32_t pcg32(void)
{
    uint64_t x = state * multiplier + increment;
	unsigned count = (unsigned)(x >> 59);		// 59 = 64 - 5
	state = x;
	x ^= x >> 18;								// 18 = (64 - 27)/2
	return rotr32((uint32_t)(x >> 27), count);	// 27 = 32 - 5
}

void pcg32_init(uint64_t seed)
{
	state = seed + increment;
}

int main(int argc, char* argv[]) {
    if (argc != 3) {
        printf("not enough arguments");
        return 1;
    }

    uint64_t seed = strtoull(argv[1], NULL, 10);
    size_t len = strtoull(argv[2], NULL, 10);

    printf("seed = %lu, length = %lu\n", seed, len);
    pcg32_init(seed);

    const char* spatial_home = getenv("SPATIAL_HOME");
    const char* path = "/test-data/pcg_test/rand.csv";
    size_t str_len = strlen(spatial_home) + strlen(path) + 1;
    char *file_path = malloc(str_len);
    file_path[str_len-1] = 0;
    memcpy(file_path, spatial_home, strlen(spatial_home));
    memcpy(file_path+strlen(spatial_home), path, strlen(path));

    FILE *out = fopen(file_path, "w");
    for (size_t i = 0; i < len; ++i) {
        fprintf(out, "%u\n", pcg32());
    }
    fclose(out);

    free(file_path);

    return 0;
}