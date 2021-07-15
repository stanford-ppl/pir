import random
import ctypes
import os
import sys
import numpy as np
import argparse
from functools import reduce

from numpy.ma.core import default_fill_value

DATA_DIR = os.environ['SPATIAL_HOME'] + '/test-data/sim_hash'
INPUT_FILE = DATA_DIR + '/gold_input.csv'
OUTPUT_FILE = DATA_DIR + '/gold_output.csv'
SHA256_HASH = DATA_DIR + '/sha256.csv'
C_LIB = os.environ['SPATIAL_HOME'] + '/pir/regression/gold/random_hash/hashlib.so'


def parse_args():
    ap = argparse.ArgumentParser()
    ap.add_argument('-r', '--ratio', type=int, default=3)
    ap.add_argument('-d', '--dimension', type=int, default=100)
    ap.add_argument('-K', '--K', type=int, default=5)
    ap.add_argument('-L', '--L', type=int, default=3)
    ap.add_argument('-H', '--hash', type=str, default='fnv1a')
    ap.add_argument('--sha256_only', default=False, action='store_true')

    return vars(ap.parse_args())


def sha256_hash(i, j):
    return ctypes.CDLL(C_LIB).sha256Hash(i, j)


def fnv1a_hash(i, j):
    return ctypes.CDLL(C_LIB).fnvHash(i, j)


def mersenne_hash(i, j):
    return ctypes.CDLL(C_LIB).mersenneHash(i, j)


def store_sha256_hash(rows, cols):
    hash_values = np.zeros((rows, cols), dtype=int)
    for i in range(rows):
        for j in range(cols):
            hash_values[i, j] = sha256_hash(i, j)
    np.savetxt(SHA256_HASH, hash_values, fmt='%d', delimiter=',')


def simhash(input, K, L, ratio, random_hash):
    results = []

    for l in range(L):  # every hash table
        hash_codes = []
        for k in range(K):  # every hash function within a table
            s = 0
            for j in range(len(input)):  # every number with in an input vector
                rand_num = random_hash(K*l+k, j)
                if (rand_num >> 2) % ratio == 0:
                    if rand_num % 2 == 0:
                        s += input[j]
                    else:
                        s -= input[j]
            hash_codes.append((1 if s < 0 else 0) << k)
        results.append(reduce(lambda a, b: a | b, hash_codes))

    return results


if __name__ == '__main__':
    if not os.path.exists(DATA_DIR):
        os.makedirs(DATA_DIR)

    args = parse_args()
    if args['sha256_only']:
        store_sha256_hash(args['K']*args['L'], args['dimension'])
    else:
        input = [random.random() * 100 - 50 for _ in range(args['dimension'])]
        np.savetxt(INPUT_FILE, input, fmt='%.4f', delimiter=',')

        if args['hash'] == 'fnv1a':
            random_hash = fnv1a_hash
        elif args['hash'] == 'sha256':
            random_hash = sha256_hash
        elif args['hash'] == 'mersenne':
            random_hash = mersenne_hash
        else:
            sys.exit('invalid hash function')
        output = simhash(np.genfromtxt(INPUT_FILE, dtype='float32', delimiter=','), K=args['K'], L=args['L'], ratio=args['ratio'], random_hash=random_hash)
        np.savetxt(OUTPUT_FILE, output, fmt='%d', delimiter=',')
