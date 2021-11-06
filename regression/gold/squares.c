#include <stdint.h>
#include <string.h>
#include <stdlib.h>
#include <malloc.h>
#include <omp.h>

inline static uint32_t squares(uint64_t ctr, uint64_t key) {
    uint64_t x, y, z;
    y = x = ctr * key; z = y + key;
    x = x*x + y; x = (x>>32) | (x<<32); /* round 1 */
    x = x*x + z; x = (x>>32) | (x<<32); /* round 2 */
    x = x*x + y; x = (x>>32) | (x<<32); /* round 3 */
    return (x*x + z) >> 32;             /* round 4 */
}

uint32_t* squares_rng(uint64_t len, uint64_t ctr, uint64_t key) {
    uint32_t* res = malloc(len * sizeof(uint32_t));

    #pragma omp parallel for
    for (uint64_t i = 0; i < len; ++i) {
        res[i] = squares(i+ctr, key);
    }

    return res;
}

void write_to_file(uint32_t len, uint32_t *data, const char* path) {
    // construct full file path
    const char* spatial_home = getenv("SPATIAL_HOME");
    size_t str_len = strlen(spatial_home) + strlen(path) + 1;
    char *file_path = malloc(str_len);
    file_path[str_len-1] = 0;
    memcpy(file_path, spatial_home, strlen(spatial_home));
    memcpy(file_path+strlen(spatial_home), path, strlen(path));

    // write data
    FILE *out = fopen(file_path, "w");
    for (size_t i = 0; i < len; ++i) {
        fprintf(out, "%u\n", data[i]);
    }

    fclose(out);
    free(file_path);
}

int main(int argc, char *argv[]) {
    if (argc != 4) {
        printf("Wrong number of arguments.");
        return 1;
    }

    uint64_t len = strtoull(argv[1], NULL, 10);
    uint64_t ctr = strtoull(argv[2], NULL, 10);
    uint64_t key = strtoull(argv[3], NULL, 16);

    printf("length  = %lu\n", len);
    printf("counter = %lu\n", ctr);
    printf("key     = %lx\n", key);

    uint32_t *data = squares_rng(len, ctr, key);
    write_to_file(len, data, "/test-data/squares_test/rand.csv");
    free(data);

    return 0;
}
