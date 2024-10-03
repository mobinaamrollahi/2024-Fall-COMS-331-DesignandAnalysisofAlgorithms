def indexoffirstone(arr):
    left = 0
    right = len(arr) - 1

    while left <= right:
        mid = int(left + (right-left)/2)

        if arr[mid] == 1:
            right = mid - 1         
        if arr[mid] == 0:
            left = mid + 1
    if arr[mid] == 0:
        return -1
    return mid

# Driver program to test above
arr = [0, 0, 0, 0, 0, 0, 0, 0, 1, 1]
n = len(arr)
ans = indexoffirstone(arr)
print (ans)