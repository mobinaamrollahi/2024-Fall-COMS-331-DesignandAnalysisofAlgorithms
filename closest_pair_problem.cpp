#include <iostream>
#include <vector>
#include <math.h>

using namespace std;

struct point{
    double x, y;
    point(double x = 0, double y = 0) : x(x), y(y) {}
};

double closestpair(const vector<point>& points);

int main() {
    vector<point> points = {
        {2, 3}, {12, 30}, {40, 50}, {5, 1}, {12, 10}, {3, 4}
    };
    
    double result = closestpair(points);
    
    cout << "The distance between the closest pair of points is: " << result << endl;
    
    return 0;
}

double closestpair(const vector<point>& points){
    int min = 10000000;
    int n = points.size();

         for(int i = 1; i < n ; i++){
            for(int j = i + 1 ; j < n + 1 ; j++)
            {
                double d = sqrt(pow(points[i].x - points[j].x,2) + pow(points[i].y - points[j].y,2));
                if (d < min) {
                    min = d;
                }
            }
         }           
     return min;
}

