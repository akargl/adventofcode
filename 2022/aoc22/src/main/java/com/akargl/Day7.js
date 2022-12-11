const fs = require("fs");

const input = fs.readFileSync("../../../../../inputs/d7_sample.txt").toString();

let lines = input
    .replaceAll("\r", "")
    .split("\n")
    .filter(l => l.trim() !== "");

let filesystemStructure = getFilesystemStructure(lines);

filesystemStructure.forEach((v, k) => {
    let folderSize = getFolderSize(k, filesystemStructure);
    console.log(`${k} ${folderSize}`);
});

let foldersUnderThresholdSize = Array.from(filesystemStructure.values())
    .filter(x => x.size <= 100000).map(x => parseInt(x.size))
    .reduce((p, c) => p+c, 0);
console.log("Part 1: " + foldersUnderThresholdSize);

let outermostSize = filesystemStructure.get("/").size;
let freeSpace = 70000000 - outermostSize;
let sizeToFreeUp = 30000000 - freeSpace;

let sortedDirs = Array.from(filesystemStructure.entries())
    .filter(x => x[1].size >= sizeToFreeUp)
    .sort((a, b) => a[1].size - b[1].size);

console.log(sortedDirs[0][1].size);

////

function getFolderSize(dirName, fileMap) {
    let f = fileMap.get(dirName);
    if (f.size == null) {
        let filesSize = f.files.map(x => parseInt(x.size)).reduce((p, c) => p+c, 0);
        let dirsSize = f.dirs.map(d => getFolderSize(`${dirName}/${d.name}`, fileMap)).reduce((p, c) => p+c, 0);

        f.size = filesSize + dirsSize;
    }

    return f.size;
}

function getFilesystemStructure(lines) {
    let currentDirPath = [];
    let folderMap = new Map();

    for (let i = 0; i < lines.length; i++) {
        const line = lines[i];
        if (line.startsWith("$ cd")) {
            let folder = line.replace("$ cd ", "");
            if (folder === "..") {
                currentDirPath.pop();
            } else {
                currentDirPath.push(folder);
            }
        } else if (line.startsWith("$ ls")) {
            let files = [];
            let dirs = [];
            while (i < lines.length - 1 && !lines[++i].startsWith("$ ")) {
                if (lines[i].startsWith("dir")) {
                    let dirName = lines[i].replace("dir ", "");
                    dirs.push({
                        name: dirName
                    });
                } else {
                    const [size, fileName] = lines[i].split(" ");
                    files.push({name: fileName, size: size});
                }
            }
            i--;

            folderMap.set(currentDirPath.join("/"), {
                files: files,
                dirs: dirs,
                size: null
            });
        }
    }

    return folderMap;
}
