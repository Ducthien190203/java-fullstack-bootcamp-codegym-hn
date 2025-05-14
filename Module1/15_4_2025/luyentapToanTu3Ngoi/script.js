let message = prompt("Nhập message");
let result = message === 'Employee' ? 'Hello' :
    message === 'Director' ? 'Greeting' :
        message === '' ? 'No login' : '';
alert(result);