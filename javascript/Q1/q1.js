window.onload = function(){
    document.getElementById("operation").onclick=function(){
        var num1 = parseInt(document.getElementById('firstNumber').value);
        var num2 = parseInt(document.getElementById('secondNumber').value);
        var result; 
        if (document.getElementById('add').checked) {
            result = num1+num2; 
          }
        else if (document.getElementById('sub').checked) {
            result = num1-num2; 
          }
        else if (document.getElementById('multiply').checked) {
            result = num1*num2; 
          }
        else if (document.getElementById('divide').checked) {
            result = num1/num2; 
          }
        document.getElementById("result").value = result;
    }
}







