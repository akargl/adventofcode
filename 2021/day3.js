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

const util = require("./util");

let lines = util.toLines("2021/input3_1.txt");

let p1Res = part1(lines);
console.log(`Part1: gamma ${p1Res.gamma} * epsilon ${p1Res.epsilon} = ${p1Res.epsilon * p1Res.gamma}`);