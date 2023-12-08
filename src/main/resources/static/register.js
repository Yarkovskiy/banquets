function validateForm() {
    // Проверка имени и фамилии на буквы русского или латинского алфавита
    var namePattern = /[A-Za-zА-Яа-яЁё]{1,}/;
    var nameField = document.getElementById('name');
    if (!namePattern.test(nameField.value)) {
        alert('Пожалуйста, введите корректное имя.');
        return false;
    }

    // Проверка фамилии
    var surnamePattern = /[A-Za-zА-Яа-яЁё]{1,}/;
    var surnameField = document.getElementById('surname');
    if (surnameField.value && !surnamePattern.test(surnameField.value)) {
        alert('Пожалуйста, введите корректную фамилию.');
        return false;
    }

    // Проверка совпадения пароля и подтверждения пароля
    var passwordField = document.getElementById('password');
    var confirmPasswordField = document.getElementById('confirmPassword');
    if (passwordField.value !== confirmPasswordField.value) {
        alert('Пароли не совпадают');
        return false;
    }

    return true;
}



