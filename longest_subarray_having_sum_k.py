def longestsubarray(arr, k):
    maxlength = 0
    prefixsum = 0
    h = {}

    for i in range(len(arr)):
        prefixsum += arr[i]
        difference = prefixsum - k

        maxlength = max(maxlength, i - h.get(difference,i))

        h[prefixsum] = i
    return maxlength

arr = [10, 5, 2, 7, 1, 9]
k = 15
print(longestsubarray(arr, k))

