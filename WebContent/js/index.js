$(function() {
	let cookies = document.cookie.split(';');
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

	$('#loading').css('display', 'inline-block');

	// 获取所有博文
	$.ajax({
		type: "get",
		url: "./getAllPost.do?target=all",
		success: function(data) {
			displayPost(data);
			getAllComments();
		},
		error: function() {
			$('.row').html(
				'<p style="text-align: center;font-size: 50px;"><i class="glyphicon glyphicon-exclamation-sign"></i>网络暂时出了点状况...</p>'
			);
		}
	});

	//获取所有评论
	function getAllComments() {
		$.ajax({
			type: "get",
			url: "./getAllComment.do",
			success: function(data) {
				$('#loading').css('display', 'none');
				displayComment(data);
			}
		});
	}

	// 发表动态
	$('button.createPost').click(function() {
		let postContent = $('#index-textarea').val();
		if (postContent.length < 5) {
			$(this).text('再多写点试试吧');
			window.setTimeout(function() {
				$('.createPost').text('发表');
			}, 5000);
			window.clearTimeout();
			return;
		}
		$.ajax({
			type: "post",
			data: {
				'text': postContent,
				'username': username
			},
			dataType: 'json',
			url: "./createPost.do",
			success: function(data) {
				if (data == '1') {
					location.reload();
				}
			}
		});
	});

	function displayPost(posts) {
		let col = 0;
		for (let j = 0; j < posts.length; j++) {
			col = col > 2 ? 0 : col;
			let items = "<div class='items'><p class='post-title'>" +
				posts[j].username +
				"<span class='post-time'>" +
				new Date(posts[j].createdTime).toLocaleString() +
				"</span></p><p class='post-content'>" +
				posts[j].text +
				"</p><hr /><div id='comment-div-" +
				posts[j].postId +
				"'></div><input type='text' class='form-control comment-input' placeholder='说点什么吧...'><p><a class='btn btn-info margin-top-6 createComment' role='button'>发表</a> <a class='btn btn-info margin-top-6 tiggleCommentInput' role='button'>评论</a></p></div>";
			$('#col-' + col).append($(items));
			col++;
		}

		// 展开和收起评论input
		$('a.tiggleCommentInput').on('click', function() {
			if ($(this).text() == '评论') {
				$(this).text('收起');
				$(this).parent().prev().css('display', 'block');
				$(this).prev().css('display', 'inline-block');
			} else {
				$(this).text('评论');
				$(this).parent().prev().css('display', 'none');
				$(this).prev().css('display', 'none');
			}
		});
	}

	function displayComment(comments) {
		for (var i = 0; i < comments.length; i++) {
			$('#comment-div-' + comments[i].postId).append('<p>' + comments[i].username + '：' + comments[i].text + (comments[i]
					.username === username ? ' <a href="./delete.do?target=comment&commentId=' + comments[i].commentId + '"> 删除</a>' : '') +
				'</p>');
		}

		// 发表评论
		$('.createComment').click(function() {
			let commentContent = $(this).parent().prev().val();
			let postId = $(this).parent().prev().prev().prop('id').split('-')[2];
			$('#comment-div-' + postId).append('<p>' + username + '：' + commentContent + '</p>');
			$(this).parent().prev().val('');
			// 提交评论
			$.ajax({
				type: "post",
				data: {
					'text': commentContent,
					'username': username,
					'postId': postId
				},
				dataType: 'json',
				url: "./createComment.do",
				success: function(data) {
					if (data.status == 1) {
						$('#comment-div-' + comments[i].postId).append('<p>' + username + '：' + comments[i].text + '</p>');
					} else {
						alert('评论失败，请稍后重试！');
					}
				},
				error: function(data) {
					alert('评论失败，请稍后重试！');
				}
			});
		});
	}

	$('footer>p').html('&copy; CopyRight 向南飞 ' + new Date().getFullYear());
})
