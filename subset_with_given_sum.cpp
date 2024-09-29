#include <iostream>
#include <vector>

using namespace std;

vector<int> findsubset(const vector<int>& arr, int sum){
    vector<vector<int> subset;
    int n = arr.size();

    for (int i = 0; i <= n; i++){
        dp[i][0] = true;
    }
    return {};
}

int main(){
    vector<int> arr = {8, 2, 6, 41};
    int sum = 57;

    vector<int> result = findsubset(arr, sum);
    
    if (findsubset(arr,sum)){
        cout << "found such subset" << endl;
    } else {
        cout << "no such subset." << endl;
    }
    return 0;
}