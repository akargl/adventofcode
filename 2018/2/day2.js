/**
 * 
 * @param {string[]} ids 
 */
function part1(ids) {
    let twoCount = 0;
    let threeCount = 0;

    ids.forEach((id) => {
        let idChars = id.split("");
        let uniqueChars = [...new Set(idChars)];

        function numOccurences(val, values) {
            return values.filter(x => x === val).length;
        }

        if (uniqueChars.some((c) => {
                return idChars.filter((x) => x === c).length === 2;
            })) {
            twoCount++;
        }

        if (uniqueChars.some((c) => {
                return idChars.filter((x) => x === c).length === 3;
            })) {
            threeCount++;
        }
    });

    return twoCount * threeCount;
}

/**
 * 
 * @param {string[]} ids 
 */
function part2(ids) {
    for (let i = 0; i < ids.length; i++) {
        let refChars = ids[i].split("");
        for (let j = 0; j < ids.length; j++) {
            if (j === i) { continue; }

            let cmpChars = ids[j].split("");
            let matches = refChars.filter((char, index) => char === cmpChars[index]);

            if (matches.length === refChars.length -1) {
                return matches.join("");
            }
        }
    }
}

const fs = require("fs");
const input = fs.readFileSync("input.txt", "utf-8");

let ids = input.split("\n")
    .filter(x => x !== "");

console.time("part1");
let part1Res = part1(ids);
console.timeEnd("part1");
console.log(`part 1 result: ${part1Res}`);

console.time("part2");
let part2Res = part2(ids);
console.timeEnd("part2");
console.log(`part 2 result: ${part2Res}`);