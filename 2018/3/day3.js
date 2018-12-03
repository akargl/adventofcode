/**
 * Check if x, y coords are contained in given claim
 * @param {number} x 
 * @param {number} y 
 * @param {Object} claim 
 */
function isInClaim(x, y, claim) {
    return x >= claim.x && x < (claim.x + claim.width) &&
        y >= claim.y && y < (claim.y + claim.height);
}

/**
 * Returns true if given coords are in at least n claims
 * @param {number} x 
 * @param {number} y 
 * @param {Object} claims 
 * @param {number} n 
 */
function isInMinNClaims(x, y, claims, n) {
    let claimCounter = 0;

    for (const claim of claims) {
        if (isInClaim(x, y, claim)) {
            claimCounter++;
        }

        if (claimCounter >= n) {
            return true;
        }
    }

    return false;
}

/**
 * Count the number of square inches contained in multiple claims
 * @param {Object[]} claims 
 */
function part1(claims) {
    let minX = Math.min(...claims.map((c) => c.x));
    let maxX = Math.max(...claims.map((c) => c.x + c.width));
    let minY = Math.min(...claims.map((c) => c.y));
    let maxY = Math.max(...claims.map((c) => c.y + c.height));

    let sharedInchCounter = 0;

    for (let x = minX; x <= maxX; x++) {
        for (let y = minY; y <= maxY; y++) {
            if (isInMinNClaims(x, y, claims, 2)) {
                sharedInchCounter++;
            }
        }
    }

    return sharedInchCounter;
}

/**
 * Find the claim that isn't contested by any other claim
 * @param {Object[]} claims 
 */
function part2(claims) {
    let uncontestedClaim = claims.find((claim) => {
        for (let x = claim.x; x <= (claim.x + claim.width); x++) {
            for (let y = claim.y; y <= (claim.y + claim.height); y++) {
                if (isInMinNClaims(x, y, claims, 2)) {
                    return false;
                }
            }
        }

        return true;
    });

    return uncontestedClaim.id;
}

const fs = require("fs");
const input = fs.readFileSync("input.txt", "utf-8");

let claimStrings = input.split("\n")
    .filter(x => x !== "");

const claimRegex = /#([\d]*) @ (\d*),(\d*): (\d*)x(\d*)/;

let claims = claimStrings.map((claimStr) => {
    let result = claimRegex.exec(claimStr);

    return {
        id : parseInt(result[1], 10),
        x : parseInt(result[2], 10),
        y : parseInt(result[3], 10),
        width : parseInt(result[4], 10),
        height : parseInt(result[5], 10)
    }
});

console.time("part1");
let part1Res = part1(claims);
console.timeEnd("part1");
console.log(`part 1 result: ${part1Res}`);

console.time("part2");
let part2Res = part2(claims);
console.timeEnd("part2");
console.log(`part 2 result: ${part2Res}`);