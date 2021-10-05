async function getAllEmployees(){
    let url = 'http://localhost:7000/manager/all';
    let response_body = await fetch(url);
    let employees = await response_body.json();

    return employees;
    console.log(employees);
}

window.onload = async function () {
    let employees = JSON.parse(this.getAllEmployees());
    this.getAllEmployees();

    

    
}

function createEmployeeTable(){

}