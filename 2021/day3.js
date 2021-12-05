const _ = require("lodash");

function part1(lines) {
    lines = lines.map(l => [...l]);
    let bits = _.zip(...lines);

    let gammaString = "";
    let epsilonString = "";

    bits.forEach(b => {
        if (b.filter(x => x === "1").length > b.length/2) {
            gammaString += "1";
            epsilonString += "0";
        } else {
            gammaString += "0";
            epsilonString += "1";
        }
    });

    return {
        gamma: parseInt(gammaString, 2),
        epsilon: parseInt(epsilonString, 2)
    };
}


function part2Helper(lines, bitCompFunc) {
    let numBits = lines[0].length;
    for (let b = 0; b < numBits; b++) {
        let bitCounter = [0, 0];
        for (let l = 0; l < lines.length; l++) {
            let currentBit = lines[l][b];
            bitCounter[currentBit]++;
        }

        let mostCommonBit = bitCompFunc(bitCounter);

        lines = lines.filter(x => x[b] === mostCommonBit);

        if (lines.length === 1) {
            break;
        }
    }

    return parseInt(lines[0], 2);
}

const util = require("./util");

let lines = util.toLines("2021/inputs/input3_1.txt");

let p1Res = part1(lines);
console.log(`Part1: gamma ${p1Res.gamma} * epsilon ${p1Res.epsilon} = ${p1Res.epsilon * p1Res.gamma}`);

let oxygenRating = part2Helper(lines, (bitCounter) => bitCounter[0] > bitCounter[1] ? "0" : "1");
let co2ScrubberRating = part2Helper(lines, (bitCounter) => bitCounter[0] > bitCounter[1] ? "1" : "0");
console.log(`Part2: ${oxygenRating * co2ScrubberRating}`);