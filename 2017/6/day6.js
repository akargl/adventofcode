const input = [4, 1, 15, 12, 0, 9, 9, 5, 5, 8, 7, 3, 14, 5, 12, 3];

/**
 * 
 * @param {number[]} origVals 
 */
function redistribute(origVals) {
    let vals = origVals.slice();

    if (!vals.length) {
        return [];
    }

    let maxIndex = vals.indexOf(Math.max(...vals));
    let maxVal = vals[maxIndex];
    vals[maxIndex] = 0;

    while(maxVal > 0) {
        maxIndex = (maxIndex === vals.length -1) ? 0 : maxIndex +1;

        vals[maxIndex]++;
        maxVal--;
    }

    return vals;
}

/**
 * Quick and dirty array equality comparison
 * @param {[]} a1 
 * @param {[]} a2 
 */
function arrayCompareStringify(a1, a2) {
    return JSON.stringify(a1) === JSON.stringify(a2);
}

/**
 * Flat primitive array comparison
 * @param {[]} a1 
 * @param {[]} a2 
 */
function arrayCompareLoop(a1, a2) {
    if (a1.length !== a2.length) {
        return false;
    }

    for (let index = 0; index < a1.length; index++) {
        if (a1[index] !== a2[index]) {
            return false;
        }
    }

    return true;
}

/**
 * 
 * @param {number[]} vals 
 */
function naive(vals) {
    let seenVariants = [vals];

    let newVariant = redistribute(vals);

    let count = 1;

    while (!(seenVariants.find((x) => arrayCompareLoop(x, newVariant)))) {
        seenVariants.push(newVariant);

        newVariant = redistribute(newVariant);
        count++;
    }

    return count;
}

console.time();
let steps = naive(input);
console.timeEnd();
console.log(steps);