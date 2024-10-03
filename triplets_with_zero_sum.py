def tripletszerosum(nums):
    h = {0:1}
    result = []

    for i in range(len(nums)-1):
        for j in range (i+1, len(nums)):
            complement = -(nums[i]+nums[j])
            if complement in h:
                triplet = [i,j,h[-nums[i] - nums[j]]]
                if triplet not in result:
                    result.append(triplet)

        h[nums[j]] = j

    return result

arr = [0, -1, 2, -3, 1]
result = tripletszerosum(arr)
print(result)
