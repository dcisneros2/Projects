const submit_form = document.querySelector("[type=submit]");


submit_form.addEventListener('click', (event) => {
    if(event.cancelable){
        event.preventDefault();
    }
    document.getElementById('login_form').submit();
    getAllEmployees();

});

function createEmployeeTable(){

}

async function getAllEmployees(){
    let employees = await response_body.json();
    console.log(employees);
    return employees;
    
}