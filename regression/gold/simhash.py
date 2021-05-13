import random


def simhash(input, K, L, ratio):
    MERSENNE_PRIME_1 = 127
    MERSENNE_PRIME_2 = 8191
    MERSENNE_PRIME_3 = 131071
    MERSENNE_PRIME_4 = 524287

    result = []

    for i in range(len(input)):  # every vector in input
        result.append([])
        for l in range(L):  # every hash table
            result[i].append([])
            for k in range(K):  # every hash function within a table
                s = 0
                for j in range(len(input[0])):  # every number with in an input vector
                    rand_num = (MERSENNE_PRIME_1 * i) \
                             + (MERSENNE_PRIME_2 * l) \
                             + (MERSENNE_PRIME_3 * k) \
                             + (MERSENNE_PRIME_4 * j)
                    if rand_num % ratio == 0:
                        if rand_num % 2 == 0:
                            s += input[i][j]
                        else:
                            s -= input[i][j]

                hash_code = 1 if s < 0 else 0
                result[i][l].append(hash_code)

    return result

if __name__ == '__main__':
    input = [[random.random() * 100 - 50 for _ in range(5)] for _ in range(10)]
    print(simhash(input, 5, 3, 3))