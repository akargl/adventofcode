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
    return this.toLines(fName)
        .map((x) => parseInt(x));
}


module.exports.toLines = (fName) => {
    let input = getInput(fName);
    let lines = input.split("\n")
        .filter(x => x.trim() !== "");
    return lines;
}