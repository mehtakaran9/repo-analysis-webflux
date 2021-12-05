var counter = 0;
db.packages.find({},{ name:1,versions: 1 } ).forEach(function(npm){
    var jsonRes={};
    jsonRes["_id"]=npm.name;
    jsonRes["name"]=npm.name;
    var ver = npm.versions[Object.keys(npm.versions)[0]];
    var project={};
    print(Object.keys(npm.versions).length)
    jsonRes["versions"]=npm.versions;
        var ver = npm.versions[Object.keys(npm.versions)[Object.keys(npm.versions).length - 1]];
        if(ver["deprecated"]) {
            counter++;
        }
});
print(counter);