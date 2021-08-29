const fs = require('fs');

const express = require('express')
    , http = require('http')
    , app = express()
    , session = require('express-session')
    , MemoryStore = require("memorystore")(session)
    , server = http.createServer(app);
const path = require("path");

app.use(express.static(path.join(__dirname, './public')));
app.use(session({
    secret: 'secret key', // 암호화하는데 쓰일 키
    // httpOnly: true, // 자바스크립트를 통해 세션 쿠키를 사용할 수 없도록 한다.
    // secure: true, // https 환경에서만 session 정보를 주고받도록 처리한다.
    resave: false, // 세션을 언제나 저장할지 설정함
    saveUninitialized: true, // 세션이 저장되기 전 uninitialized 상태로 미리 만들어 저장
/*    store: new MemoryStore({
        checkPeriod: 86400000, // 60 * 30 * 1000ms => 30분
    }),*/
    cookie: {
        domain: true,
        path: '/',
        maxAge: 86400000,
        sameSite: 'None',
        httpOnly: true,
        // Secure: true
    }
}));

app.get('/', function (req, res) {
/*    console.log(req.session);*/
/*    if (req.session.num === undefined) {
        req.session.num = 1;
    } else {
        req.session.num += 1;
    }
    res.send(`View : ${req.session.num}`);*/

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
