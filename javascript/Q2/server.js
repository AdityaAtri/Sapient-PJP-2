const mysql = require('mysql');
const express  = require('express');
const bodyParser = require('body-parser');
var app = express();
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true })); 

var mysqlConnection = mysql.createConnection({
    host: "localhost",
    user:"root",
    password:"password",
    database:"EmployeeDB",
    multipleStatements:true
});

app.post('/example', (req, res) => {
    name = req.body.name;
    var query = "SELECT * FROM EmployeeDetails where Ename='"+name+"'";  
    mysqlConnection.query(query, function(err, rows, fields)   
    {  
      mysqlConnection.end();
      if (err) throw err;  
      res.json(rows); 
    });
});

mysqlConnection.connect((err)=>{
    if(!err){
        console.log("Connection Success");
    }
    else{
        console.log("Connection Failed");
    }
});

app.listen(3000, function () {
    console.log('Connected to port 3000');
});