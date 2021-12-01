const fs = require("fs");

function getInput(fName = process.stdin.fd) {
    return fs.readFileSync(fName).toString();
}

/**
 * 
 * @param {string} fName 
 * @returns {number[]}
 */
module.exports.inputToNumbers = (fName) => {
    let input = getInput(fName);
    let numbers = input.split("\n")
        .map((x) => parseInt(x))
        .filter((x) => !isNaN(x));
    return numbers;
}
