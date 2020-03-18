const userName = document.getElementById('userName');
const userSurname = document.getElementById('userSurname');
const editForm = document.getElementById('edit-form');
const errorElement = document.getElementById('error');
const pattern = new RegExp('[A-Z][a-z]*');

errorElement.style.display  = 'none';

editForm.addEventListener('submit', (e) => {
    let messages = [];

    if (userName.length < 3 || userName.length > 12) {
        messages.push('Name size must be between 3 and 12')
    }

    if (userSurname.length < 3 || userSurname.length > 12) {
        messages.push('Surname size must be between 3 and 12')
    }

    if (pattern.test(userName.value)) {
        messages.push('Invalid name')
    }

    if (pattern.test(userSurname.value)) {
        messages.push('Invalid surname')
    }


    if (messages.length > 0) {
        e.preventDefault();
        errorElement.innerText = messages.join(', ');
        errorElement.style.display = 'flex'
    }
});