
#include <iostream>
#include <fstream>
#include <istream>
#include "repl.h"
#include "DUT.h"
#include "cppgenutil.h"
#include "hostio.h"

using namespace std;

void printHelp() {
  fprintf(stderr, "Help for app: Squares_0\n");
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
  vector<string> *args = new vector<string>(0);
  vector<string> *W_pre = new vector<string>(0);
  vector<string> *W_post = new vector<string>(0);
  for (int i=1; i<argc; i++) {
    if (std::string(argv[i]) == "--WPRE" || std::string(argv[i]) == "--WPOST")  {
          assert(i+1 != argc);
          if (std::string(argv[i]) == "--WPRE") {
            std::cout << "Add pre-execution command: " << argv[i+1] << std::endl;
            W_pre->push_back(argv[i+1]); 
          } else {
            std::cout << "Add post-execution command: " << argv[i+1] << std::endl;
            W_post->push_back(argv[i+1]); 
          }
          i++;
          continue;
            }
        
    if (std::string(argv[i]) == "--help" | std::string(argv[i]) == "-h") {printHelp();}
    if (std::string(argv[i]) == "--gen-link" | std::string(argv[i]) == "-l") {genLink();}
    (*args).push_back(std::string(argv[i]));
  }
  std::ifstream x139_file (string("/home/acrucker/spatial/test-data/squares_test/rand.csv"));
  assert(x139_file.good() && "File Const(/home/acrucker/spatial/test-data/squares_test/rand.csv) does not exist"); 
  std::vector<string>* x140 = new std::vector<string>; 
  if (x139_file.is_open()) {
    while ( x139_file.good() ) {
      string x140_line;
      getline (x139_file, x140_line);
      string x140_delim = string("\n");
      size_t x140_pos = 0;
      while (x140_line.find(x140_delim) != std::string::npos | x140_line.length() > 0) {
        if (x140_line.find(x140_delim) != std::string::npos) {
          x140_pos = x140_line.find(x140_delim);
        } else {
          x140_pos = x140_line.length();
        }
        string x140_token = x140_line.substr(0, x140_pos);
        x140_line.erase(0, x140_pos + x140_delim.length());
        x140->push_back(x140_token);
      }
    }
  }
  x139_file.clear();
  x139_file.seekg(0, x139_file.beg);
  x139_file.close();
  vector<uint32_t>* x144 = new vector<uint32_t>((*x140).size());
  for (int b4 = 0; b4 < (*x140).size(); b4++) { 
    string x142 = (*x140)[b4];
    uint32_t x143 = std::stoul(x142);
    (*x144)[b4] = (int32_t) x143;
  }
  Allocx145();
  RunAccel(*W_pre, *W_post);
  vector<uint32_t>* x251 = new vector<uint32_t>(131072);
  memcpy(&(*x251)[0], x145, (*x251).size() * sizeof(uint32_t));
  std::ofstream x253_file (string("/home/acrucker/spatial/test-data/squares_test/rand_out.csv"));
  assert(x253_file.good() && "File Const(/home/acrucker/spatial/test-data/squares_test/rand_out.csv) does not exist"); 
  int32_t x254 = (*x251).size();
  for (int b87 = 0; b87 < x254; b87++) {
    if (x253_file.is_open()) {
      uint32_t x255 = (*x251)[b87];
      string x256 = std::to_string(x255);
      x253_file << x256;
      x253_file << '\n';
    }
  }
  x253_file.close();
  vector<bool>* x262 = new vector<bool>((*x251).size());
  for (int b92 = 0; b92 < (*x251).size(); b92++) { 
    uint32_t x259 = (*x251)[b92];
    uint32_t x260 = (*x144)[b92];
    bool x261 = x259 == x260;
    (*x262)[b92] = x261;
  }
  vector<bool>* x264 = new vector<bool>((*x262).size());
  for (int b97 = 0; b97 < (*x262).size(); b97++) { 
    bool x263 = (*x262)[b97];
    (*x264)[b97] = x263;
  }
  bool x267;
  if ((*x264).size() > 0) { // Hack to handle reductions on things of length 0
    x267 = (*x264)[0];
  }
  else {
    x267 = 0;
  }
  for (int b100 = 1; b100 < (*x264).size(); b100++) {
    bool b101 = (*x264)[b100];
    bool b102 = x267;
    bool x266 = b101 & b102;
    x267 = x266;
  }
  string x268 = ("\n=================\n" + (string("Squares.scala:85:15: Assertion failure") + "\n=================\n"));
  if (true) { ASSERT(x267, x268.c_str()); }
  cout << "Complete Simulation" << endl;
}
