const input = `$ cd /
$ ls
dir a
14848514 b.txt
8504156 c.dat
dir d
$ cd a
$ ls
dir e
29116 f
2557 g
62596 h.lst
$ cd e
$ ls
584 i
$ cd ..
$ cd ..
$ cd d
$ ls
4060174 j
8033020 d.log
5626152 d.ext
7214296 k`;

let lines = input.split("\n").filter(l => l !== "");

let folderMap = new Map();

let currentDirPath = [];

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
        while (!lines[i++].startsWith("$ ")) {
            if (lines[i].startsWith("dir")) {
                let dirName = lines[i].replace("dir ", "");
                dirs.push({
                    name: dirName
                });
            } else {
                const {size, fileName} = lines[i].split(" ");
                files.push({name: fileName, size: size});
            }
        }

        folderMap.set(currentDirPath.join("/"), {
            files: files,
            dirs: dirs
        });
    }
}
