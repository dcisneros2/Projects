
const submit_form = document.querySelector("[type=submit]");
const logout_button = document.getElementById("logout_button");


window.onload = function () {
    this.getAllReimbursements();
}

submit_form.addEventListener('click', (event) => {
    if(event.cancelable){
        event.preventDefault();
    }
    document.getElementById('reinbursement_form').submit();
    getAllReimbursements();
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



function getAllReimbursements(){
    let url = 'http://localhost:7000/employee/reimbursements';
    var employee;
    // Using the ready states of an XMLHttpRequest can guide your AJAX workflow.
    let xhr = new XMLHttpRequest(); //readyState is 0

    xhr.onreadystatechange = function(){
        function generateTableHead(table, user) {
            let thead = table.createTHead();
            let row = thead.insertRow();
            for (let key in user) {
                console.log(key);

                if(!((key == "username") || (key == "password"))){
                    let th = document.createElement("th");
                    let text = document.createTextNode(key);
                    th.appendChild(text);
                    row.appendChild(th);
                }
                
            }
          }
          
          function generateTable(table, user) {

            console.log(user.reinbursements[0].travel_r);
           
            
            //for (let element of user) {
              
          
            var row = table.insertRow();
            for (key in user) {
                //console.log(key);
                
                if(!((key == "username") || (key == "password"))){
                    if(!(key == "reinbursements")){
                        
                        let cell = row.insertCell();
                        let text = document.createTextNode(user[key]);
                        cell.appendChild(text);
                    }
                    else if(key == "reinbursements") {
                        //console.log(data[key]);

                        for(let reinbursement of user[key]){
                            console.log(reinbursement);
                            var row = table.insertRow();
                            for(r_key in reinbursement){
                                let cell = row.insertCell();
                                let text = document.createTextNode(reinbursement[r_key]);
                                cell.appendChild(text);
                            }
                            row = table.insertRow();
                        }
 
                        //console.log(data[0]["reinbursements"][0]);
                        //generateTableHead(table, Object.keys(data[0]["reinbursements"][0]));
                    }
                }
              }
            //}
          }
         

        if(xhr.readyState === 4 && xhr.status === 200){
            employee = JSON.parse(xhr.response);
            console.log(employee);

            
            let table = document.querySelector("table");        
            generateTable(table, employee);
            generateTableHead(table, employee);     
            generateTableHead(table, employee.reinbursements[0])
        }
    }
        xhr.open('GET', url); //readyState is 1
        
        // Send HTTP request.
        xhr.send(); //readyState is 2
        
        return employee;
}