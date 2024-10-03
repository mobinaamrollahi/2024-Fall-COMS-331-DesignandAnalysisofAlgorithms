def checkpermutations(A, B):
    hB = {}
    hA = {}
    for i in B:
        hB[i] = 1

    for i in A:
        hA[i] = 1

    for i in A:
        if i not in hB:
            return False
        
    for i in B:
        if i not in hA:
            return False
    
    return True

arr1 = [2, 1, 3, 5, 4, 3, 2]
arr2 = [3, 2, 4, 12, 5, 3, 1]
if (checkpermutations(arr1, arr2)):
    print("Arrays are permutations of each other")
else:
    print("Arrays are NOT permutations of each other")
        