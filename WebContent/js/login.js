$(function () {
    // 登录
    $('button').click(function () {
        let username = $('input[name=username]').val();
        let password = $('input[name=password]').val();

        if (username == "" || username == null) {
            $('#tip-span').css('color', 'red');
            $('#tip-span').text('请填写用户名');
            return false;
        }
        if (password == "" || password == null) {
            $('#tip-span').css('color', 'red');
            $('#tip-span').text('请填写密码');
            return false;
        }
        if (password.length < 6) {
            $('#tip-span').css('color', 'red');
            $('#tip-span').text('密码不能小于6个字符');
            return false;
        }

        $.ajax({
            type: "post",
            data: {
                'username': username,
                'password': password,
                'autoLogin': $('input[name=autoLogin]').is(':checked')
            },
            dataType: 'json',
            url: "./login.do",
            success: function (data) {
                if (data.status == -1) {
                    $('#tip-span').css('color', 'red');
                    $('#tip-span').text('密码错误，请仔细检查密码');
                } else if (data.status == 0) {
                    $('#tip-span').css('color', 'red');
                    $('#tip-span').text('请检查用户名，或者前往注册');
                } else if (data.status == 1) {
                    location.href = './index';
                }
            }
        });
        return false;
    });

})