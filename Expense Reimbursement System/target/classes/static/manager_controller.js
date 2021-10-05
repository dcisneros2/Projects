
const submit_form = document.querySelector("[type=submit]");
const logout_button = document.getElementById("logout_button");


window.onload = function () {
    this.getAllEmployees();
}

submit_form.addEventListener('click', (event) => {
    if(event.cancelable){
        event.preventDefault();
    }
    document.getElementById('reinbursement_form').submit();
    getAllEmployees();
});

logout_button.addEventListener('click', (event) =>{
    let url = 'http://localhost:7000/logout';
    let xhr = new XMLHttpRequest(); //readyState is 0

    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4){
            alert(xhr.response);
        }

    }

    xhr.open('GET', url); //readyState is 1
        
    // Send HTTP request.
    xhr.send(); //readyState is 2
});



function getAllEmployees(){
    let url = 'http://localhost:7000/manager/all';
    var employees;
    // Using the ready states of an XMLHttpRequest can guide your AJAX workflow.
    let xhr = new XMLHttpRequest(); //readyState is 0

    xhr.onreadystatechange = function(){
        function generateTableHead(table, data) {
            let thead = table.createTHead();
            let row = thead.insertRow();
            for (let key of data) {
                

                if(!((key == "username") || (key == "password"))){
                    let th = document.createElement("th");
                    let text = document.createTextNode(key);
                    th.appendChild(text);
                    row.appendChild(th);
                }
                
            }
          }
          
          function generateTable(table, data) {


            for (let element of data) {
              let row = table.insertRow();
              for (key in element) {
                
                if(!((key == "username") || (key == "password"))){
                    if(!(key == "reinbursements")){
                        let cell = row.insertCell();
                        let text = document.createTextNode(element[key]);
                        cell.appendChild(text);
                    }
                    else if(key == "reinbursements") {
                        //console.log(element[key]);
                        generateTable(table, element[key]);

                        //console.log(data[0]["reinbursements"][0]);
                        //generateTableHead(table, Object.keys(data[0]["reinbursements"][0]));
                    }
                }
              }
            }
          }
         

        if(xhr.readyState === 4 && xhr.status === 200){
            employees = JSON.parse(xhr.response);
            console.log(employees);
            
            let table = document.querySelector("table");        
            generateTable(table, employees);
            generateTableHead(table, Object.keys(employees[0]));     
            generateTableHead(table, Object.keys(employees[0]["reinbursements"][0]))
        }
    }
        xhr.open('GET', url); //readyState is 1
        
        // Send HTTP request.
        xhr.send(); //readyState is 2
        
        return employees;
}