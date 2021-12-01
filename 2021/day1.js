const util = require("./util");

let numbers = util.inputToNumbers("2021/input1_1.txt");

let resPart1 = numbers.map((_, i) => numbers[i+1] -numbers[i])
    .filter(x => x > 0)
    .length;
console.log(`part1: ${resPart1}`);

let resPart2 = numbers.map((_, i) => numbers[i+3] - numbers[i])
    .filter(x => x > 0)
    .length;
console.log(`part2: ${resPart2}`);