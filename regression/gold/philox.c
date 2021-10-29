#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define NUM_ROUNDS 7
#define MULT_0 0xD2511F53
#define MULT_1 0xCD9E8D57
#define WEYL_0 0x9E3779B9
#define WEYL_1 0xBB67AE85

void mulhilo(uint32_t a, uint32_t b, uint32_t *hi, uint32_t *lo) {
    uint64_t mul = ((uint64_t) a) * ((uint64_t) b);
    *lo = (uint32_t)mul;
    *hi = (uint32_t)(mul >> 32);
}

void single_round(uint32_t ctr[4], const uint32_t key[2]) {
    uint32_t lo0, hi0, lo1, hi1;
    mulhilo(ctr[0], MULT_0, &hi0, &lo0);
    mulhilo(ctr[2], MULT_1, &hi1, &lo1);
    ctr[0] = hi1 ^ key[0] ^ ctr[1];
    ctr[1] = lo1;
    ctr[2] = hi0 ^ key[1] ^ ctr[3];
    ctr[3] = lo0;
}

void philox_compute(const uint32_t ctr[4], const uint32_t key[2], uint32_t out[4]) {
    uint32_t ctr_[4], key_[2];
    memcpy(ctr_, ctr, sizeof(ctr_));
    memcpy(key_, key, sizeof(key_));

    for (size_t i = 0; i < NUM_ROUNDS - 1; ++i) {
        single_round(ctr_, key_);
        key_[0] += WEYL_0; key_[1] += WEYL_1;
    }
    single_round(ctr_, key_);

    memcpy(out, ctr_, sizeof(ctr_));
}

void incr(uint32_t ctr[4]) {
    // series of checking overflow
    if (++ctr[0]) return;
    if (++ctr[1]) return;
    if (++ctr[2]) return;
    ++ctr[3];
}

uint32_t philox(uint32_t ctr[4], const uint32_t key[2], unsigned *selector) {
    uint32_t out[4];
    philox_compute(ctr, key, out);
    incr(ctr);
    uint32_t res = out[*selector];
    *selector = (*selector + 1) & 3;
    return res;
}

int main(int argc, char *argv[]) {
    // if (argc != 7) {
    //     printf("not enough arguments\n");
    //     return 1;
    // }
    // uint32_t key[2];
    // uint32_t ctr[4];
    // key[0] = strtoul(argv[1], NULL, 10);
    // key[1] = strtoul(argv[2], NULL, 10);
    // ctr[0] = strtoul(argv[3], NULL, 10);
    // ctr[1] = strtoul(argv[4], NULL, 10);
    // ctr[2] = strtoul(argv[5], NULL, 10);
    // ctr[3] = strtoul(argv[6], NULL, 10);
    // unsigned selector = 0;

    // for (unsigned i = 0; i < 10; ++i) {
    //     printf("%u\n", philox(ctr, key, &selector));
    // }

    if (argc != 2) {
        printf("not enough arguments");
        return 1;
    }

    size_t len = strtoull(argv[1], NULL, 10);

    printf("length = %lu\n", len);

    uint32_t key[2] = {0, 0};
    uint32_t ctr[4] = {0, 0, 0, 0};
    unsigned selector = 0;

    const char* spatial_home = getenv("SPATIAL_HOME");
    const char* path = "/test-data/philox_test/rand.csv";
    size_t str_len = strlen(spatial_home) + strlen(path) + 1;
    char *file_path = malloc(str_len);
    file_path[str_len-1] = 0;
    memcpy(file_path, spatial_home, strlen(spatial_home));
    memcpy(file_path+strlen(spatial_home), path, strlen(path));

    FILE *out = fopen(file_path, "w");
    for (size_t i = 0; i < len; ++i) {
        fprintf(out, "%u\n", philox(ctr, key, &selector));
    }
    fclose(out);

    free(file_path);

    return 0;
}
