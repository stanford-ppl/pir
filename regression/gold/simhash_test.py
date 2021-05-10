import os
import numpy as np
import subprocess

SPATIAL_HOME = os.environ['SPATIAL_HOME']
DATA_DIR = SPATIAL_HOME + '/test-data/sim_hash'

INPUT = DATA_DIR + '/test_input.csv'
OUTPUT = DATA_DIR + '/test_output.csv'
RAND_BITS = DATA_DIR + '/test_rand_bits.csv'

DIMENSION = 512
NUM_HASHES = 8
RATIO = 0.33


def compile_and_run():
    subprocess.run(['bin/spatial', 'SimHash', '--sim'], cwd=f'{SPATIAL_HOME}')
    subprocess.run(['make'], cwd=f'{SPATIAL_HOME}/gen/SimHash')
    subprocess.run(['bash', 'run.sh', str(DIMENSION)], cwd=f'{SPATIAL_HOME}/gen/SimHash')


def test_sim_hash():
    vec = (np.random.rand(DIMENSION) - 0.5) * 100
    np.savetxt(INPUT, vec, delimiter='\n')

    compile_and_run()

    # rand_bits = np.genfromtxt(RAND_BITS, delimiter=',')
    # results = np.genfromtxt(OUTPUT, delimiter='\n')

    # verify_results(vec, rand_bits, results)


def verify_results(vec, rand_bits, results):
    for i in range(rand_bits.shape[0]):
        s = np.dot(rand_bits[i], vec)
        gold = 0 if s >= 0 else 1
        if gold != results[i]:
            print(f'Error: expected {i}th result to be {gold}, got {results[i]}')


if __name__ == '__main__':
    if not os.path.exists(DATA_DIR):
        os.makedirs(DATA_DIR)

    test_sim_hash()
