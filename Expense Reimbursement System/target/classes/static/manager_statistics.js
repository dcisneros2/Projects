 window.onload = function () {
    this.maxSpend();
}


function maxSpend(){
    let url = 'http://localhost:7000/manager/all';
    var data;
    // Using the ready states of an XMLHttpRequest can guide your AJAX workflow.
    let xhr = new XMLHttpRequest(); //readyState is 0

    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4 && xhr.status === 200){
            data = JSON.parse(xhr.response);
            console.log(data);

            //console.log(data[0]);
            //console.log(data[0]["reinbursements"]);

            let current_max_user=0;
            let total_employees=0;
            let number_reimbursements=0;
            let max_expense=0;
            let min_expense=0; 


            for (let element of data) {
                console.log(element);
                total_employees++;
                for (key in element) {
                    
                    number_reimbursements++;
                    
                  if(!((key == "username") || (key == "password"))){
                        if(!(key == "reinbursements")){
                    
                        }
                        else if(key == "reinbursements") {
                          //console.log(element[key]);
  
                          //console.log(data[0]["reinbursements"][0]);
                          //generateTableHead(table, Object.keys(data[0]["reinbursements"][0]));
                        }
                  }
                }
            }
            

            var num_employees = document.getElementById('total_employees');
            var text = document.createTextNode(total_employees);
            num_employees.appendChild(text);
            console.log(number_reimbursements);
            console.log(total_employees);
        }



    }

    xhr.open('GET', url); //readyState is 1
        
    // Send HTTP request.
    xhr.send(); //readyState is 2

}
