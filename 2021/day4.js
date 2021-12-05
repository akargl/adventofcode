
function isEmptyString(s) { 
    return s.trim() === "";
}

function parseNumbersTobeDrawn(line) {
    return line.split(",")
        .map(x => parseInt(x));
}

function parseBoards(lines) {
    let boards = [];

    for (let i = 1; i < lines.length;) {
        while(i < lines.length && isEmptyString(lines[i])) { i++; }
        let board = [];
        while(i < lines.length && !isEmptyString(lines[i])) {
            let row = lines[i]
                .trim()
                .replaceAll(/\s+/g, " ")
                .split(" ")
                .map(x => parseInt(x));
            board.push(row);
            i++;
        }
        if (board.length) {
            boards.push(board);
        }
    }

    return boards;
}

function checkIfBoardHasWon(board) {
    for (let i = 0; i < 2; i++) {
        for (const row of board) {
            if (row.every(x => x === "")) {
                return true;
            }
        }
    
        //transpose board
        board = board[0].map((_, col) => board.map((row) => row[col]));
    }

    return false;
}

function calculateScore(board, winningNumber) {
    let sumOfBoard = board.flat().filter(x => x !== "").reduce((previous, current) => previous + current, 0);
    return sumOfBoard * winningNumber;
}

function play(lines, playToFirstWinner) {
    let numbersToBeDrawn = parseNumbersTobeDrawn(lines[0]);

    let boards = parseBoards(lines);

    for (const number of numbersToBeDrawn) {
        for (let b = 0; b < boards.length; b++) {
            for (let row = 0; row < boards[b].length; row++) {
                for (let i = 0; i < boards[b][row].length; i++) {
                    if (boards[b][row][i] === number) {
                        boards[b][row][i] = "";
                    }
                }
            }

            if (checkIfBoardHasWon(boards[b])) {
                if (playToFirstWinner) {
                    return calculateScore(boards[b], number);
                } else {
                    if (boards.length === 1) {
                        return calculateScore(boards[b], number);
                    }
                    boards.splice(b, 1);
                    b--;
                }
            }
        }
    }
}

const util = require("./util");

let lines = util.toLines("2021/inputs/input4_1.txt", true);

let p1Score = play(lines, true);
console.log(`p1: ${p1Score}`);

let p2Score = play(lines, false);
console.log(`p2: ${p2Score}`);
