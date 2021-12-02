function splitCommand(commandString) {
    let [command, value] = commandString.split(" ");
    return {
        command: command,
        value: parseInt(value)
    };
}

function part1(lines) {
    let position = {
        forward: 0,
        depth: 0
    };

    let commands = lines.map(l => splitCommand(l));

    for (const command of commands) {
        switch (command.command) {
            case "forward":
                position.forward += command.value;
                break;
            case "down":
                position.depth += command.value;
                break;
            case "up":
                position.depth -= command.value;
                break;
        }
    }

    return position;
}

function part2(lines) {
    let position = {
        forward: 0,
        depth: 0,
        aim: 0
    };

    let commands = lines.map(l => splitCommand(l));

    for (const command of commands) {
        switch (command.command) {
            case "forward":
                position.forward += command.value;
                position.depth += position.aim * command.value;
                break;
            case "down":
                position.aim += command.value;
                break;
            case "up":
                position.aim -= command.value;
                break;
        }
    }

    return position;
}

const util = require("./util");

let lines = util.toLines("2021/input2_1.txt");

let positionP1 = part1(lines);
console.log(`Part1: forward ${positionP1.forward} * depth ${positionP1.depth} = ${positionP1.forward * positionP1.depth}`);

let positionP2 = part2(lines);
console.log(`Part1: forward ${positionP2.forward} * depth ${positionP2.depth} = ${positionP2.forward * positionP2.depth}`);