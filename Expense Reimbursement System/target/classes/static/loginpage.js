const submit_form = document.querySelector("[type=submit]");


submit_form.addEventListener('click', (event) => {
    if(event.cancelable){
        event.preventDefault();
    }
    document.getElementById('login_form').submit();
});