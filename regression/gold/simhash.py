import random
import csv
import os
from functools import reduce

DATA_DIR = os.environ['SPATIAL_HOME'] + '/test-data/sim_hash'
INPUT_FILE = DATA_DIR + '/test_input.csv'
OUTPUT_FILE = DATA_DIR + '/test_output.csv'


def mersenne_hash(i, j, k, l):
    MERSENNE_PRIME_1 = 127
    MERSENNE_PRIME_2 = 8191
    MERSENNE_PRIME_3 = 131071
    MERSENNE_PRIME_4 = 524287

    return (MERSENNE_PRIME_1 * i) \
         + (MERSENNE_PRIME_2 * l) \
         + (MERSENNE_PRIME_3 * k) \
         + (MERSENNE_PRIME_4 * j)


def simhash(input, K, L, ratio, random_hash):

    result = []

    for i in range(len(input)):  # every vector in input
        result.append([])
        for l in range(L):  # every hash table
            hash_codes = []
            for k in range(K):  # every hash function within a table
                s = 0
                for j in range(len(input[0])):  # every number with in an input vector
                    rand_num = random_hash(i, j, k, l)
                    if rand_num % ratio == 0:
                        if rand_num % 2 == 0:
                            s += input[i][j]
                        else:
                            s -= input[i][j]

                hash_codes.append((1 if s < 0 else 0) << k)
            result[i].append(reduce(lambda a, b: a | b, hash_codes))

    return result


def write_csv(data, file):
    with open(file, 'w+') as f:
        csvWriter = csv.writer(f,delimiter=',')
        csvWriter.writerows(data)


if __name__ == '__main__':
    if not os.path.exists(DATA_DIR):
        os.makedirs(DATA_DIR)

    input = [[random.random() * 100 - 50 for _ in range(50)] for _ in range(100)]
    write_csv(input, INPUT_FILE)

    output = simhash(input, K=5, L=3, ratio=3, random_hash=mersenne_hash)
    write_csv(output, OUTPUT_FILE)

