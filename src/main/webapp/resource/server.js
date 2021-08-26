const fs = require('fs');

const express = require('express')
    , http = require('http')
    , app = express()
    , server = http.createServer(app);
const path = require("path");

app.use(express.static(path.join(__dirname, './public')));

app.get('/', function (req, res) {
    res.writeHead(200, {"Content-Type": "text/html"});
    fs.createReadStream("./index.html").pipe(res);
});

app.get('/member/login', function (req, res) {
    res.writeHead(200, {"Content-Type": "text/html"});
    fs.createReadStream("./member/login.html").pipe(res);
});

app.get('/member/signup', function (req, res) {
    res.writeHead(200, {"Content-Type": "text/html"});
    fs.createReadStream("./member/signup.html").pipe(res);
});

app.get('/register/registerAgree', function (req, res) {
    res.writeHead(200, {"Content-Type": "text/html"});
    fs.createReadStream("./register/registagree.html").pipe(res);
});

app.get('/register/regist', function (req, res) {
    res.writeHead(200, {"Content-Type": "text/html"});
    fs.createReadStream("./register/regist.html").pipe(res);
});

app.get('/register/regist/basicInfo', function (req, res) {
    res.writeHead(200, {"Content-Type": "text/html"});
    fs.createReadStream("./register/basicInfo.html").pipe(res);
});

app.get('/register/regist/creatorInfo', function (req, res) {
    res.writeHead(200, {"Content-Type": "text/html"});
    fs.createReadStream("./register/creatorInfo.html").pipe(res);
});

app.get('/register/regist/fundingInfo', function (req, res) {
    res.writeHead(200, {"Content-Type": "text/html"});
    fs.createReadStream("./register/fundingInfo.html").pipe(res);
});

app.get('/project/list', function (req, res) {
    res.writeHead(200, {"Content-Type": "text/html"});
    fs.createReadStream("./project/list.html").pipe(res);
});

app.get('/project/info', function (req, res) {
    res.writeHead(200, {"Content-Type": "text/html"});
    fs.createReadStream("./project/info.html").pipe(res);
});

app.get('/project/apply', function (req, res) {
    res.writeHead(200, {"Content-Type": "text/html"});
    fs.createReadStream("./project/apply.html").pipe(res);
});

app.get('/project2/joinlist', function (req, res) {
    res.writeHead(200, {"Content-Type": "text/html"});
    fs.createReadStream("./project2/joinlist.html").pipe(res);
});

app.get('/creator/cmypage', function (req, res) {
    res.writeHead(200, {"Content-Type": "text/html"});
    fs.createReadStream("./creator/cmypage.html").pipe(res);
});

app.get('/creator/mylist', function (req, res) {
    res.writeHead(200, {"Content-Type": "text/html"});
    fs.createReadStream("./creator/mylist.html").pipe(res);
});

server.listen(80, function () {
    console.log('Express server listening on port ' + server.address().port);
});
