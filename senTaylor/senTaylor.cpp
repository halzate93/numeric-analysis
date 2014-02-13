#include <iostream>
#include <iomanip>
#include <cmath>
#include <stdlib.h>

/* MacLaurin Serie for sin(x), Taylor serie with a = 0
 * sin(x) = (Σn=0, ∞)(((-1 ^ n)/(2n + 1)!)(x^(2n + 1)) 
  
 * As the serie converges, the next term of the sum will be
 * smaller than the last one, and also will be alternating
 * signs between + and -, therefore each iteration aproaches
 * the correct value. We need to get a given number of correct
 * digits, so we must make the last term calculated lesser
 * than the required absolute error. */

using namespace std;

double sin(double x, double E);
double maxAbsErr(int cd);
ulong fact(ulong n);

int main(int argc, char* args[]){
  double x;
  int cd;

  if(argc > 2){
    x = atof(args[1]);
    cd = atoi(args[2]);
  }else{
    cout << "Input x and cd:" << endl;
    cin >> x;
    cin >> cd;
  }
  
  double E = maxAbsErr(cd);

  cout << "sin(" << x << ") = " << setprecision(cd) << sin(x, E) 
       << ", E = " << E << ", cd = " << cd << endl;
  
  return 0;
}

//sin(x) = (Σn=0, ∞)(((-1 ^ n)/(2n + 1)!)(x^(2n + 1))
double sin(double x, double E){

  double sin = 0.0d, term, last;

  int n = 0;
  bool bigger = true;
  while(bigger){
    last = sin;
    term = (pow(-1.0d, n) / fact(2*n + 1)) * pow(x, 2*n + 1);
    if(abs(sin + term - last) > E){
      sin += term;
    }else{
      bigger = false;
    }
    n++;
  }

  return sin;
}

//Emax = 1.0 x 10 ^ -cd, asuming c++ uses floor as round method
double maxAbsErr(int cd) {
  return pow(10.0d, -cd);
}

ulong fact(ulong n){
  if(n <= 1) return 1;
  return n * fact(n - 1);
}
