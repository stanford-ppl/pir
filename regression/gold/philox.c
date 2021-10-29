#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>

#define NUM_ROUNDS 7
#define MULT_0 0xCD9E8D57
#define MULT_1 0xD2511F53
#define WEYL_0 0x7F4A7C15
#define WEYL_1 0x84CAA73B

typedef struct feistel {
    uint32_t L;
    uint32_t R;
    uint32_t key;
    uint32_t const M;
    uint32_t const weyl;
} feistel_t;

static feistel_t philox_states[2] = {
    { .L = 0, .R = 0, .key = 0, .M = MULT_0, .weyl = WEYL_0 },
    { .L = 0, .R = 0, .key = 0, .M = MULT_1, .weyl = WEYL_1 },
};

void print_state() {
    for (int i = 0; i < 2; ++i) {
        printf("State[%d], { .L = %u, .R = %u, .key = %u, .M = %u, .weyl = %u}\n",
                i+1,
                philox_states[0].L,
                philox_states[0].R,
                philox_states[0].key,
                philox_states[0].M,
                philox_states[0].weyl);
    }
    printf("\n");
}

void sbox(feistel_t *state) {
    uint64_t prod = ((uint64_t)state->R) * ((uint64_t)state->M);
    uint32_t LL = (uint32_t)prod;
    uint32_t RR = (uint32_t)(prod >> 32) ^ state->L ^ state->key;
    state->L = LL;
    state->R = RR;
    state->key += state->weyl;
}

void swap(uint32_t *a, uint32_t *b) {
    // perhaps Plasticine can benefit from XOR swap?
    uint32_t tmp = *a;
    *a = *b;
    *b = tmp;
}

void pbox(feistel_t (*states)[2]) {
    swap(&states[0]->R, &states[1]->R);
}

void set_key(uint32_t keys[2]) {
    philox_states[0].key = keys[0];
    philox_states[1].key = keys[1];
}

void set_counter(uint32_t counter[4]) {
    philox_states[0].L = counter[0];
    philox_states[0].R = counter[1];
    philox_states[1].L = counter[2];
    philox_states[1].R = counter[3];
}

uint32_t philox() {
    for (size_t i = 0; i < NUM_ROUNDS; ++i) {
        pbox(&philox_states);
        sbox(&philox_states[0]);
        sbox(&philox_states[1]);
    }
    return philox_states[0].L;
}

int main(int argc, char *argv[]) {
    if (argc != 7) {
        printf("not enough arguments");
        return 1;
    }
    uint32_t keys[2];
    uint32_t counter[4];
    keys[0] = strtoul(argv[1], NULL, 10);
    keys[1] = strtoul(argv[2], NULL, 10);
    counter[0] = strtoul(argv[3], NULL, 10);
    counter[1] = strtoul(argv[4], NULL, 10);
    counter[2] = strtoul(argv[5], NULL, 10);
    counter[3] = strtoul(argv[6], NULL, 10);
    set_key(keys);
    set_counter(counter);

    for (size_t i = 0; i < 10; ++i) {
        printf("rand = %u\n", philox());
    }
}
