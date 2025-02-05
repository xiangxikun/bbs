$(function () {
    let flag = false;
    // 注册
    $('button').click(function () {
        var nickname = $('input[name = username]').val();
        var password = $('input[name = password]').val();
        if (nickname == "" || nickname == null) {
            $('.span-tips').css('color', 'red');
            $('.span-tips').text('请填写用户名');
            return false;
        }
        if (password == "" || password == null) {
            $('.span-tips').css('color', 'red');
            $('.span-tips').text('请填写密码');
            return false;
        }
        if (password.length < 6) {
            $('.span-tips').css('color', 'red');
            $('.span-tips').text('密码不能小于6个字符');
            return false;
        }
        if (!flag) {
            return false;
        }
        form.submit();
        return false;
    });

    // 用户名是否被注册
    $('input[name=username]').on('input propertychange', function () {
        var nickname = $(this).val();
        $.ajax({
            type: "post",
            url: "./checkUser",
            data: {
                'username': nickname
            },
            dataType: 'json',
            success: function (data) {
                if (data == '-1') {
                    flag = false;
                    $('.span-tips').css('color', 'red');
                    $('.span-tips').text('该用户名已被注册');
                }
                if (data == '1') {
                    flag = true;
                    $('.span-tips').css('color', 'green');
                    $('.span-tips').text('该用户名可以使用');
                }
            }
        });
    });

    // 确认密码
    $('input[name=password1]').on('input propertychange', function () {
        var password1 = $(this).val();
        if (password1 != $('input[name=password]').val()) {
            flag = false;
            $('.span-tips').css('color', 'red');
            $('.span-tips').text('确认密码与密码不符');
        }
        if (password1 == $('input[name=password]').val()) {
            flag = true;
            $('.span-tips').text('');
        }
    });
})