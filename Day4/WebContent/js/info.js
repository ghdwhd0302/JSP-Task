function sendit() {
    
    const userpw = document.getElementById('userpw');
    const userpw_re = document.getElementById('userpw_re');
    const name = document.getElementById('name');
    const hp = document.getElementById('hp');
    const email = document.getElementById('email');
    const hobby = document.getElementsByName('hobby');

    
    if (userpw.value == ''){
        alert('비밀번호를 입력하세요!');
        userpw.focus();
        return false;
    }

    if (!expPwText.test(userpw.value)) {
        alert('비밀번호 형식을 확인하세요\n소문자, 대문자, 숫자, 특수문자 1개씩 꼭 입력해야합니다');
        userpw.focus();
        return false;
    }

    if (userpw.value != userpw_re.value) {
        alert('비밀번호가 다릅니다');
        userpw_re.focus;
        return false;
    }

    if (!expNameText.test(name.value)) {
        alert('이름은 한글로 입력하세요')
        name.focus();
        return false;
    }

    if(!expHpText.test(hp.value)){
        alert('휴대폰번호 형식을 확인하세요\n하이픈(-)을 포함한 숫자를 입력해주세요');
        hp.focus();
        return false;
    }

    if (!expEmailText.test(email.value)) {  
        alert('이메일 형식에 맞게 입력해주세요\n예) apple@apple.com')
        email.focus();
        return false;
    }

    let count = 0;

    for (let i in hobby) {
        if (hobby[i].checked) {
            count++;
        }
    }

    if (count == 0) {
        alert('취미는 적어도 한개이상 선택하세요');
        return false;
    }

    return true;
}









