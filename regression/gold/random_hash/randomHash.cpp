#include <string>
#include <sstream>
#include "sha256.h"

using namespace std;

extern "C"
unsigned int mersenneHash(size_t i, size_t j) {
    const unsigned int MERSENNE_1 = 131071;
    const unsigned int MERSENNE_2 = 524287;
    return i * MERSENNE_1 + j * MERSENNE_2;
}

extern "C"
unsigned int sha256Hash(size_t i, size_t j) {
    // get random number from sha256
    static SHA256 sha256;
    stringstream ss;
    ss << "(" << i << " ," << j << ")";
    string hash_string = sha256(ss.str());
    return stoul(hash_string.substr(56, 64), nullptr, 16);
}

extern "C"
unsigned int fnvHash(size_t i, size_t j) {
    static const unsigned int FNV_PRIME = 16777619;
    static const unsigned int OFFSET_BASIS = 2166136261;

    unsigned int tmp[4];
    tmp[0] = i & 0xffff;
    tmp[1] = (i & 0xffff0000) >> 8;
    tmp[2] = j & 0xffff;
    tmp[3] = (j & 0xffff0000) >> 8;

    unsigned int hash = OFFSET_BASIS;
    for (int k = 0; k < 4; ++k) {
        hash ^= tmp[k];
        hash *= FNV_PRIME;
    }

    return hash;
}

extern "C"
unsigned int idHash(size_t i, size_t j) {
    return i + j;
}