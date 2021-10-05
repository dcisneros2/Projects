function getAllEmployees(){
    let url = 'http://localhost:8000/recipe/all';

    // Using the ready states of an XMLHttpRequest can guide your AJAX workflow.
    let xhr = new XMLHttpRequest(); //readyState is 0
    console.log(xhr.status);

    //While the readyState is 3 or 4, we're just waiting around for the response. As such, we need to determine what will happen when we do receive a response.

    /*
        Each time the readyState changes, this callback we define will be invoked.
    */
    xhr.onreadystatechange = function(){
        // If the readyState is 4 and the HTTP status code is 200, I have access to the data I requested when I sent the HTTP request.
        if(xhr.readyState === 4 && xhr.status === 200){
            // My first order of business is to access the data itself. Note that the data comes to us as JSON but that we want to be able to use the data as a JavaScript object.
            let recipes = JSON.parse(xhr.response);

        }
    }
        // Now let's open our HTTP request. We need to specify an HTTP verb and the URL.
        xhr.open('GET', url); //readyState is 1

        // Now let's send our HTTP request.
        xhr.send(); //readyState is 2
}

window.onload = async function () {

    this.getAllEmployees();

    

    
}

function createEmployeeTable(){

}