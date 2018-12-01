/**
 * 
 * @param {number[]} numbers 
 * @returns {number} sum of numbers
 */
function part1(numbers) {
    return numbers.reduce((previous, current) => previous + current, 0);
}

/**
 * 
 * @param {number[]} numbers 
 * @returns {number} 
 */
function part2(numbers) {
    /**
     * 
     * @param {number[]} numbers 
     * @param {number} initialSum
     * @yields next sum from numbers
     */
    function* numGen(numbers, initialSum = 0) {
        let i = 0;
        let sum = initialSum;
        while (true) {
            yield sum;
            sum += numbers[i];
            i = i === numbers.length -1 ? 0 : i +1;
        }
    }

    let sumGen = numGen(numbers);

    let encounteredSums = [];
    let currentSum = sumGen.next().value;
    while (!encounteredSums.includes(currentSum)) {
        encounteredSums.push(currentSum);
        currentSum = sumGen.next().value;
    }

    return currentSum;
}


const fs = require("fs");

console.time("read input");

const input = fs.readFileSync("input", "utf-8");

let numbers = input.split("\n")
    .map((x) => parseInt(x))
    .filter((x) => !isNaN(x));

console.timeEnd("read input");

console.time("part1");
let part1Res = part1(numbers);
console.timeEnd("part1");
console.log(`part 1 result: ${part1Res}`);

console.time("part1");
let part2Res = part2(numbers);
console.timeEnd("part1");
console.log(`part 2 result: ${part2Res}`);
