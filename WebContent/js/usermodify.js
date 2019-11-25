$(function () {
    let cookies = document.cookie.split(';');
    console.log(cookies);
    let username = null;
    let password = null;
    cookies.forEach(cookie => {
        if (cookie.split('=')[0].trim() == 'uname') {
            username = cookie.split('=')[1].trim();
            username = decodeURIComponent(username);
        } else if (cookie.split('=')[0].trim() == 'pwd') {
            password = cookie.split('=')[1].trim();
            password = decodeURIComponent(password);
        }
    });

    $('a[data-toggle=dropdown]').html(username + '<span class="caret"></span>');

    // 用户名修改提交用户名
    let canRegister = false;
    $('.button-username').click(function () {
        let newUsername = $('input[name = username]').val();
        if (newUsername == "" || newUsername == null) {
            $('.span-tip1').css('color', 'red');
            $('.span-tip1').text('请填写用户名');
            return false;
        }
        if (!canRegister) {
            return false;
        }
        console.log(username + '修改为：' + newUsername);
        // 提交用户名
        $.ajax({
            type: "post",
            url: "./updateUserInfo",
            data: {
                "sign": "uname",
                "oldUsername": username,
                "newUsername": newUsername
            },
            dataType: 'json',
            success: function (data) {
                if (data.status == -1) {
                    alert('修改失败');
                }
                if (data.status == 1) {
                    alert('修改成功，请重新登录');
                    location.href = './login.html';
                }
            },
            error: function () {
                alert('修改失败');
            }
        });
        return false;
    });

    // 用户名是否被注册
    $('input[name=username]').on('input propertychange', function () {
        let uname = $(this).val();
        $.ajax({
            type: "post",
            url: "./checkUser",
            data: {
                'username': uname
            },
            dataType: 'json',
            success: function (data) {
                if (data == '-1') {
                    canRegister = false;
                    $('.span-tip1').css('color', 'red');
                    $('.span-tip1').text('该用户名已被注册');
                }
                if (data == '1') {
                    canRegister = true;
                    $('.span-tip1').css('color', 'green');
                    $('.span-tip1').text('该用户名可以使用');
                }
            }
        });
    });

    // 修改密码
    let flag = false;
    $('.button-password').click(function () {
        let password = $('input[name = password]').val();
        if (password == "" || password == null) {
            $('.span-tip2 ').css('color', 'red');
            $('.span-tip2').text('请填写密码');
            return false;
        }
        if (password.length < 6) {
            $('.span-tip2').css('color', 'red');
            $('.span-tip2').text('密码不能小于6个字符');
            return false;
        }
        if (!flag) {
            $('.span-tip3').css('color', 'red');
            $('.span-tip3').text('请先确认密码');
            return false;
        }
        $.ajax({
            type: "post",
            url: "./updateUserInfo",
            data: {
                "sign": "pwd",
                "oldUsername": username,
                "newPassword": password
            },
            dataType: 'json',
            success: function (data) {
                if (data.status == -1) {
                    alert('修改失败');
                }
                if (data.status == 1) {
                    alert('修改成功，请重新登录');
                    location.href = './login.html';
                }
            },
            error: function () {
                alert('修改失败');
            }
        });
        return false;
    });

    // 确认密码
    $('input[name=password1]').on('input propertychange', function () {
        var password1 = $(this).val();
        if (password1 != $('input[name=password]').val()) {
            flag = false;
            $('.span-tip3').css('color', 'red');
            $('.span-tip3').text('确认密码与密码不符');
        }
        if (password1 == $('input[name=password]').val()) {
            flag = true;
            $('.span-tip3').text('');
        }
    });
})