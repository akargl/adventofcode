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
 * @param {number[]} startVals 
 * @returns {number} number of redistribution cycles needed
 */
function naive(startVals) {
    let seenVariants = [startVals];

    let newVariant = redistribute(startVals);

    let count = 1;

    while (!(seenVariants.find((x) => arrayCompareLoop(x, newVariant)))) {
        seenVariants.push(newVariant);

        newVariant = redistribute(newVariant);
        count++;
    }

    return count;
}

/**
 * Brents cycle detection algorithm
 * @param {number[]} startVals 
 * @returns {number} number of redistribution cycles needed
 */
function brentAlgo(startVals) {
    //main phase: search successive powers of two
    let power = 1, lam = 1;
    let tortoise = startVals.slice();
    let hare = redistribute(startVals);

    while (!arrayCompareLoop(tortoise, hare)) {
        //time to start a new power of two?
        if (power === lam) {
            tortoise = hare.slice();
            power *= 2;
            lam = 0;
        }
        hare = redistribute(hare);
        lam++;
    }

    //Find the position of the first repetition of length lam
    let mu = 0;
    tortoise = startVals.slice();
    hare = startVals.slice();
    for (let i = 0; i < lam; i++) {
        hare = redistribute(hare);
    }
    //The distance between the hare and tortoise is now lam.
    //Next, the hare and tortoise move at same speed until they agree
    while (!arrayCompareLoop(tortoise, hare)) {
        tortoise = redistribute(tortoise);
        hare = redistribute(hare);
        mu++;
    }

    return mu + lam;
}

console.time();
let steps = brentAlgo(input);
console.timeEnd();
console.log(steps);