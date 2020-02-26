
#include <iostream>
#include <fstream>
#include <istream>
#include "repl.h"
#include "DUT.h"
#include "cppgenutil.h"
#include "hostio.h"

using namespace std;

void printHelp() {
  fprintf(stderr, "Help for app: DotProduct_0\n");
  fprintf(stderr, "  -- Args:    <No input args>\n");
  fprintf(stderr, "    -- Example: ./tungsten \n");
  exit(1);
}
void genLink() {
  Top* top = new Top;
  Module DUT({top}, "DUT");
  REPL repl(&DUT, std::cout);
  repl.Command("source script_link");
  delete top;
  exit(1);
}
int main(int argc, char **argv) {
  vector<string> *args = new vector<string>(argc-1);
  for (int i=1; i<argc; i++) {
    (*args)[i-1] = std::string(argv[i]);
    if (std::string(argv[i]) == "--help" | std::string(argv[i]) == "-h") {printHelp();}
    if (std::string(argv[i]) == "--gen-link" | std::string(argv[i]) == "-l") {genLink();}
  }
  vector<int32_t>* x124 = new vector<int32_t>(1024);
  for (int b1 = 0; b1 < 1024; b1++) {
    (*x124)[b1] = b1;
  }
  vector<int32_t>* x125 = new vector<int32_t>(1024);
  for (int b3 = 0; b3 < 1024; b3++) {
    (*x125)[b3] = b3;
  }
  Allocx126();
  Allocx127();
  memcpy(x126, &(*x124)[0], (*x124).size() * sizeof(int32_t));
  memcpy(x127, &(*x125)[0], (*x125).size() * sizeof(int32_t));
  RunAccel();
  vector<int32_t>* x225 = new vector<int32_t>((*x124).size());
  for (int b45 = 0; b45 < (*x124).size(); b45++) { 
    int32_t x222 = (*x124)[b45];
    int32_t x223 = (*x125)[b45];
    int32_t x224 = x222 * x223;
    (*x225)[b45] = (int32_t) x224;
  }
  int32_t x228;
  if ((*x225).size() > 0) { // Hack to handle reductions on things of length 0
    x228 = (*x225)[0];
  }
  else {
    x228 = 0;
  }
  for (int b50 = 1; b50 < (*x225).size(); b50++) {
    int32_t b51 = (*x225)[b50];
    int32_t b52 = x228;
    int32_t x227 = b51 + b52;
    x228 = x227;
  }
  auto x229 = x128;
  string x230 = std::to_string(x229);
  string x231 = (string("out Result: ") + x230);
  string x232 = (x231 + string("\n"));
  std::cout << x232;
  string x234 = std::to_string(x228);
  string x235 = (string("out Gold: ") + x234);
  string x236 = (x235 + string("\n"));
  std::cout << x236;
  int32_t x238 = x229 - x228;
  int32_t x239 = fabs(x238);
  bool x240 = x239 <= 0;
  string x241 = x240 ? string("true") : string("false");
  string x242 = (string("PASS: ") + x241);
  string x243 = (x242 + string(" (DotProduct)"));
  string x244 = (x243 + string("\n"));
  std::cout << x244;
  string x246 = ("\n=================\n" + (string("DotProduct.scala:54:11: Assertion failure") + "\n=================\n"));
  if (true) { ASSERT(x240, x246.c_str()); }
  cout << "Complete Simulation" << endl;
}
