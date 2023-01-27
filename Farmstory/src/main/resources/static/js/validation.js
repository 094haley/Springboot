
 // 데이터 검증에 사용하는 정규표현식
let regUid = /^[a-z]+[a-z0-9]{5,19}$/g;
let regPass = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/;
let regName = /^[ㄱ-힣]+$/;
let regNick = /^[a-zA-Zㄱ-힣0-9][a-zA-Zㄱ-힣0-9]*$/;
let regEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
let regHp = /^01(?:0|1|[6-9])-(?:\d{4})-\d{4}$/;

// 폼 데이터 검증 결과 상태변수
let isUidOk = false;
let isPass1Ok = false;
let isPass2Ok = false;
let isNameOk = false;
let isNickOk = false;
let isEmailOk = false;
let isEmailAuthOk = false;
let isHpOk = false;

// 인증번호 전송 중복클릭 방지
let preventDoubleClick = false;

window.onload = function(){

    // 값이 변경되었을 때 초기화
    document.querySelector('input[name=uid]').addEventListener('keydown', ()=>{
        isUidOk = false;
    });

    document.querySelector('input[name=pass1]').addEventListener('keydown', ()=>{
        isPass1Ok = false;
    });

    document.querySelector('input[name=pass2]').addEventListener('keydown', ()=>{
        isPass2Ok = false;
    });

    document.querySelector('input[name=name]').addEventListener('keydown', ()=>{
        isNameOk = false;
    });

    document.querySelector('input[name=nick]').addEventListener('keydown', ()=>{
        isNickOk = false;
    });

    document.querySelector('input[name=email]').addEventListener('keydown', ()=>{
        // 이메일 인증이 완료되었을 경우 초기화x
        if(isEmailAuthOk){return;}
        isEmailOk = false;
        isEmailAuthOk = false;
        preventDoubleClick = false;
    });

    document.querySelector('input[name=hp]').addEventListener('keydown', ()=>{
        isHpOk = false;
    });


    // 아이디 검사
    const btnCheckUid = document.getElementById('btnCheckUid');

    btnCheckUid.addEventListener('click', ()=>{

        let uid = document.querySelector('input[name=uid]').value;
        const resultUid = document.querySelector('.resultUid');

        if(isUidOk){
            return;
        }

        if(!uid.match(regUid)){
            isPass1Ok = false;
            resultUid.innerText = '유효한 아이디 형식이 아닙니다.';
            resultUid.style.color = 'red';
            return;
        }

        // AJAX 전송
        const xhr = new XMLHttpRequest();
        xhr.open("GET", "/Farmstory/user/checkUid?uid="+uid);
        xhr.responseType = "json";
        xhr.send();

        xhr.onreadystatechange = function(){

            if(xhr.readyState == XMLHttpRequest.DONE){

                if(xhr.status == 200){
                    const data = xhr.response;
                    console.log(data);

                    const resultUid = document.querySelector('.resultUid');

                    if(data.result == 1){
                        isUidOk = false;
                        resultUid.innerText = '이미 사용중인 아이디입니다.';
                        resultUid.style.color = 'red';
                    }else{
                        isUidOk = true;
                        resultUid.innerText = '사용 가능한 아이디입니다.';
                        resultUid.style.color = 'green';
                    }

                }else{
                    alert('Request fail...')
                }
            }
        }
    });

    // 비밀번호 검사
    const inputpass1 = document.querySelector('input[name=pass1]');
    inputpass1.addEventListener('focusout', ()=>{

        let pass1 = inputpass1.value;
        const resultPass1 = document.querySelector('.resultPass1');

        if(pass1 == ''){
            return;
        }

        if(!pass1.match(regPass)){
            isPass1Ok = false;
            resultPass1.innerText = '숫자,영문,특수문자 포함 8자리 이상이어야 합니다.';
            resultPass1.style.color = 'red';
        }else{
            isPass1Ok = true;
            resultPass1.innerText = '사용 가능한 비밀번호입니다.';
            resultPass1.style.color = 'green';
        }

    });

    const inputpass2 = document.querySelector('input[name=pass2]');
    inputpass2.addEventListener('focusout', ()=>{

        let pass1 = document.querySelector('input[name=pass1]').value;
        let pass2 = document.querySelector('input[name=pass2]').value;
        const resultPass2 = document.querySelector('.resultPass2');

        if(pass1 == '' || pass2 == ''){
            return;
        }

        if(pass1 == pass2){
            isPass2Ok = true;
            resultPass2.innerText = '비밀번호가 일치합니다.';
            resultPass2.style.color = 'green';
        }else{
            isPass2Ok = false;
            resultPass2.innerText = '비밀번호가 일치하지 않습니다.';
            resultPass2.style.color = 'red';
        }
    });

    // 이름 검사
    const  inputname = document.querySelector('input[name=name]');
    inputname.addEventListener('focusout', ()=>{

        let name = document.querySelector('input[name=name]').value;
        const resultName = document.querySelector('.resultName');

        if(name == ''){
            return;
        }

        if(!name.match(regName)){
            isNameOk = false;
            resultName.innerText = '유효한 이름이 아닙니다.';
            resultName.style.color = 'red';
        }else{
            isNameOk = true;
            resultName.innerText = '';
        }

    });

    // 닉네임 검사
    const btnCheckNick = document.getElementById('btnCheckNick');

    btnCheckNick.addEventListener('click', ()=>{

        let nick = document.querySelector('input[name=nick]').value;
        const resultNick = document.querySelector('.resultNick');

        if(isNickOk){
            return;
        }

        if(!nick.match(regNick)){
            isPass1Ok = false;
            resultNick.innerText = '유효한 닉네임 형식이 아닙니다.';
            resultNick.style.color = 'red';
            return;
        }

        // AJAX 전송
        const xhr = new XMLHttpRequest();
        xhr.open("GET", "/Farmstory/user/checkNick?nick="+nick);
        xhr.responseType = "json";
        xhr.send();

        xhr.onreadystatechange = function(){

            if(xhr.readyState == XMLHttpRequest.DONE){

                if(xhr.status == 200){
                    const data = xhr.response;
                    console.log(data);

                    const resultNick = document.querySelector('.resultNick');

                    if(data.result == 1){
                        isNickOk = false;
                        resultNick.innerText = '이미 사용중인 별명입니다.';
                        resultNick.style.color = 'red';
                    }else{
                        isNickOk = true;
                        resultNick.innerText = '사용 가능한 별명입니다.';
                        resultNick.style.color = 'green';
                    }

                }else{
                    alert('Request fail...')
                }
            }
        }
    });

    // 이메일 검사
    let emailCode = 0;
    document.querySelector('.auth').style.display="none";

    const inputemail = document.querySelector('input[name=email]');
    inputemail.addEventListener('focusout', ()=>{

        let email = inputemail.value;
        const resultEmail = document.querySelector('.resultEmail');

        if(email == ''){
            return;
        }

        if(!email.match(regEmail)){
            isEmailOk = false;
            resultEmail.innerText = '유효한 이메일 형식이 아닙니다.';
            resultEmail.style.color = 'red';
        }else{
            isEmailOk = true;
            resultEmail.innerText = '';
        }
    });


    const btnEmailAuth = document.getElementById('btnEmailAuth');
            btnEmailAuth.addEventListener('click', ()=>{

                let email = document.querySelector('input[name=email]').value;

                // AJAX 전송
                const xhr = new XMLHttpRequest();
                xhr.open("Get", "/Farmstory/user/emailAuth?email="+email);
                xhr.responseType = "json";
                xhr.send();

                xhr.onreadystatechange = function(){

                    if(xhr.readyState == XMLHttpRequest.DONE){

                        if(xhr.status == 200){

                            const data = xhr.response;
                            console.log(data);

                            if(data.status == 1){
                                // 메일 발송 성공
                                emailCode = data.code;
                            }else{
                                // 메일 발송 실패
                                alert('실패!');
                            }
                        }
                    }
                }
            });

    // 휴대폰 검사
    const inputhp = document.querySelector('input[name=hp]');
    inputhp.addEventListener('focusout', ()=>{

        let hp = inputhp.value;
        const resultHp = document.querySelector('.resultHp');

        if(hp == ''){
            return;
        }

        if(!hp.match(regHp)){
            isHpOk = false;
            resultHp.innerText = '유효한 휴대폰번호 형식이 아닙니다.';
            resultHp.style.color = 'red';
        }else{
            isHpOk = true;
            resultHp.innerText = '';
        }
    });

    // 최종 폼 전송
    $('.register > form').submit(function() {

        // 아이디 검증
        if (!isUidOk) {
            alert('아이디를 확인하십시오.');
            return false;
        }
        // 비밀번호 검증
        if (!isPass1Ok) {
            alert('비밀번호가 유효하지 않습니다.');
            return false;
        }

        if (!isPass2Ok) {
            alert('비밀번호가 유효하지 않습니다.');
            return false;
        }

        // 이름 검증
        if (!isNameOk) {
            alert('이름이 유효하지 않습니다.');
            return false;
        }

        // 닉네임 검증
        if (!isNickOk) {
            alert('별명이 유효하지 않습니다.');
            return false;
        }

        // 이메일 검증
        if (!isEmailOk) {
            alert('이메일이 유효하지 않습니다.');
            return false;
        }

        // 휴대폰 검증
        if (!isHpOk) {
            alert('휴대폰이 유효하지 않습니다.');
            return false;
        }

        return true;
    });
}